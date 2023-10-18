import java.util.ArrayList;

public class Jugador {
    private String nombre;

    //asociación con Partida
    private ArrayList<Partida> blancas;
    private ArrayList<Partida> negras;


    public Jugador(String nombre) {
        this.nombre=nombre;
        blancas=new ArrayList<>();
        negras=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean addPartida(Partida partida, ColorPieza miColor){
        switch (miColor){
            case BLANCAS -> {return blancas.add(partida);}
            case NEGRAS -> {return negras.add(partida);}
        }
        return false;
    }
}
