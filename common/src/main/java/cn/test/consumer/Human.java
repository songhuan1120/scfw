package cn.test.consumer;

public class Human {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello===: My Name is :"+ this.name + "My Age is :"+this.age);
    }

    public void sayYear() {
        System.out.println("Year===: My Name is :"+ this.name + "My Age is :"+this.age);
    }
}
