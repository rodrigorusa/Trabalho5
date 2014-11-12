/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Rodrigo
 */
public class Event {
    
    private int codEv;
    private String nomeEv = null;
    private String descricaoEv = null;
    private String websiteEv = null;
    private int totalArtigosApresentadosEv = 0;
    
    public Event(String nomeEv, String descricaoEv, String websiteEv, int totalArtigosApresentadosEv) {
        this.nomeEv = nomeEv;
        this.descricaoEv = descricaoEv;
        this.websiteEv = websiteEv;
        this.totalArtigosApresentadosEv = totalArtigosApresentadosEv;
    }
    
    public Event(int codEv, String nomeEv, String descricaoEv, String websiteEv, int totalArtigosApresentadosEv) {
        this.codEv = codEv;
        this.nomeEv = nomeEv;
        this.descricaoEv = descricaoEv;
        this.websiteEv = websiteEv;
        this.totalArtigosApresentadosEv = totalArtigosApresentadosEv;
    }
    
    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }
    
    public void setNomeEv(String nomeEv) {
        this.nomeEv = nomeEv;
    }
    
    public void setDescricaoEv(String descricaoEv) {
        this.descricaoEv = descricaoEv;
    }
    
    public void setWebsiteEv(String websiteEv) {
        this.websiteEv = websiteEv;
    }
    
    public void setTotalArtigosApresentadosEv(int totalArtigosApresentadosEv) {
        this.totalArtigosApresentadosEv = totalArtigosApresentadosEv;
    }

    public int getCodEv() {
        return this.codEv;
    }
    
    public String getNomeEv() {
        return this.nomeEv;
    }
    
    public String getDescricaoEv() {
        return this.descricaoEv;
    }
    
    public String getWebsiteEv() {
        return this.websiteEv;
    }
    
    public int getTotalArtigosApresentadosEv() {
        return this.totalArtigosApresentadosEv;
    }
    
    /**
     * 
     * Insere evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO evento VALUES (seq_evento.NEXTVAL, '"+this.nomeEv+"', '"+this.descricaoEv+"', "
                + "'"+this.websiteEv+"', "+this.totalArtigosApresentadosEv+")";
        System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE evento SET nomeEv = '"+this.nomeEv+"', descricaoEv = '"+this.descricaoEv+"', "
                + "websiteEv = '"+this.websiteEv+"' WHERE codEv = '"+this.codEv+"'";
        System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM evento WHERE codEv = '"+this.codEv+"'";
        System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca todos os eventos
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT codEv, nomeEv, descricaoEv, websiteEv, totalArtigosApresentadosEv FROM evento";
        System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca os eventos pelo nome
     * 
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT codEv, nomeEv, descricaoEv, websiteEv, totalArtigosApresentadosEv FROM evento "
                + "WHERE UPPER(nomeEv) LIKE UPPER('%"+name+"%')";
        System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Retorna um a um os eventos
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Event next(ResultSet rs) throws SQLException {
        Event event = null;
        if (rs.next()) {
            event = new Event(rs.getInt("codEv"), rs.getString("nomeEv"), rs.getString("descricaoEv"), rs.getString("websiteEv"), 
                    rs.getInt("totalArtigosApresentadosEv"));
        }
        return event;
    }
    
}
