/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import descargaseries.Excepciones.UrlNoAdecuada;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author alumno
 */
public class Serie {

    private String name;
    private String urlcapitulo[];
    private int duracion;
    String url;

    public Serie(String url) {
        try {
            this.url = url;
            Document doc = Jsoup.connect(url).userAgent("mozilla").get();
  
            Elements vector = doc.getElementsByClass("serie-capitulos__list__item");
            urlcapitulo=new String[vector.size()];
            
            for (int i = 0; i < vector.size(); i++) {
                urlcapitulo[vector.size()-i-1]=(String)vector.get(i).child(1).attr("href");
            }
            
            for (int i = 0; i < urlcapitulo.length; i++) {
                System.out.println(urlcapitulo[i]);
            }
            
            duracion=urlcapitulo.length;
            //name=doc.getElementsByClass("serie-header__title").html();
//            Elements vector=doc.getElementsByClass("serie-capitulos__list__item");
//            for (int i = 0; i < vector.toArray().length; i++) {
//                System.out.println(vector.get(i).html());
//            }
            

        } catch (UrlNoAdecuada ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
