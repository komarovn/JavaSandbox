package com.sandbox.packages.papierMâché;

public class ПапьеMâché {

    private int инт;

    public ПапьеMâché(int инт) {
        this.инт = инт;
    }

    public int getИнт() {
        return инт;
    }

    public void setИнт(int инт) {
        this.инт = инт;
    }

    public void суперКрутойМетод() {
        System.out.println("А кто запрещал писать кириллицей?");
        System.out.println("инт = " + инт);
    }

}
