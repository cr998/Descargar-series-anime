/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class DescargaSeries {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) { 
        if(System.getProperty("os.name").contains("Linux")){
            
            new Serie("http://www.animeyt.tv/12-sai-chicchana-mune-no-tokimeki");
            
        }else{
            System.out.println("El programa solo se puede ejecutar en linux");
        }

        
    }
    
}
