package dominio;


public class Caja{
    private int cantidadGatos;
    private int cantidadGatitos;
    
    public Caja(){
        this.setCantidadGatitos(8);
        this.setCantidadGatos(0);
    }

    public int getCantidadGatos() {
        return cantidadGatos;
    }

    public void setCantidadGatos(int cantidadGatos) {
        this.cantidadGatos = cantidadGatos;
    }

    public int getCantidadGatitos() {
        return cantidadGatitos;
    }

    public void setCantidadGatitos(int cantidadGatitos) {
        this.cantidadGatitos = cantidadGatitos;
    }

}

