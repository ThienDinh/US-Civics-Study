/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

/**
 *
 * @author ThienDinh
 */
public class Question {

    private int qNo;
    private String qQuestion;
    private String qAnswer;
    private boolean age65;

    /**
     * Constructor.
     * @param no index of the question.
     * @param question content of question.
     * @param age65 used for age 65 up.
     */
    public Question(int no, String question, boolean age65) {
        this.qNo = no;
        this.qQuestion = question;
        this.age65 = age65;
    }

    /**
     * Set answer.
     * @param answer an answer.
     */
    public void setAnswer(String answer) {
        this.qAnswer = answer;
    }

    /**
     * Get index of the question.
     * @return index
     */
    public int getNo() {
        return qNo;
    }
    
    /**
     * Is used for age 65 up.
     * @return true or false.
     */
    public boolean isAge65()
    {
        return this.age65;
    }

    /**
     * Get the question.
     * @return the question.
     */
    public String getQuestion() {
        return qQuestion;
    }
    
    /**
     * Get the answer.
     * @return the answer.
     */
    public String getAnswer()
    {
        return qAnswer;
    }

    /**
     * Print the index and content of a question.
     * @return a string.
     */
    public String toString() {
        return "No." + qNo + ": " + qQuestion;
    }
}
