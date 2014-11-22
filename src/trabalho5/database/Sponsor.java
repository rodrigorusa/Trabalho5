/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import trabalho5.view.MainFrame;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class Sponsor {

    private String cnpjPat;
    private String razaoSocialPat;
    private String telefonePat;
    private String enderecoPat;
    
    public Sponsor(String cnpjPat, String razaoSocialPat, String telefonePat, String enderecoPat) {
        this.cnpjPat = cnpjPat;
        this.razaoSocialPat = razaoSocialPat;
        this.telefonePat = telefonePat;
        this.enderecoPat = enderecoPat;
    }

    public void setCnpjPat(String cnpjPat) {
        this.cnpjPat = cnpjPat;
    }

    public void setRazaoSocialPat(String razaoSocialPat) {
        this.razaoSocialPat = razaoSocialPat;
    }

    public void setTelefonePat(String telefonePat) {
        this.telefonePat = telefonePat;
    }

    public void setEnderecoPat(String enderecoPat) {
        this.enderecoPat = enderecoPat;
    }

    public String getCnpjPat() {
        return cnpjPat;
    }

    public String getRazaoSocialPat() {
        return razaoSocialPat;
    }

    public String getTelefonePat() {
        return telefonePat;
    }

    public String getEnderecoPat() {
        return enderecoPat;
    }
    
    /**
     * 
     * Insere patrocinador no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO patrocinador VALUES("
                + this.cnpjPat+", "
                + "'"+this.razaoSocialPat+"', ";
        
        if (this.telefonePat == null)
            sql += "null, ";
        else
            sql += "'"+this.telefonePat+"', ";
        
        if (this.enderecoPat == null)
            sql += "null)";
        else
            sql += "'"+this.enderecoPat+"')";
        
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza patrocinador no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE patrocinador SET razaoSocialPat = '"+this.razaoSocialPat+"', ";
        
        sql += "telefonePat = ";
        if (this.telefonePat == null)
            sql += "null, ";
        else
            sql += "'"+this.telefonePat+"', ";
        
        sql += "enderecoPat = ";
        if (this.enderecoPat == null)
            sql += "null";
        else
            sql += "'"+this.enderecoPat+"'";
        
        sql += " WHERE cnpjPat = "+this.cnpjPat;
        
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove patrocinador do banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM patrocinador WHERE cnpjPat = "+this.cnpjPat;
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca todas os patrocinadores
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT cnpjPat, razaoSocialPat, telefonePat, enderecoPat FROM patrocinador";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca os patrocinadores pelo nome
     * 
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT cnpjPat, razaoSocialPat, telefonePat, enderecoPat FROM patrocinador "
                + "WHERE UPPER(razaoSocialPat) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Retorna um a um os patrocinadores
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Sponsor next(ResultSet rs) throws SQLException {
        Sponsor sponsor = null;
        if (rs.next()) {
            sponsor = new Sponsor(rs.getString("cnpjPat"), rs.getString("razaoSocialPat"), rs.getString("telefonePat"),
                    rs.getString("enderecoPat"));
        }
        return sponsor;
    }
    
}
