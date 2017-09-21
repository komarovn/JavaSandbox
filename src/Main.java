import java.util.*;

import com.sandbox.ExceptionTesting;
import com.sandbox.Jam;
import com.sandbox.generics.Penthouse;
import com.sandbox.inheritance.C;
import com.sandbox.inheritance.C;
import com.sandbox.inheritance.InheritanceTester;
import com.sandbox.packages.Door;
import com.sandbox.packages.papierMâché.ПапьеMâché;
import org.apache.commons.lang3.*;

public class Main {

    public void partThree() {
        String привет = "hello";
        System.out.println(привет); // hello

        System.out.println("\u0430"); // a

        String nullStr = null;
        System.out.println(nullStr); // null

        System.out.println("world\b"); // worl
        System.out.println("wor\tld");
        System.out.println("guate\nmala");
        System.out.println("indo\rnesia");
        System.out.println("saudi\farabia");
        System.out.println("\377");
    }

    public void printAllProperties() {
        Properties properties = System.getProperties();
        for (Map.Entry entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " = " + StringEscapeUtils.escapeJava((String) entry.getValue()));
        }
    }

    public void partFour() {
        double a = 1.0;
        double b = -0.0;
        double c = 0.0;
        double d = Double.NEGATIVE_INFINITY;
        double e = Double.POSITIVE_INFINITY;
        double f = Double.NaN;
        System.out.println("a / b = " + a / b);
        System.out.println("a / c = " + a / c);
        System.out.println("0.0 / 0.0 = " + c / c);
        System.out.println("-Infinity / Infinity = " + d / e);
        System.out.println("Infinity / 1.0 = " + e / a);
        System.out.println("NaN != NaN is " + (f != f));
    }

    public void partFourInheritance() {
        InheritanceTester inheritanceTester = new InheritanceTester();
        List<C> view = new ArrayList<C>();
        view.add(new C());
        inheritanceTester.captureLowerBound(view);
        inheritanceTester.captureUpperBound(view);

        inheritanceTester.rawTypes();
    }

    public void boxingConversion() {
        int i = 3;
        Integer i1 = (Integer) i;
        Integer i2 = (Integer) i;
        System.out.println("(Integer) 3 == (Integer) 3 is " + (i1 == i2));
    }

    public void naming() {
        String s = "abc";
        String newS = s;
        s = "ghi";
        System.out.println(s);
        System.out.println(newS);
    }

    public void packages() {
        ПапьеMâché паьеМаше = new ПапьеMâché(3986);
        паьеМаше.суперКрутойМетод();

        Door doorOrWindow = new Penthouse();
        doorOrWindow.lock();
    }

    public void testFinallyBlock() {
        ExceptionTesting exceptionTesting = new ExceptionTesting();

        Jam jam = exceptionTesting.produceJam();
        System.out.println(jam.getBerryType());

        jam = exceptionTesting.produceStrawberryJam();
        System.out.println(jam.getBerryType());
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Main m = new Main();
        m.partThree();

        //m.printAllProperties();

        m.partFour();
        m.partFourInheritance();

        m.boxingConversion();

        m.naming();

        m.packages();

        m.testFinallyBlock();
    }
}
