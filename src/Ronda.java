import java.util.ArrayList;

public class Ronda {
    private int numero;
    //composición con Partida
    private ArrayList<Partida> partidas=new ArrayList<>();

    public Ronda(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void addPartidas(Partida[] partidas){
        for (int i = 0; i < partidas.length; i++) {
            this.partidas.add(partidas[i]);
        }
    }

    private boolean sinResultado(Partida p){
        float[] res=p.getResultado();
        return (res[0]==0 && res[1]==0);
    }
    public boolean registrarResultadoPartida(int numPartida, float[] res){
        for (Partida partida : partidas) {
            if(partida.getNumero()==numPartida && sinResultado(partida)){
                partida.setResultado(res);
                return true;
            }
        }
        return false;
    }


    public String[][] getResultadoParidas(){
        String[][] out=new String[partidas.size()][4];
        int i=0;
        for (Partida partida : partidas) {
            out[i][0]= String.valueOf(partida.getNumero());
            out[i][1]=partida.getNombreJugador(ColorPieza.BLANCAS);
            out[i][2]=partida.getNombreJugador(ColorPieza.NEGRAS);
            float[] resultado=partida.getResultado();
            out[i][3]=resultado[0]+"-"+resultado[1];
            i++;
        }
        return out;
    }
}
