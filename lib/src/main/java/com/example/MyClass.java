package com.example;

public class MyClass implements MyClassIF{
    /**
     * @return only print "hello!!" message
     */
    public String sayHello()
    {
        return "Hello!!";
    }

    @Override
    public String sayHello(String name) {
        return String.format("Hello %s !!", name);
    }
}
