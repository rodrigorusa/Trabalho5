/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.database;

import trabalho5.view.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class Report {
    
    /**
     * 
     * Gera o relatório executando o procedimento
     * 
     * @param db
     * @throws SQLException
     */
    public static void generate(DbConnection db) throws SQLException {
        String sql = "{call relatorio_eventos}";
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        db.query(sql);
    }
    
    /**
     * 
     * SELECT ALL events na view do relatório geral
     * 
     * @param db
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAll(DbConnection db) throws SQLException {
        String sql = "SELECT * FROM relatorio";
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT ALL editions na view do relatório geral
     * 
     * @param db
     * @param codEv
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewAllEditions(DbConnection db, int codEv) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE codEv = "+codEv;
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
    /**
     * 
     * SELECT na view do relatório geral por evento e edição
     * 
     * @param db
     * @param codEv
     * @param numEd
     * @return 
     * @throws SQLException 
     */
    public static ResultSet findViewByEventAndEdition(DbConnection db, int codEv, int numEd) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE codEv = "+codEv+" AND numEd = "+numEd;
        // debugg
        if (MainFrame.debugg)
            System.out.println(sql);
        return db.query(sql);
    }
    
}
