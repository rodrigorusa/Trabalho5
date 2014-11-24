/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.ResultSet;
import trabalho5.view.MainFrame;

import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class Expense {
    
    private int codDesp;
    private int codEv;
    private int numEd;
    private String cnpjPat;
    private int codEvPat;
    private int numEdPat;
    private String dataDesp;
    private double valorDesp;
    private String descricaoDesp;

    public Expense(int codEv, int numEd, String cnpjPat, int codEvPat, int numEdPat, String dataDesp, double valorDesp, String descricaoDesp) {
        this.codEv = codEv;
        this.numEd = numEd;
        this.cnpjPat = cnpjPat;
        this.codEvPat = codEvPat;
        this.numEdPat = numEdPat;
        this.dataDesp = dataDesp;
        this.valorDesp = valorDesp;
        this.descricaoDesp = descricaoDesp;
    }
    
    public Expense(int codDesp, int codEv, int numEd, String cnpjPat, int codEvPat, int numEdPat, String dataDesp, double valorDesp, String descricaoDesp) {
        this.codDesp = codDesp;
        this.codEv = codEv;
        this.numEd = numEd;
        this.cnpjPat = cnpjPat;
        this.codEvPat = codEvPat;
        this.numEdPat = numEdPat;
        this.dataDesp = dataDesp;
        this.valorDesp = valorDesp;
        this.descricaoDesp = descricaoDesp;
    }

    public void setCodDesp(int codDesp) {
        this.codDesp = codDesp;
    }

    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }

    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }

    public void setCnpjPat(String cnpjPat) {
        this.cnpjPat = cnpjPat;
    }

    public void setCodEvPat(int codEvPat) {
        this.codEvPat = codEvPat;
    }

    public void setNumEdPat(int numEdPat) {
        this.numEdPat = numEdPat;
    }

    public void setDataDesp(String dataDesp) {
        this.dataDesp = dataDesp;
    }

    public void setValorDesp(double valorDesp) {
        this.valorDesp = valorDesp;
    }

    public void setDescricaoDesp(String descricaoDesp) {
        this.descricaoDesp = descricaoDesp;
    }

    public int getCodDesp() {
        return codDesp;
    }

    public int getCodEv() {
        return codEv;
    }

    public int getNumEd() {
        return numEd;
    }

    public String getCnpjPat() {
        return cnpjPat;
    }

    public int getCodEvPat() {
        return codEvPat;
    }

    public int getNumEdPat() {
        return numEdPat;
    }

    public String getDataDesp() {
        return dataDesp;
    }

    public double getValorDesp() {
        return valorDesp;
    }

    public String getDescricaoDesp() {
        return descricaoDesp;
    }
    
    /**
     * 
     * Insere despesa no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO despesa VALUES("
                + "seq_despesa.NEXTVAL, "
                + this.codEv+", "
                + this.numEd+", "
                + this.cnpjPat+", "
                + this.codEvPat+", "
                + this.numEdPat+", ";
                
        if (this.dataDesp == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataDesp+"', 'dd/mm/yyyy'), ";
        
        sql += this.valorDesp+", ";
        
        if (this.descricaoDesp == null)
            sql += "null)";
        else
            sql += "'"+this.descricaoDesp+"')";
                
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza despesa no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE despesa SET cnpjPat = "+this.cnpjPat+", "
                + "codEvPat = "+this.codEvPat+", "
                + "numEdPat = "+this.numEdPat+", "
                + "dataDesp = ";
        
        if (this.dataDesp == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataDesp+"', 'dd/mm/yyyy'), ";
        
        sql += "valorDesp = "+this.valorDesp+", ";
        
        sql += "descricaoDesp = ";
        if (this.descricaoDesp == null)
            sql += "null";
        else
            sql += "'"+this.descricaoDesp+"'";
        
        sql += " WHERE codDesp = "+this.codDesp+" AND codEv = "+this.codEv+" AND numEd = "+this.numEd;
        
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove despesa do banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM despesa WHERE codDesp = "+this.codDesp+" AND codEv = "+this.codEv+" AND numEd = "+this.numEd;
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
      /**
     * 
     * SELECT ALL na view de despesas
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_despesas";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * SELECT By Name na view de despesas
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_despesas WHERE UPPER(\"descricaoDesp\") LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
