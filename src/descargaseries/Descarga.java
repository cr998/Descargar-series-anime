/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descargaseries;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author alumno
 */
public class Descarga extends Thread {

    private String url;

    public Descarga(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(url).userAgent("mozilla").get();
            Document doc2 = Jsoup.connect(doc.getElementsByTag("a").select("[href^=http://www.animeyt.tv/descargar/]").attr("href")).userAgent("mozilla").get();
            String script2parse = doc2.getElementsByTag("script").last().html();
            String urldescarga = script2parse.substring(script2parse.indexOf("{ url = \"") + ("{ url = \"").length(), script2parse.indexOf("\";"));

            if (urldescarga.contains("mega")) {
                throw new Exception("Enlace de mega no implementado");
            }

            enlaceNormal(urldescarga);
        } catch (IOException ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String enlaceNormal(String url) {
        String endes="null";
        try {
            URL link=new URL(url);
            InetAddress address = InetAddress.getByName(link.getHost());
            String ip = address.getHostAddress();
            
            System.out.println(url);

            Socket sc = new Socket(ip, 80);
            String peticion = "GET "+link.getFile()+" HTTP/1.1\n";
            peticion += "Accept: */*\n";
            peticion += "Accept-Language: es\n";
            peticion += "Accept-Encoding: gzip, deflate\n";
            peticion += "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36\n";
            peticion += "Host: "+link.getHost()+"\n";
            peticion += "Connection: Keep-Alive\n\n";
            DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
            salida.write(peticion.getBytes());
            System.out.println(peticion);
            DataInputStream entrada =new DataInputStream(sc.getInputStream());
            InputStreamReader isr =new InputStreamReader(entrada);
            BufferedReader br=new BufferedReader(isr);
            String linea=br.readLine();
            while(!linea.contains("Location")){
                linea=br.readLine();
            }
            
            endes=linea.split(" ")[1];
            br.close();
            isr.close();
            entrada.close();
            salida.close();
            
            URL url2=new URL(endes);
            URLConnection con = url2.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            con.connect();
            DataInputStream entrada2 =new DataInputStream(con.getInputStream());
            InputStreamReader isr2 =new InputStreamReader(entrada2);
            BufferedReader br2=new BufferedReader(isr2);
            String linea2=br2.readLine();
            while(linea2!=null){
                linea2=br2.readLine();
                System.out.println(linea2);
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return endes;
    }
}
