/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;


/**
 *
 * @author alumno
 */
public class DescargaSeries {

    public static String ruta="E:\\series\\";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        if(new File("/tmp").exists() && new File("/tmp").isDirectory()){
//            System.out.println("Se creara una carpeta temporal");
//            new File("/tmp").mkdirs();
//        }
//          
//        System.out.println("Escaneando");
//        ArrayList<Serie> series = ScanSeries.start();
//        
//        DownloaderManager down = new DownloaderManager("E:\\series\\", series);
//        down.start();
            
        new Serie("http://www.animeyt.tv/-summer",true).descargar(ruta);

    }
//
}
