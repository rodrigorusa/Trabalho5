/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Rodrigo
 */
public class Article {

    private int idArt;
    private String tituloArt;
    private String dataApresArt;
    private String horaApresArt;
    private int codEv;
    private int numEd;
    private int idApr;
    
     public Article(String tituloArt, String dataApresArt, String horaApresArt, int codEv, int numEd, int idApr) {
        this.tituloArt = tituloArt;
        this.dataApresArt = dataApresArt;
        this.horaApresArt = horaApresArt;
        this.codEv = codEv;
        this.numEd = numEd;
        this.idApr = idApr;
    }
    
    public Article(int idArt, String tituloArt, String dataApresArt, String horaApresArt, int codEv, int numEd, int idApr) {
        this.idArt = idArt;
        this.tituloArt = tituloArt;
        this.dataApresArt = dataApresArt;
        this.horaApresArt = horaApresArt;
        this.codEv = codEv;
        this.numEd = numEd;
        this.idApr = idApr;
    }
    
    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    public void setTituloArt(String tituloArt) {
        this.tituloArt = tituloArt;
    }

    public void setDataApresArt(String dataApresArt) {
        this.dataApresArt = dataApresArt;
    }

    public void setHoraApresArt(String horaApresArt) {
        this.horaApresArt = horaApresArt;
    }

    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }

    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }

    public void setIdApr(int idApr) {
        this.idApr = idApr;
    }
    
     public int getIdArt() {
        return idArt;
    }

    public String getTituloArt() {
        return tituloArt;
    }

    public String getDataApresArt() {
        return dataApresArt;
    }

    public String getHoraApresArt() {
        return horaApresArt;
    }

    public int getCodEv() {
        return codEv;
    }

    public int getNumEd() {
        return numEd;
    }

    public int getIdApr() {
        return idApr;
    }
    
    /**
     * 
     * Insere artigo no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO artigo VALUES("
                + "seq_artigo.NEXTVAL, "
                + "'"+this.tituloArt+"', ";
        
        if(this.dataApresArt == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataApresArt+"', 'dd/mm/yyyy'), ";
        if(this.horaApresArt == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataApresArt+" "+this.horaApresArt+"', 'dd/mm/yyyy hh24:mi'), ";
        
        sql += this.codEv+", "+ this.numEd+", "+ this.idApr+ ")";
        System.out.println(sql);
        db.execute(sql);
    }
}
