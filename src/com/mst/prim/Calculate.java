package com.mst.prim;

public class Calculate {

    private static int square(int n) {
        return n * n;
    }

    private static int abs(int n) {
        if (n < 0) {
            return -n;
        }

        return n;
    }

    public static int distance(int x1, int x2, int y1, int y2) {
        return (int)Math.round(Math.sqrt(abs(square(x1 - x2) + square(y1 - y2))));
    }
}