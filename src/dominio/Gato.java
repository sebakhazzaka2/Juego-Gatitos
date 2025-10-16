package dominio;


public class Gato{
    private boolean esGrande;
    private int posX;
    private String posY;
    private Jugador jugador;

    
    
    public Gato(boolean grande, int coordenadaX, String coordenadaY, Jugador unJugador){
        this.setTamano(grande);
        this.setPosX(coordenadaX);
        this.setPosY(coordenadaY);
        this.setJugador(unJugador);

    }
    
    public void setTamano(boolean tamano){
        this.esGrande = tamano;
    }
    
    
    public boolean getTamano(){
        return this.esGrande;
    }

    public int getPosX() {
        return posX;
    }
    
    public int getIndexPosX(){
        return (posX-1);
    }
    

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    
    public void setJugador(Jugador unJugador){
        this.jugador = unJugador;
    }
    
    public Jugador getJugador(){
        return this.jugador;
    }
    
    public int getIndexPosY(){
        String aux = this.getPosY();
        int pos = aux.toUpperCase().charAt(0);
        return pos-65;//resto 65 ya que el código ASCII de A es 65 y necesito que la coordenada A sea cero.
    }
    
}
