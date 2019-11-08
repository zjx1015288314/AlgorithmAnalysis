package com.zjx.ConcurrentProgram;

public class ObjectCloneTest2 {

    public static void main(String[] args) {
        Animal a1 = new Animal(1, "pig");
        Animal a2 = (Animal) a1.clone();
        System.out.println(a1.getName() == a2.getName() ? "浅复制" : "深复制");

        System.out.println(a1);
        a1.setAge(11);
        a1.setName("big pig");
        System.out.println(a1.age + ":" + a1.name);

        System.out.println(a2);
        System.out.println(a2.age + ":" + a2.name);

    }


    static class Animal implements Cloneable {
        int age;
        String name;

        Animal(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Animal clone() {
            Animal o = null;

            try {
                o = (Animal) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            return o;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
