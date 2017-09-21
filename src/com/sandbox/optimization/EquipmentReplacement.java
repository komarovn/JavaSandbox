package com.sandbox.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentReplacement {

    public static final Integer P = 17;
    public static final Integer N = 8;

    private List<Map<Integer, Double>> criteria = new ArrayList<Map<Integer, Double>>();

    public double f(int t) {
        return 30.0 - t / 2.0;
    }

    public double r(int t) {
        return 13.0 + t / 2.0;
    }

    public double phi(int t) {
        if (0 <= t && t <= 5) {
            return 7.0;
        } else if (t >= 6) {
            return 3;
        } else {
            throw new RuntimeException();
        }
    }

    public double SOne(int t) {
        double uZero = f(t + 1) - r(t + 1) + phi(t + 2);
        double uOne = phi(t + 1) - P + f(0) - r(0) + phi(1);
        return printIterAndGetMax(t, uZero, uOne);
    }

    public double SKPlusOne(int t, int k) {
        double uZero = f(t + 1) - r(t + 1) + criteria.get(k - 1).get(t + 1);
        //double phiLast = t == 0 ? P : phi(t + 1);
        double uOne = phi(t + 1) - P + f (0) - r(0) + criteria.get(k - 1).get(0);
        return printIterAndGetMax(t, uZero, uOne);
    }

    public double printIterAndGetMax(int t, double uZero, double uOne) {
        if (uZero > uOne) {
            System.out.println(t + "         |   " + uZero + "          |   " + "0");
            return uZero;
        } else if (uOne > uZero) {
            System.out.println(t + "         |   " + uOne + "          |   " + "1");
            return uOne;
        } else {
            System.out.println(t + "         |   " + uZero + "          |   " + "0, 1");
            return uZero;
        }
    }

    public void processStepOne() {
        System.out.println("t_" + (N - 1) + "       |   S_1(t_" + (N - 1) + ")      |   u_" + N);

        Map<Integer, Double> criterion = new HashMap<Integer, Double>();

        for (int t = 0; t < 10; t++) {
            criterion.put(t, SOne(t));
        }

        System.out.println("\n\n");
        criteria.add(criterion);
    }

    public void processStepKPlusOne(int k) {
        System.out.println("t_" + (N - k - 1) + "       |   S_" + (k + 1) + "(t_" + (N - k - 1) + ")      |   u_" + (N - k));

        Map<Integer, Double> criterion = new HashMap<Integer, Double>();

        for (int t = 0; t < 10 - k; t++) {
            criterion.put(t, SKPlusOne(t, k));
        }

        System.out.println("\n\n");
        criteria.add(criterion);
    }

    public static void main(String ... args) {
        EquipmentReplacement program = new EquipmentReplacement();

        System.out.println("\nEquipment Replacement Problem\n");
        program.processStepOne();
        for (int k = 1; k < 8; k++) {
            program.processStepKPlusOne(k);
        }
    }

}
