package com.sandbox.generics;

public class Person<Type extends Penthouse & Car & Tree> {

    public void isFull(Type type) {
        type.drive();
        type.grow();
    }

}
