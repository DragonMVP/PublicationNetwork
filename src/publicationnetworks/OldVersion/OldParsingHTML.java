/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicationnetworks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author BimbozZ
 */
public class OldParsingHTML {

    URL url;
    String Agent;

    public OldParsingHTML() {

        Agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    }

    public boolean isPapersPage(String URL) throws MalformedURLException, IOException {
        url = new URL(URL);
        Document document = Jsoup.connect(url.toString()).userAgent(Agent)
                .get();

        String Title = document.select("title").text().toLowerCase();
        return Title.contains("publications") || Title.contains("papers");
    }

    public boolean isFacultyPage(String URL) throws MalformedURLException, IOException {
        url = new URL(URL);
        Document document = Jsoup.connect(url.toString()).userAgent(Agent)
                .get();
        String Title = document.select("title").text().toLowerCase();
        return Title.contains("faculty") || Title.contains("research");
    }

    public boolean isPaperBusinessPage(String URL) throws MalformedURLException, IOException {
        url = new URL(URL);
        Document document = Jsoup.connect(url.toString()).userAgent(Agent)
                .get();

        return false;
    }

    //Functions to extract an specific info
//------------------------------------------------------------------------------    
    public ArrayList<Paper> getPapers(String URL) throws MalformedURLException, IOException {
        //Connection
        url = new URL("https://bepp.wharton.upenn.edu/research/research-listing/");
        Document document = Jsoup.connect(url.toString()).userAgent(Agent)
                .get();

        //Paper Iteration Particular Case : Class Publication
        System.out.println("Iterando");
        Document doc = Jsoup.parse(document.toString());
        Element content = doc.getElementById("content");
        ArrayList<Paper> Papers = new ArrayList();
        
        //getTag(content.toString());
        Elements insideContent = content.getElementsByClass("publicationCitation");

        
        for (int i = 0; i < insideContent.size(); i++) {//inicio for  
            Paper currentPaper = new Paper();
            //System.out.println("linkText: " + insideContent.get(i).text());            
            String[] ContentSplit = insideContent.get(i).text().split(",");
            Elements insideTag = insideContent.get(i).getElementsByTag("strong");
            currentPaper.setName(insideTag.get(0).text());
            for (int j = 0; j < ContentSplit.length; j++) {
                
                Author currentAuthor = new Author();
                if (ContentSplit[j].length() > 5 && ContentSplit[j].length() < 25) {//is author name                    
                    //clean string  
                    String[] cleanerAuthor = ContentSplit[j].split(Pattern.quote("("));
                    if (Character.isUpperCase(cleanerAuthor[0].charAt(0))||cleanerAuthor[0].charAt(0)==' ') {
                        currentAuthor.setName(cleanerAuthor[0]);
                        currentPaper.setAuthor(currentAuthor);                        
                        /*if (currentPaper.getAuthor().isEmpty()) {
                            System.out.println("Tamaño 0");
                            currentAuthor.setName(cleanerAuthor[0]);
                            currentPaper.setAuthor(currentAuthor);
                        } else {
                            System.out.println("Validar");
                            for (int k = 0; k < currentPaper.getAuthor().size(); k++) {
                                if (!currentPaper.getAuthor().get(k).equals(cleanerAuthor[0])) {
                                    currentAuthor.setName(cleanerAuthor[0]);
                                    currentPaper.setAuthor(currentAuthor);

                                }
                            }

                        }*/

                    }

                }

                
               
            }//fin for

            //to get more specific information
            //4Elements insideTag = insideContent.get(i).getElementsByTag("a");
            /*for (int j = 0; j <insideTag.size()-insideTag.size(); j++) {//

             Author currentAuthor= buildAuthor(insideTag.get(j));
             currentPaper.setAuthor(currentAuthor);
                
             }*///fin for 
            Papers.add(currentPaper);
        }//fin for

        //printt Papers
        for (int i = 0; i < Papers.size(); i++) {//inincio for

            System.out.println("Paper NAME : " + Papers.get(i).getName());
            System.out.print("Authors: ");
            for (int j = 0; j < Papers.get(i).getAuthor().size(); j++) {

                System.out.print(Papers.get(i).getAuthor().get(j).getName() + ",");

            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("-----------------------------------------------");
        }//fin for
        return Papers;
    }

    public Author buildAuthor(Element insideTag) throws MalformedURLException, IOException {

        Author A = new Author();
        System.out.println("Elemento :" + insideTag);
        //  String linkHref = insideTag.attr("href");
        // String urlAbs= insideTag.absUrl("href");
        String linkText = insideTag.text();
        // System.out.println("Lunk HREF: "+linkHref);
        System.out.println("linkText: " + linkText);
        //  System.out.println("urlAbs: "+urlAbs);
        A.Name = linkText;

        return A;
    }

    //extrac HTML Structure
    public void getHTMlStrcuture(String HTML) {

    }

    public boolean isPaperInfo() {

        return false;
    }

    public String getTag(String HTML) {

        return null;
    }

}//fin clase
