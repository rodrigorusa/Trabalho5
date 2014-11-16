/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.SQLException;

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
        System.out.println(sql);
        db.execute(sql);
    }
}
