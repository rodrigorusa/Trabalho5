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
public class Write {
    
    private int idAut;
    private int idArt;
    
    public Write(int idAut, int idArt) {
        this.idAut = idAut;
        this.idArt = idArt;
    }
    
    public void setIdAut(int idAut) {
        this.idAut = idAut;
    }
    
    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }
    
    public int getIdAut() {
        return this.idAut;
    }
    
    public int getIdArt() {
        return this.idArt;
    }
    
    /**
     * 
     * Insere escreve no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO escreve VALUES("+this.idAut+", "+this.idArt+")";
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca os autores pelo artigo
     * 
     * @param db
     * @param idArt
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByArticle(DbConnection db, int idArt) throws SQLException {
        String sql = "SELECT idAut, idArt FROM escreve WHERE idArt = "+idArt;
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Retorna um a um
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Write next(ResultSet rs) throws SQLException {
        Write write = null;
        if (rs.next()) {
            write = new Write(rs.getInt("idAut"), rs.getInt("idArt"));
        }
        return write;
    }
    
}
