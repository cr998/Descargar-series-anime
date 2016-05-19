/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries.Excepciones;

import java.net.MalformedURLException;

/**
 *
 * @author alumno
 */
public class UrlNoAdecuada extends MalformedURLException{

    public UrlNoAdecuada(String string) {
        super(string);
    }

    public UrlNoAdecuada() {
    }
    
    
    
}
