/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import trabalho5.database.Event;
import trabalho5.database.Edition;
import trabalho5.database.People;
import trabalho5.database.Registered;
import trabalho5.database.Article;
import trabalho5.database.Write;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;

/**
 *
 * @author Rodrigo
 */
public class InsertArticle extends javax.swing.JFrame {
    
    private final ArrayList<People> authors;
    private ArrayList<Integer> ids_registereds;
    
    /**
     * Creates new form InsertArticle
     * 
     */
    public InsertArticle() {
        this.authors = new ArrayList();
        initComponents();
        
        try {
            // busca todos os eventos
            ResultSet rs = Event.findAll(MainFrame.db);
            Event e = Event.next(rs);
            while(e != null) {
                this.jComboBox1.addItem(e.getNomeEv());
                e = Event.next(rs);
            }
            // fecha o cursor
            MainFrame.db.close();
            
            // busca todos os autores
            Vector<String> authors_name = new Vector();
            rs = People.findAuthors(MainFrame.db);
            People p = People.next(rs);
            while(p != null) {
                // armazena os autores no array
                this.authors.add(p);
                // armazena os nomes dos autores no vetor
                authors_name.add(p.getNomePe());
                p = People.next(rs);
            }
            // insere os nomes dos autores na jList
            this.jList1.setListData(authors_name);
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

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Artigo");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Evento*");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Edição*");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
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

        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("Título*");

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Apresentação");

        jLabel6.setText("Data");

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setText("Hora");

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Apresentador*");

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Autores*");

        jScrollPane2.setViewportView(jList1);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, 0, 175, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
     * Cadastrar artigo
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String eventName = (String) this.jComboBox1.getSelectedItem();
        String editionName = (String) this.jComboBox2.getSelectedItem();
        String tituloArt = this.jTextField1.getText();
       
        String dataApresArt = null;
        if(!this.jFormattedTextField1.getText().equals("  /  /    "))
            dataApresArt = this.jFormattedTextField1.getText();
        
        String horaApresArt = null;
        if(!this.jFormattedTextField2.getText().equals("  :  "))
            horaApresArt = this.jFormattedTextField2.getText();
        
        String presenterName = (String) this.jComboBox3.getSelectedItem();
        int presenter_index = this.jComboBox3.getSelectedIndex();
        
        // campos obrigatórios não preenchidos
        if(eventName == null || editionName == null || tituloArt.isEmpty() || presenterName == null) {
            Message msg = new Message(this, true, "Campos obrigatórios não preenchidos");
            msg.setTitle("Erro");
            msg.setVisible(true);
            return;
        }
        try {
            // pega o evento selecionado
            ResultSet rs = Event.findByName(MainFrame.db, eventName);
            Event e = Event.next(rs);
            int codEv = e.getCodEv();
            // fecha o cursor
            MainFrame.db.close();

            // pega a edição selecionada
            String[] parts = editionName.split(" ");
            int numEd = Integer.valueOf(parts[0]).intValue();

            // se a data não é nula
            if (dataApresArt != null) {
                Edition ed = Edition.findByPrimaryKey(MainFrame.db, codEv, numEd);
                // fecha o cursor
                MainFrame.db.close();

                // verifica se a data de apresentação do artigo está contida na data da edição do evento
                String parts_artigo[] = dataApresArt.split("/");
                String parts_inicio[] = ed.getDataInicioEd().split("/");
                String parts_fim[] = ed.getDataFimEd().split("/");
                
                Date data_artigo = new Date(Integer.parseInt(parts_artigo[2]), Integer.parseInt(parts_artigo[1]), 
                        Integer.parseInt(parts_artigo[0]));
                Date data_inicio = new Date(Integer.parseInt(parts_inicio[2]), Integer.parseInt(parts_inicio[1]), 
                        Integer.parseInt(parts_inicio[0]));
                Date data_fim = new Date(Integer.parseInt(parts_fim[2]), Integer.parseInt(parts_fim[1]), 
                        Integer.parseInt(parts_fim[0]));

                if (data_artigo.compareTo(data_inicio) < 0 || data_artigo.compareTo(data_fim) > 0) {
                    Message msg = new Message(this, true, "Data de apresentação inválida.");
                    msg.setTitle("Erro");
                    msg.setVisible(true);
                    return;
                }
            }
 
            // pega o apresentador selecionado
            int idApr = this.ids_registereds.get(presenter_index);

            // insere o artigo
            Article article = new Article(tituloArt, dataApresArt, horaApresArt, codEv, numEd, idApr);
            article.insert(MainFrame.db);

            // pega o id do artigo que acabou de ser inserido
            rs = MainFrame.db.query("SELECT seq_artigo.CURRVAL FROM DUAL");
            int idArt = 0;
            if(rs.next())
                idArt = rs.getInt("CURRVAL");
            // fecha o cursor
            MainFrame.db.close();

            // pega os autores
            int[] authors_selecteds = this.jList1.getSelectedIndices();
            int flag = 0;
            for (int i = 0; i < authors_selecteds.length; i++) {

                // pega o autor selecionado
                People p = this.authors.get(authors_selecteds[i]);

                // verifica se foi selecionado o apresentador como autor
                if(presenterName.equals(p.getNomePe()))
                    flag = 1;

                // insere na tabela escreve
                Write write = new Write(p.getIdPe(), idArt);
                write.insert(MainFrame.db);
            }
            // se o apresentador não foi selecionado como autor, insere-o
            if(flag == 0) {
                Write write = new Write(idApr, idArt);
                write.insert(MainFrame.db);
            }

            new Message(this, true, "Artigo cadastrado.").setVisible(true);
            this.dispose();

        } catch(SQLException e) {
            String error;
            if (e.getErrorCode() == 1850 || e.getErrorCode() == 1851)
                error = "Horário inválido.";
            else
                error = e.getMessage();
            Message msg = new Message(this, true, error);
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Busca inscritos
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // remove os itens
        this.jComboBox3.removeAllItems();
        try {
            // pega o evento selecionado
            String eventName = (String) this.jComboBox1.getSelectedItem();
            ResultSet rs = Event.findByName(MainFrame.db, eventName);
            Event e = Event.next(rs);
            int codEv = e.getCodEv();
            // fecha o cursor
            MainFrame.db.close();
            
            // pega a edição selecionada
            String editionName = (String) this.jComboBox2.getSelectedItem();
            if (editionName == null)
                return;
            String[] parts = editionName.split(" ");
            int numEd = Integer.valueOf(parts[0]).intValue();
            
            // busca os inscritos da edição do evento
            this.ids_registereds = new ArrayList();
            rs = Registered.findByEventAndEdition(MainFrame.db, codEv, numEd, true);
            while(rs.next()) {
                // adiciona os ids dos inscritos no array
                this.ids_registereds.add(rs.getInt("idPart"));
                // adiciona o nome do inscrito na ComboBox
                this.jComboBox3.addItem(rs.getString("nomePe"));
            }
            // fecha o cursor
            MainFrame.db.close();
        
        } catch(SQLException e) {
            Message msg = new Message(this, true, e.getMessage());
            msg.setTitle("Erro");
            msg.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * Buscar edições
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // remove os itens
        this.jComboBox2.removeAllItems();
        // pega o nome do evento selecionado
        String nomeEv = (String) this.jComboBox1.getSelectedItem();
        try {
            // pega as edições do evento
            ResultSet rs = Edition.findByEvent(MainFrame.db, nomeEv);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
