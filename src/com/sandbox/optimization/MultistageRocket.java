package com.sandbox.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultistageRocket {

    private static final Integer m = 1;
    private static final Integer N = 3;
    private static final Integer M = 64;

    private List<Map<Integer, Double>> criteria = new ArrayList<Map<Integer, Double>>();

    public void processStepOne() {
        Map<Integer, Double> criterion = new HashMap<Integer, Double>();

        System.out.println("x_" + (N - 1) + "\t|\tS_" + "1" + "(x_" + (N - 1) + ")\t\t\t|\tu*_" + N);

        for (int x = 0; x < M; x++) {
            criterion.put(x, SOne(x));
        }

        System.out.println("\n\n");

        criteria.add(criterion);
    }

    public void processStepK(int k) {
        Map<Integer, Double> criterion = new HashMap<Integer, Double>();

        System.out.println("x_" + (N - k) + "\t|\tS_" + k + "(x_" + (N - k) + ")\t\t\t|\tu*_" + (N - k + 1));

        for (int x = 0; x < M; x++) {
            criterion.put(x, Sk(x, k));
        }

        System.out.println("\n\n");

        criteria.add(criterion);
    }

    public double SOne(int x) {
        double result = x / (double) (m + x);

        System.out.println(x + "\t|\t" + result + "\t|\t" + x);

        return result;
    }

    public double Sk(int x, int k) {
        Map<Integer, Double> criterionByU = new HashMap<Integer, Double>();

        for (int u = 0; u <= x; u++) {
            double s = u / (double) (m + x) + criteria.get(k - 2).get(x - u);
            criterionByU.put(u, s);
        }

        Map.Entry<Integer, Double> maxEntry = null;
        for (Map.Entry<Integer, Double> entry : criterionByU.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        System.out.println(x + "\t|\t" + maxEntry.getValue() + "\t|\t" + maxEntry.getKey());

        return maxEntry.getValue();
    }

    public static void main(String ... args) {
        MultistageRocket program = new MultistageRocket();

        System.out.println("\nMultistage Rocket Problem\n");
        program.processStepOne();
        for (int k = 2; k <= N; k++) {
            program.processStepK(k);
        }
    }

}
