package com.iticbcn.bdor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        String[] cnnset = DemanarConnexio();

        PersonaDAOimplBDOR pDAOimplBDOR = new PersonaDAOimplBDOR (cnnset[0],cnnset[1],cnnset[2]);

        GestioOpcions(pDAOimplBDOR);
    }

    public static String[] DemanarConnexio() {

        String[] connSettings = new String[3];

        System.err.print("A quina BD us voleu connectar? : ");
        String database = Entrada.readLine();
        connSettings[0] = "jdbc:postgresql://localhost:54320/" + database;
        
        System.err.print("Usuari: ");
        connSettings[1] = Entrada.readLine();
        System.err.print("Password: ");
        connSettings[2] = Entrada.readLine();

        return connSettings;

    }

    public static void GestioOpcions(PersonaDAOimplBDOR pDAOimplBDOR) {

        String opcio;
        boolean validOpt = true;

        while (validOpt) {

            System.out.println("Què voleu fer?");
            System.out.println("A. Inserir nova persona");
            System.out.println("B. Mostrar totes les persones");
    
            opcio = Entrada.readLine();
    
            if (opcio.equalsIgnoreCase("a")) {
                Persones per = DemanarDades();
                pDAOimplBDOR.InserirPersona(per);
                validOpt = false;
            } else if (opcio.equalsIgnoreCase("b")) {
                pDAOimplBDOR.MostrarPersones();
                validOpt = false;
    
            } else {
                System.out.println("Opció no vàlida, introduir l'opció correcta");
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
        System.out.print("Ciutat: ");
        String ciutat = Entrada.readLine();
        System.out.print("Codi Postal: ");
        String cp = Entrada.readLine();
        System.out.print("Provincia: ");
        String provincia = Entrada.readLine();

        Adresa add = new Adresa(carrer,nombre,ciutat,cp,provincia);

        boolean demanartelefons = true;

        ArrayList<String> telflist = new ArrayList<>(); // Lista dinámica

        int tel = 0;

        while (demanartelefons) {
            System.out.print("Introdueix el telefon [" + tel + "]: ");
            String telefon = Entrada.readLine();

            if (telefon.isEmpty()) { 
                demanartelefons = false;
            } else {
                telflist.add(telefon);
                tel++;
            }

            System.out.print("Vols introduir més telèfons s/n?: ");
            String demtel = Entrada.readLine();

            if (demtel.equalsIgnoreCase("n")) {
                demanartelefons = false;
            }
        }

        String [] telefons = telflist.toArray(new String[0]);

        Persones pers = new Persones(nom,cognoms,add,dataNaix,telefons);

        return pers;

    }

}