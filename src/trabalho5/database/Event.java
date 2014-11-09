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
public class Event {
    
    String nomeEv = null;
    String descricaoEv = null;
    String websiteEv = null;
    
    public Event(String nomeEv, String descricaoEv, String websiteEv) {
        this.nomeEv = nomeEv;
        this.descricaoEv = descricaoEv;
        this.websiteEv = websiteEv;
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

    public String getNomeEv() {
        return this.nomeEv;
    }
    
    public String getDescricaoEv() {
        return this.descricaoEv;
    }
    
    public String getWebsiteEv() {
        return this.websiteEv;
    }
    
    /**
     * 
     * Insere evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException{
        String sql = "INSERT INTO evento VALUES (seq_evento.NEXTVAL, '"+this.nomeEv+"', '"+this.descricaoEv+"', "
                + "'"+this.websiteEv+"', 0)";
        System.out.println(sql);
        db.execute(sql);
    }
    
}
