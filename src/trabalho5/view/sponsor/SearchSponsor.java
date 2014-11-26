/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view.sponsor;

import trabalho5.database.Sponsor;
import trabalho5.view.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class SearchSponsor extends javax.swing.JFrame {

    private final int type;
    
    /**
     * Creates new form SearchSponsor
     * 
     * @param type
     * @param name
     */
    public SearchSponsor(int type, String name) {
        this.type = type;
        initComponents();
        
        // imprime os patrocinadores
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if (name.isEmpty())
                rs = Sponsor.findAll(MainFrame.db);
            // SELECT By Name
            else
                rs = Sponsor.findByName(MainFrame.db, name);
            Sponsor s = Sponsor.next(rs);
            while(s != null) {
                // adiciona uma linha na tabela
                model.addRow(new Object[] {s.getCnpjPat(), s.getRazaoSocialPat(), s.getTelefonePat(), s.getEnderecoPat()});
                s = Sponsor.next(rs);
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
        setTitle("Patrocinadores");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNPJ", "Razão Social", "Telefone", "Endereço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Inicia a tela de atualização ou remoção de patrocinador
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // pega o patrocinador selecionado
        int i = this.jTable1.getSelectedRow();
        
        Sponsor s = new Sponsor((String) this.jTable1.getValueAt(i, 0), (String) this.jTable1.getValueAt(i, 1), 
            (String) this.jTable1.getValueAt(i, 2), (String) this.jTable1.getValueAt(i, 3));
        
        // inicia a interface de atualização
        if (this.type == CRUDType.UPDATE) {
            UpdateSponsor updateSponsor = new UpdateSponsor(s);
            updateSponsor.setVisible(true);
            this.dispose();
        }
        // inicia a interface de remoção
        if (this.type == CRUDType.REMOVE) {
            RemoveSponsor removeSponsor = new RemoveSponsor(s);
            removeSponsor.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
