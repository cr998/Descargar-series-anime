/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author alumno
 */
public class DescargaSeries {

    public static String ruta = "C:\\Users\\cr998\\Desktop\\series\\";
    public static Connection c;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serie s=new Serie(1,"http://www.animeyt.tv/renai-boukun",true);
        Descarga[] des = s.descargar("/Users/cr998/Desktop/series/1/");
        
        for (int i = 0; i < des.length; i++) {
            Descarga de = des[i];
            de.run();
        }
    }
//
}
