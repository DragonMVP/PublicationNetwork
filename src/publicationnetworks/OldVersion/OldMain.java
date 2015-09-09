/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicationnetworks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BimbozZ
 */
public class OldMain {

    static ArrayList<String> WebSites;
    static ArrayList<Author> Author;
    static ArrayList<Paper> Papers;
    static OldParsingHTML ParserHTML;

    public static void main(String[] args) throws IOException {

        ParserHTML = new OldParsingHTML();
        WebSites = new ArrayList();
        Author = new ArrayList();
        Papers = new ArrayList();

/*        LoadWebSites();
        URLProcess(WebSites);*/
        
        System.out.println("URLs analizadas");
    }

    public static void URLProcess(ArrayList WebSites) throws IOException {//inicio for

        for (int i = 0; i < 1; i++) {
            System.out.println("BUSCANDO EN :" + WebSites.get(i).toString());
            //find links of faculty             
            LinksExtractor FacultyLinks = new LinksExtractor(WebSites.get(i).toString());
            String FacultyURL = "";
            if (!WebSites.get(i).equals("")) {
                System.out.println("Calling: Faculties");
                FacultyURL = findLink(FacultyLinks.getLinks(),"facultyandresearch","faculty-research","facultyresearch", "research","research");

            }
            if (!FacultyURL.equals("")) {
                /*make case, if FACULTYURL provides the page of papers*/
                if (isPapersPage(FacultyURL)) {
                    System.out.println("Es paper Page");
                    getPapers(FacultyURL);
                        
                    
                } else {
                    //Else find links of papers//publications                                             
                    LinksExtractor PapersLinks = new LinksExtractor(FacultyURL);
                    System.out.println("Calling : Papers");
                    String PaperURL = findLink(PapersLinks.getLinks(),"publications", "papers", "onlinepapers", "publication","paper");
                    getPapers(PaperURL);
                }
            }
        }//fin for

    }

    private static String findLink(ArrayList<String> Link, String toFind, String toFind2, String toFind3,String toFind4,String toFind5) {

        for (int i = 0; i < Link.size(); i++) {
            //clean String
            String Pure = Link.get(i).replaceAll("-", "");
            Pure = Link.get(i).replaceAll("&", "");
            Pure = Link.get(i).replaceAll(".", "");
            Pure = Link.get(i).replaceAll("/", "");
            if (Pure.contains(toFind) || Pure.contains(toFind2) || Pure.contains(toFind3)||Pure.contains(toFind4)||Pure.contains(toFind5)) {
                if (Link.get(i).contains("www")) {
                    System.out.println("I returned " + Link.get(i));
                    return Link.get(i);

                }
            }

        }
        return "";

    }

    private static void LoadWebSites() {

        String FileName = "./src/publicationnetworks/Maindata.txt";
        try {

            BufferedReader bufferReader = new BufferedReader(new FileReader(FileName));
            String Line = "";
            while ((Line = bufferReader.readLine()) != null) {
                WebSites.add(Line);
            }
            bufferReader.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al leer la linea del archivo:" + e.getMessage());
        }

    }

    //Functions to know where i am
//-----------------------------------------------------------------------------    
    private static boolean isPapersPage(String URL) throws IOException {

        return ParserHTML.isPapersPage(URL);
    }

    private static boolean isPaperBusinessPage(String URL) throws IOException {

        return ParserHTML.isPaperBusinessPage(URL);
    }

    //Functions to extract an specific info
//------------------------------------------------------------------------------    
    private static ArrayList<Paper> getPapers(String URL) throws IOException {

        return ParserHTML.getPapers(URL);
    }

 

}
