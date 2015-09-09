/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicationnetworks;

/**
 *
 * @author BimbozZ
 */
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.script.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws ScriptException, IOException {

        createFile();

    }//end Main

    public static void createFile() throws MalformedURLException, IOException {

        /*TextTable Table = new TextTable(columnNames, data);                                                         
         Table.printTable(); */
        File input = new File("./src/publicationnetworks/Resources/result.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");

        Elements TB = doc.select("table");
        Elements TR = TB.select("tr");

        for (int i = 1; i < 10; i++) {

            Elements TD = TR.get(i).select("td");

            Elements LB;
            LB = TD.get(0).select("label");
            System.out.println("JOURNAL: " + LB.text());

            LB = TD.get(1).select("label");
            System.out.println("ARTICLE: " + LB.get(0).text());
            //get citations 

            int citations = getCitations(LB.get(0).text());
            System.out.println("Citations: " + citations);

            String td = TD.get(2).toString();
            td = CleanString(td);

            System.out.println("AUTHOR: ");
            String spaceSplit[] = td.split("<br>");

            for (int k = 0; k < spaceSplit.length; k++) {

                if (!spaceSplit[k].equals(" ")) {
                    System.out.println("         " + (k + 1) + ".-" + spaceSplit[k]);
                }
            }

            LB = TD.get(3).select("label");
            System.out.println("YEAR:    " + LB.text());

            LB = TD.get(4).select("label");
            System.out.println("VOLUMEN: " + LB.text());

            System.out.println("===============================================");
        }

    }

    public static String CleanString(String stringClean) {

        stringClean = stringClean.replaceAll("<td>", "");
        stringClean = stringClean.replaceAll("<label>", "");
        stringClean = stringClean.replaceAll("</label>", "");
        stringClean = stringClean.replaceAll("</td>", "");
        stringClean = stringClean.replaceAll("</b>", "");
        stringClean = stringClean.replaceAll("<b>", "");

        return stringClean;
    }

    public static int getCitations(String URL) throws MalformedURLException, IOException {

        int ready = 0;
        //connect to URL 
        URL = buildString(URL);
        URL url = new URL(URL);
        while (ready<3) {
            try {
                Document document = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();

                Elements Links = document.select("a");

                for (int i = 0; i < Links.size(); i++) {
                    if (Links.get(i).text().contains("Citado")) {
                        return Integer.parseInt(Links.get(i).text().replaceAll("[^0-9]", ""));
                    }
                }

                ready++;
            } catch (Exception E) {
                System.out.println("Connection Error try: "+ready);
                E.printStackTrace();
                ready++;
            }

        }//end while

        return 0;

    }

    public static String searchingGoogle(String Paper) throws MalformedURLException, UnsupportedEncodingException, IOException {

        //Connection conn = Jsoup.connect(url);
        String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String query = Paper;
        String charset = "UTF-8";

        URL url = new URL(address + URLEncoder.encode(query, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class
        );

        int total = results.getResponseData().getResults().size();

        System.out.println(
                "total: " + total);

        /*for(int i=0; i<=total-1; i++){
         System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
         System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");
 
         }*/
        return results.toString();
    }

    public static String buildString(String Paper) {

        String scholarBegin = "http://scholar.google.com/scholar?hl=es&q=";
        String scholarEnd = "&btnG=&as_sdt=1%2C5&as_sdtp=";
        Paper = Paper.replaceAll(" ", "+");
        String scholarLink = scholarBegin + Paper + scholarEnd;

        return scholarLink;
    }

}//end Class
