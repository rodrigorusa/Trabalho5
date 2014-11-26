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
public class People {
    
    private int idPe;
    private String nomePe;
    private String emailPe;
    private String instituicaoPe;
    private String telefonePe;
    private String nacionalidadePe;
    private String enderecoPe;
    private char tipoOrganizador;
    private char tipoParticipante;
    private char tipoAutor;
    
    public People(String nomePe, String emailPe, String instituicaoPe, String telefonePe, String nacionalidadePe, 
            String enderecoPe, char tipoOrganizador, char tipoParticipante, char tipoAutor) {
        this.nomePe = nomePe;
        this.emailPe = emailPe;
        this.instituicaoPe = instituicaoPe;
        this.telefonePe = telefonePe;
        this.nacionalidadePe = nacionalidadePe;
        this.enderecoPe = enderecoPe;
        this.tipoOrganizador = tipoOrganizador;
        this.tipoParticipante = tipoParticipante;
        this.tipoAutor = tipoAutor;
    }
    
    public People(int idPe, String nomePe, String emailPe, String instituicaoPe, String telefonePe, String nacionalidadePe, 
            String enderecoPe, char tipoOrganizador, char tipoParticipante, char tipoAutor) {
        this.idPe = idPe;
        this.nomePe = nomePe;
        this.emailPe = emailPe;
        this.instituicaoPe = instituicaoPe;
        this.telefonePe = telefonePe;
        this.nacionalidadePe = nacionalidadePe;
        this.enderecoPe = enderecoPe;
        this.tipoOrganizador = tipoOrganizador;
        this.tipoParticipante = tipoParticipante;
        this.tipoAutor = tipoAutor;
    }
    
    public void setIdPe(int idPe) {
        this.idPe = idPe;
    }
    
    public void setNomePe(String nomePe) {
        this.nomePe = nomePe;
    }
    
    public void setEmailPe(String emailPe) {
        this.emailPe = emailPe;
    }
    
    public void setInstituicaoPe(String instituicaoPe) {
        this.instituicaoPe = instituicaoPe;
    }
    
    public void setTelefonePe(String telefonePe) {
        this.telefonePe = telefonePe;
    }
    
    public void setNacionalidadePe(String nacionalidadePe) {
        this.nacionalidadePe = nacionalidadePe;
    }
    
    public void setEnderecoPe(String enderecoPe) {
        this.enderecoPe = enderecoPe;
    }
    
    public void setTipoOrganizador(char tipoOrganizador) {
        this.tipoOrganizador = tipoOrganizador;
    }
    
    public void setTipoParticipante(char tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }
    
    public void setTipoAutor(char tipoAutor) {
        this.tipoAutor = tipoAutor;
    }
    
    public int getIdPe() {
        return this.idPe;
    }
    
    public String getNomePe() {
        return this.nomePe;
    }
    
    public String getEmailPe() {
        return this.emailPe;
    }
    
    public String getInstituicaoPe() {
        return this.instituicaoPe;
    }
    
    public String getTelefonePe() {
        return this.telefonePe;
    }
    
    public String getNacionalidadePe() {
        return this.nacionalidadePe;
    }
    
    public String getEnderecoPe() {
        return this.enderecoPe;
    }
    
    public char getTipoOrganizador() {
        return this.tipoOrganizador;
    }
    
    public char getTipoParticipante() {
        return this.tipoParticipante;
    }
    
    public char getTipoAutor() {
        return this.tipoAutor;
    }
    
    /**
     * 
     * Insere evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO pessoa VALUES ("
                + "seq_pessoa.NEXTVAL, "
                + "'"+this.nomePe+"', "
                + "'"+this.emailPe+"', ";
        
        if (this.instituicaoPe == null)
            sql += "null, ";
        else
            sql += "'"+this.instituicaoPe+"', ";
        if (this.telefonePe == null)
            sql += "null, ";
        else
            sql += "'"+this.telefonePe+"', ";
        if (this.nacionalidadePe == null)
            sql += "null, ";
        else
            sql += "'"+this.nacionalidadePe+"', ";
        if (this.enderecoPe == null)
            sql += "null, ";
        else
            sql += "'"+this.enderecoPe+"', ";
  
        sql += "'"+this.tipoOrganizador+"', '"+this.tipoParticipante+"', '"+this.tipoAutor+"')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza pessoa no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE pessoa SET nomePe = '"+this.nomePe+"', emailPe = '"+this.emailPe+"', ";
        
        sql += "instituicaoPe = ";
        if (this.instituicaoPe == null)
            sql += "null, ";
        else
            sql += "'"+this.instituicaoPe+"', ";
        
        sql += "telefonePe = ";
        if (this.telefonePe == null)
            sql += "null, ";
        else
            sql += "'"+this.telefonePe+"', ";
        
        sql += "nacionalidadePe = ";
        if (this.nacionalidadePe == null)
            sql += "null, ";
        else
            sql += "'"+this.nacionalidadePe+"', ";
        
        sql += "enderecoPe = ";
        if (this.enderecoPe == null)
            sql += "null, ";
        else
            sql += "'"+this.enderecoPe+"', ";
        
        sql += "tipoOrganizador = '"+this.tipoOrganizador+"', "
                + "tipoParticipante = '"+this.tipoParticipante+"', "
                + "tipoAutor = '"+this.tipoAutor+"' "
                + "WHERE idPe = "+this.idPe+"";
        
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
     /**
     * 
     * Remove pessoa no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE idPe = "+this.idPe;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca pessoa pela chave primária
     * 
     * @param db
     * @param primaryKey
     * @return 
     * @throws SQLException
     */
    public static People findByPrimaryKey(DbConnection db, int primaryKey) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa WHERE idPe = "+primaryKey;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return People.next(db.query(sql));        
    }
    
    /**
     * 
     * Busca todas as pessoas
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa ORDER BY nomePe, instituicaoPe, nacionalidadePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca as pessoas pelo nome
     * 
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa nomePe, instituicaoPe, nacionalidadePe"
                + "WHERE UPPER(nomePe) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca todos participantes
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findParticipants(DbConnection db) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa WHERE tipoParticipante = 'S'"
                + "ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca todos autores
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findAuthors(DbConnection db) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa WHERE tipoAutor = 'S'"
                + "ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca todos organizadores
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findOrganizers(DbConnection db) throws SQLException {
        String sql = "SELECT idPe, nomePe, emailPe, instituicaoPe, telefonePe, nacionalidadePe, enderecoPe, "
                + "tipoOrganizador, tipoParticipante, tipoAutor FROM pessoa WHERE tipoOrganizador = 'S'"
                + "ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Retorna um a um as pessoas
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static People next(ResultSet rs) throws SQLException {
        People people = null;
        if (rs.next()) {
            people = new People(rs.getInt("idPe"), rs.getString("nomePe"), rs.getString("emailPe"), rs.getString("instituicaoPe"), 
                    rs.getString("telefonePe"), rs.getString("nacionalidadePe"), rs.getString("enderecoPe"),
                    rs.getString("tipoOrganizador").charAt(0), rs.getString("tipoParticipante").charAt(0), 
                    rs.getString("tipoAutor").charAt(0));
        }
        return people;
    }
    
}
