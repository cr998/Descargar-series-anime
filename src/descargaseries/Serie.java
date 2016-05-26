/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author alumno
 */
public class Serie {

    private String name;
    private String urlcapitulo[];
    private int capitulos;
    private String url;
    private String[] categorias;

    public Serie(String url, boolean get) {
        this.url = url;
        if (get) {
            getData();
        }
    }

    public Serie(String url) {
        this.url = url;
    }

    public boolean isDescargable() {
        if (capitulos < 30) {
            return true;
        } else {
            return false;
        }
    }

    public void descargar(String dirsalida) {

        if (urlcapitulo == null) {
            System.out.println("olle, no has obtenido los datos antes de descargar");
        } else {
            for (int i = 0; i < urlcapitulo.length; i++) {
                new Descarga(urlcapitulo[i], dirsalida+this.name+"\\").run();
            }
        }

    }

    public void getData() {
        try {
            Document doc = Jsoup.connect(url).userAgent("mozilla").get();

            this.name = "";
            for (String string : url.split("/")[3].split("-")) {
                name += string + " ";
            }

            Elements vector = doc.getElementsByClass("serie-capitulos__list__item");
            urlcapitulo = new String[vector.size()];
            capitulos = urlcapitulo.length;
            
            
            for (int i = 0; i < vector.size(); i++) {
                urlcapitulo[vector.size() - i - 1] = (String) vector.get(i).child(1).attr("href");
                
            }


            //name=doc.getElementsByClass("serie-header__title").html();
//            Elements vector=doc.getElementsByClass("serie-capitulos__list__item");
//            for (int i = 0; i < vector.toArray().length; i++) {
//                System.out.println(vector.get(i).html());
//            }
        } catch (IOException ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public String[] getUrlcapitulo() {
        return urlcapitulo;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public String[] getCategorias() {
        return categorias;
    }

}
