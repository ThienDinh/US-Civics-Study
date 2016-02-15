/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uscivicsstudy;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author ThienDinh
 */
public class Vdict {

    private String word;
    private String definition;

    public Vdict() {

    }

    public void setWord(String word) {
        this.word = word;
        definition = this.findDefinition();
    }

    public String getDefinition() {
        return this.definition;
    }

    public String findDefinition() {
        String example = "";
        try {
            String address = "http://vdict.com/" + word + ",1,0,0.html";
            URL pageLocation = new URL(address);
            File download = new File("tempVn.kin");
            Scanner in = new Scanner(pageLocation.openStream(), "UTF-8");
            PrintWriter out = new PrintWriter(download, "UTF-8");
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
            in.close();
            out.close();
            String line = "";
            in = new Scanner(download, "UTF-8");
            while (in.hasNextLine()) {
                line = in.nextLine();
                if (line.contains("class=\"phanloai\"")) {
                    example += this.emptyTags(line);
                }
            }
            in.close();
        } catch (Exception ex) {
            example = "The program couldn't search for the example!\n";
        }
        return example;
    }

    /**
     * Clean all the tags
     *
     * @param tagsLine
     * @return the text without any HTML tags
     */
    private String emptyTags(String tagsLine) {
        tagsLine = tagsLine.trim();
        boolean setRecord = true;
        String tag = "";
        int m = 0;
        String editedLine = "";
        tagsLine = tagsLine.replaceAll("", "\"");
        for (int i = 0; i < tagsLine.length(); i++) {
            if (tagsLine.charAt(i) == '<') {
                m = i;
                setRecord = false;
                continue;
            } else if (tagsLine.charAt(i) == '>') {
                setRecord = true;
                tag = tagsLine.substring(m + 1, i);
               
                if (tag.contains("class=\"phanloai\"")
                        || tag.contains("class=\"idioms\"")) { //
                    editedLine += "\n # ";
                }
                
                if (tag.contains("class=\"list1\"")) {
                    editedLine += "\n   +";
                }
                if (tag.contains("br/")) {
                    editedLine += "\n      .";
                }
                if (tag.contains("class=\"list2\"")
                        ) { 
                    editedLine += "\n      -";
                }
                continue;
            }
            if (setRecord) {
                editedLine += "" + tagsLine.charAt(i);
            }
        }
        return editedLine;
    }
}
