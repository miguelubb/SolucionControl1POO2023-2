public class Partida {
    private int numero;
    private float[] resultado = {0f, 0f};
    //asociacióncon Jugador
    private Jugador blancas;
    private Jugador negras;

    public Partida(int num, Jugador jugadorBlancas, Jugador jugadorNegras) {
        numero=num;
        blancas=jugadorBlancas;
        negras=jugadorNegras;
        jugadorBlancas.addPartida(this, ColorPieza.BLANCAS);
        jugadorNegras.addPartida(this, ColorPieza.NEGRAS);
    }

    public int getNumero() {
        return numero;
    }

    public float[] getResultado() {
        return resultado;
    }

    public void setResultado(float[] resultado) {
        this.resultado = resultado;
    }

    public String getNombreJugador(ColorPieza color){
        switch (color){
            case BLANCAS -> {return blancas.getNombre() ;}
            case NEGRAS -> { return negras.getNombre();}
        }
        return "";
    }
}
