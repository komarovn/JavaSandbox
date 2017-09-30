package com.sandbox.optimization;

import java.util.*;

public class ProductionRhythm {
    public static final int N = 5;
    public static final int E = 10;
    public static final int E0 = 2;
    public static final int EN = 8;
    public static final List<Integer> pk = Arrays.asList(1, 1, 1, 6, 7);
    public double a;

    private List<Map<Integer, Double>> backwardCriteria = new ArrayList<Map<Integer, Double>>();
    private List<Map<Integer, Double>> upwardCriteria = new ArrayList<Map<Integer, Double>>();
    private List<List<Integer>> previousStates = new ArrayList<List<Integer>>();

    public ProductionRhythm() {
        double totalQuantity = 0.0;

        for (Integer p : pk) {
            totalQuantity += p;
        }

        this.a = (E0 + totalQuantity - EN) / (double) N;
    }

    public void printIteration(int state, double criterion, List<Integer> controls) {
        System.out.println(state + "\t|\t" + criterion + "\t\t|\t" + controls.toString());
    }

    public void printIteration(int state, double criterion, List<Integer> controls, List<Integer> optimalState) {
        System.out.println(state + "\t|\t" + criterion + "\t\t|\t" + controls.toString() + "\t|\t" + optimalState.toString());
    }

    public double backwardSK(int E, int k) {
        double S;
        List<Integer> controls = new ArrayList<Integer>();

        if (k == 1) {
            int control = -EN + pk.get(N - 1) + E;
            assert control >= 0 : "xN should be greater or equals than 0";
            controls.add(control);

            S = Math.pow(control - a, 2);
        } else {
            Map<Integer, Double> Sx = new HashMap<Integer, Double>(); // x, S

            int lowerBound = Math.max(0, pk.get(N - k) + E - ProductionRhythm.E);
            int upperBound = E + pk.get(N - k);

            for (int x = lowerBound; x <= upperBound; x++) {
                try {
                    double value = Math.pow(x - a, 2) + backwardCriteria.get(k - 2).get(pk.get(N - k) + E - x);
                    Sx.put(x, value);
                } catch (Exception e) {
                    // there is no S_(k-1)
                }
            }

            Map.Entry<Integer, Double> minEntry = null;
            for (Map.Entry<Integer, Double> entry : Sx.entrySet()) {
                if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
                    minEntry = entry;
                }
            }

            S = minEntry.getValue();
            for (Map.Entry<Integer, Double> entry : Sx.entrySet()) {
                if (entry.getValue().equals(S)) {
                    controls.add(entry.getKey());
                }
            }
        }

        printIteration(E, S, controls);

        return S;
    }

    public void backwardProcessStepK(int k) {
        Map<Integer, Double> criterion = new HashMap<Integer, Double>();

        System.out.println("E_" + (N - k) + "\t|\tS_" + k + "(E_" + (N - k) + ")\t|\tx_" + (N - k + 1));

        for (int E = 0; E <= ProductionRhythm.E; E++) {
            try {
                criterion.put(E, backwardSK(E, k));
            } catch (AssertionError e) {
                // incorrect control value
            }
        }

        System.out.println("\n");

        backwardCriteria.add(criterion);
    }

    public void backwardSolution() {
        for (int k = 1; k <= N; k++) {
            backwardProcessStepK(k);
        }
    }

    public double upwardZK(int E, int k) {
        double Z = 0.0;
        List<Integer> controls = new ArrayList<Integer>();
        List<Integer> optimalState = new ArrayList<Integer>();

        if (k == 1) {
            int control = pk.get(k - 1) + E0 - E;
            assert control >= 0;
            controls.add(control);

            Z = Math.pow(control - a, 2);
            optimalState.add(E0);
        } else {
            List<UpwardUnion> Zxs = new ArrayList<UpwardUnion>(); // x, Z, state

            for (Integer state : previousStates.get(k - 2)) {
                int x = state + pk.get(k - 1) - E;
                if (x < 0) continue;

                try {
                    double value = Math.pow(x - a, 2) + upwardCriteria.get(k - 2).get(state);
                    Zxs.add(new UpwardUnion(x, state, value));
                } catch (Exception e) {
                    // incorrect state
                }
            }

            UpwardUnion minUnion = null;
            for (UpwardUnion union : Zxs) {
                if (minUnion == null || union.compareTo(minUnion.Z) > 0) {
                    minUnion = union;
                }
            }

            assert minUnion != null : "There is no value for criterion";
            Z = minUnion.Z;
            for (UpwardUnion union : Zxs) {
                if (union.Z == Z) {
                    if (!controls.contains(union.control)) {
                        controls.add(union.control);
                    }
                    optimalState.add(union.state);
                }
            }
        }

        printIteration(E, Z, controls, optimalState);

        return Z;
    }

    public void upwardProcessStepK(int k) {
        Map<Integer, Double> criterion = new HashMap<Integer, Double>();
        List<Integer> previousState = new ArrayList<Integer>();

        System.out.println("E_" + k + "\t|\tZ_" + k + "(E_" + k + ")\t|\tx*_" + k + "\t|\tE*_" + (k - 1));

        for (int E = 0; E <= ProductionRhythm.E; E++) {
            try {
                criterion.put(E, upwardZK(E, k));
                previousState.add(E);
            } catch (AssertionError e) {
                // incorrect control value
            }
        }

        System.out.println("\n");

        previousStates.add(previousState);
        upwardCriteria.add(criterion);
    }

    public void upwardSolution() {
        for (int k = 1; k <= N; k++) {
            upwardProcessStepK(k);
        }
    }

    public static void main(String ... args) {
        ProductionRhythm program = new ProductionRhythm();

        System.out.println("\nProduction Rhythm Problem\n\n");

        System.out.println("Backward solution:\n");
        program.backwardSolution();
        System.out.println("Upward solution:\n");
        program.upwardSolution();
    }

    private class UpwardUnion implements Comparable<Double> {
        int control;
        int state;
        double Z;

        public UpwardUnion(int control, int state, double z) {
            this.control = control;
            this.state = state;
            Z = z;
        }

        @Override
        public int compareTo(Double o) {
            return (int) (o - Z);
        }
    }

}
