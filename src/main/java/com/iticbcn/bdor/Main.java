package com.iticbcn.bdor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    
    public static void main(String[] args) {

        String[] cnnset = DemanarConnexio();

        PersonaDAOimplBDOR pDAOimplBDOR = new PersonaDAOimplBDOR (cnnset[1],cnnset[2],cnnset[3]);

        GestioOpcions(pDAOimplBDOR);
    }

    public static String[] DemanarConnexio() {

        String[] connSettings = {};

        System.err.print("A quina BD us voleu connectar? : ");
        String database = Entrada.readLine();
        connSettings[1] = "jdbc:postgresql://localhost:30000/" + database;
        
        System.err.print("Usuari: ");
        connSettings[2] = Entrada.readLine();
        System.err.print("Password: ");
        connSettings[3] = Entrada.readLine();

        return connSettings;

    }

    public static void GestioOpcions(PersonaDAOimplBDOR pDAOimplBDOR) {

        String opcio;
        boolean validOpt = true;

        while (!validOpt) {

            System.out.println("Què voleu fer?");
            System.out.println("A. Inserir nova persona");
            System.out.println("B. Mostrar totes les persones");
    
            opcio = Entrada.readLine();
    
            if (opcio.equalsIgnoreCase("a")) {
                Persones per = DemanarDades();
                pDAOimplBDOR.InserirPersona(per);
            } else if (opcio.equalsIgnoreCase("b")) {
    
            } else {
                System.out.println("Opció no vàlida, introduir l'opció correcta");
                validOpt = false;
            }

        }

    }

    public static Persones DemanarDades() {

        System.out.print("Nom: ");
        String nom = Entrada.readLine();
        System.out.print("Cognoms: ");
        String cognoms = Entrada.readLine();
        System.out.print("Data de naixament: ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataNaix = LocalDateTime.parse(Entrada.readLine(),formatter);

        System.out.print("Carrer: ");    
        String carrer = Entrada.readLine();
        System.out.print("Nombre: ");    
        int nombre = Integer.parseInt(Entrada.readLine());
        System.out.print("Ciutat");
        String ciutat = Entrada.readLine();
        System.out.print("Codi Postal");
        String cp = Entrada.readLine();
        System.out.print("Provincia");
        String provincia = Entrada.readLine();

        Adresa add = new Adresa(carrer,nombre,ciutat,cp,provincia);

        boolean demanartelefons = true;

        String [] telefons = {};

        while(demanartelefons) {
            int tel = 1;
            System.out.print("Introdueix el telefon [" + tel + "]" );
            telefons[tel] = Entrada.readLine();
            tel++;
        }

        Persones pers = new Persones(nom,cognoms,add,dataNaix,telefons);

        return pers;

    }

}