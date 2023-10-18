import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private Scanner tcld;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Torneo> torneos;

    public static void main(String[] args) {
        // No modifique este codigo
        Application app = new Application();
        app.jugadores = new ArrayList<>();
        app.torneos = new ArrayList<>();
        app.tcld = new Scanner(System.in);
        app.tcld.useDelimiter("[\r\n]");

        app.cargaTorneo();

        int opcion;
        do {
            System.out.println("\n\nMENU DE OPCIONES");
            System.out.println("1.Crear un Jugador");
            System.out.println("2.Crear un Torneo");
            System.out.println("3.Agregar Partidas a un Torneo");
            System.out.println("4.Registrar Resultado de una Partida");
            System.out.println("5.Desplegar Resultado de un Torneo");
            System.out.println("6.Salir");
            System.out.print("\tIngrese opcion: ");
            opcion = app.tcld.nextInt();

            switch (opcion) {
                case 1 -> app.creaJugador();
                case 2 -> app.creaTorneo();
                case 3 -> app.agregaPartidasATorneo();
                case 4 -> app.registraResultadoDePartida();
                case 5 -> app.despliegaResultadoDeTorneo();
                case 6 -> System.out.println("\nFinalizando ejecucion...");
                default -> System.out.println("\nOpcion erronea");
            }
        } while (opcion != 6);
    }



    private void cargaTorneo() {
        String[] nombres = {"Juan", "Ana", "Isabel", "Patricio", "Rodrigo", "Luis", "Margarita", "Alejandra"};
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }
        int numRondas = 3;
        torneos.add(new Torneo("SuperTorneo", numRondas));
        //crear rondas
        int[][][] parejas = {//parejas[x][y][z]: x=ronda, y=partida, z=pieza(blanca o negra)
                {{0, 1}, {2, 3}, {4, 5}, {6, 7}},
                {{0, 2}, {4, 6}, {1, 3}, {5, 7}},
                {{0, 4}, {1, 5}, {2, 6}, {3, 7}}
        };
        int partida = 1;
        for (int i = 0; i < parejas.length; i++) {
            Partida[] partidas = new Partida[4];
            int k = 0;
            for (int j = 0; j < parejas[0].length; j++) {
                Partida p = new Partida(partida++, jugadores.get(parejas[i][j][0]), jugadores.get(parejas[i][j][1]));
                partidas[k++] = p;
            }
            torneos.get(0).addPartidasARonda(i + 1, partidas);
        }
        float[] res = {1f, 0f};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i * 4 + j + 1) < 12) {
                    torneos.get(0).registrarResultadoPartida(i + 1, i * 4 + j + 1, res);
                }
            }
        }
    }

    private void creaJugador() {
        // No modifique este codigo
        String nombre;
        System.out.println("\nCreando un jugador...");
        System.out.print("Nombre: ");
        nombre = tcld.next();
        if (buscaJugador(nombre) == null) {
            jugadores.add(new Jugador(nombre));
            System.out.println("\nEl jugador fue creado exitosamente");
        } else
            System.out.println("\nYa existe un jugador con el nombre dado");
    }

    private void creaTorneo() {
        // No modifique este codigo
        String nombre;
        int nroJugadores, nroRondas;
        System.out.println("\nCreando un torneo...");
        System.out.print("Nombre: ");
        nombre = tcld.next();
        if (buscaTorneo(nombre) == null) {
            System.out.print("Nro. de jugadores: ");
            nroJugadores = tcld.nextInt();
            nroRondas = (int) (Math.log10(nroJugadores) / Math.log10(2)); // Calcula log2(n)
            torneos.add(new Torneo(nombre, nroRondas));
            System.out.println("\nEl torneo fue creado exitosamente");
        } else
            System.out.println("\nYa existe un torneo con el nombre dado");
    }

    private void agregaPartidasATorneo() {
        // No modifique este metodo
        String nombre, nomJugadorBlancas, nomJugadorNegras;
        int nroRondas, nroPartidasRonda, numPartida = 1;
        Jugador jugadorBlancas, jugadorNegras;
        System.out.println("\nAgregando partidas a un torneo...");
        System.out.print("Nombre torneo: ");
        nombre = tcld.next();
        Torneo torneo = buscaTorneo(nombre);
        if (torneo != null) {
            nroRondas = torneo.getNroRondas();
            for (int i = 1; i <= nroRondas; i++) {
                System.out.println("Ronda --> " + i);
                nroPartidasRonda = (int) Math.pow(2, nroRondas - 1);
                Partida[] partidas = new Partida[nroPartidasRonda];
                for (int j = 0; j < nroPartidasRonda; j++) {
                    System.out.println("Partida ==> " + numPartida);
                    System.out.print("Nombre jugador con blancas: ");
                    nomJugadorBlancas = tcld.next();
                    jugadorBlancas = buscaJugador(nomJugadorBlancas);
                    System.out.print("Nombre jugador con negras: ");
                    nomJugadorNegras = tcld.next();
                    jugadorNegras = buscaJugador(nomJugadorNegras);
                    partidas[j] = new Partida(numPartida++, jugadorBlancas, jugadorNegras);
                    System.out.println();
                }
                torneo.addPartidasARonda(i, partidas);
            }
            System.out.println("\nSe agregaron partidas al torneo exitosamente");
        } else System.out.println("\nNo existe un torneo con el nombre dado");
    }

    private void registraResultadoDePartida() {
        System.out.println("Ingrese el nombre del torneo");
        String nombre = tcld.next();
        System.out.println("Ingrese el número de ronda");
        int ronda = tcld.nextInt();
        System.out.println("Ingrese el número de la partida");
        int num = tcld.nextInt();
        System.out.println("Ingrese el resultado de la partida");
        String resultado = tcld.next();
        String[] resultadoSplit = resultado.split("-");
        float[] res = {Float.parseFloat(resultadoSplit[0]), Float.parseFloat(resultadoSplit[1])};
        Torneo elTorneo = buscaTorneo(nombre);
        if (elTorneo == null) {
            System.out.println("ERROR: El torneo no existe.");
            return;
        }
        if (elTorneo.registrarResultadoPartida(ronda, num, res)) {
            System.out.println("Se ha registrado la partida exitosamente");
        } else {
            System.out.println("No fue posible registrar la partida, Ronda o partida inexistente");
        }
    }

    private void despliegaResultadoDeTorneo() {
        System.out.println("Ingrese el nombre del torneo:");
        String nombre = tcld.next();
        Torneo torneo = buscaTorneo(nombre);
        if (torneo != null) {
            System.out.println("RESULTADO PARTIDAS TORNEO DE AJEDREZ: " + nombre);
            System.out.printf("%-7s %-15s %-15s %-8s %n", "Partida", "Blancas", "Negras", "Resultado");
            String[][] data = torneo.getResultadoPartidasDeRondas();
            for (int i = 0; i < data.length; i++) {
                System.out.printf("%-7s %-15s %-15s %-8s %n", data[i][0], data[i][1], data[i][2], data[i][3]);
            }
        } else {
            System.out.println("No se encuentra el torneo ingresado");
        }
    }

    private Jugador buscaJugador(String nombre) {
        // No modifique este codigo
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    private Torneo buscaTorneo(String nombre) {
        // No modifique este codigo
        for (Torneo torneo : torneos) {
            if (torneo.getNombre().equalsIgnoreCase(nombre)) {
                return torneo;
            }
        }
        return null;
    }
}