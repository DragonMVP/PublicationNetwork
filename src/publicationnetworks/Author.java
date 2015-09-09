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
public class Author {
    
    String Name;
    String Phd;
    String Nationality;
    String UConferring;
    String YConferring;
    ArrayList<String>Papers;

    
    public Author(){
       this.Papers = new ArrayList();
    }

    public Author(String Name, String Phd, String Nationality, String UConferring, String YConferring) {
        this.Name = Name;
        this.Phd = Phd;
        this.Nationality = Nationality;
        this.UConferring = UConferring;
        this.YConferring = YConferring;
    }
    
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public ArrayList<String> getPapers() {
        return Papers;
    }

    public void setPapers(String Paper) {
        this.Papers.add(Paper);
    }

    public String getUConferring() {
        return UConferring;
    }

    public void setUConferring(String UConferring) {
        this.UConferring = UConferring;
    }

    public String getYConferring() {
        return YConferring;
    }

    public void setYConferring(String YConferring) {
        this.YConferring = YConferring;
    }

    public String getPhd() {
        return Phd;
    }

    public void setPhd(String Phd) {
        this.Phd = Phd;
    }
    
    
    
}
