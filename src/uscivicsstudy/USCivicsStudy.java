/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 *
 * @author ThienDinh
 */
public class USCivicsStudy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        JFrame main = new FrameMenu(args);
        main.setVisible(true);
    }

}
