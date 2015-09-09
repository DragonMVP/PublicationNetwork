/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package publicationnetworks;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author David Discua
 */
public class LinksExtractor {//inicio main

    
    private ArrayList<String>Links;

    
    public LinksExtractor(String URL) throws MalformedURLException, IOException{
        Links = new ArrayList();
        URL url = new URL(URL);
        Document document = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
     .get();
              
        Elements links = document.select("a");
        for (Element linki : links) {
            Links.add(linki.attr("href"));
            Links.add(linki.absUrl("href"));
        }
    
    }
    
    public ArrayList<String> getLinks() {
        return Links;
    }

    public void setLinks(ArrayList<String> Links) {
        this.Links = Links;
    }

  
  
}//fin clase main
