/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view.article;

import trabalho5.database.Event;
import trabalho5.database.Edition;
import trabalho5.view.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class SearchArticleByName extends javax.swing.JFrame {

    private final int type;
    protected static final int EVENTANDEDITION = 0;
    protected static final int NAME = 1;
    
    /**
     * Creates new form SearchArticleByName
     *
     * @param type
     */
    public SearchArticleByName(int type) {
        this.type = type;
        initComponents();
        
        // busca todos os eventos
        try {
            ResultSet rs = Event.findAll(MainFrame.db);
            Event e = Event.next(rs);
            while(e != null) {
                this.jComboBox2.addItem(e.getNomeEv());
                e = Event.next(rs);
            }
            // adiciona a opção todos os eventos (SELECT ALL)
            this.jComboBox2.addItem("Todos");
            
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

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Artigo");

        jTextField1.setEnabled(false);

        jLabel1.setText("Título do Artigo");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar por");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Evento e edição", "Título" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Evento");

        jComboBox2.setEnabled(false);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Edição");

        jComboBox3.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
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
     * Buscar
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // nenhum filtro selecionado
        if (this.jComboBox1.getSelectedItem().equals("")) {
            Message msg = new Message(this, true, "Nenhum filtro selecionado.");
            msg.setTitle("Erro");
            msg.setVisible(true);
            return;
        }
        
        SearchArticle searchArticle;
        // buscar por evento e edição
        if (this.jComboBox1.getSelectedItem().equals("Evento e edição"))
            searchArticle = new SearchArticle(this.type, SearchArticleByName.EVENTANDEDITION, 
                    (String) this.jComboBox2.getSelectedItem(), (String) this.jComboBox3.getSelectedItem());
        // buscar por nome
        else
            searchArticle = new SearchArticle(this.type, SearchArticleByName.NAME, 
                    (String) this.jTextField1.getText(), null);
        
        searchArticle.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Filtro
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (this.jComboBox1.getSelectedItem().equals("Evento e edição")) {
            if (this.jTextField1.isEnabled())
                this.jTextField1.setEnabled(false);
            if (!this.jComboBox2.isEnabled())
                this.jComboBox2.setEnabled(true);
            if (!this.jComboBox3.isEnabled())
                this.jComboBox3.setEnabled(true);
        }
        else if (this.jComboBox1.getSelectedItem().equals("Título")) {
            if (this.jComboBox2.isEnabled())
                this.jComboBox2.setEnabled(false);
            if (this.jComboBox3.isEnabled())
                this.jComboBox3.setEnabled(false);
            if (!this.jTextField1.isEnabled())
                this.jTextField1.setEnabled(true);
        }
        else { 
            if (this.jComboBox2.isEnabled())
                this.jComboBox2.setEnabled(false);
            if (this.jComboBox3.isEnabled())
                this.jComboBox3.setEnabled(false);
            if (this.jTextField1.isEnabled())
                this.jTextField1.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Buscar as edições
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // remove os itens
        this.jComboBox3.removeAllItems();
        // pega o nome do evento selecionado
        String event_name = (String) this.jComboBox2.getSelectedItem();
        try {
            // pega as edições do evento
            ResultSet rs = Edition.findByEvent(MainFrame.db, event_name);
            Edition ed = Edition.next(rs);
            while(ed != null) {
                String info = ed.getNumEd() + " de " + ed.getDataInicioEd() + " a " + ed.getDataFimEd()
                        + " em " + ed.getLocalEd();
                this.jComboBox3.addItem(info);
                ed = Edition.next(rs);
            }   
            // adiciona a opção de todas as edições
            this.jComboBox3.addItem("Todas");
            // fecha o cursor
            MainFrame.db.close();
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
