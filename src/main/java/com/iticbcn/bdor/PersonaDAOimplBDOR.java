package com.iticbcn.bdor;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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

            String query = "INSERT INTO persones01 (nompers,cognomspers,datanaix,domicilipers,telefons)" +
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
    public void MostrarPersones() {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dpPassword)) {

            try (Statement statement = conn.createStatement()) {

                String query = "SELECT nompers, cognomspers, datanaix, (domicilipers).carrer," +
             "(domicilipers).nombre, (domicilipers).ciutat, (domicilipers).cp, (domicilipers).provincia" +
             " from persones01";

             ResultSet rset = statement.executeQuery(query);

                 //obtenim numero de columnes i nom
            int colNum = getColumnNames(rset);

            //Si el nombre de columnes és >0 procedim a llegir i mostrar els registres
            if (colNum > 0) {
                recorrerRegistres(rset,colNum);
            }


            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

        public static int getColumnNames(ResultSet rs) throws SQLException {
        
        int numberOfColumns = 0;
        
        if (rs != null) {   
            ResultSetMetaData rsMetaData = rs.getMetaData();
            numberOfColumns = rsMetaData.getColumnCount();   
        
            for (int i = 1; i < numberOfColumns + 1; i++) {  
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }
        }
        
        System.out.println();

        return numberOfColumns;
        
    }

    public void recorrerRegistres(ResultSet rs, int ColNum) throws SQLException {

        while(rs.next()) {
            for(int i =0; i<ColNum; i++) {
                if(i+1 == ColNum) {
                    System.out.println(rs.getString(i+1));
                } else {
            
                System.out.print(rs.getString(i+1)+ ", ");
                }
            } 
        }
            
    }
    
}
