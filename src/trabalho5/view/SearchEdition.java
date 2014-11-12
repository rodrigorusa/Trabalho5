/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import trabalho5.database.DbConnection;
import trabalho5.database.Edition;
import trabalho5.database.Event;

/**
 *
 * @author Rodrigo
 */
public class SearchEdition extends javax.swing.JFrame {

    protected DbConnection db;
    private final int type;
    
    /**
     * Creates new form SearchEdition
     * 
     * @param db
     * @param type
     * @param option
     */
    public SearchEdition(DbConnection db, int type, String option) {
        this.db = db;
        this.type = type;
        initComponents();
        
        // Imprime todos os eventos
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if (option.equals("Todos"))
                rs = Edition.findAll(this.db);
            // SELECT BY Event
            else {
                // pega o evento selecionado
                rs = Event.findByName(this.db, option);
                Event ev = Event.next(rs);
                rs = Edition.findByEvent(this.db, ev);
            }    
            Edition ed = Edition.next(rs);
            while (ed != null) {
                // adiciona uma linha na tabela
                model.addRow(new Object[]{ed.getCodEv(), ed.getNumEd(), ed.getDescricaoEd(), ed.getDataInicioEd(), 
                    ed.getDataFimEd(), ed.getLocalEd(), ed.getTaxaEd(), ed.getSaldoFinanceiroEd(), 
                    ed.getQtdArtigosApresentadosEd()});
                ed = Edition.next(rs);
            }
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edições");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código do Evento", "Número da Edição", "Descrição", "Data de Início", "Data de Fim", "Local", "Taxa", "Saldo", "Artigos Apresentados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(336, 336, 336))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Voltar
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
