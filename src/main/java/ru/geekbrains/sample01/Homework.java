package ru.geekbrains.sample01;

import java.util.ArrayList;

public class Homework {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());

        appleBox2.addFruit(new Apple());
        appleBox2.addFruit(new Apple());

        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());

        orangeBox2.addFruit(new Orange());
        orangeBox2.addFruit(new Orange());

        System.out.println("Вес коробок до пересыпания:");
        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeight());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeight());
        System.out.println("Вес коробки с апельсинами №1: " + orangeBox1.getWeight());
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeight());
        System.out.println();

        System.out.println("Сравнение коробок с разными типами фруктов до пересыпания:");
        System.out.println("Вес коробки с яблоками №1 и вес коробки с апельсинами №1: " + appleBox1.compare(orangeBox1));
        System.out.println("Вес коробки с яблоками №2 и вес коробки с апельсинами №2: " + appleBox2.compare(orangeBox2));
        System.out.println();

        appleBox1.pourInto(appleBox2);
        orangeBox1.pourInto(orangeBox2);

        System.out.println("Вес коробок после пересыпания:");
        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeight());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeight());
        System.out.println("Вес коробки с апельсинами №1: " + orangeBox1.getWeight());
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeight());
        System.out.println();

        System.out.println("Сравнение коробок с разными типами фруктов после пересыпания:");
        System.out.println("Вес коробки с яблоками №1 и вес коробки с апельсинами №1: " + appleBox1.compare(orangeBox1));
        System.out.println("Вес коробки с яблоками №2 и вес коробки с апельсинами №2: " + appleBox2.compare(orangeBox2));
        System.out.println();
    }
}

abstract class Fruit {
    private final float weight;

    public float getWeight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0.0f;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    public void pourInto(Box<T> anotherBox) {
        if (this != anotherBox) {
            anotherBox.fruits.addAll(this.fruits);
            this.fruits.clear();
        }
    }
}