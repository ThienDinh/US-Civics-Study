/*
 * To change this template, choose Tools | Templates
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
public class Example{

    private String word;

    public Example() {
    }


    public void setWord(String word) {
        this.word = word;
    }

    public String findExample() {
        String example = "";
        try {
            String address = "http://www.reference.com/example-sentences/" + word;
            URL pageLocation = new URL(address);
            File download = new File("tempEx.kin");
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
                if (line.contains("\"example example_show\"")
                        || line.contains("\"example example_hide\"")) {
                    example += this.emptyTags(line);
                }
            }
            in.close();
        } catch (Exception ex) {
            example = "The program couldn't search for the example!\n";
        }
        return example;
    }

    private String emptyTags(String tagsLine) {
        boolean setRecord = true;
        // Save the tag
        String tag = "";
        int m = 0;
        String editedLine = "";
        tagsLine = tagsLine.trim();
        for (int i = 0; i < tagsLine.length(); i++) {
            if (tagsLine.charAt(i) == '<') {
                m = i;
                setRecord = false;
                continue;
            } else if (tagsLine.charAt(i) == '>') {
                setRecord = true;
                tag = tagsLine.substring(m + 1, i);
                // find example
                if (tag.contains("\"example example_show\"")
                        || tag.contains("\"example example_hide\"")) {
                    editedLine += "\n\n     - ";
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
