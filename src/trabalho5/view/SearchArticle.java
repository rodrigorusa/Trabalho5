/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import trabalho5.database.Article;
import trabalho5.database.Event;
import trabalho5.database.People;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class SearchArticle extends javax.swing.JFrame {

    private final int type;
    
    /**
     * Creates new form SearchArticle
     * 
     * @param type
     * @param name
     */
    public SearchArticle(int type, String name) {
        this.type = type;
        initComponents();
        
        // imprime os artigos
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        try {
            ResultSet rs;
            // SELECT ALL
            if(name.isEmpty())
                rs = Article.findAll(MainFrame.db);
            // SELECT By Name
            else
                rs = Article.findByName(MainFrame.db, name);
            Article a = Article.next(rs);
            while(a != null) {
                // pega o evento
                Event e = Event.findByPrimaryKey(MainFrame.db, a.getCodEv());
                // pega o apresentador
                People p = People.findByPrimaryKey(MainFrame.db, a.getIdApr());
                // adiciona uma linha na tabela
                model.addRow(new Object[] {a.getIdArt(), a.getTituloArt(), a.getDataApresArt(), a.getHoraApresArt(),
                    e.getNomeEv(), a.getNumEd(), p.getNomePe()});
                a = Article.next(rs);
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
        setTitle("Artigos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Título", "Data de Apresentação", "Horário de Apresentação", "Evento", "Edição", "Apresentador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(5);
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
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addComponent(jButton1)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
     * Inicia a tela de atualização ou remoção de artigo
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Pega o artigo selecionado
        int i = this.jTable1.getSelectedRow();
        try {
            // pega o código do evento
            ResultSet rs = Event.findByName(MainFrame.db, (String) this.jTable1.getValueAt(i, 4));
            Event e = Event.next(rs);
            int codEv = e.getCodEv();
            // pega o id do apresentador
            rs = People.findByName(MainFrame.db, (String) this.jTable1.getValueAt(i, 6));
            People p = People.next(rs);
            int idApr = p.getIdPe();

            Article a = new Article((int) this.jTable1.getValueAt(i, 0), (String) this.jTable1.getValueAt(i, 1), 
                (String) this.jTable1.getValueAt(i, 2), (String) this.jTable1.getValueAt(i, 3), codEv, 
                (int) this.jTable1.getValueAt(i, 5), idApr);

            // busca os autores
            if (this.type == CRUDType.SEARCH) {
                // inicia a interface de impressão dos autores do artigo selecionado
                Authors authors = new Authors(a);
                authors.setVisible(true);
            }

            // atualização de artigo
            if (this.type == CRUDType.UPDATE) {
                // inicia a interface de atualização
                UpdateArticle updateArticle = new UpdateArticle(a);
                updateArticle.setVisible(true);
                this.dispose();
            }
            // remoção de artigo
            if (this.type == CRUDType.REMOVE) {
                // inicia a interface de remoção
                RemoveArticle removeArticle = new RemoveArticle(a);
                removeArticle.setVisible(true);
                this.dispose();
            }
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
