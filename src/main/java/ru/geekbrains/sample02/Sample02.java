package ru.geekbrains.sample02;

public class Sample02 {

    public static void main(String[] args) {

        DebetAccount<Entity> entityDebetAccount1 =
                new DebetAccount<>(new Entity("Рога и копыта", "112344111"), 42000000);
        CreditAccount<Person> personCreditAccount1 =
                new CreditAccount<>(new Person("Андрей", "Иванов", "44455522"), 20);

        Transaction<Account<?>> transaction1
                = new Transaction<>(entityDebetAccount1, personCreditAccount1, 20000);
        transaction1.execute();

        transaction1
                = new Transaction<>(entityDebetAccount1, personCreditAccount1, 30000);
        transaction1.execute();

    }

}

class Transaction<T extends Account<? extends PersonalData>>{

    private final T from;
    private final T to;
    private final double amount;

    public Transaction(T from, T to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void execute(){
        if (from.getAmount() > amount){
            System.out.printf("Осуществлен перевод средств между счетами на сумму %.2f руб.\n", amount);
            System.out.printf("Счет списания: #%s: %.2f руб.\nСчет зачисления: #%s: %.2f руб.\n",
                    from.getData(), from.getAmount(), to.getData(), to.getAmount());
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);

            System.out.println("Текущее состояние счетов:");
            System.out.println(from);
            System.out.println(to);
        }
        else {
            System.out.println("Операция невозможна, недостаточно средств на счете.");
        }
    }



}

class DebetAccount<T extends PersonalData> extends Account<T>{


    public DebetAccount(T data, double amount) {
        super(data, amount);
    }
}

class CreditAccount<T extends PersonalData> extends Account<T>{


    public CreditAccount(T data, double amount) {
        super(data, amount);
    }
}


abstract class Account<T extends PersonalData>{

    private final T data;
    private double amount;

    public T getData() {
        return data;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account(T data, double amount) {
        this.data = data;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s; %.2f руб.", data, amount);
    }
}

interface PersonalData{

    String getINN();

}

/**
 * Физическое лицо
 */
class Person implements PersonalData{

    private final String firstName;
    private final String lastName;
    private final String inn;

    public Person(String firstName, String lastName, String inn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inn = inn;
    }

    @Override
    public String getINN() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s; %s %s;", inn, lastName, firstName);
    }
}

/**
 * Юридическое лицо
 */
class Entity implements PersonalData{

    private final String name;
    private final String inn;

    public Entity(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    public String getINN() {
        return inn;
    }

    @Override
    public String toString() {
        return String.format("%s; %s;", inn, name);
    }

}