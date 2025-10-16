package interfaz;

import dominio.*;
import java.util.*;

public class Menu {

    private static Juego nuevoJuego;
    private static Tablero tablero;

    public static void main(String[] args) {
        nuevoJuego = new Juego();
        tablero = new Tablero(nuevoJuego.getColcha());
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("   MENÚ   ");
            System.out.println("1- Registrar jugador");
            System.out.println("2- Jugar Saltitos versión simple");
            System.out.println("3- Jugar Saltitos versión full");
            System.out.println("4- Generar reporte PDF");
            System.out.println("5- Salir");
            System.out.print("Ingrese una opción (Del 1 al 5): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    nuevoJuego.registroJugador();
                    break;
                case 2:
                    jugarSaltitosSimple();
                    break;
                case 3:
                    jugarSaltitosFull();
                    break;
                case 4:
                    generarReporte();
                    crearArchivo();
                    break;
                case 5:
                    System.out.println("  FIN DE JUEGO  ");
                    break;
                default:
                    System.out.println("La opción ingresada no es válida, inténtelo de nuevo");
                    break;
            }
        } while (opcion != 5);
    }
    

    public static void crearArchivo() {
        nuevoJuego.getSistema().generarPDF(nuevoJuego.getSistema().
                getListaJugadores());
    }

    public static void jugarSaltitosSimple() {
        System.out.println("   JUGAR SALTITOS VERSIÓN SIMPLE   ");
        if(nuevoJuego.getSistema().getListaJugadores().size() >1){
            nuevoJuego.elegirJugadores();
            String ganador = "No";
            String turno = "";
            while(ganador.equals("No")){
                turno = nuevoJuego.obtenerTurno();
                tablero.mostrarTablero();
                System.out.println("Turno del jugador " + turno);
                nuevoJuego.imprimirCantidadesSimple();
                System.out.println("Ingrese movimiento jugador " + turno);
                nuevoJuego.ingresarMovimientoSimple();
                ganador = nuevoJuego.verificarGanador();
                nuevoJuego.cambiarTurno();
            }
            tablero.mostrarTablero();
            if(nuevoJuego.verificarGanador().equals("Empate")){
                System.out.println("Resultado: Empate");
            } else{
            System.out.println("El ganador es: "+ nuevoJuego.verificarGanador());
            }
        } else{
            System.out.println("Registre al menos dos jugadores para proceder "
                    + "a jugar.");
        }

    }

    public static void jugarSaltitosFull() {
        System.out.println("   JUGAR SALTITOS VERSIÓN FULL   ");
        Scanner in = new Scanner(System.in);
        if(nuevoJuego.getSistema().getListaJugadores().size() >1){
        nuevoJuego.elegirJugadores();
        String ganador = "No";
        String turno = "";
        while(ganador.equals("No")){
            turno = nuevoJuego.obtenerTurno();
            nuevoJuego.reglasEspeciales();            
            tablero.mostrarTablero();
            System.out.println("Turno del jugador " + turno);
            nuevoJuego.imprimirCantidadesFull();
            System.out.println("Ingrese movimiento jugador " + turno);
            nuevoJuego.ingresarMovimientoFull();
            ganador = nuevoJuego.verificarGanadorFull();
            if(!nuevoJuego.correspondeRetirarGatos().equals(" ")){
                tablero.mostrarTablero();
                boolean valido = false;
                while(!valido){
                    System.out.println("Ingrese coordenada inicial de gatitos en "
                            + "linea a retirar");
                    String inicio = in.nextLine();
                    System.out.println("Ingrese coordenada final de gatitos en "
                            + "linea a retirar");
                    String fin = in.nextLine();
                    if(nuevoJuego.esRetiroEnLineaValido(inicio, fin)){
                        nuevoJuego.quitarTresGatosIndicados(inicio, fin);
                        valido = true;
                    } else{
                        System.out.println("No es valido el retiro ingresado");
                    }
                }    
            }
            nuevoJuego.cambiarTurno();
        }
        tablero.mostrarTablero();
        if(nuevoJuego.verificarGanadorFull().equals("Empate")){
            System.out.println("Resultado: Empate");
        } else{
        System.out.println("El ganador es: "+ nuevoJuego.verificarGanadorFull());
        }
        } else{
            System.out.println("Registre al menos dos jugadores para proceder "
                    + "a jugar.");
        }
    }


    public static void generarReporte() {
        System.out.println("   GENERAR REPORTE   ");
        nuevoJuego.getSistema().ordenarLista(nuevoJuego.getSistema().
                getListaJugadores());
    }
    

    

}
