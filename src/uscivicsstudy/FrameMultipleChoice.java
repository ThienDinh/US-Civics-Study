/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author ThienDinh
 */
public class FrameMultipleChoice extends javax.swing.JFrame {

    private ArrayList<Question> randQuestions;
    private Random random;
    private int index;
    private int score;
    private boolean lock;
    private String message = "You haven't finished the Civics test!";
    private Clip clip;
    private boolean age65;
    private boolean pass = false;

    /**
     * Creates new form CivicsTestFrame
     */
    public FrameMultipleChoice() {
        if (Profile.getCurrentProfile().getAge() >= 65) {
            this.age65 = true;
        } else {
            this.age65 = false;
        }
        lock = false;
        random = new Random();
        randQuestions = new ArrayList<Question>();
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
            java.util.logging.Logger.getLogger(FrameMultipleChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMultipleChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMultipleChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMultipleChoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screen.width / 2 - this.getWidth() / 2,
                screen.height / 2 - this.getHeight() / 2);
        generateQuestions();
        setQuestion();
    }

    private void updateProfile(boolean pass) {
        try {
            Profile currentProfile = Profile.getCurrentProfile();
            currentProfile.setTakenTest(currentProfile.getTakenTest() + 1);
            if (pass) {
                currentProfile.setTestPassed(currentProfile.getTestPassed() + 1);
            }
            // Write new Profile            
            File newProfile = new File("data/" + currentProfile.getName() + ".plf");
            PrintWriter writeProfile = new PrintWriter(newProfile);
            writeProfile.println(currentProfile.getName());
            writeProfile.println(currentProfile.getAge());
            writeProfile.println(currentProfile.getDateStarted());
            writeProfile.println(currentProfile.getTakenTest()); // tests taken
            writeProfile.println(currentProfile.getTestPassed()); // tests passed
            writeProfile.println(currentProfile.getOralPassed()); // oral passed
            writeProfile.close();
        } catch (Exception ex) {
            System.out.println("Error in MultipleChoiceFrame: updateProfile");
        }
    }

    private void generateQuestions() {
        QuestionLoader loader = new QuestionLoader(new File("data/QuestionBank.txt"));
        ArrayList<Question> qList = loader.getList(this.age65);
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(qList.size());
            randQuestions.add(qList.get(rand));
            qList.remove(rand);
        }
    }

    private void setQuestion() {
        int rand;
        JTextArea[] ans = {
            txaAns1, txaAns2, txaAns3, txaAns4
        };
        for (JTextArea area : ans) {
            area.setText("");
        }
        ArrayList<Question> tempAns = new ArrayList<Question>(randQuestions);
        lbQuestion.setText(randQuestions.get(index).toString());

        // Put the correct answer in random position
        rand = random.nextInt(4);
        ans[rand].setText(tempAns.get(index).getAnswer());
        // Remove the correct answer out of the answer list
        tempAns.remove(index);
        for (int i = 0; i < 4; i++) {
            if (ans[i].getText().equals("")) {
                // Get a random answer from the list of remaining answers
                rand = random.nextInt(tempAns.size());
                ans[i].setText(tempAns.get(rand).getAnswer());
                tempAns.remove(rand);
            }
        }
    }

    private void nextQuestion() {
        if (index < 9) {
            this.lbResult.setText("__________");
            index++;
            setQuestion();
            JTextArea[] ans = {
                txaAns1, txaAns2, txaAns3, txaAns4
            };
            for (JTextArea area : ans) {
                area.setBackground(new Color(255, 255, 255));
            }
            btnNext.setEnabled(false);
            // Unlock clicking on the answers
            lock = false;
        } else {
            message = "You have answered correctly " + score + " out of 10 questions. You have failed the test!";
            pass = false;
            this.dispose();
        }

    }

    private void showCorrectAnswer() {
        // Lock clicking the answers
        lock = true;
        JTextArea[] ans = {
            txaAns1, txaAns2, txaAns3, txaAns4
        };
        // Fill the correct for correct and incorrect answers
        for (JTextArea area : ans) {
            //if correct
            if (randQuestions.get(index).getAnswer().equals(area.getText())) {
                area.setBackground(new Color(229, 255, 204));
            } else {
                area.setBackground(new Color(255, 204, 204));
            }
        }
        // Enable the next button after all the answered are showed
        btnNext.setEnabled(true);
    }

    private void playSound(String sound) {
        try {
            if (clip != null) {
                clip.close();
            }
            // TODO add your handling code here:
            File audio = new File("data/" + sound + ".wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(FrameStudy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameStudy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(FrameStudy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeFrame() {
        if (clip != null) {
            clip.close();
        }
        updateProfile(pass);
        JOptionPane.showMessageDialog(this, this.message);
        Frame[] frameList = JFrame.getFrames();
        for (Frame aFrame : frameList) {
            if (aFrame.getName().equals("menuFrame")) {
                aFrame.setVisible(true);
            }
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

        lbWarning = new javax.swing.JLabel();
        pnlAnsGroup = new javax.swing.JPanel();
        spnlAns1 = new javax.swing.JScrollPane();
        txaAns1 = new javax.swing.JTextArea();
        spnlAns2 = new javax.swing.JScrollPane();
        txaAns2 = new javax.swing.JTextArea();
        spnlAns3 = new javax.swing.JScrollPane();
        txaAns3 = new javax.swing.JTextArea();
        spnlAns4 = new javax.swing.JScrollPane();
        txaAns4 = new javax.swing.JTextArea();
        pnlControl = new javax.swing.JPanel();
        lbResult = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        lbQuestion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CIVICS TEST");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lbWarning.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbWarning.setForeground(new java.awt.Color(204, 0, 20));
        lbWarning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbWarning.setText("You must answer correctly at  least six (6) of the 10 questions to pass the civics test. ");

        pnlAnsGroup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pnlAnsGroup.setLayout(new java.awt.GridLayout(2, 2));

        txaAns1.setEditable(false);
        txaAns1.setColumns(20);
        txaAns1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txaAns1.setRows(5);
        txaAns1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        txaAns1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txaAns1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txaAns1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txaAns1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txaAns1MousePressed(evt);
            }
        });
        spnlAns1.setViewportView(txaAns1);

        pnlAnsGroup.add(spnlAns1);

        txaAns2.setEditable(false);
        txaAns2.setColumns(20);
        txaAns2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txaAns2.setRows(5);
        txaAns2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        txaAns2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txaAns2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txaAns2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txaAns2MousePressed(evt);
            }
        });
        spnlAns2.setViewportView(txaAns2);

        pnlAnsGroup.add(spnlAns2);

        txaAns3.setEditable(false);
        txaAns3.setColumns(20);
        txaAns3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txaAns3.setRows(5);
        txaAns3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        txaAns3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txaAns3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txaAns3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txaAns3MousePressed(evt);
            }
        });
        spnlAns3.setViewportView(txaAns3);

        pnlAnsGroup.add(spnlAns3);

        txaAns4.setEditable(false);
        txaAns4.setColumns(20);
        txaAns4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txaAns4.setRows(5);
        txaAns4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        txaAns4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txaAns4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txaAns4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txaAns4MousePressed(evt);
            }
        });
        spnlAns4.setViewportView(txaAns4);

        pnlAnsGroup.add(spnlAns4);

        pnlControl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlControl.setLayout(new java.awt.GridLayout(1, 2));

        lbResult.setFont(new java.awt.Font("Segoe Print", 0, 36)); // NOI18N
        lbResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbResult.setText("__________");
        lbResult.setBorder(javax.swing.BorderFactory.createTitledBorder("Your answer is"));
        pnlControl.add(lbResult);

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNext.setText("Next Question");
        btnNext.setEnabled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlControl.add(btnNext);

        lbQuestion.setBackground(new java.awt.Color(51, 51, 51));
        lbQuestion.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbQuestion.setText("Question");
        lbQuestion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbWarning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(lbQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAnsGroup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlAnsGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        closeFrame();
    }//GEN-LAST:event_formWindowClosed

    private void txaAns1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txaAns1MouseEntered

    private void txaAns1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txaAns1MouseClicked

    private void txaAns2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txaAns2MouseClicked

    private void txaAns3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txaAns3MouseClicked

    private void txaAns4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txaAns4MouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (score == 6) {
            message = "Congratulation! You have passed the Civics test!";
            pass = true;
            this.dispose();
        } else {
            this.nextQuestion();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void txaAns1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns1MousePressed
        // TODO add your handling code here:
        if (!lock) {
            if (randQuestions.get(index).getAnswer().equals(this.txaAns1.getText())) {
                lbResult.setText("Correct!");
                playSound("correct");
                score++;
                System.out.println(score);
            } else {
                lbResult.setText("Wrong!");
                playSound("wrong");
            }
            showCorrectAnswer();
        }
    }//GEN-LAST:event_txaAns1MousePressed

    private void txaAns2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns2MousePressed
        // TODO add your handling code here:
        if (!lock) {
            if (randQuestions.get(index).getAnswer().equals(this.txaAns2.getText())) {
                lbResult.setText("Correct!");
                playSound("correct");
                score++;
                System.out.println(score);
            } else {
                lbResult.setText("Wrong!");
                playSound("wrong");
            }
            showCorrectAnswer();
        }
    }//GEN-LAST:event_txaAns2MousePressed

    private void txaAns3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns3MousePressed
        // TODO add your handling code here:
        if (!lock) {
            if (randQuestions.get(index).getAnswer().equals(this.txaAns3.getText())) {
                lbResult.setText("Correct!");
                playSound("correct");
                score++;
                System.out.println(score);
            } else {
                lbResult.setText("Wrong!");
                playSound("wrong");
            }
            showCorrectAnswer();
        }
    }//GEN-LAST:event_txaAns3MousePressed

    private void txaAns4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txaAns4MousePressed
        // TODO add your handling code here:
        if (!lock) {
            if (randQuestions.get(index).getAnswer().equals(this.txaAns4.getText())) {
                lbResult.setText("Correct!");
                playSound("correct");
                score++;
                System.out.println(score);
            } else {
                lbResult.setText("Wrong!");
                playSound("wrong");
            }
            showCorrectAnswer();
        }
    }//GEN-LAST:event_txaAns4MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel lbQuestion;
    private javax.swing.JLabel lbResult;
    private javax.swing.JLabel lbWarning;
    private javax.swing.JPanel pnlAnsGroup;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JScrollPane spnlAns1;
    private javax.swing.JScrollPane spnlAns2;
    private javax.swing.JScrollPane spnlAns3;
    private javax.swing.JScrollPane spnlAns4;
    private javax.swing.JTextArea txaAns1;
    private javax.swing.JTextArea txaAns2;
    private javax.swing.JTextArea txaAns3;
    private javax.swing.JTextArea txaAns4;
    // End of variables declaration//GEN-END:variables
}
