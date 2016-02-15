/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ThienDinh
 */
public class Profile {

    public static String currentProfile;
    private String dateStarted;
    private String name;
    private int age;
    private int takenTest;
    private int testPassed;
    private int oralPassed;

    public Profile(String name, int age, String dateStarted) {
        this.name = name;
        this.age = age;
        this.dateStarted = dateStarted;
    }

    public static Profile getCurrentProfile() {
        try {
            Profile userProfile = null;
            File profile = new File("data/" + Profile.currentProfile + ".plf");
            Scanner readProfile = new Scanner(profile);
            while (readProfile.hasNextLine()) {
                // Name, age, date start
                userProfile = new Profile(readProfile.nextLine(),
                        Integer.parseInt(readProfile.nextLine()), readProfile.nextLine());
                // Total tests taken
                userProfile.setTakenTest(Integer.parseInt(readProfile.nextLine()));
                // Multiple choice tests
                userProfile.setTestPassed(Integer.parseInt(readProfile.nextLine()));
                // Oral tests
                userProfile.setOralPassed(Integer.parseInt(readProfile.nextLine()));
            }
            readProfile.close();
            return userProfile;
        } catch (IOException ex) {
            System.out.println("Damn error: " + ex.getMessage());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDateStarted() {
        return this.dateStarted;
    }

    public int getTakenTest() {
        return this.takenTest;
    }
    
    public int getOralPassed(){
        return this.oralPassed;
    }

    public int getTestPassed() {
        return this.testPassed;
    }

    public void setTakenTest(int number) {
        this.takenTest = number;
    }

    public void setTestPassed(int number) {
        this.testPassed = number;
    }
    
    public void setOralPassed(int number){
        this.oralPassed = number;
    }
}
