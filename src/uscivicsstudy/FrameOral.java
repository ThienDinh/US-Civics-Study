/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ThienDinh
 */
public class FrameOral extends javax.swing.JFrame implements Runnable {

    
    private Lock locker;
    private StringTransfer strTrans;
    private Thread curThread;
    private Thread voiceThread;
    private int correct;
    private boolean age65;
    private ArrayList<Question> randQuestions;
    private Random random;
    private int index = -1;
    private boolean passTest = false;
    private int[] specAns = {2, 9, 12, 14, 16, 36, 37, 51, 52, 55, 57, 61, 64, 77, 85, 100};
    private int[] numReq = {3, 2, 4, 2, 3, 2, 4, 2, 2, 2, 2, 3, 3, 2, 2, 2};
    private ArrayList<String> savedAns = new ArrayList<>();

    /**
     * Creates new form DisplayFrame
     */
    public FrameOral(StringTransfer strTrans) {
        locker = new ReentrantLock();
        this.strTrans = strTrans;
        //this.age65 = isAge65;
        random = new Random();
        randQuestions = new ArrayList<Question>();
        initComponents();
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screen.width / 2 - this.getWidth() / 2,
                screen.height / 4 * 3 - this.getHeight() / 2);
        this.setVisible(true);
        generateQuestions();
        nextQuestion();
    }

    private void generateQuestions() {
        QuestionLoader loader = new QuestionLoader(new File("data/QuestionBank.txt"));
        ArrayList<Question> qList = loader.getList(this.age65);
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(qList.size());
            randQuestions.add(qList.get(rand));
            qList.remove(rand);
        }
        // Testing
        //randQuestions = qList;
    }

    private void nextQuestion() {
        this.txaQuestion.setText(this.randQuestions.get(++index).getQuestion()
                + "\n" + this.randQuestions.get(index).getAnswer());
        this.savedAns.clear();
    }

    private void updateProfile(boolean pass) {
        try {
            Profile currentProfile = Profile.getCurrentProfile();
            currentProfile.setTakenTest(currentProfile.getTakenTest() + 1);
            if (pass) {
                currentProfile.setOralPassed(currentProfile.getOralPassed() + 1);
            }
            // Write new Profile            
            File newProfile = new File("data/" + currentProfile.getName() + ".plf");
            PrintWriter writeProfile = new PrintWriter(newProfile);
            writeProfile.println(currentProfile.getName());
            writeProfile.println(currentProfile.getAge());
            writeProfile.println(currentProfile.getDateStarted());
            writeProfile.println(currentProfile.getTakenTest()); // tests taken
            writeProfile.println(currentProfile.getTestPassed()); // tests passed
            writeProfile.println(currentProfile.getOralPassed());  // oral passed
            writeProfile.close();
        } catch (Exception ex) {
            System.out.println("Error in MultipleChoiceFrame: updateProfile");
        }
    }

    private boolean checkAnswer(String userAnswer) {
        userAnswer = userAnswer.trim();
        String[] answers = this.randQuestions.get(index).getAnswer().split("\n");
        for (String answer : answers) {
            String cleanAnswer = CleanAnswer.cleanAnswer(answer).toLowerCase().trim();
            System.out.println("Compare:[" + cleanAnswer + "] ?= [" + userAnswer + "]");
            if (userAnswer.equals(cleanAnswer)) {
                for (int i = 0; i < this.specAns.length; i++) {
                    if (this.randQuestions.get(index).getNo() == specAns[i]) {
                        for (String check : savedAns) {
                            if (check.equals(userAnswer)) {
                                return false;
                            }
                        }
                        this.savedAns.add(cleanAnswer);
                        return true;
                    }
                }
                return true;
            }
            if (cleanAnswer.contains(userAnswer)) {
                for (int i = 0; i < this.specAns.length; i++) {
                    if (this.randQuestions.get(index).getNo() == specAns[i]) {
                        for (String check : savedAns) {
                            if (check.equals(userAnswer)) {
                                return false;
                            }
                        }
                        this.savedAns.add(cleanAnswer);
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void addThread(Thread voice) {
        this.voiceThread = voice;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbWord = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaQuestion = new javax.swing.JTextArea();
        btnSkip = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Oral Civics Test");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbWord.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbWord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbWord.setText("Loading...");

        btnStatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnStatus.setText("Test");

        txaQuestion.setEditable(false);
        txaQuestion.setColumns(20);
        txaQuestion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txaQuestion.setLineWrap(true);
        txaQuestion.setRows(5);
        txaQuestion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txaQuestion);

        btnSkip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSkip.setText("Skip");
        btnSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbWord, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSkip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbWord, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSkip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        //curThread.interrupt();        
        updateProfile(passTest);
        FrameMenu.frameThr.interrupt();
        FrameMenu.recordThr.interrupt();
        //voiceThread.interrupt();
        closeFrame();
    }//GEN-LAST:event_formWindowClosed

    private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkipActionPerformed
        // TODO add your handling code here:
        if (index < 9) {
            nextQuestion();
        }else
        {
            this.checkComplete();
        }
    }//GEN-LAST:event_btnSkipActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSkip;
    private javax.swing.JButton btnStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbWord;
    private javax.swing.JTextArea txaQuestion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.btnStatus.setText("Listening...");
        this.curThread = Thread.currentThread();
        try {
            while (!Thread.interrupted()) {
                if (this.checkAnswer(strTrans.getString())) {
                    locker.lock();
                    checkComplete();
                    locker.unlock();
                }
                this.lbWord.setText(strTrans.getString());
                Thread.sleep(1000);
                System.out.println(curThread.getName() + " is " + Thread.interrupted());
            }
        } catch (InterruptedException ex) {
            closeFrame();
        }
    }
    
    private void checkComplete()
    {
        if (index == 9) {
                        if (correct >= 6) {
                            this.txaQuestion.setText("CONGRATULATION!!!");
                            JOptionPane.showMessageDialog(this, "Congratulation! By answering " + correct
                                    + " corrects over 10 questions, you have passed oral test!");
                            passTest = true;
                        } else {
                            this.txaQuestion.setText("KEEP STUDYING!!!");
                            JOptionPane.showMessageDialog(this, "Oops! By answering just " + correct
                                    + " corrects over 10 questions, you have failed oral test!");
                        }
                        this.dispose();
                    } else {
                        boolean wait = false;
                        for (int i = 0; i < this.specAns.length; i++) {
                            if (this.randQuestions.get(index).getNo() == specAns[i]) {
                                if (this.savedAns.size() == this.numReq[i]) {
                                    wait = false;
                                } else {
                                    wait = true;
                                }
                            }
                        }
                        if (!wait) {
                            correct++;
                            this.nextQuestion();
                            this.strTrans.setString("Listening...");
                            this.savedAns.clear();
                        }
                    }
    }

    /**
     * Close the frame and return to the Menu Frame.
     */
    private void closeFrame() {
        Frame[] frameList = JFrame.getFrames();
        for (Frame aFrame : frameList) {
            if (aFrame.getName().equals("menuFrame")) {
                aFrame.setVisible(true);
            }
        }
    }
}
