/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;

/**
 *
 * @author ThienDinh
 */
public class Voice implements Runnable {

    private StringTransfer strTrans;
    private String record;
    private JFrame readFrame;
    private ConfigurationManager cm;
    private Lock recordLock;

    public Voice(String[] args, StringTransfer strTrans, JFrame readFrame) {
        recordLock = new ReentrantLock();
        this.readFrame = readFrame;
        this.strTrans = strTrans;
        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(USCivicsStudy.class.getResource("new.xml"));
        }
    }

    @Override
    public void run() {
        this.recordLock.lock();
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible    
        Microphone microphone = (Microphone) cm.lookup("microphone");
        // If the microphone can not start recording
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
        Result result;
        this.recordLock.unlock();
        // loop the recognition until the programm exits.
        try {
            while (!Thread.interrupted()) {
                recordLock.lock();
                result = recognizer.recognize();
                if (result != null) {
                    String resultText = result.getBestFinalResultNoFiller();
                    switch (resultText) {
                        case "":
                            record = "You should say something.\n";
                            System.out.println("You should say something.\n");
                            break;
                        case "close frame":
                            System.out.println("Frame closed");
                            readFrame.dispose();
                        //Thread.currentThread().interrupt();
                        default:
                            record = resultText + "\n";
                            System.out.println(resultText + "\n");
                    }
                } else {
                    record = "I can't hear what you said.\n";
                    System.out.println("I can't hear what you said.\n");
                }
                strTrans.setString(record);
                recordLock.unlock();
            }
        } catch (Exception ex) {
            System.out.println("line 82 Voice.java" + ex.getMessage());
        } finally {
            if (!recordLock.tryLock()) {
                recordLock.lock();
            }
            microphone.stopRecording();
            microphone.clear();
            recognizer.deallocate();
            recordLock.unlock();
        }
    }
}
