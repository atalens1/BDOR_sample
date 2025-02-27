package com.iticbcn.bdor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    private static final String dbUrl = "jdbc:postgresql://localhost:54320/samp1";
    
    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            System.err.print("Usuari: ");
            String dbUser = br1.readLine();
            System.err.print("Password: ");
            String dbPassword = br1.readLine();

            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                System.out.println("Connexió exitosa");

            } catch (Exception connex) {
                System.err.println("Error de connexió");
                System.err.println(connex.getMessage());
            }

        } catch (Exception ioe) {
            System.err.println("Error de i/o");
            System.err.println(ioe.getMessage());
        }

        
    }

    public static void InserirAdresa(Connection conn, Adresa add) {

        String query = "";

    }
}