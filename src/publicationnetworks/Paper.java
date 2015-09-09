/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package publicationnetworks;

import java.util.ArrayList;

/**
 *
 * @author BimbozZ
 */
public class Paper {
    
    String Journal;
    String Name;
    ArrayList<String> Author;
    String Year;
    String Vol;
    String Citations;

    
    public Paper(){
        this.Author= new ArrayList();
    }

    public String getJournal() {
        return Journal;
    }

    public void setJournal(String Journal) {
        this.Journal = Journal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<String> getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author.add(Author);
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getVol() {
        return Vol;
    }

    public void setVol(String Vol) {
        this.Vol = Vol;
    }

    public String getCitations() {
        return Citations;
    }

    public void setCitations(String Citations) {
        this.Citations = Citations;
    }
        
    
    
}
