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
        
        DownloadHandler down = new DownloadHandler();
        

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");
            Statement stmt = c.createStatement();
            String sql="SELECT id, url FROM Series WHERE descargada = 0 AND duracion <=20";
            ResultSet r = stmt.executeQuery(sql);
            int i=0;
            while(r.next()){
                i++;
                Serie s = new Serie(r.getInt("id"),r.getString("url"),true);
                System.out.println(s.getName());
                Descarga[] des = s.descargar(ruta);
                for (int j = 0; j < des.length; j++) {
                    down.addDownload(des[j]);
                }
            }
            
            down.start();
            
        } catch (SQLException ex) {
            Logger.getLogger(DownloadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
}
