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
public class Edition {
    
    private int codEv;
    private int numEd;
    private String descricaoEd;
    private String dataInicioEd;
    private String dataFimEd;
    private String localEd;
    private double taxaEd;
    private double saldoFinanceiroEd;
    private int qtdArtigosApresentadosEd;
    
    public Edition(int codEv, int numEd, String descricaoEd, String dataInicioEd, String dataFimEd, String localEd,
            double taxaEd, double saldoFinanceiroEd, int qtdArtigosApresentadosEd) {
        this.codEv = codEv;
        this.numEd = numEd;
        this.descricaoEd = descricaoEd;
        this.dataInicioEd = dataInicioEd;
        this.dataFimEd = dataFimEd;
        this.localEd = localEd;
        this.taxaEd = taxaEd;
        this.saldoFinanceiroEd = saldoFinanceiroEd;
        this.qtdArtigosApresentadosEd = qtdArtigosApresentadosEd;
    }
    
    public void setCodEv(int codEv) {
        this.codEv = codEv;
    }
    
    public void setNumEd(int numEd) {
        this.numEd = numEd;
    }
    
    public void setDescricaoEd(String descricaoEd) {
        this.descricaoEd = descricaoEd;
    }
    
    public void setDataInicioEd(String dataInicioEd) {
        this.dataInicioEd = dataInicioEd;
    }
    
    public void setDataFimEd(String dataFimEd) {
        this.dataFimEd = dataFimEd;
    }
    
    public void setLocalEd(String localEd) {
        this.localEd = localEd;
    }
    
    public void setTaxaEd(double taxaEd) {
        this.taxaEd = taxaEd;
    }
    
    public void setSaldoFinanceiroEd(double saldoFinanceiroEd) {
        this.saldoFinanceiroEd = saldoFinanceiroEd;
    }
    
    public void setQtdArtigosApresentadosEd(int qtdArtigosApresentadosEd) {
        this.qtdArtigosApresentadosEd = qtdArtigosApresentadosEd;
    }
    
    public int getCodEv() {
        return this.codEv;
    }
    
    public int getNumEd() {
        return this.numEd;
    }
    
    public String getDescricaoEd() {
        return this.descricaoEd;
    }
    
    public String getDataInicioEd() {
        return this.dataInicioEd;
    }
    
    public String getDataFimEd() {
        return this.dataFimEd;
    }
    
    public String getLocalEd() {
        return this.localEd;
    }
    
    public double getTaxaEd() {
        return this.taxaEd;
    }
    
    public double getSaldoFinanceiroEd() {
        return this.saldoFinanceiroEd;
    }
    
    public int getQtdArtigosApresentadosEd() {
        return this.qtdArtigosApresentadosEd;
    }
    
    /**
     * 
     * Insere evento no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void insert(DbConnection db) throws SQLException {
        String sql = "INSERT INTO edicao VALUES ("
                + this.codEv+", "
                + this.numEd+", ";
        
        if (this.descricaoEd == null)
            sql += "null, ";
        else
            sql += "'"+this.descricaoEd+"', ";
        
        sql += "to_date('"+this.dataInicioEd+"', 'dd/mm/yyyy'), "
                + "to_date('"+this.dataFimEd+"', 'dd/mm/yyyy'), "
                + "'"+this.localEd+"', "
                + this.taxaEd+", "
                + this.saldoFinanceiroEd+", "
                + this.qtdArtigosApresentadosEd+")";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Atualiza edição no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void update(DbConnection db) throws SQLException {
        String sql = "UPDATE edicao SET descricaoEd = ";
                
        if (this.descricaoEd == null)
            sql += "null, ";
        else
            sql += "'"+this.descricaoEd+"', ";
        
       sql += "dataInicioEd = to_date('"+this.dataInicioEd+"', 'dd/mm/yyyy'), "
               + "dataFimEd = to_date('"+this.dataFimEd+"', 'dd/mm/yyyy'), "
               + "localEd = '"+this.localEd+"', "
               + "taxaEd = "+this.taxaEd+" "
               + "WHERE codEv = '"+this.codEv+"' AND numEd = '"+this.numEd+"'";
       // debugg 
       if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Remove edição no banco de dados
     * 
     * @param db
     * @throws SQLException
     */
    public void remove(DbConnection db) throws SQLException {
        String sql = "DELETE FROM edicao WHERE codEv = "+this.codEv+" AND numEd = "+this.numEd ;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        db.execute(sql);
    }
    
    /**
     * 
     * Busca todas as edições
     * 
     * @param db
     * @return 
     * @throws SQLException
     */
    public static ResultSet findAll(DbConnection db) throws SQLException {
        String sql = "SELECT codEv, numEd, descricaoEd, to_char(dataInicioEd, 'DD/MM/YYYY') AS \"DataInicioEd\", "
                + "to_char(dataFimEd, 'DD/MM/YYYY') AS \"DataFimEd\", localEd, "
                + "taxaEd, "
                + "saldoFinanceiroEd, "
                + "qtdArtigosApresentadosEd FROM edicao";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca as edições pelo evento
     * 
     * 
     * @param db
     * @param ev
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByEvent(DbConnection db, Event ev) throws SQLException {
        String sql = "SELECT codEv, numEd, descricaoEd, to_char(dataInicioEd, 'DD/MM/YYYY') AS \"DataInicioEd\", "
                + "to_char(dataFimEd, 'DD/MM/YYYY') AS \"DataFimEd\", localEd, "
                + "taxaEd, "
                + "saldoFinanceiroEd, "
                + "qtdArtigosApresentadosEd FROM edicao "
                + "WHERE codEv = "+ev.getCodEv();
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca as edições pelo local
     * 
     * @param db
     * @param local
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByLocal(DbConnection db, String local) throws SQLException {
        String sql = "SELECT codEv, numEd, descricaoEd, to_char(dataInicioEd, 'DD/MM/YYYY') AS \"DataInicioEd\", "
                + "to_char(dataFimEd, 'DD/MM/YYYY') AS \"DataFimEd\", localEd, "
                + "taxaEd, "
                + "saldoFinanceiroEd, "
                + "qtdArtigosApresentadosEd FROM edicao "
                + "WHERE UPPER(localEd) LIKE UPPER('%"+local+"%')";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca as edições pelo nome do evento
     * 
     * 
     * @param db
     * @param nomeEv
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findByEvent(DbConnection db, String nomeEv) throws SQLException {
        String sql = "SELECT codEv, numEd, descricaoEd, to_char(dataInicioEd, 'DD/MM/YYYY') AS \"DataInicioEd\", "
                + "to_char(dataFimEd, 'DD/MM/YYYY') AS \"DataFimEd\", localEd, "
                + "taxaEd, "
                + "saldoFinanceiroEd, "
                + "qtdArtigosApresentadosEd FROM edicao NATURAL JOIN evento "
                + "WHERE nomeEv = '"+nomeEv+"'";
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * Busca as edição pela chave primária
     * 
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @return 
     * @throws SQLException 
     */
    public static Edition findByPrimaryKey(DbConnection db, int codEv, int numEd) throws SQLException {
        String sql = "SELECT codEv, numEd, descricaoEd, to_char(dataInicioEd, 'DD/MM/YYYY') AS \"DataInicioEd\", "
                + "to_char(dataFimEd, 'DD/MM/YYYY') AS \"DataFimEd\", localEd, "
                + "taxaEd, "
                + "saldoFinanceiroEd, "
                + "qtdArtigosApresentadosEd FROM edicao "
                + "WHERE codEv = "+codEv+" AND numEd = "+numEd;
        // debugg
        if(MainFrame.debugg)
            System.out.println(sql);
        return Edition.next(db.query(sql));
    }
    
    /**
     * 
     * Retorna um a um as edições
     * 
     * @param rs
     * @return 
     * @throws SQLException
     */
    public static Edition next(ResultSet rs) throws SQLException {
        Edition edition = null;
        if (rs.next()) {
            edition = new Edition(rs.getInt("codEv"), rs.getInt("numEd"), rs.getString("descricaoEd"), rs.getString("dataInicioEd"),
                rs.getString("dataFimEd"), rs.getString("localEd"), rs.getDouble("taxaEd"), rs.getDouble("saldoFinanceiroEd"),
                rs.getInt("qtdArtigosApresentadosEd"));
        }
        return edition;
    }
    
}
