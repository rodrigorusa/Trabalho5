/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import trabalho5.database.Edition;
import trabalho5.database.Event;
import trabalho5.database.People;
import trabalho5.database.Registered;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class InsertRegistered extends javax.swing.JFrame {
    
    private final ArrayList<People> participants;
    
    /**
     * Creates new form InsertRegistered
     * 
     */
    public InsertRegistered() {
        this.participants = new ArrayList();
        initComponents();
        
        // busca todos os eventos
        try {
            ResultSet rs = Event.findAll(MainFrame.db);
            Event e = Event.next(rs);
            while(e != null) {
                this.jComboBox1.addItem(e.getNomeEv());
                e = Event.next(rs);
            }
            // fecha o cursor
            MainFrame.db.close();
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
        
        // busca participantes
        try {
            ResultSet rs = People.findParticipants(MainFrame.db);
            People p = People.next(rs);
            while(p != null) {
                // adiciona no ComboBox
                this.jComboBox3.addItem(p.getNomePe());
                // adiciona no array
                this.participants.add(p);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Inscrito");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Evento*");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cadastrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Edição*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Participante*");

        jComboBox3.setAutoscrolls(true);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("* campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cancelar
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Buscar edições
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // remove os itens
        this.jComboBox2.removeAllItems();
        // pega o nome do evento selecionado
        String event_name = (String) this.jComboBox1.getSelectedItem();
        try {
            // pega as edições do evento
            ResultSet rs = Edition.findByEvent(MainFrame.db, event_name);
            Edition ed = Edition.next(rs);
            while(ed != null) {
                String info = ed.getNumEd() + " de " + ed.getDataInicioEd() + " a " + ed.getDataFimEd()
                        + " em " + ed.getLocalEd();
                this.jComboBox2.addItem(info);
                ed = Edition.next(rs);
            }   
            // fecha o cursor
            MainFrame.db.close();
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Cadastrar inscrito
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // pega os itens selecionados
            String eventName = (String) this.jComboBox1.getSelectedItem();
            String editionName = (String) this.jComboBox2.getSelectedItem();
            int people_index = this.jComboBox3.getSelectedIndex();
            
            // campos obrigatórios não preenchidos
            if (eventName == null || editionName == null || people_index == -1) {
                Message msg = new Message(this, true, "Campos obrigatórios não preenchidos.");
                msg.setTitle("Erro");
                msg.setVisible(true);
            } else {

                // pega o evento selecionado
                ResultSet rs = Event.findByName(MainFrame.db, eventName);
                Event ev = Event.next(rs);
                int codEv = ev.getCodEv();
                // fecha o cursor
                MainFrame.db.close();

                // pega a edição selecionada
                String[] parts = editionName.split(" ");
                int numEd = Integer.valueOf(parts[0]).intValue();

                // pega a pessoa selecionada
                People p = this.participants.get(people_index);
                int idPart = p.getIdPe();

                // insere inscrito
                Registered registered = new Registered(codEv, numEd, idPart);
                registered.insert(MainFrame.db);
                new Message(this, true, "Inscrito cadastrado.").setVisible(true);
                this.dispose();
            }
            
        } catch(SQLException e) {
            String error;
            if (e.getErrorCode() == 1)
                error = "Participante já inscrito.";
            else
                error = e.getMessage();
            Message msg = new Message(this, true, error);
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
