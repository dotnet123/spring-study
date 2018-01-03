package com.study.spring.jmx;

public class Hello implements HelloMBean {

    private String name;
    private String age;

    @Override
    public void getTelephone() {
        System.out.println("get Telephone");
    }

    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }

    @Override
    public void helloWorld(String str) {
        System.out.println("helloWorld:" + str);
    }

    @Override
    public String getName() {
        System.out.println("get name:" + name);
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("set name:" + name);
        this.name = name;
    }

    @Override
    public String getAge() {
        System.out.println("get age:" + age);
        return age;
    }

    @Override
    public void setAge(String age) {
        System.out.println("set age:" + age);
        this.age = age;
    }
}
