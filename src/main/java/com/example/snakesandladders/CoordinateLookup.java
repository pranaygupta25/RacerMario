package com.example.snakesandladders;

public class CoordinateLookup {

    // -----------------------------------------------------------------------------------------------------------------

    /*static private int getX(int current){
        int res = 0;
        switch (current){
            case  1, 20, 21, 40, 41, 60, 61, 80, 81, 100 -> res = 0;
            case  2, 19, 22, 39, 42, 59, 62, 79, 82, 99  -> res = 50;
            case  3, 18, 23, 38, 43, 58, 63, 78, 83, 98  -> res = 100;
            case  4, 17, 24, 37, 44, 57, 64, 77, 84, 97  -> res = 150;
            case  5, 16, 25, 36, 45, 56, 65, 76, 85, 96  -> res = 200;
            case  6, 15, 26, 35, 46, 55, 66, 75, 86, 95  -> res = 250;
            case  7, 14, 27, 34, 47, 54, 67, 74, 87, 94  -> res = 300;
            case  8, 13, 28, 33, 48, 53, 68, 73, 88, 93  -> res = 350;
            case  9, 12, 29, 32, 49, 52, 69, 72, 89, 92  -> res = 400;
            case 10, 11, 30, 31, 50, 51, 70, 71, 90, 91  -> res = 450;
        }
        return res;
    }

    static private int getY(int current){
        int res = 0;
        switch (current){
            case  1,  2,  3,  4,  5,  6,  7,  8,  9, 10  -> res = -50;
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20  -> res = -100;
            case 21, 22, 23, 24, 25, 26, 27, 28, 29, 30  -> res = -150;
            case 31, 32, 33, 34, 35, 36, 37, 38, 39, 40  -> res = -200;
            case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50  -> res = -250;
            case 51, 52, 53, 54, 55, 56, 57, 58, 59, 60  -> res = -300;
            case 61, 62, 63, 64, 65, 66, 67, 68, 69, 70  -> res = -350;
            case 71, 72, 73, 74, 75, 76, 77, 78, 79, 80  -> res = -400;
            case 81, 82, 83, 84, 85, 86, 87, 88, 89, 90  -> res = -450;
            case 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> res = -500;
        }
        return res;
    }*/

    static private int getX(int current){
        int res = 0;
        switch (current){
            case 1, 10, 11, 20, 21 -> res = 0;
            case 2,  9, 12, 19, 22 -> res = 100;
            case 3,  8, 13, 18, 23 -> res = 200;
            case 4,  7, 14, 17, 24 -> res = 300;
            case 5,  6, 15, 16, 25 -> res = 400;
        }
        return res;
    }

    static private int getY(int current){
        int res = 0;
        switch (current){
            case  1,  2,  3,  4,  5 -> res = -0;
            case 10,  9,  8,  7,  6 -> res = -100;
            case 11, 12, 13, 14, 15 -> res = -200;
            case 20, 19, 18, 17, 16 -> res = -300;
            case 21, 22, 23, 24, 25 -> res = -400;
        }
        return res;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static int[] getCoordinates(int current) {
        int[] result = new int[2];
        result[0] = CoordinateLookup.getX(current);
        result[1] = CoordinateLookup.getY(current);
        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------

}
