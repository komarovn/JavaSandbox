package com.sandbox.generics;

import com.sandbox.packages.Door;
import com.sandbox.packages.Window;

public class Penthouse implements Door, Window {

    public void liveIn() {
        System.out.println("I am penthouse, not a com.sandbox.generics.Car!");
    }

    protected void build() {
        System.out.println("Build a penthouse!");
    }

    @Override
    public void lock() {
        System.out.println("This is an implementation of method in both Door interface and Window interface!");
    }

}
