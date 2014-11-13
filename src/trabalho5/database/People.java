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
    
    public People(int idPe, String nomePe, String emailPe, String instituicaoPe, String telefonePe, 
            String nacionalidadePe, String enderecoPe, char tipoOrganizador, char tipoParticipante, char tipoAutor) {
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
                + "nomePe = '"+this.nomePe+"', "
                + "emailPe = '"+this.emailPe+"', "
                + "instituicaoPe = '"+this.instituicaoPe+"', "
                + "telefonePe = '"+this.telefonePe+"', "
                + "nacionalidadePe = '"+this.nacionalidadePe+"', "
                + "enderecoPe = '"+this.enderecoPe+"', "
                + "tipoOrganizador = '"+this.tipoOrganizador+"', "
                + "tipoParticipante = '"+this.tipoParticipante+"', "
                + "tipoAutor = '"+this.tipoAutor+"' "
                +")";
        System.out.println(sql);
        db.execute(sql);
    }
    
}
