package com.example.untitled6;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println(Application.applicationLogic.strPlusStr("999", "9999"));
        System.out.println(Application.applicationLogic.multiplyStringBy2("9999"));
        System.out.println(Application.applicationLogic.multiplyStringByInt("999"));
        System.out.println(Application.applicationLogic.divideStringBy2("27").getKey());
        System.out.println(Application.applicationLogic.divideStringBy2("27").getValue());
        System.out.println(Application.applicationLogic.divideStringBy17("1717"));
    }
}
