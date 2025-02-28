package com.iticbcn.bdor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Entrada {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("No es pot deixar aquest camp en blanc");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}