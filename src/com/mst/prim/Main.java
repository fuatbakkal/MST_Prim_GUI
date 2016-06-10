package com.mst.prim;

public class Main {
    private static final int ROWS = 20;
    private static final int COLUMNS = 20;

    public static void main(final String[] args) {
        MainWindow mainWindow = new MainWindow(ROWS, COLUMNS);
        mainWindow.show();
    }
}