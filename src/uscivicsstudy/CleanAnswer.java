/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

/**
 *
 * @author ThienDinh
 */
public class CleanAnswer {

    public static String cleanAnswer(String uncleanAnswer) {
        uncleanAnswer = uncleanAnswer.replace("(27)", "");
        uncleanAnswer = uncleanAnswer.replace("(100)", "");
        uncleanAnswer = uncleanAnswer.replace("(6)", "");
        uncleanAnswer = uncleanAnswer.replace("(435)", "");
        uncleanAnswer = uncleanAnswer.replace("(2)", "");
        uncleanAnswer = uncleanAnswer.replace("(4)", "");
        uncleanAnswer = uncleanAnswer.replace("(9)", "");
        uncleanAnswer = uncleanAnswer.replace("(18)", "");
        uncleanAnswer = uncleanAnswer.replace("(26)", "");
        uncleanAnswer = uncleanAnswer.replace("July 4, 1776", "July fourth seventeen seventy six");
        uncleanAnswer = uncleanAnswer.replace("1787", "seventeen eighty seven");
        uncleanAnswer = uncleanAnswer.replace("Barack", "bah rack");
        uncleanAnswer = uncleanAnswer.replace("Obama", "oh bah mah");
        uncleanAnswer = uncleanAnswer.replace("War of 1812", "War of eighteen twelve");
        uncleanAnswer = uncleanAnswer.replace("World War II", "World War two");
        uncleanAnswer = uncleanAnswer.replace("World War I", "World War one");
        uncleanAnswer = uncleanAnswer.replace("Blackfeet", "black feet");
        uncleanAnswer = uncleanAnswer.replace("13 original colonies", "thirteen original colonies");
        uncleanAnswer = uncleanAnswer.replace("50 states", "fifty states");
        uncleanAnswer = uncleanAnswer.replace("July 4", "July fourth");
        uncleanAnswer = uncleanAnswer.replace("April 15", "April fifteenth");
        uncleanAnswer = uncleanAnswer.replace("D.C.", "D C");
        uncleanAnswer = uncleanAnswer.replace(" (John G. Roberts, Jr.)", "");
        uncleanAnswer = uncleanAnswer.replace("-", " ");
        uncleanAnswer = uncleanAnswer.replace("(", "");
        uncleanAnswer = uncleanAnswer.replace(")", "");
        uncleanAnswer = uncleanAnswer.replace(",", "");
        uncleanAnswer = uncleanAnswer.replace(".", " ");
        uncleanAnswer = uncleanAnswer.replace("’", "");
        uncleanAnswer = uncleanAnswer.replace("\"", "");
        uncleanAnswer = uncleanAnswer.replace("  ", " ");
        return uncleanAnswer;
    }

}
