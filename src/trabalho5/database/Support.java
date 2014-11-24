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
public class Support {
    
    private String cnpjPat;
    private int codEvPat;
    private int numEdPat;
    private int codEvApr;
    private int numEdApr;
    private int idApr;
    private double valorAux;
    private String dataAux;
    private String tipoAux;
    
    public Support(String cnpjPat, int codEvPat, int numEdPat, int codEvApr, int numEdApr, int idApr, double valorAux, String dataAux, String tipoAux) {
        this.cnpjPat = cnpjPat;
        this.codEvPat = codEvPat;
        this.numEdPat = numEdPat;
        this.codEvApr = codEvApr;
        this.numEdApr = numEdApr;
        this.idApr = idApr;
        this.valorAux = valorAux;
        this.dataAux = dataAux;
        this.tipoAux = tipoAux;
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

    public void setCodEvApr(int codEvApr) {
        this.codEvApr = codEvApr;
    }

    public void setNumEdApr(int numEdApr) {
        this.numEdApr = numEdApr;
    }

    public void setIdApr(int idApr) {
        this.idApr = idApr;
    }

    public void setValorAux(double valorAux) {
        this.valorAux = valorAux;
    }

    public void setDataAux(String dataAux) {
        this.dataAux = dataAux;
    }

    public void setTipoAux(String tipoAux) {
        this.tipoAux = tipoAux;
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

    public int getCodEvApr() {
        return codEvApr;
    }

    public int getNumEdApr() {
        return numEdApr;
    }

    public int getIdApr() {
        return idApr;
    }

    public double getValorAux() {
        return valorAux;
    }

    public String getDataAux() {
        return dataAux;
    }

    public String getTipoAux() {
        return tipoAux;
    }
    
    /**
     * 
     * Insere auxílio no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO auxilio VALUES("
                + this.cnpjPat+", "
                + this.codEvPat+", "
                + this.numEdPat+", "
                + this.codEvApr+", "
                + this.numEdApr+", "
                + this.idApr+", "
                + this.valorAux+", ";
        
        if (this.dataAux == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataAux+"', 'dd/mm/yyyy'), ";
        
        sql += "'"+this.tipoAux+"')";
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * SELECT ALL na view de auxílios
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_auxilios";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * SELECT By Name na view de auxílios
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_auxilios WHERE UPPER(\"nomePe\") LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
