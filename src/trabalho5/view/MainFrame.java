/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho5.view;

import trabalho5.database.DbConnection;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class MainFrame extends javax.swing.JFrame {

    protected static DbConnection db;
    public static boolean debugg;
    
    /**
     * Creates new form MainFrame
     * 
     * @param db
     */
    public MainFrame(DbConnection db) {
        MainFrame.db = db;
        MainFrame.debugg = true;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conferência Acadêmica");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jMenu1.setText("Cadastrar");

        jMenuItem1.setText("Evento");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Edição");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem9.setText("Pessoa");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem13.setText("Inscrito");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuItem16.setText("Artigo");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        jMenuItem20.setText("Organizador");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem20);

        jMenuItem24.setText("Patrocinador");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem24);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Buscar");

        jMenuItem4.setText("Evento");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem6.setText("Edição");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem10.setText("Pessoa");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem14.setText("Inscrito");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem17.setText("Artigo");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem21.setText("Organizador");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem21);

        jMenuItem25.setText("Patrocinador");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem25);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Atualizar");

        jMenuItem2.setText("Evento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem8.setText("Edição");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem11.setText("Pessoa");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem18.setText("Artigo");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem22.setText("Organizador");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem22);

        jMenuItem26.setText("Patrocinador");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem26);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Remover");

        jMenuItem3.setText("Evento");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem7.setText("Edição");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem12.setText("Pessoa");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem15.setText("Inscrito");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem19.setText("Artigo");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);

        jMenuItem23.setText("Organizador");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem23);

        jMenuItem27.setText("Patrocinador");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem27);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Opções");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Depurar");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cadastrar evento
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        InsertEvent insertEvent = new InsertEvent();
        insertEvent.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Fechar conexão
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            MainFrame.db.disconnect();
            System.out.println("Conexão fechada com sucesso.");
        } catch(SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados.");
        }
    }//GEN-LAST:event_formWindowClosing
    
    /**
     * Atualizar evento
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        SearchEventByName searchEvent = new SearchEventByName(CRUDType.UPDATE);
        searchEvent.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * Remover evento
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        SearchEventByName searchEvent = new SearchEventByName(CRUDType.REMOVE);
        searchEvent.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Buscar evento
     */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        SearchEventByName searchEvent = new SearchEventByName(CRUDType.SEARCH);
        searchEvent.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * Cadastrar edição
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        InsertEdition insertEdition = new InsertEdition();
        insertEdition.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * Buscar edição
     */
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        SearchEditionByEvent searchEdition = new SearchEditionByEvent(CRUDType.SEARCH);
        searchEdition.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Remover edição
     */
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        SearchEditionByEvent searchEdition = new SearchEditionByEvent(CRUDType.REMOVE);
        searchEdition.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * Atualizar edição
     */
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        SearchEditionByEvent searchEdition = new SearchEditionByEvent(CRUDType.UPDATE);
        searchEdition.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * Cadastrar pessoa
     */
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        InsertPeople insertPeople = new InsertPeople();
        insertPeople.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * Buscar pessoa
     */
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        SearchPeopleByName searchPeople = new SearchPeopleByName(CRUDType.SEARCH);
        searchPeople.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * Atualizar pessoa
     */
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        SearchPeopleByName searchPeople = new SearchPeopleByName(CRUDType.UPDATE);
        searchPeople.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * Remover pessoa
     */
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        SearchPeopleByName searchPeople = new SearchPeopleByName(CRUDType.REMOVE);
        searchPeople.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    /**
     * Cadastrar inscrito
     */
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        InsertRegistered insertRegistered = new InsertRegistered();
        insertRegistered.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    /**
     * Buscar inscrito
     */
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        SearchRegisteredByPeople searchRegistered = new SearchRegisteredByPeople(CRUDType.SEARCH);
        searchRegistered.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    /**
     * Remover inscrito
     */
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        SearchRegisteredByPeople searchRegistered = new SearchRegisteredByPeople(CRUDType.REMOVE);
        searchRegistered.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    /**
     *  Cadastrar artigo
     */
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        InsertArticle insertArticle = new InsertArticle();
        insertArticle.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    /**
     * Buscar artigo
     */
    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        SearchArticleByName searchArticle = new SearchArticleByName(CRUDType.SEARCH);
        searchArticle.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    /**
     * Atualizar artigo
     */
    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        SearchArticleByName searchArticle = new SearchArticleByName(CRUDType.UPDATE);
        searchArticle.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    /**
     * Remover artigo
     */
    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        SearchArticleByName searchArticle = new SearchArticleByName(CRUDType.REMOVE);
        searchArticle.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    /**
     * Opções
     */
    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        MainFrame.debugg = this.jCheckBoxMenuItem1.getState();
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    /**
     * Cadastrar organizador
     */
    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        InsertOrganizer insertOrganizer = new InsertOrganizer();
        insertOrganizer.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * Buscar organizador
     */
    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        SearchOrganizerByName searchOrganizer = new SearchOrganizerByName(CRUDType.SEARCH);
        searchOrganizer.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    /**
     * Atualizar organizador
     */
    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        SearchOrganizerByName searchOrganizer = new SearchOrganizerByName(CRUDType.UPDATE);
        searchOrganizer.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    /**
     * Remove organizador
     */
    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        SearchOrganizerByName searchOrganizer = new SearchOrganizerByName(CRUDType.REMOVE);
        searchOrganizer.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    /**
     * Cadastrar patrocinador
     */
    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        InsertSponsor insertSponsor = new InsertSponsor();
        insertSponsor.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    /**
     * Buscar patrocinador
     */
    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        SearchSponsorByName searchSponsor = new SearchSponsorByName(CRUDType.SEARCH);
        searchSponsor.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    /**
     * Atualizar patocinador
     */
    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        SearchSponsorByName searchSponsor = new SearchSponsorByName(CRUDType.UPDATE);
        searchSponsor.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    /**
     * Remover patrocinador
     */
    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        SearchSponsorByName searchSponsor = new SearchSponsorByName(CRUDType.REMOVE);
        searchSponsor.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
