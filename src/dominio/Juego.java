package dominio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;



public class Juego {

    private Sistema sistema;
    private Jugador jugadorAzul;
    private Jugador jugadorRojo;
    private Colcha colcha;
    private boolean juegoCompleto;
    private Jugador turno;
    private Caja cajaRojo;
    private Caja cajaAzul;  
    private boolean turnoRojo;
    
    public Juego(){
        this.sistema = new Sistema();
        this.colcha = new Colcha();
        this.cajaAzul = new Caja();
        this.cajaRojo = new Caja();
        this.turnoRojo = true;
    }
    
    
    public void inicializarJuegoSimple(){

    }
    
    public Colcha getColcha(){
        return this.colcha;
    }
    
    
    public void setColcha(Colcha unaColcha){
        this.colcha = unaColcha;
    }
    
    public Sistema getSistema(){
        return this.sistema;
    }

    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }

    public void setJugadorAzul(String nombre, int edad, String alias) {
        this.jugadorAzul = new Jugador(nombre,edad,alias);
        this.jugadorAzul.setColor("Azul");
    }

    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }

    public void setJugadorRojo(String nombre, int edad, String alias) {
        this.jugadorRojo = new Jugador(nombre,edad,alias);
        this.jugadorRojo.setColor("Rojo");
    }

    public Caja getCajaRojo() {
        return cajaRojo;
    }
   

    public Caja getCajaAzul() {
        return cajaAzul;
    }    
    
    public boolean getTurnoRojo(){
        return this.turnoRojo;
    }
    
    public void cambiarTurno(){
        this.turnoRojo = !this.getTurnoRojo();
    }

    public String obtenerTurno(){
        String turno = "Azul";
        if(this.getTurnoRojo()){
            turno = "Rojo";
        }
        return turno;
    }
    
    public String colorTresGatosEnLinea(ArrayList<Integer> listaPosiciones){
        // Verifico el color en caso de existir tres gatos grandes en linea
        String ganador = " ";
        int coordenada = 0;
        if(!listaPosiciones.isEmpty()){
            for(int j = 0; j < listaPosiciones.size(); j = j+6){
                int cantidadGrande = 0;
                for(int i = j; i < j+6; i = i+2){
                    Gato gato = null;
                    gato=this.getColcha().obtenerGatoSegunPosicion
                (listaPosiciones.get(i),listaPosiciones.get(i+1));
                    if(gato.getTamano()){
                        cantidadGrande++;
                        coordenada = i;
                    }
                }
                if(cantidadGrande>2){
                    if(ganador.equals(" ")){
                        ganador = this.getColcha().obtenerColorSegunPosicion
                        (listaPosiciones.get(coordenada), listaPosiciones.
                        get(coordenada+1));
                    } else{
                        if(!this.getColcha().obtenerColorSegunPosicion
                        (listaPosiciones.get(coordenada), listaPosiciones.
                        get(coordenada+1)).equals(ganador)){
                            ganador = "Empate";
                        }
                    }
                }    
            }    
        }    
        return ganador;
    }
    
    
    public void ingresarMovimientoSimple(){
        Scanner in = new Scanner(System.in);
        boolean valido = false;
        String movimiento = "";
        while(!valido){
            movimiento = in.nextLine();
            String[][] aux = this.getColcha().getCeldasOcupadas();
            int y = movimiento.toUpperCase().charAt(0) - 65;
            int x = movimiento.charAt(1) - 49;
            if(aux[y][x].equals("  ")){
                if(movimiento.length()==2){
                    int valor1 = movimiento.toUpperCase().charAt(0);
                    int valor2 = movimiento.charAt(1);
                    if(valor1<71 && valor1 > 64 && valor2 < 55 && valor2 > 48){ 
        //validamos que el codigo ASCII corresponde al de las letras A a la F. 
                        valido = true;
                    } else{
                        System.out.println("Ingrese una fila de la A a la F y una "
                                + "columna del 1 al 6");                
                    }
                } else{
                    System.out.println("Ingrese una fila de la A a la F y una "
                            + "columna del 1 al 6");                  
                }
            }else{
                System.out.println("Celda ocupada, vuelva a ingresar.");
            }    
        }
        String coordenadaY = movimiento.charAt(0)+"";
        int coordenadaX = movimiento.charAt(1)-48;
        Jugador movimientoDe = new Jugador();
        if(this.getTurnoRojo()){
            movimientoDe = this.getJugadorRojo();
        } else {
            movimientoDe = this.getJugadorAzul();
        }
        this.agregarGatoColchaSimple(coordenadaY, coordenadaX, 
                movimientoDe, false);
        if(this.getTurnoRojo()){
            this.getCajaRojo().setCantidadGatitos(this.getCajaRojo().
                    getCantidadGatitos()-1);
        } else{
            this.getCajaAzul().setCantidadGatitos(this.getCajaAzul().
                    getCantidadGatitos()-1);            
        }
        
    }
    
    
    public void ingresarMovimientoFull(){
        Scanner in = new Scanner(System.in);
        boolean valido = false;
        String movimiento = "";
        while(!valido){
            movimiento = in.nextLine();
            String[][] aux = this.getColcha().getCeldasOcupadas();
            int y = movimiento.toUpperCase().charAt(0) - 65; // restamos
            //65 para obtener la posicion equivalente a la letra.
            int x = movimiento.charAt(1) - 49; // restamos 49 para obtener 
            //la posicion equivalente al numero ingresado como string.
            if(aux[y][x].equals("  ")){
                if(movimiento.length()==3){
                    int cantidadGatos;
                    int cantidadGatitos;
                    if(this.getTurnoRojo()){
                        cantidadGatos = this.getCajaRojo().
                                getCantidadGatos();
                        cantidadGatitos = this.getCajaRojo().
                                getCantidadGatitos();
                    } else {
                        cantidadGatos = this.getCajaAzul().
                                getCantidadGatos();  
                        cantidadGatitos = this.getCajaAzul().
                                getCantidadGatitos();
                    }
                    int valor1 = movimiento.toUpperCase().charAt(0);
                    int valor2 = movimiento.charAt(1);
                    String valor3 = movimiento.charAt(2)+"";
                    if((valor3.equals("G") && cantidadGatos > 0) || 
                            (valor3.equals("g") && cantidadGatitos>0)){
                        if(valor1<71 && valor1 > 64 && valor2 < 55 && valor2 > 48 
                                && valor3.equalsIgnoreCase("g")){ //validamos que el codigo ASCII corresponde al de las letras A a la F. 
                            valido = true;
                        } else{
                            System.out.println("Ingrese una fila de la A a la"
                                    + " F y una columna del 1 al 6");                
                        }
                    } else{
                        System.out.println("No tiene a disposicion");
                    }   
                } else{
                    System.out.println("Ingrese una fila de la A a la F y una "
                            + "columna del 1 al 6 y 'G' o 'g' según el tamaño del "
                            + "gato");                  
                }
            } else {
                System.out.println("Celda ocupada, vuelva a ingresar.");
            }    
        }
        String coordenadaY = movimiento.charAt(0)+"";
        int coordenadaX = movimiento.charAt(1)-48;
        String letra = movimiento.charAt(2)+"";
        boolean tamano = false;
        if(letra.equals("G")){
            tamano = true;
        }
        Jugador movimientoDe = new Jugador();
        if(this.getTurnoRojo()){
            movimientoDe = this.getJugadorRojo();
        } else {
            movimientoDe = this.getJugadorAzul();
        }
        this.agregarGatoColchaSimple(coordenadaY, coordenadaX, 
                movimientoDe, tamano);
        if(this.getTurnoRojo() && !tamano){
            this.getCajaRojo().setCantidadGatitos(this.getCajaRojo().
                    getCantidadGatitos()-1);
        } else{
            if(!tamano){
                this.getCajaAzul().setCantidadGatitos(this.getCajaAzul().
                getCantidadGatitos()-1);
            }    
        }
        if(this.getTurnoRojo() && tamano){
            this.getCajaRojo().setCantidadGatos(this.getCajaRojo().
                    getCantidadGatos()-1);
        } else{
            if(tamano){
                this.getCajaAzul().setCantidadGatos(this.getCajaAzul().
                getCantidadGatos()-1);
            }    
        } 
    }
    

    
    public String verificarGanador(){
        //Obtenemos ganador en caso de existir, si hay mas de un color con tres 
        //en linea implica un empate.
        String ganador = "No";
        int cant = 0;
        if(!this.getColcha().colorTresEnLineaDiagonalAscendente().equals("")){
            ganador = this.getColcha().colorTresEnLineaDiagonalAscendente();
            if(!this.getColcha().colorTresEnLineaDiagonalAscendente().equals(ganador)){
                ganador = "Empate";
            }
        }
        if(!this.getColcha().colorTresEnLineaDiagonalDescendente().equals("")){
            ganador = this.getColcha().colorTresEnLineaDiagonalDescendente();
            if(!this.getColcha().colorTresEnLineaDiagonalDescendente().equals(ganador)){
                ganador = "Empate";
            }            
        }
        if(!this.getColcha().colorTresEnLineaHorizontal().equals("")){
            ganador = this.getColcha().colorTresEnLineaHorizontal();
            if(!this.getColcha().colorTresEnLineaHorizontal().equals(ganador)){
                ganador = "Empate";
            }                   
        }
        if(!this.getColcha().colorTresEnLineaVertical().equals("")){
            ganador = this.getColcha().colorTresEnLineaVertical();
            if(!this.getColcha().colorTresEnLineaVertical().equals(ganador)){
                ganador = "Empate";
            }                   
        }
        if(this.getCajaRojo().getCantidadGatitos() == 0){
            ganador = "Azul";
        }
        if(this.getCajaAzul().getCantidadGatitos() == 0){
            ganador = "Rojo";
        }
        return ganador;
    }
    
    public String verificarGanadorFull(){
        String ganador = "No";
        ArrayList<Integer> lista = this.getColcha().obtenerTodasLasPosicionesEnLinea();
        String aux = this.colorTresGatosEnLinea(lista);
        if (!aux.equals(" ")){
            ganador = aux;
        }
        return ganador;
    }
    
    public void quitarTresGatosIndicados(String inicio, String fin){
        int filaInicio = inicio.toUpperCase().charAt(0)-65;
        int filaFinal = fin.toUpperCase().charAt(0)-65;
        int columnaInicio = Character.getNumericValue(inicio.charAt(1))-1;
        int columnaFinal = Character.getNumericValue(fin.charAt(1))-1;
        if((columnaInicio == columnaFinal)){
            for(int i = filaInicio; i <= filaFinal; i++){
                this.getColcha().quitarGatoSegunPosicion(i, columnaFinal);
            }
        }
        if(filaInicio == filaFinal){
            for(int j = columnaInicio; j <= columnaFinal; j++){
                this.getColcha().quitarGatoSegunPosicion(filaFinal, j);
            }
        } else {
            if (filaFinal >filaInicio){
                for(int i = filaInicio; i<= filaFinal;i++){
                    for(int j = columnaInicio; j<=columnaFinal;j++){
                        if((i-j) == (filaInicio-columnaInicio)){
                            this.getColcha().quitarGatoSegunPosicion(i, j);
                        }
                    }
                }
            } else {
                for(int i = filaInicio; i<= filaFinal;i++){
                    for(int j = columnaInicio; j>=columnaFinal;j--){
                        if((i-j) == (filaInicio-columnaInicio)){
                            this.getColcha().quitarGatoSegunPosicion(i, j);
                        }
                    }
                }
            }    
        }
        if(this.getTurnoRojo()){
            int cantidadGatos = this.getCajaRojo().getCantidadGatos();
            this.getCajaRojo().setCantidadGatos(cantidadGatos+3);
        } else {
            int cantidadGatos = this.getCajaAzul().getCantidadGatos();
            this.getCajaAzul().setCantidadGatos(cantidadGatos+3);
        }
    }
    
    public boolean esRetiroEnLineaValido(String inicio, String fin){
        //validamos el retiro de las lineas que esta ingresando el usuario
        boolean valido = true;
        String turno =this.obtenerTurno();
        int filaInicio = inicio.toUpperCase().charAt(0)-65;
        int filaFinal = fin.toUpperCase().charAt(0)-65;
        int columnaInicio = Character.getNumericValue(inicio.charAt(1))-1;
        int columnaFinal = Character.getNumericValue(fin.charAt(1))-1;
        if((columnaInicio == columnaFinal)){
            for(int i = filaInicio; i <= filaFinal; i++){
                if(!this.getColcha().obtenerColorSegunPosicion(i, 
                        columnaFinal).equals(turno)){
                    valido = false;
                }
            }
        }
        if(filaInicio == filaFinal){
            for(int j = columnaInicio; j <= columnaFinal; j++){
                if(!this.getColcha().obtenerColorSegunPosicion(filaFinal,
                        j).equals(turno)){
                    valido = false;
                }
            }
        }
        if (filaFinal >filaInicio){
            for(int i = filaInicio; i<= filaFinal;i++){
                for(int j = columnaInicio; j<=columnaFinal;j++){
                    if((i-j) == (filaInicio-columnaInicio)){
                        if(!this.getColcha().obtenerColorSegunPosicion
                        (i,j).equals(turno)){
                            valido = false;
                        }
                    }
                }
            }
        } else {
            for(int i = filaInicio; i<= filaFinal;i++){
                for(int j = columnaInicio; j>=columnaFinal;j--){
                    if((i-j) == (filaInicio-columnaInicio)){
                        if(!this.getColcha().obtenerColorSegunPosicion
                        (i,j).equals(turno)){
                            valido = false;
                        }
                    }
                }
            }
        } 
        return valido;
    }
    
    public String correspondeRetirarGatos(){
        // Otorga el mensaje que corresponde retirar gatos en caso de que exista tres
        //en linea en el juego completo.
        String mensaje =" ";
        String ganador = this.verificarGanadorFull();
        ArrayList<Integer> lista = this.getColcha().obtenerTodasLasPosicionesEnLinea();
        if(ganador.equals("No") && !lista.isEmpty()){
            mensaje = "Corresponde retirar 3 gatos";
        }
        return mensaje;
    }
    
    public int cantidadAnimalesEnColcha(){
        int cant = 0;
        ArrayList<Gato> lista = this.getColcha().getListaGatosEnColcha();
        Iterator<Gato> it = lista.iterator();
        while(it.hasNext()){
            Gato g = it.next();
            if(g.getJugador().getColor().equals(this.obtenerTurno())){
                cant++;
            }
        }
        return cant;
    }

    
    
    public Jugador obtenerJugadorPorAlias(String unAlias){
        Jugador jugador = new Jugador();
        jugador = null;
        Iterator<Jugador> it = this.sistema.getListaJugadores().iterator(); 
        while(it.hasNext()){
            Jugador j = it.next();
            if(j.getAlias().toUpperCase().equals(unAlias.toUpperCase())){
                jugador = j;
            } 
        }
        return jugador;
    }
    
    public void elegirJugadores(){
        Scanner in = new Scanner (System.in);
        boolean nombreValido = false;
        while(!nombreValido){
            System.out.println("Ingrese alias del jugador rojo");
            String aux = in.nextLine();
            if(this.obtenerJugadorPorAlias(aux) !=null){
                this.setJugadorRojo(this.
                    obtenerJugadorPorAlias(aux).getNombre(),this.
                    obtenerJugadorPorAlias(aux).getEdad(),this.
                    obtenerJugadorPorAlias(aux).getAlias());
                nombreValido = true;
                this.agregarCantidadPartidas(aux);
            } else{
                System.out.println("El alias ingresado no está registrado en "
                        + "el sistema. Por favor, verifique o regístrese como "
                        + "nuevo jugador.");

            }
        }
        nombreValido = false;
        while(!nombreValido){
            System.out.println("Ingrese alias del jugador azul");
            String aux = in.nextLine();
            if(this.obtenerJugadorPorAlias(aux) !=null){
                this.setJugadorAzul(this.
                    obtenerJugadorPorAlias(aux).getNombre(),this.
                    obtenerJugadorPorAlias(aux).getEdad(),this.
                    obtenerJugadorPorAlias(aux).getAlias());
                nombreValido = true;
                this.agregarCantidadPartidas(aux);
            } else{
                System.out.println("El alias ingresado no está registrado en "
                        + "el sistema. Por favor, verifique o regístrese como "
                        + "nuevo jugador.");
            }
        }   
      
    }    
    
    
    public void registroJugador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("   REGISTRAR JUGADOR   ");
        System.out.print("Ingrese un nombre: ");
        String nombre = scanner.nextLine();
        int edad = 0;
        boolean entradaValida = false;
        while (!entradaValida){
            System.out.print("Ingrese edad: ");            
            try {
                edad = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("La edad debe ser un numero. Vuelva a "
                        + "ingresar");
                scanner.nextLine();
            }
        }    
        scanner.nextLine();
        String alias;
        boolean unicoAlias = false;
        do {
            System.out.print("Ingrese su alias: ");
            alias = scanner.nextLine();

            if (this.getSistema().getListaJugadores() == null || this.getSistema().getListaJugadores().isEmpty()) {
                unicoAlias = true;
            } else {
                boolean aliasDiferente = true;
                for (int i = 0; i < this.getSistema().getListaJugadores().size() && aliasDiferente; i++) {
                    Jugador jugador = this.getSistema().getListaJugadores().get(i);
                    if (jugador.getAlias().equalsIgnoreCase(alias)) {
                        System.out.print("El alias ingresado ya está siendo utilizado, porfavor ingrese uno nuevo");
                        System.out.println("");
                        aliasDiferente = false;
                    }
                }
                if (aliasDiferente) {
                    unicoAlias = true;
                }
            }
        } while (!unicoAlias);
        Caja caja = new Caja();  // Al momento de cargar Version simple o full, se determina la cantidad de gatitos iniciales en las cajas.
        Jugador jugador = new Jugador(nombre, edad, alias);
        this.getSistema().getListaJugadores().add(jugador);
        System.out.println("El jugador fue registrado con éxito");
    }
    
    public void imprimirCantidadesFull(){
            System.out.println("Gatitos rojos: " + this.getCajaRojo().
                    getCantidadGatitos());
            System.out.println("Gatitos azules: " + this.getCajaAzul().
                    getCantidadGatitos());
            System.out.println("Gatos rojos: " + this.getCajaRojo().
                    getCantidadGatos());
            System.out.println("Gatos azules: " + this.getCajaAzul().
                    getCantidadGatos());        
    }
    
    public void imprimirCantidadesSimple(){
        System.out.println("Gatitos rojos: " + this.getCajaRojo().
                getCantidadGatitos());
        System.out.println("Gatitos azules: " + this.getCajaAzul().
                getCantidadGatitos());
       
    }
    
    public void reglasEspeciales(){
        Scanner in = new Scanner(System.in);
        if(this.cantidadAnimalesEnColcha() == 8){
            boolean valido = false;
            while(!valido){
                System.out.println("Tiene 8 animales en juego, ingrese regla "
                        + "adicional a utilizar");
                String regla = in.nextLine();
                String parte1 = regla.substring(0,2);
                int numeroRegla = regla.charAt(2)-48;
                int y = regla.toUpperCase().charAt(3) - 65;
                int x = regla.charAt(4) - 49;
                int valor1 = regla.toUpperCase().charAt(3);
                int valor2 = regla.charAt(4);
                if(parte1.equalsIgnoreCase(parte1) && numeroRegla<3
                        && numeroRegla>0 && valor1<71 && valor1 > 64 &&
                        valor2 < 55 &&valor2 > 48){
                    if(numeroRegla == 1){
                        if(!this.getColcha().obtenerGatoSegunPosicion
                        (y, x).getTamano() && this.getColcha().
                        obtenerColorSegunPosicion(y, x).equals
                        (this.obtenerTurno()) ){
                            this.getColcha().quitarGatoSegunPosicion(y, x);
                            if(this.getTurnoRojo()){
                                this.getCajaRojo().setCantidadGatos
                                (this.getCajaRojo().getCantidadGatos()+1);
                                valido = true;
                            } else{
                                 this.getCajaAzul().setCantidadGatos
                                (this.getCajaAzul().getCantidadGatos()+1);
                                 valido = true;
                            }
                        } else{
                            System.out.println("Animal inválido, ingrese "
                                    + "nuevamente");
                        }
                    }
                    if(numeroRegla == 2){
                        if(this.getColcha().obtenerGatoSegunPosicion
                        (y, x).getTamano() && this.getColcha().
                        obtenerColorSegunPosicion(y, x).equals
                        (this.obtenerTurno()) ){
                            this.getColcha().quitarGatoSegunPosicion(y, x);
                            if(this.getTurnoRojo()){
                                this.getCajaRojo().setCantidadGatos
                                (this.getCajaRojo().getCantidadGatos()+1);
                                valido = true;
                            } else{
                                 this.getCajaAzul().setCantidadGatos
                                (this.getCajaAzul().getCantidadGatos()+1);
                                 valido = true;
                            }
                        } else{
                            System.out.println("Animal inválido, ingrese "
                                    + "nuevamente");
                        }
                    }
                    
                } else{
                    System.out.println("Regla no válida, ingrese nuevamente");
                }
            }
            
        }
    }
    
    public void agregarGatoColchaSimple(String coordenadaY, int coordenadaX, 
            Jugador jugador, boolean grande){
        this.getColcha().getListaGatosEnColcha().add(new Gato(grande,coordenadaX,
                coordenadaY,jugador));
        this.moverAdyacentes(coordenadaY, coordenadaX);
        this.getColcha().setCeldasOcupadas();        
    }    
    
    public void agregarGatoColchaFull(String coordenadaY, int coordenadaX, 
            Jugador jugador, boolean grande){
        this.getColcha().getListaGatosEnColcha().add(new Gato(grande,coordenadaX,
                coordenadaY,jugador));
        this.moverAdyacentesFull(coordenadaY, coordenadaX);
        this.getColcha().setCeldasOcupadas();        
    }    
    
    public void moverAdyacentes(String posY, int posX){
        int indexY = posY.toUpperCase().charAt(0) - 65;
        int indexX = posX - 1;
        String [][] aux = this.getColcha().getCeldasOcupadas();
        for(int i = Math.max(indexY - 1, 0); i < Math.min(indexY + 2, aux.length);i++){ 
            for(int j = Math.max(indexX - 1, 0); j<Math.min(indexX + 2, aux[i].length);j++){
                if(!aux[i][j].equals("  ") && (indexY != i || indexX != j)){
                    if(i-(indexY-i) > 5 || i-(indexY-i) <0 || j-(indexX-j)>5 || j-(indexX-j)<0) {
                        String color = this.getColcha().
                                obtenerColorSegunPosicion(i, j);
                            if(color.equals("Rojo")){
                                this.getCajaRojo().setCantidadGatitos
                                (this.getCajaRojo().getCantidadGatitos()+1);
                            } else{
                                 this.getCajaAzul().setCantidadGatitos
                                (this.getCajaAzul().getCantidadGatitos()+1);
                            }     
                        this.getColcha().quitarGatoSegunPosicion(i, j);
                        this.getSistema().emitirSonido();
                    } else {
                        if(aux[i-(indexY-i)][j-(indexX-j)].equals("  ")){
                        this.getColcha().cambiarPosicionGato(i, j, (i-(indexY-i)), (j-(indexX-j)));
                        }
                    }        
                }
            }
        }
    }

    public void moverAdyacentesFull(String posY, int posX){
        int indexY = posY.toUpperCase().charAt(0) - 65;
        int indexX = posX - 1;
        String rojoChico = "\u001B[31mg \u001B[0m";
        String azulChico = "\u001B[34mg \u001B[0m";
        String [][] aux = this.getColcha().getCeldasOcupadas();
        Gato g = this.getColcha().obtenerGatoSegunPosicion(indexY,indexX);
        if(g.getTamano()){
            for(int i = Math.max(indexY - 1, 0); i < Math.min(indexY + 2, aux.length);i++){ 
                for(int j = Math.max(indexX - 1, 0); j<Math.min(indexX + 2, aux[i].length);j++){
                    if(!aux[i][j].equals("  ") && (indexY != i || indexX != j)){
                        if(i-(indexY-i) > 5 || i-(indexY-i) <0 || j-(indexX-j)>5 || j-(indexX-j)<0) {
                            String color = this.getColcha().
                                    obtenerColorSegunPosicion(i, j);
                            if(color.equals("Rojo")){
                                this.getCajaRojo().setCantidadGatitos
                                (this.getCajaRojo().getCantidadGatitos()+1);
                            } else{
                                 this.getCajaAzul().setCantidadGatitos
                                (this.getCajaAzul().getCantidadGatitos()+1);
                            }                            
                            this.getColcha().quitarGatoSegunPosicion(i, j);
                            this.getSistema().emitirSonido();
                        } else if(aux[i-(indexY-i)][j-(indexX-j)].equals("  ")){
                            this.getColcha().cambiarPosicionGato(i,
                                j, (i-(indexY-i)), (j-(indexX-j)));
                        }
                    }
                }
            }            
        } else{
            for(int i = Math.max(indexY - 1, 0); i < Math.min(indexY + 2, 
                    aux.length);i++){ 
                for(int j = Math.max(indexX - 1, 0); j<Math.min(indexX + 2, 
                        aux[i].length);j++){
                    if((aux[i][j].equals(rojoChico) || aux [i][j].
                            equals(azulChico))&& (indexY != i || 
                            indexX != j)){
                        if(i-(indexY-i) > 5 || i-(indexY-i) <0 || j-(indexX-j)>5
                                || j-(indexX-j)<0) {
                            String color = this.getColcha().
                                    obtenerColorSegunPosicion(i, j);
                            if(color.equals("Rojo")){
                                this.getCajaRojo().setCantidadGatitos
                                (this.getCajaRojo().getCantidadGatitos()+1);
                            } else{
                                 this.getCajaAzul().setCantidadGatitos
                                (this.getCajaAzul().getCantidadGatitos()+1);
                            }                            
                            this.getColcha().quitarGatoSegunPosicion(i, j);
                            this.getSistema().emitirSonido();
                        } else if(aux[i-(indexY-i)][j-(indexX-j)].equals("  ")){
                            this.getColcha().cambiarPosicionGato(i, 
                                 j, (i-(indexY-i)), (j-(indexX-j)));
                        }
                    }
                }
            }
        }    
    }    
    
    public void agregarCantidadPartidas(String alias){
        ArrayList<Jugador> listaJugadores = this.getSistema().getListaJugadores();
        Iterator<Jugador> it = listaJugadores.iterator();
        while(it.hasNext()){
            Jugador j = it.next();
            if(alias.equals(j.getAlias())){
                j.setCantidadPartidasJugadas(j.getCantidadPartidasJugadas() + 1);
            }
        }
    }
    

    
    
}
