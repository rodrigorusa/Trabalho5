/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5;

import java.sql.SQLException;
import trabalho5.database.DbConnection;

import trabalho5.view.MainFrame;

/**
 *
 * @author Rodrigo
 */
public class Main {

    private static DbConnection db;
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        /**
         * Conecta-se ao banco de dados
         */
        System.out.println("Conectando ao banco de dados ...");
        try {
            db = new DbConnection();
            System.out.println("Conectado com sucesso.");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível carregar o driver.");
        } catch (SQLException e1) {
            System.out.println("Falha ao conectar-se ao banco de dados.");
        }
        
        /**
         * Inicia a interface principal
         */
        new MainFrame(db).setVisible(true);
        
    }
    
}
