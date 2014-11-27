/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view.expense;

import trabalho5.database.Expense;
import trabalho5.view.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;
import trabalho5.database.Event;
import trabalho5.database.Sponsorship;

/**
 *
 * @author Rodrigo
 */
public class SearchExpense extends javax.swing.JFrame {

    private final int type;
    private final ArrayList<String> cnpjs;
    
    /**
     * Creates new form SearchExpense
     * 
     * @param type
     * @param name
     */
    public SearchExpense(int type, String name) {
        this.type = type;
        this.cnpjs = new ArrayList();
        initComponents();
        
        // imprime as despesas
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if (name.isEmpty())
                rs = Expense.findViewAll(MainFrame.db);
            // SELECT By Name
            else
                rs = Expense.findViewByName(MainFrame.db, name);
            
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            
            while(rs.next()) {
                // armazena os cnpjs dos patrocinadores
                this.cnpjs.add(rs.getString("cnpjPat"));
                // adiciona uma linha na tabela
                model.addRow(new Object[] {rs.getInt("codDesp"), rs.getString("nomeEv"), rs.getInt("numEd"), 
                    rs.getString("razaoSocialPat"), rs.getString("dataDesp"), nf.format(rs.getDouble("valorDesp")),
                    rs.getString("descricaoDesp")});
            }
            // fecha o cursor
            MainFrame.db.close();
            
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Despesas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Evento", "Ediçao", "Patrocinador", "Data da Despesa", "Valor da Despesa", "Descrição da Despesa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(55);
            jTable1.getColumnModel().getColumn(2).setMinWidth(50);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setMinWidth(70);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(4).setMinWidth(110);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(5).setMinWidth(110);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Inicia a tela de atualização ou remoção de despesa
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (this.type != CRUDType.SEARCH) {
            // pega a despesa selecionada
            int i = this.jTable1.getSelectedRow();
            try {
                // pega o código do evento
                ResultSet rs = Event.findByName(MainFrame.db, (String) this.jTable1.getValueAt(i, 1));
                Event ev = Event.next(rs);
                int codEv = ev.getCodEv();
                // fecha o cursor
                MainFrame.db.close();
                
                // pega a edição
                int numEd = (int) this.jTable1.getValueAt(i, 2);
                
                // pega o patrocínio
                Sponsorship ss = Sponsorship.findByPrimaryKey(MainFrame.db, this.cnpjs.get(i), codEv, numEd);

                Expense e = new Expense((int) this.jTable1.getValueAt(i, 0), codEv, numEd, ss.getCnpjPat(), ss.getCodEv(),
                        ss.getNumEd(), (String) this.jTable1.getValueAt(i, 4), (double) this.jTable1.getValueAt(i, 5), 
                        (String) this.jTable1.getValueAt(i, 6));
                
                // inicia a interface de atualização
                if (this.type == CRUDType.UPDATE) {
                    UpdateExpense updateExpense = new UpdateExpense(e);
                    updateExpense.setVisible(true);
                    this.dispose();
                }
                // inicia a interface de remoção
                if (this.type == CRUDType.REMOVE) {
                    RemoveExpense removeExpense = new RemoveExpense(e);
                    removeExpense.setVisible(true);
                    this.dispose();
                }
                
            } catch(SQLException e) {
                Message msg = new Message(this, true, e.getMessage());
                msg.setTitle("Erro");
                msg.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
