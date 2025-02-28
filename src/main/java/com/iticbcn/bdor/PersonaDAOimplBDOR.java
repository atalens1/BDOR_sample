package com.iticbcn.bdor;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class PersonaDAOimplBDOR implements IntPersonaDAO{

    private String dbUrl;
    private String dbUser;
    private String dpPassword;

    public PersonaDAOimplBDOR(String dbUrl, String dbUser, String dpPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dpPassword = dpPassword;
    }

    @Override
    public void InserirPersona(Persones pers) {

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dpPassword)) {

            String query = "INSERT INTO persones01 (nompers,cognomspers,datanaix,domicili,telefons)" +
            " VALUES (?,?,?,ROW(?,?,?,?,?),?)";
    
            boolean autocommitvalue = conn.getAutoCommit();
    
            conn.setAutoCommit(false);
    
            try (PreparedStatement prep = conn.prepareStatement(query)) {
                prep.setString(1, pers.getNomPers());
                prep.setString(2, pers.getCognomsPers());
                prep.setTimestamp(3, Timestamp.valueOf(pers.getDataNaix()));;
                prep.setString(4, pers.getDomiciliPers().getCarrer());
                prep.setInt(5,pers.getDomiciliPers().getNombre());
                prep.setString(6, pers.getDomiciliPers().getCiutat());
                prep.setString(7, pers.getDomiciliPers().getCp());
                prep.setString(8, pers.getDomiciliPers().getProvincia());
    
                Array telArray = conn.createArrayOf("text", pers.getTelefons());
    
                prep.setArray(9, telArray);
    
                prep.executeUpdate();
    
                conn.commit();
    
                System.out.println("Persona inserida amb èxit");
    
            } catch (Exception ex) {
    
                conn.rollback();
                System.err.println(ex.getMessage());
            }
    
            conn.setAutoCommit(autocommitvalue);
    

        } catch (Exception ex) {

        }

    }

    @Override
    public List<Persones> MostrarPersones() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MostrarPersones'");
    }
    
}
