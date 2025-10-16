package interfaz;

import dominio.*;
import java.util.HashMap;
import java.util.Random;

public class Tablero {

    Colcha colcha;
    public HashMap<String, String> colorTablero;
    public String color;
    
    public Tablero(Colcha colcha) {
        this.colcha = colcha;
        colorTablero = new HashMap<>();
        colorTablero.put("Violeta", "\u001B[35m");
        colorTablero.put("Blanco", "\u001B[37m");
        colorTablero.put("Verde", "\u001B[32m");
        colorTablero.put("Amarillo", "\u001B[33m");
        colorTablero.put("Cyan", "\u001B[36m");
        colorTablero.put("reset", "\u001B[0m");
        color = obtenerColorAleatorio();
    }

    public void mostrarTablero() {
        String[][] mat = colcha.getCeldasOcupadas();
        String pat = colcha.getDiseno();
       
        
        String[] letrasTablero = {"A ", "B ", "C ", "D ", "E ", "F "};
        System.out.println( "      1       2       3       4       5       6" );
        System.out.println(colorTablero.get(color) + "  * - - - * - - - * - - - * - - - * - - - * - - - *" + colorTablero.get("reset"));
        
        for (int i = 0; i < 6; i++) {

            System.out.println(colorTablero.get(color) + "  | " + pat + pat + pat + "| " + pat + pat + pat + "| " + pat + pat + pat
                    + "| " + pat + pat + pat + "| " + pat + pat + pat + "| " + pat + pat + pat +   "|" + colorTablero.get("reset"));
            System.out.println( letrasTablero[i] + colorTablero.get(color) + "| " +  pat + mat[i][0] + colorTablero.get(color) + pat + "| "
                    + pat + mat[i][1] + colorTablero.get(color) + pat + "| " + pat + mat[i][2] + colorTablero.get(color) + pat + "| " + pat + mat[i][3] + colorTablero.get(color) +
                     pat + "| " + pat + mat[i][4] + colorTablero.get(color) + pat + "| " + pat + mat[i][5] + colorTablero.get(color) + pat + "|" + colorTablero.get("reset"));
            System.out.println(colorTablero.get(color) + "  | "  + pat + pat + pat + "| " + pat + pat + pat + "| " + pat + pat + pat
                    + "| " + pat + pat + pat + "| " + pat + pat + pat + "| " + pat + pat + pat + "|" + colorTablero.get("reset"));
            System.out.println(colorTablero.get(color) + "  * - - - * - - - * - - - * - - - * - - - *" + " - - - *" + colorTablero.get("reset"));
        }
    }

    public String obtenerColorAleatorio() {
        String[] colores = {"Violeta", "Blanco", "Verde", "Amarillo", "Cyan"};
        Random random = new Random();
        int indice = random.nextInt(colores.length);
        return colores[indice];
    }
    

    
}
