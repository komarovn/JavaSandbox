package com.sandbox;

import com.sandbox.annotation.NonNull;

public class Assertion {

    public int goose(boolean statement) {
        int result = 3;
        //if (result == 3) throw new RuntimeException();
        assert statement : result = 5;
        return result;
    }

    public void raccoon(@NonNull String str) {
        System.out.println("This is NonNull String: " + str);
    }

    public static void main(String ... args) {
        Assertion ass = new Assertion();

        System.out.println(ass.goose(true));
        //System.out.println(ass.goose(false));

        ass.raccoon(null);

        ExceptionTesting.testEnum();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
