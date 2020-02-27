package com.java.demo;

import com.java.demo.util.Utils;

import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Main.demo1();
    }


    public static void demo1() throws ClassNotFoundException {
        Class aClass = Class.forName ("com.java.demo.child.Child");
        Class aClass1 = Class.forName("com.java.demo.child.ChildImpl1");

        Utils.myPrint(String.valueOf(aClass));
        Utils.myPrint(String.valueOf(aClass1));
        String name = aClass.getName();
        Utils.myPrint(name);
        Utils.myPrint(aClass.getPackage());
        Utils.myPrint(aClass.getClassLoader());
        Utils.myPrint(aClass.getConstructors());
        Utils.myPrint("aClass ======================over");
        Utils.myPrint("aClass1 ======================start");

        Utils utils = new Utils();
        Class<?>[] allInterface = utils.getAllInterface(aClass1);
        for (Class clas : allInterface) {
            Utils.myPrint(clas);
        }

        Utils.myPrint(Modifier.toString(aClass1.getModifiers()));
    }


}
