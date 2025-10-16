package dominio;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import javazoom.jl.player.Player;
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;


public class Sistema {
    
    
    private ArrayList<Jugador> listaJugadores;
    
    public Sistema(){
        this.listaJugadores = new ArrayList<>();
    }
    
    
    public ArrayList<Jugador> getListaJugadores(){
        return this.listaJugadores;
    }
    
    public void agregarJugador(Jugador unJugador){
        this.getListaJugadores().add(unJugador);
    }
    
    public void ordenarLista(ArrayList<Jugador> jugadores) {
    Collections.sort(jugadores, new Comparator<Jugador>() {
        @Override
        public int compare(Jugador jugador1, Jugador jugador2) {
            return jugador1.getNombre().compareToIgnoreCase(jugador2.getNombre());
        }
    });
    }
    
    public static void generarPDF(ArrayList<Jugador> jugadores) {
        Document documento = new Document();
        try {
            // Ruta y nombre del archivo PDF a crear
            PdfWriter.getInstance(documento, new 
        FileOutputStream("./juego.pdf"));
            documento.open();
            
            // Agregar contenido al archivo PDF
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                documento.add(new Paragraph("Nombre: " + jugador.getNombre()));
               documento.add(new Paragraph("Partidas jugadas: " + jugador.
                       getCantidadPartidasJugadas()));
                documento.add(new Paragraph("------"));
            }
            
            documento.close();
            System.out.println("Archivo PDF creado correctamente.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
       }
    }
    
   public void emitirSonido() {
        //try {
          //  FileInputStream fis = new FileInputStream("./sonido_gato.mp3");
            //BufferedInputStream bis = new BufferedInputStream(fis);
           // Player player = new Player(bis);
            //player.play();
        //} catch (Exception e) {
         //   e.printStackTrace();
        //}
    }

}
