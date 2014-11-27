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
public class Registered {
    
    private int codEv;
    private int numEd;
    private int idPart;
    private String dataInsc;
    private char tipoApresentador;
    
     public Registered(int codEv, int numEd, int idPart) {
        this.codEv = codEv;
        this.numEd = numEd;
        this.idPart = idPart;
    }
    
    public Registered(int codEv, int numEd, int idPart, String dataInsc, char tipoApresentador) {
        this.codEv = codEv;
        this.numEd = numEd;
        this.idPart = idPart;
        this.dataInsc = dataInsc;
        this.tipoApresentador = tipoApresentador;
    }

    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }

    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public void setDataInsc(String dataInsc) {
        this.dataInsc = dataInsc;
    }

    public void setTipoApresentador(char tipoApresentador) {
        this.tipoApresentador = tipoApresentador;
    }
    
    public int getCodEv() {
        return codEv;
    }

    public int getNumEd() {
        return numEd;
    }

    public int getIdPart() {
        return idPart;
    }

    public String getDataInsc() {
        return dataInsc;
    }

    public char getTipoApresentador() {
        return tipoApresentador;
    }
    
    /**
     * 
     * Insere inscrito no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO inscrito VALUES ("
                + this.codEv+", "
                + this.numEd+", "
                + this.idPart+", "
                + "to_date(SYSDATE, 'dd/mm/yyyy'), "
                + "'N')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza inscrito no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE inscrito SET tipoApresentador = '"+this.tipoApresentador+"' "
                + "WHERE codEv = "+this.codEv+" AND numEd = "+this.numEd+" AND idPart = "+this.idPart;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove inscrito no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM inscrito "
                + "WHERE codEv = "+this.codEv+" AND numEd = "+this.numEd+" AND idPart = "+this.idPart;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca todos os inscritos
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca inscritos pelo nome
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito JOIN pessoa ON (idPart = idPe)"
                + " WHERE UPPER(nomePe) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca inscritos pelo evento e edição
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @param order
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByEventAndEdition(DbConnection db, int codEv, int numEd, boolean order) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, nomePe, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito JOIN pessoa ON (idPart = idPe)"
                + " WHERE codEv = "+codEv+" AND numEd = "+numEd;
        // ordena pelo nome
        if(order)
            sql += " ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca inscritos autores pelo evento e edição
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @param order
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByEventAndEditionAndAuthors(DbConnection db, int codEv, int numEd, boolean order) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, nomePe, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito JOIN pessoa ON (idPart = idPe)"
                + " WHERE codEv = "+codEv+" AND numEd = "+numEd+" AND tipoAutor = 'S'";
        // ordena pelo nome
        if(order)
            sql += " ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * Busca inscritos pela chave primária
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @param idPart
     * @return 
     * @throws SQLException 
     */
    public static Registered findByPrimaryKey(DbConnection db, int codEv, int numEd, int idPart) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito"
                + " WHERE codEv = "+codEv+" AND numEd = "+numEd+" AND idPart = "+idPart;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return Registered.next(db.query(sql));
    }
    
    /**
     * 
     * Busca os inscritos apresentadores
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findPresenters(DbConnection db) throws SQLException {
        String sql = "SELECT codEv, numEd, idPart, nomePe, to_char(dataInsc, 'dd/mm/yyyy') AS \"dataInsc\","
                + " tipoApresentador FROM inscrito JOIN pessoa ON (idPart = idPe)"
                + " WHERE tipoApresentador = 'S'"
                + " ORDER BY nomePe";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Retorna um a um os inscritos
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Registered next(ResultSet rs) throws SQLException {
        Registered registered = null;
        if (rs.next()) {
            registered = new Registered(rs.getInt("codEv"), rs.getInt("numEd"), rs.getInt("idPart"), rs.getString("dataInsc"), 
                    rs.getString("tipoApresentador").charAt(0));
        }
        return registered;
    }
   
    /**
     * 
     * SELECT ALL na view de inscritos
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM view_inscritos";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
     /**
     * 
     * SELECT By Name na view de inscritos
     * 
     * @param db
     * @param name
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByName(DbConnection db, String name) throws SQLException {
        String sql = "SELECT * FROM view_inscritos WHERE UPPER(nomePe) LIKE UPPER('%"+name+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT By evento e edição na view de inscritos
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByEventAndEdition(DbConnection db, int codEv, int numEd) throws SQLException {
        String sql = "SELECT * FROM view_inscritos WHERE codEv = "+codEv+" AND numEd = "+numEd;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT By evento na view de inscritos
     * 
     * @param db
     * @param codEv
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByEvent(DbConnection db, int codEv) throws SQLException {
        String sql = "SELECT * FROM view_inscritos WHERE codEv = "+codEv;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
