/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import trabalho5.view.MainFrame;

import java.sql.SQLException;
import java.sql.ResultSet;

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
        
        int flag = 0;
        if(this.dataApresArt == null) {
            sql += "null, null, ";
            flag = 1;
        }
        else
            sql += "to_date('"+this.dataApresArt+"', 'dd/mm/yyyy'), ";
        if(flag == 0) {
            if(this.horaApresArt == null)
                sql += "null, ";
            else
                sql += "to_date('"+this.dataApresArt+" "+this.horaApresArt+"', 'dd/mm/yyyy hh24:mi'), ";
        }
        
        sql += this.codEv+", "+ this.numEd+", "+ this.idApr+ ")";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
     /**
     * 
     * Atualiza artigo no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE artigo SET tituloArt = '"+this.tituloArt+"', ";
        
        sql += "dataApresArt = ";
        if(this.dataApresArt == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataApresArt+"', 'dd/mm/yyyy'), ";
        sql += "horaApresArt = ";
        if(this.horaApresArt == null)
            sql += "null, ";
        else
            sql += "to_date('"+this.dataApresArt+" "+this.horaApresArt+"', 'dd/mm/yyyy hh24:mi'), ";
        
        sql += "codEv = "+this.codEv+", numEd = "+ this.numEd+", idApr = "+ this.idApr
                + " WHERE idArt = "+this.idArt;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove artigo no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM artigo WHERE idArt = "+this.idArt;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca todos os artigos
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT idArt, tituloArt, to_char(dataApresArt, 'dd/mm/yyyy') AS \"dataApresArt\","
                + " to_char(horaApresArt, 'hh24:mi') AS \"horaApresArt\", codEv, numEd, idApr FROM artigo";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca artigos pelo nome
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT idArt, tituloArt, to_char(dataApresArt, 'dd/mm/yyyy') AS \"dataApresArt\","
                + " to_char(horaApresArt, 'hh24:mi') AS \"horaApresArt\", codEv, numEd, idApr FROM artigo"
                + " WHERE UPPER(tituloArt) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca número de artigos apresentados por um inscrito
     * 
     * @param db
     * @param codEv
     * @param idApr
     * @param numEd
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findNumberArticlesPresenter(DbConnection db, int codEv, int numEd, int idApr) throws SQLException {
        String sql = "SELECT COUNT(idArt) AS \"n_artigos\""
                + " FROM artigo"
                + " WHERE codEv = "+numEd+" AND numEd = "+numEd+" AND idApr = "+idApr;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * Retorna um a um os artigos
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Article next(ResultSet rs) throws SQLException {
        Article article = null;
        if (rs.next()) {
            article = new Article(rs.getInt("idArt"), rs.getString("tituloArt"), rs.getString("dataApresArt"),
                    rs.getString("horaApresArt"), rs.getInt("codEv"), rs.getInt("numEd"), rs.getInt("idApr"));
        }
        return article;
    }
    
     /**
     * 
     * SELECT ALL na view de artigos
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_artigos";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * SELECT By Name na view de artigos
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_artigos WHERE UPPER(\"tituloArt\") LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT By evento e edição na view de artigos
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByEventAndEdition(DbConnection db, int codEv, int numEd) throws SQLException {
        String sql = "SELECT * FROM view_artigos WHERE \"codEv\" = "+codEv+" AND \"numEd\" = "+numEd;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
