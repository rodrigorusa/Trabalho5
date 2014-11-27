/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view.people;

import trabalho5.database.People;
import trabalho5.view.*;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class SearchPeople extends javax.swing.JFrame {

    private final int type;
    
    /**
     * Creates new form SearchPeople
     * 
     * @param type
     * @param name
     */
    public SearchPeople(int type, String name) {
        this.type = type;
        initComponents();
        
        // imprime as pessoas
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if (name.isEmpty())
                rs = People.findAll(MainFrame.db);
            // SELECT BY Name
            else 
                rs = People.findByName(MainFrame.db, name);
            People p = People.next(rs);
            while (p != null) {
                // adiciona uma linha na tabela
                String type_organizer = "Não";
                String type_participant = "Não";
                String type_author = "Não";
                if (p.getTipoOrganizador() == 'S')
                    type_organizer = "Sim";
                if (p.getTipoParticipante() == 'S')
                    type_participant = "Sim";
                if (p.getTipoAutor() == 'S')
                    type_author = "Sim";
                model.addRow(new Object[]{p.getIdPe(), p.getNomePe(), p.getEmailPe(), p.getInstituicaoPe(), p.getTelefonePe(),
                    p.getNacionalidadePe(), p.getEnderecoPe(), type_organizer, type_participant, type_author});
                p = People.next(rs);
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
        setTitle("Pessoas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Email", "Instituição", "Telefone", "Nacionalidade", "Endereço", "Organizador", "Participante", "Autor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setMinWidth(20);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(7).setMinWidth(80);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(8).setMinWidth(80);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(9).setMinWidth(70);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(80);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Inicia a tela de atualização ou remoção de pessoa
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // pega a pessoa selecionada
        int i = this.jTable1.getSelectedRow();
        People p = new People((int) this.jTable1.getValueAt(i, 0), (String) this.jTable1.getValueAt(i, 1),
            (String) this.jTable1.getValueAt(i, 2), (String) this.jTable1.getValueAt(i, 3), 
            (String) this.jTable1.getValueAt(i, 4), (String) this.jTable1.getValueAt(i, 5), 
            (String) this.jTable1.getValueAt(i, 6), (char) this.jTable1.getValueAt(i, 7), (char) this.jTable1.getValueAt(i, 8),
            (char) this.jTable1.getValueAt(i, 9));
        // atualização de pessoa
        if (this.type == CRUDType.UPDATE) {
            // inicia a interface de atualização
            UpdatePeople updatePeople = new UpdatePeople(p);
            updatePeople.setVisible(true);
            this.dispose();
        }
        // remoção de pessoa
        if (this.type == CRUDType.REMOVE) {
            // inicia a interface de remoção
            RemovePeople removePeople = new RemovePeople(p);
            removePeople.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
