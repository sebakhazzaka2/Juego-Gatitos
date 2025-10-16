package dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


public class Colcha{
    private ArrayList<Gato> listaGatosEnColcha;
    private String diseno;
    private String[][] celdasOcupadas;
    private HashMap<String,String> colores;
    
    public Colcha(){
        this.setDisenoAleatorio();    
        this.listaGatosEnColcha = new ArrayList<>();
        this.celdasOcupadas = new String[6][6];
        this.iniciarCeldasOcupadas();
        this.colores = new HashMap<>();
        colores.put("Rojo","\u001B[31m");
        colores.put("Azul", "\u001B[34m");
        colores.put("reset", "\u001B[0m");
    }
    
    
    public void setDisenoAleatorio(){
        // obtenemos un diseño aleatorio el cual luego va a ser utilizado en tablero
        String [] disenos = {"*","#","0"," ","x"};
        int index = ThreadLocalRandom.current().nextInt(disenos.length);
        diseno = disenos[index];
    }
    
    public String getDiseno(){
        return this.diseno + " ";
    }
    
    ArrayList<Gato> getListaGatosEnColcha(){
        return listaGatosEnColcha;
    }
    
    
    public void quitarGatoSegunPosicion(int fila, int columna){
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if (g.getIndexPosY()== fila && g.getIndexPosX() == columna){
                it.remove();
            }
        }
        this.setCeldasOcupadas();        
    }
    

    
    public void cambiarPosicionGato(int filaInicial, int columnaInicial, 
            int filaNueva, int columnaNueva){
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if (g.getIndexPosY()== filaInicial && g.getIndexPosX() == columnaInicial){
                g.setPosX(columnaNueva + 1);
                g.setPosY(String.valueOf((char) (filaNueva+65)));
            }
        }
        this.setCeldasOcupadas();
    }
    
    
    
    public void iniciarCeldasOcupadas(){ 
        celdasOcupadas = new String[6][6];
        for(int i = 0; i <6; i++){
            for(int j = 0;j < 6; j++){
                celdasOcupadas[i][j] = "  ";
            }
        }
    }
    
    public void setCeldasOcupadas(){
        // ingresa los valores a la matriz que luego es utilizada para imprimir en tablero 
        // teniendo en cuenta las posiciones otorgadaas por la lista de gatos.
        this.iniciarCeldasOcupadas();
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            String aux = "";
            if(g.getTamano()){
                aux = colores.get(g.getJugador().getColor()) +"G " +colores.get("reset");
            } else{
                aux = colores.get(g.getJugador().getColor()) +"g " +colores.get("reset") ;
            }
            this.getCeldasOcupadas()[g.getIndexPosY()][g.getPosX()-1] = aux;
        }
    }

    
    public String[][] getCeldasOcupadas(){
        return this.celdasOcupadas;
    }
    
    public String colorTresEnLineaHorizontal(){
        //retorna el color de los gatos que estan en linea horizontal, en caso de 
        //corresponder a mas de uno lo retorna empate.
        String enLinea = "";
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length; i++){
            for(int j = 0; j < (a.length-2); j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i,j+1)) &&
                    a[i][j].equals(a[i][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i,j+2))){
                    enLinea = this.obtenerColorSegunPosicion(i, j);
                    if(!this.obtenerColorSegunPosicion(i,j).equals(enLinea)){
                        enLinea = "Empate";
                    }
                }
            }
        }
        return enLinea;
    }
    
    public ArrayList<Integer> posicionesTresHorizontal(){
        //retorna un ArrayList el cual se compone por las ubicaciones de los 
        // animales en linea
        ArrayList<Integer> listaPosiciones = new ArrayList<>();
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length; i++){
            for(int j = 0; j < (a.length-2); j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i,j+1)) &&
                    a[i][j].equals(a[i][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i,j+2))){
                    listaPosiciones.add(i);
                    listaPosiciones.add(j);
                    listaPosiciones.add(i);
                    listaPosiciones.add((j+1));
                    listaPosiciones.add(i);
                    listaPosiciones.add((j+2));
                }
            }
        }
        return listaPosiciones;
    }
    
    public String colorTresEnLineaVertical(){
        String enLinea = "";
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length-2; i++){
            for(int j = 0; j < (a.length); j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i+1][j]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+1,j)) &&
                    a[i][j].equals(a[i+2][j]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+2,j))){
                    enLinea= this.obtenerColorSegunPosicion(i, j);
                    if(!this.obtenerColorSegunPosicion(i,j).equals(enLinea)){
                        enLinea = "Empate";
                    }    
                }
            }
        }
        return enLinea;
    }

    public ArrayList<Integer> posicionesTresVertical(){
        ArrayList<Integer> listaPosiciones = new ArrayList<>();
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length-2; i++){
            for(int j = 0; j < (a.length); j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i+1][j]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+1,j)) &&
                    a[i][j].equals(a[i+2][j]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+2,j))){
                    listaPosiciones.add(i);
                    listaPosiciones.add(j);
                    listaPosiciones.add((i+1));
                    listaPosiciones.add(j);
                    listaPosiciones.add((i+2));
                    listaPosiciones.add(j);
                }
            }
        }
        return listaPosiciones;
    }
    
    
    public String colorTresEnLineaDiagonalAscendente(){
        String enLinea = "";
        String[][] a = this.getCeldasOcupadas();
        for(int i = a.length-1; i>1; i--){
            for(int j = 0; j < a.length-2; j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i-1][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i-1,j+1)) &&
                    a[i][j].equals(a[i-2][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i-2,j+2))){
                    enLinea= this.obtenerColorSegunPosicion(i, j);
                    if(!this.obtenerColorSegunPosicion(i,j).equals(enLinea)){
                        enLinea = "Empate";
                    }                      
                }
            }
        }
        return enLinea;
    }     
    
    public ArrayList<Integer> posicionesTresDiagonalAscendente(){
        ArrayList<Integer> listaPosiciones = new ArrayList<>();
        String[][] a = this.getCeldasOcupadas();
        for(int i = a.length-1; i>1; i--){
            for(int j = 0; j < a.length-2; j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i-1][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i-1,j+1)) &&
                    a[i][j].equals(a[i-2][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i-2,j+2))){
                    listaPosiciones.add(i);
                    listaPosiciones.add(j);
                    listaPosiciones.add((i-1));
                    listaPosiciones.add((j+1));
                    listaPosiciones.add((i-2));
                    listaPosiciones.add((j+2));
                }
            }
        }
        return listaPosiciones;
    }  
    
    public String colorTresEnLineaDiagonalDescendente(){
        String enLinea = "";
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length-2; i++){
            for(int j = 0; j < a.length-2; j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i+1][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+1,j+1)) &&
                    a[i][j].equals(a[i+2][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+2,j+2))){
                    enLinea= this.obtenerColorSegunPosicion(i, j);
                    if(!this.obtenerColorSegunPosicion(i,j).equals(enLinea)){
                        enLinea = "Empate";
                    }                      
                }
            }
        }
        return enLinea;
    }
    
    public ArrayList<Integer> posicionesTresDiagonalDescendente(){
        ArrayList<Integer> listaPosiciones = new ArrayList<>();
        String[][] a = this.getCeldasOcupadas();
        for(int i = 0; i< a.length-2; i++){
            for(int j = 0; j < a.length-2; j++){
                if(!a[i][j].equals("  ") && a[i][j].equals(a[i+1][j+1]) &&
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+1,j+1)) &&
                    a[i][j].equals(a[i+2][j+2]) && 
                    this.obtenerColorSegunPosicion(i,j).
                    equals(this.obtenerColorSegunPosicion(i+2,j+2))){
                    listaPosiciones.add(i);
                    listaPosiciones.add(j);
                    listaPosiciones.add((i+1));
                    listaPosiciones.add((j+1));
                    listaPosiciones.add((i+2));
                    listaPosiciones.add((j+2));                    
                }
            }
        }
        return listaPosiciones;
    }    
    
    public ArrayList<Integer> obtenerTodasLasPosicionesEnLinea(){
        ArrayList<Integer> listaPosiciones = new ArrayList<>();
        listaPosiciones.addAll(this.posicionesTresDiagonalAscendente());
        listaPosiciones.addAll(this.posicionesTresDiagonalDescendente());
        listaPosiciones.addAll(this.posicionesTresHorizontal());
        listaPosiciones.addAll(this.posicionesTresVertical());
        
        return listaPosiciones;
    }
     
    
    public String obtenerColorSegunPosicion(int posY, int posX){
        String color = "";
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if (g.getIndexPosX() == posX && g.getIndexPosY() == posY){
                color = g.getJugador().getColor();
            }
        }
        return color;
    }
    
    public String obtenerNombreSegunPosicion(int posY, int posX){
        String nombre = "";
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if (g.getIndexPosX() == posX && g.getIndexPosY() == posY){
                nombre = "El ganador es: " + g.getJugador().getNombre();
            }
        }
        return nombre;
    }
    
    public Gato obtenerGatoSegunPosicion(int posY, int posX){
        Gato gato = null;
        Iterator<Gato> it = this.getListaGatosEnColcha().iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if (g.getIndexPosX() == posX && g.getIndexPosY() == posY){
                gato = g;
            }
        }
        return gato;
    }
    

}
