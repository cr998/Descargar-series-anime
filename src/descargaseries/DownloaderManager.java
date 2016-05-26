/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cr998
 */
public class DownloaderManager extends Thread {

    String dirsalida;
    ArrayList<Serie> cola;

    public DownloaderManager(String dirsalida, ArrayList<Serie> cola) {
        this.dirsalida = dirsalida;
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            if (cola.size() != 0) {
                cola.get(0).descargar(dirsalida);
                cola.remove(0);
            } else {
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DownloaderManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
