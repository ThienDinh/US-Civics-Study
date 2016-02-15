/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ThienDinh
 */
public class QuestionLoader {

    private ArrayList<Question> qList;

    /**
     * Set up a Question loader by reading from data text, and put questions
     * into a List.
     *
     * @param qBank path of data text.
     */
    public QuestionLoader(File qBank) {
        // Initialize the List.
        qList = new ArrayList<Question>();
        try {
            // Scan the data text to read.
            Scanner in = new Scanner(qBank, "Unicode");
            try {
                in.nextLine();
                Question q = null;
                String ans = "";
                // Go through each line of the data text.
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    char digit = line.charAt(0);
                    // If the first character is a digit, which is a question.
                    // This is also used to signal that the program reaches the next question.
                    if (Character.isDigit(digit)) {
                        // If the question is not created yet (only in the case of the first question).
                        if (q == null) {
                            // respectively, index of question, question text, check for age 65 up.
                            q = new Question(Integer.parseInt(line.substring(0, line.indexOf(". "))),
                                    line.substring(line.indexOf(". ") + 2), line.charAt(line.length() - 1) == '*');
                        } else {
                            // Save the answer.
                            q.setAnswer(ans);
                            // Add the completed Question object into the List.
                            qList.add(q);
                            // Create a new Question object with incomplete answer.
                            // respectively, index of question, question text, check for age 65 up.
                            q = new Question(Integer.parseInt(line.substring(0, line.indexOf(". "))),
                                    line.substring(line.indexOf(". ") + 2), line.charAt(line.length() - 1) == '*');
                            // Reset the answer to empty to store the answer for the new question
                            ans = "";
                        }

                    } else { // If the first character of the line is not a digit, then accumulate the answer
                        // Get answer
                        ans += line.substring(1) + "\n";
                    }
                }
                // The program reaches the end of file, however it doesn't signal to complete the question.
                // Then we have to do it.
                q.setAnswer(ans);
                qList.add(q);
            } finally {
                // Close file after reading.
                in.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Return the requested list according to its requirement with age is 65 up or not.
     * @param age65 yes or no to get only questions for age 65 up.
     * @return 
     */
    public ArrayList<Question> getList(boolean age65) {
        // If the list is requested with age restriction less than 65.
        if (age65) {
            // Create a new List with question marked with age 65, and return the new list.
            ArrayList<Question> requestedList = new ArrayList<Question>();
            for (int i = 0; i < qList.size(); i++) {
                // Add questions marked with age 65 up
                if (qList.get(i).isAge65()) {
                    requestedList.add(qList.get(i));
                }
            }
            return requestedList;
        } else {
            // If no age restriction, get all the question.
            return qList;
        }
    }
}
