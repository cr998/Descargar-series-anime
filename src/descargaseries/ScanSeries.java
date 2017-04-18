/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author cr998
 */
public class ScanSeries {

    public static ArrayList<Serie> start() {
        ArrayList<Serie> series=new ArrayList();
        
        try {
            int page  = 1;
            Serie[] seriespage;
            while (true) {
                seriespage= getUrlSeries(page);
                for (int j = 0; j < seriespage.length; j++) {
                    series.add(seriespage[j]);
                }

                page++;
                
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return series;
    }

    public static Serie[] getUrlSeries(int i) throws ArrayIndexOutOfBoundsException {
        Serie[] seriesperpage=null;
        
        try {
            Document doc = Jsoup.connect("http://www.animeyt.tv/animes/?page=" + i).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36s").get();
            Elements animes = doc.getElementsByClass("anime__img-container");

            if (animes.size() <= 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            
            Iterator<Element> ite = animes.iterator();
            seriesperpage= new Serie[animes.size()];
            int e=0;
            while(ite.hasNext()){
                //seriesperpage[e]=new Serie(ite.next().attr("href"),true);
                System.out.println(seriesperpage[e].getName());
                e++;
            }

        } catch (IOException ex) {
            Logger.getLogger(ScanSeries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return seriesperpage;
    }
}
