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
public class Sponsorship {
    
    private String cnpjPat;
    private int codEv;
    private int numEd;
    private double valorPat;
    private double saldoPat;
    private String dataPat;
    
    public Sponsorship(String cnpjPat, int codEv, int numEd, double valorPat, double saldoPat, String dataPat) {
        this.cnpjPat = cnpjPat;
        this.codEv = codEv;
        this.numEd = numEd;
        this.valorPat = valorPat;
        this.saldoPat = saldoPat;
        this.dataPat = dataPat;
    }

    public void setCnpjPat(String cnpjPat) {
        this.cnpjPat = cnpjPat;
    }

    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }

    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }

    public void setValorPat(double valorPat) {
        this.valorPat = valorPat;
    }

    public void setSaldoPat(double saldoPat) {
        this.saldoPat = saldoPat;
    }

    public void setDataPat(String dataPat) {
        this.dataPat = dataPat;
    }

    public String getCnpjPat() {
        return cnpjPat;
    }

    public int getCodEv() {
        return codEv;
    }

    public int getNumEd() {
        return numEd;
    }

    public double getValorPat() {
        return valorPat;
    }

    public double getSaldoPat() {
        return saldoPat;
    }

    public String getDataPat() {
        return dataPat;
    }
    
    /**
     * 
     * Insere patrocínio no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO patrocinio VALUES("
                + this.cnpjPat+", "
                + this.codEv+", "
                + this.numEd+", "
                + this.valorPat+", "
                + this.saldoPat+", ";
        
        if (this.dataPat == null)
            sql += "null)";
        else
            sql += "to_date('"+this.dataPat+"', 'dd/mm/yyyy'))";
        
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza patrocínio no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE patrocinio SET valorPat = "+this.valorPat+", dataPat = ";
        
        if (this.dataPat == null)
            sql += "null";
        else
            sql += "to_date('"+this.dataPat+"', 'dd/mm/yyyy')";
        
        sql += " WHERE cnpjPat = "+this.cnpjPat+" AND codEv = "+this.codEv+" AND numEd = "+this.numEd;
        
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove patrocínio do banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM patrocinio WHERE cnpjPat = "+this.cnpjPat+" AND codEv = "+this.codEv+" AND numEd = "+this.numEd;
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
     /**
     * 
     * SELECT ALL na view de patrocinios
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_patrocinios";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * SELECT By Name na view de patrocinios
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_patrocinios WHERE UPPER(razaoSocialPat) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
