/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Rodrigo
 */
public class DbConnection implements Config {
    
    private final Connection conn;
    private Statement stmt;
    
    /**
     *
     * Efetua a conexão com o banco de dados
     * 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public DbConnection() throws ClassNotFoundException, SQLException { 
        Class.forName(driver);
        this.conn = DriverManager.getConnection(url, username, password);
    }
    
    /**
     *
     * Fecha a conexão com o banco de dados
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        this.conn.close();
    }
    
    /**
     *
     * Efetua o commit no banco de dados
     * 
     * @throws SQLException
     */
    public void commit() throws SQLException {
        this.conn.commit();
    }
    
    /**
     *
     * Efetua o rollback no banco de dados
     * 
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        this.conn.rollback();
    }
    
    /**
     * 
     * Executa um comando sql
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean execute(String sql) throws SQLException {
        this.stmt = this.conn.createStatement();
        boolean result = this.stmt.execute(sql);
        this.stmt.close();
        return result;
    }
    
    /**
     * 
     * Executa um procedimento sql
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean process(String sql) throws SQLException {
        CallableStatement cs = this.conn.prepareCall(sql);
        boolean result = cs.execute();
        return result;
    }
     
    /**
     * 
     * Executa uma query sql
     * 
     * @param sql
     * @return 
     * @throws SQLException
     */
    public ResultSet query(String sql) throws SQLException {
        this.stmt = this.conn.createStatement();
        return this.stmt.executeQuery(sql);
    }
    
    /**
     * 
     * Fecha o Statement
     * 
     * @throws SQLException
     */
    public void close() throws SQLException {
        this.stmt.close();
    }
    
}
