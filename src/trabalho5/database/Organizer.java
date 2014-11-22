/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import trabalho5.view.MainFrame;

/**
 *
 * @author Rodrigo
 */
public class Organizer {
    
    private int idOrg;
    private int codEv;
    private int numEd;
    private String cargoOrg;
    
     public Organizer(int idOrg, int codEv, int numEd, String cargoOrg) {
        this.idOrg = idOrg;
        this.codEv = codEv;
        this.numEd = numEd;
        this.cargoOrg = cargoOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }

    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }

    public void setCargoOrg(String cargoOrg) {
        this.cargoOrg = cargoOrg;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public int getCodEv() {
        return codEv;
    }

    public int getNumEd() {
        return numEd;
    }

    public String getCargoOrg() {
        return cargoOrg;
    }
    
    /**
     * 
     * Insere organizador no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO organiza VALUES ("
                + this.idOrg+", "
                + this.codEv+", "
                + this.numEd+", ";
        
        if (this.cargoOrg == null)
            sql += "null)";
        else
            sql += "'"+this.cargoOrg+"')";
        
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza organizador no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE organiza SET cargoOrg = '"+this.cargoOrg+"'";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove organizador no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM organiza WHERE idOrg = "+this.idOrg+" AND codEv = "+this.codEv+" AND numEd = "+this.numEd;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
     /**
     * 
     * SELECT ALL na view de organizadores
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_organizadores";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT By Name na view de organizadores
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_organizadores WHERE UPPER(nomePe) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
