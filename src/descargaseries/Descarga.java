/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author alumno
 */
public class Descarga  extends Thread {
    private String url;

    public Descarga(String url)  {
            this.url = url;
    }

    @Override
    public void run() {
        try { 
            Document doc = Jsoup.connect(url).userAgent("mozilla").get();
            Document doc2 = Jsoup.connect(doc.getElementsByTag("a").select("[href^=http://www.animeyt.tv/descargar/]").attr("href")).userAgent("mozilla").get();
            String script2parse = doc2.getElementsByTag("script").last().html();
            String urldescarga = script2parse.substring(
                    script2parse.indexOf("{ url = \"")+("{ url = \"").length(), script2parse.indexOf("\";"));
            System.out.println("FINNN");
        } catch (IOException ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
