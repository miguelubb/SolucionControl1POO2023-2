import java.util.ArrayList;

public class Torneo {
    private String nombre;
    //composición con Ronda
    private ArrayList<Ronda> rondas;

    public Torneo(String nombre, int nroRondas) {
        this.nombre=nombre;
        rondas=new ArrayList<>();
        for (int i = 1; i <= nroRondas; i++) {
            rondas.add(new Ronda(i));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getNroRondas() {
        return rondas.size();
    }

    public void addPartidasARonda(int num, Partida[] partidas) {
        for (Ronda ronda : rondas) {
            if(ronda.getNumero()==num){
                ronda.addPartidas(partidas);
            }
        }
    }

    public boolean registrarResultadoPartida(int numRonda, int numPartida, float[] res){
        for (Ronda ronda : rondas) {
            if(ronda.getNumero()==numRonda){
                return ronda.registrarResultadoPartida(numPartida,res);
            }
        }
        return false;
    }

    public String[][] getResultadoPartidasDeRondas(){
        String[][] out=new String[(int)(rondas.size()*Math.pow(2, rondas.size()-1))][];
        int i=0;
        for (Ronda ronda : rondas) {
            String[][] partidas=ronda.getResultadoParidas();
            for (String[] partida : partidas) {
                out[i++]=partida;
            }
        }
        return out;
    }
}
