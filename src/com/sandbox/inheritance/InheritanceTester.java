package com.sandbox.inheritance;

import java.util.ArrayList;
import java.util.List;

public class InheritanceTester {

    public void captureLowerBound(List<? super D> view) {
        if (!view.isEmpty()) {
            System.out.println(view.get(0).getClass().toString());
        }
    }

    public void captureUpperBound(List<? extends B> view) {
        if (!view.isEmpty()) {
            System.out.println(view.get(0).getClass().toString());
        }
    }

    public void rawTypes() {
        List names = new ArrayList(); // warning: raw type!
        names.add("John");
        names.add("Mary");
        names.add(Boolean.FALSE); // not a compilation error!

        for (Object name : names) {
            System.out.println(name);
        }
    }

}
