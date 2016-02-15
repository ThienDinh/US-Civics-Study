/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

/**
 * Medium between Voice object and Display frame
 * @author ThienDinh
 */
public class StringTransfer {
    private String aString;
    
    /**
     * Constructor.
     * @param str Default string.
     */
    public StringTransfer(String str)
    {
        aString = str;
    }
    
    /**
     * Set string.
     * @param str a new string.
     */
    public void setString(String str)
    {
        aString = str;
    }
    
    /**
     * Get string.
     * @return the stored string.
     */
    public String getString()
    {
        return aString;
    }
    
}
