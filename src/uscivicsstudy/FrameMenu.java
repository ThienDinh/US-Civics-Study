/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author ThienDinh
 */
public class FrameMenu extends javax.swing.JFrame {

    // This parameter will be passed to Voice class
    private String[] args;
    public static Thread frameThr;
    public static Thread recordThr;

    /**
     * Creates new form MenuFrame
     */
    public FrameMenu(String[] args) {
        loadCurrentProfile();
        this.args = args;
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screen.width / 2 - this.getWidth() / 2,
                screen.height / 2 - this.getHeight() / 2);
    }

    private void loadCurrentProfile() {
        try {
            Scanner readConfig = new Scanner(new File("data/" + "config.txt"));
            Profile.currentProfile =  readConfig.nextLine();
            readConfig.close();
            System.out.println(Profile.currentProfile);
        } catch (IOException ex) {
            System.out.println("Damn error: " + ex.getMessage());
        }
    }

    private String encodeLang(String language) {
        String encoded = "";
        switch (language) {
            case "ARABIC":
                encoded = "ar";
                break;
            case "BULGARIAN":
                encoded = "bg";
                break;
            case "CATALAN":
                encoded = "ca";
                break;
            case "CHINESE SIMPLIFIED":
                encoded = "zh-CHS";
                break;
            case "CHINESE TRADITIONAL":
                encoded = "zh-CHT";
                break;
            case "CZECH":
                encoded = "cs";
                break;
            case "DANISH":
                encoded = "da";
                break;
            case "DUTCH":
                encoded = "nl";
                break;
            case "ENGLISH":
                encoded = "en";
                break;
            case "ESTONIAN":
                encoded = "et";
                break;
            case "FINNISH":
                encoded = "fi";
                break;
            case "FRENCH":
                encoded = "fr";
                break;
            case "GERMAN":
                encoded = "de";
                break;
            case "GREEK":
                encoded = "el";
                break;
            case "HAITIAN CREOLE":
                encoded = "ht";
                break;
            case "HEBREW":
                encoded = "he";
                break;
            case "HINDI":
                encoded = "hi";
                break;
            case "HMONG DAW":
                encoded = "mww";
                break;
            case "HUNGARIAN":
                encoded = "hu";
                break;
            case "INDONESIAN":
                encoded = "id";
                break;
            case "ITALIAN":
                encoded = "it";
                break;
            case "JAPANESE":
                encoded = "ja";
                break;
            case "KOREAN":
                encoded = "ko";
                break;
            case "LATVIAN":
                encoded = "lv";
                break;
            case "LITHUANIAN":
                encoded = "lt";
                break;
            case "MALAY":
                encoded = "ms";
                break;
            case "NORWEGIAN":
                encoded = "no";
                break;
            case "PERSIAN":
                encoded = "fa";
                break;
            case "POLISH":
                encoded = "pl";
                break;
            case "PORTUGUESE":
                encoded = "pt";
                break;
            case "ROMANIAN":
                encoded = "ro";
                break;
            case "RUSSIAN":
                encoded = "ru";
                break;
            case "SLOVAK":
                encoded = "sk";
                break;
            case "SLOVENIAN":
                encoded = "sl";
                break;
            case "SPANISH":
                encoded = "es";
                break;
            case "SWEDISH":
                encoded = "sv";
                break;
            case "THAI":
                encoded = "th";
                break;
            case "TURKISH":
                encoded = "tr";
                break;
            case "UKRAINIAN":
                encoded = "uk";
                break;
            case "URDU":
                encoded = "ur";
                break;
            case "VIETNAMESE":
                encoded = "vi";
                break;
        }
        System.out.println(encoded);
        return encoded;
    }

 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
 regenerated by the Form FrameStudy.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnStudy = new javax.swing.JButton();
        btnCivicsTest = new javax.swing.JButton();
        btnOral = new javax.swing.JButton();
        btnDictionary = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbbLanguage = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lbAudio = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUser = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnWebsite = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USA Civics Study");
        setName("menuFrame"); // NOI18N
        setResizable(false);

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        pnlMenu.setLayout(new java.awt.GridLayout(4, 1));

        btnStudy.setBackground(new java.awt.Color(255, 0, 0));
        btnStudy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStudy.setText("Study");
        btnStudy.setToolTipText("");
        btnStudy.setName(""); // NOI18N
        btnStudy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudyMouseEntered(evt);
            }
        });
        btnStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudyActionPerformed(evt);
            }
        });
        pnlMenu.add(btnStudy);

        btnCivicsTest.setBackground(new java.awt.Color(0, 255, 0));
        btnCivicsTest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCivicsTest.setText("Multiple Choice Test");
        btnCivicsTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCivicsTestMouseEntered(evt);
            }
        });
        btnCivicsTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCivicsTestActionPerformed(evt);
            }
        });
        pnlMenu.add(btnCivicsTest);

        btnOral.setBackground(new java.awt.Color(204, 255, 0));
        btnOral.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOral.setText("Oral Test");
        btnOral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOralMouseEntered(evt);
            }
        });
        btnOral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOralActionPerformed(evt);
            }
        });
        pnlMenu.add(btnOral);

        btnDictionary.setBackground(new java.awt.Color(51, 51, 255));
        btnDictionary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDictionary.setText("Dictionary");
        btnDictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDictionaryActionPerformed(evt);
            }
        });
        pnlMenu.add(btnDictionary);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        cbbLanguage.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        cbbLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "    ENGLISH", "    ARABIC", "    BULGARIAN", "    CATALAN", "    CHINESE SIMPLIFIED", "    CHINESE TRADITIONAL", "    CZECH", "    DANISH", "    DUTCH", "    ESTONIAN", "    FINNISH", "    FRENCH", "    GERMAN", "    GREEK", "    HAITIAN CREOLE", "    HEBREW", "    HINDI", "    HMONG DAW", "    HUNGARIAN", "    INDONESIAN", "    ITALIAN", "    JAPANESE", "    KOREAN", "    LATVIAN", "    LITHUANIAN", "    MALAY", "    NORWEGIAN", "    PERSIAN", "    POLISH", "    PORTUGUESE", "    ROMANIAN", "    RUSSIAN", "    SLOVAK", "    SLOVENIAN", "    SPANISH", "    SWEDISH", "    THAI", "    TURKISH", "    UKRAINIAN", "    URDU", "    VIETNAMESE" }));
        cbbLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLanguageActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel1.setText("Select a Language to Study");

        lbAudio.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbAudio.setText("(Audio Available)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbAudio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        btnUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUser.setForeground(new java.awt.Color(0, 0, 204));
        btnUser.setText("USER PROFILE");
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnUser);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        btnWebsite.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnWebsite.setText("USCIS.gov");
        btnWebsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnWebsiteMouseEntered(evt);
            }
        });
        btnWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWebsiteActionPerformed(evt);
            }
        });
        jPanel3.add(btnWebsite);

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 0, 0));
        btnExit.setText("Exit");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel3.add(btnExit);

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the program.
     *
     * @param evt
     */
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * Open the Civics Study Frame.
     *
     * @param evt
     */
    private void btnStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudyActionPerformed
        // TODO add your handling code here:
        String language = (String) this.cbbLanguage.getSelectedItem();
        JFrame test = new FrameStudy(this.encodeLang(language.trim()));
        test.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnStudyActionPerformed

    /**
     * Open the web site of government.
     *
     * @param evt
     */
    private void btnWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWebsiteActionPerformed
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(new URI("http://www.uscis.gov/portal/site/uscis"
                    + "/menuitem.749cabd81f5ffc8fba713d10526e0aa0"
                    + "/?vgnextoid=fa66cf2351488210VgnVCM10000025e6a00aRCRD&vgnextchannel"
                    + "=fa66cf2351488210VgnVCM10000025e6a00aRCRD"));
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnWebsiteActionPerformed

    /**
     * Open the multiple choice test program.
     *
     * @param evt
     */
    private void btnCivicsTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCivicsTestActionPerformed
        // TODO add your handling code here:
        JFrame test = new FrameMultipleChoice();
        test.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCivicsTestActionPerformed

    private void btnStudyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudyMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStudyMouseEntered

    private void btnCivicsTestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCivicsTestMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCivicsTestMouseEntered

    private void btnWebsiteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWebsiteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnWebsiteMouseEntered

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitMouseEntered

    /**
     * Open the oral test.
     *
     * @param evt
     */
    private void btnOralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOralActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        StringTransfer strTrans = new StringTransfer("Preparing...");
        FrameOral frame = new FrameOral(strTrans);
        Voice record = new Voice(args, strTrans, frame);
        frameThr = new Thread(frame, "frame");
        recordThr = new Thread(record, "record");
        frame.addThread(recordThr);
        frameThr.start();
        recordThr.start();
    }//GEN-LAST:event_btnOralActionPerformed

    private void btnOralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOralMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOralMouseEntered

    private void btnDictionaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDictionaryActionPerformed
        // TODO add your handling code here:
        FrameDictionary searchForm = new FrameDictionary();
        searchForm.setVisible(true);
    }//GEN-LAST:event_btnDictionaryActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        // TODO add your handling code here:
        FrameProfile profile = new FrameProfile();
        profile.setVisible(true);
    }//GEN-LAST:event_btnUserActionPerformed

    private void cbbLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLanguageActionPerformed
        // TODO add your handling code here:
        if(((String) this.cbbLanguage.getSelectedItem()).equals("ENGLISH"))
        {
            this.lbAudio.setText("(Audio Available)");
        }else
        {
            this.lbAudio.setText("(Audio not Available)");            
        }
    }//GEN-LAST:event_cbbLanguageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCivicsTest;
    private javax.swing.JButton btnDictionary;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOral;
    private javax.swing.JButton btnStudy;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton btnWebsite;
    private javax.swing.JComboBox cbbLanguage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbAudio;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
