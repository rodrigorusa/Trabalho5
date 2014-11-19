/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import trabalho5.database.Registered;
import trabalho5.database.Event;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class SearchRegistered extends javax.swing.JFrame {

    private final int type;
    private final ArrayList<Integer> ids;
    
    /**
     * Creates new form SearchRegistered
     * 
     * @param type
     * @param name
     */
    public SearchRegistered(int type, String name) {
        this.type = type;
        this.ids = new ArrayList();
        initComponents();
        
        // imprime os inscritos
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if (name.isEmpty())
                rs = Registered.findViewAll(MainFrame.db);
            // SELECT By Name
            else
                rs = Registered.findViewByName(MainFrame.db, name);
            while(rs.next()) {
                // armazena o id da pessoa no array
                this.ids.add(rs.getInt("idPe"));
                // adiciona uma linha na tabela
                model.addRow(new Object[] {rs.getString("nomeEv"), rs.getInt("numEd"), rs.getString("NomePe"), 
                    rs.getString("dataInsc"), rs.getString("tipoApresentador").charAt(0)});
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inscritos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Evento", "Edição", "Nome do Inscrito", "Data de Inscrição", "Apresentador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
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

    /**
     * Inicia a tela de remoção de inscrito
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // remoção de inscrito
        if(this.type == CRUDType.REMOVE) {
            // pega o inscrito selecionado
            int i = this.jTable1.getSelectedRow();
            try {
                // pega o código do evento
                ResultSet rs = Event.findByName(MainFrame.db, (String) this.jTable1.getValueAt(i, 0));
                Event ev = Event.next(rs);
                // fecha o cursor
                MainFrame.db.close();
                
                // pega o id do inscrito
                int indice_people = this.jTable1.getSelectedRow();
                int idApr = this.ids.get(indice_people);
                
                // pega o objeto a ser removido
                Registered r = new Registered(ev.getCodEv(), (int) this.jTable1.getValueAt(i, 1), idApr, 
                    (String) this.jTable1.getValueAt(i, 3), (char) this.jTable1.getValueAt(i, 4));
                // inicia a interface de remoção
                RemoveRegistered removeRegistered = new RemoveRegistered(r);
                removeRegistered.setVisible(true);
                this.dispose();
                
            } catch(SQLException e) {
                Message msg = new Message(this, true, e.getMessage());
                msg.setTitle("Erro");
                msg.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
