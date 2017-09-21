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

    public static void testEnum() {
        BerryType type = BerryType.BLACKBERRY;

        switch (type) {
            case STRAWBERRY:
                System.out.println(BerryType.STRAWBERRY.toString());
            default:
                System.out.println("default");
            case BLACKBERRY:
                System.out.println(BerryType.BLACKBERRY);
        }

        int i;
        int j;
        for (i = 0, j = 0 ; j < 1 ; ++j , i++){
            System.out.println( i + " " + j );
        }
        System.out.println( i + " " + j );
    }

}
