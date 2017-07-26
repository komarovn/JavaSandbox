package com.sandbox;

public class ExceptionTesting {

    public Jam produceJam() {
        Jam jam = new Jam(BerryType.STRAWBERRY);
        try {
            jam.setBerryType(BerryType.BLACKBERRY);
            return jam;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            jam.setBerryType(BerryType.RASPBERRY);
        }
        return null;
    }

    public Jam produceStrawberryJam() {
        Jam jam = new Jam(BerryType.STRAWBERRY);
        try {
            jam.setBerryType(BerryType.BLACKBERRY);
            return jam;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            jam.setBerryType(BerryType.RASPBERRY);
            return new Jam(BerryType.STRAWBERRY);
        }
    }

}
