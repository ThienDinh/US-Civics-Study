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
public class WordDefinition{

    private String pronunciation;
    private String word;
    private String definition;

    public WordDefinition() {
    }


    public void setWord(String word) {
        this.word = word;
        definition = this.findDefinition();
    }

    public String findDefinition() {
        String definition = "";
        try {
            String address = "http://www.ahdictionary.com/word/search.html?q=" + word;
            URL pageLocation = new URL(address);
            File download = new File("tempDi.kin");
            Scanner in = new Scanner(pageLocation.openStream(), "UTF-8");
            PrintWriter out = new PrintWriter(download, "UTF-8");
            while (in.hasNextLine()) {
                String line = in.nextLine();
                out.println(line);
            }
            in.close();
            out.close();
            String line = "";
            String prvLine;
            pronunciation = "wavs/";
            boolean firstAudio = false;
            in = new Scanner(download, "UTF-8");
            while (in.hasNextLine()) {
                prvLine = line;
                line = in.nextLine();
                if (line.contains(pronunciation) && !firstAudio) {
                    pronunciation = line.substring(
                            line.indexOf(pronunciation) + pronunciation.length(), line.indexOf(".wav"));
                    firstAudio = true;
                }
                if (line.contains("Share:")) {
                    if (prvLine.contains("</script> (")) {
                        definition += emptyTags(prvLine) + "\n";
                    }
                    definition += emptyTags(line.substring(line.indexOf("<div class=\"pseg\">"))) + "\n";
                }
                if (line.contains("\"rtseg\"")) {
                    definition += emptyTags(line.substring(line.indexOf("<div class=\"rtseg\">"))) + "\n";
                }
            }
            in.close();
        } catch (Exception ex) {
            definition = "The program couldn't search for the word!\n";
        }
        return definition;
    }

    public String getDefinition() {
        return this.definition;
    }

    public String getPronunciation() {
        return pronunciation;
    }

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
                if (tag.contains("/a")) {
                    editedLine += " ";
                }
                // pseg: part of speech; etyseg; runseg
                if (tag.contains("\"pseg\"") || tag.contains("\"etyseg\"") || tag.contains("\"idmseg\"")
                        || tag.contains("\"rtseg\"") || tag.contains("\"runseg\"")) {
                    //editedLine += "\n===================================\n";
                    editedLine += "\n--------------------------------------------------------\n";
                }
                // ds-list: number; anttx: antonym; syntx: synonym; ds-single
                if (tag.contains("\"ds-list\"") || tag.contains("\"anttx\"")
                        || tag.contains("\"syntx\"") || tag.contains("\"ds-single\"")) {
                    editedLine += "\n     ";
                }
                // sds-list: alphabet
                if (tag.contains("\"sds-list\"")) {
                    editedLine += "\n          ";
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
