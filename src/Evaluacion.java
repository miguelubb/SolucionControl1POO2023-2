import java.lang.reflect.Field;

public class Evaluacion {
    public static void main(String[] args) {
        int puntos = 0;
        System.out.print("Implementación de enumeraición:");
        puntos += enumeracionColorPieza();
        System.out.println("Clase Jugador");
        puntos += claseJugador();
        System.out.println("Puntaje Total: " + puntos);
    }

    public static int enumeracionColorPieza() {
        int puntos = 0;
        Class c = ColorPieza.class;
        Field[] fields = c.getDeclaredFields();
        switch (fields.length) {
            case 3:
                puntos = 3;
                System.out.println("Implementa 100% de la enumeracion (3)");
                break;
            case 2:
                puntos = 2;
                System.out.println("implementa 50% o más de la enumeración (2)");
                break;
            default:
                System.out.println("Implementa 50% o menos de la enumeración (0)");
        }

        return puntos;
    }

    public static int claseJugador(){
        int puntos=0;
        Class c=Jugador.class;
        Field[] fields=c.getDeclaredFields();
        int contString=0,contArrayList=0;
        for (Field field : fields) {
            if(field.getType().getName().contains("String")){
                contString++;
            }
            if(field.getType().getName().contains("ArrayList")){
                contArrayList++;
            }
        }
        System.out.print("Implementación de atributos, asignación, getter y setter: ");
        if(contString>0){
            puntos+=2;
            System.out.println("Implementa 100% de lo requerido (2)");
        }else{
            System.out.println("Cumple menos del 50% (0)");
        }
        System.out.print("Implementacion de variables de instancia y asignación de relaciones correspondientes:");
        if(contArrayList>=2){
            System.out.println("cumple 100% (5)");
            puntos+=5;
        }else if(contArrayList>=1){
            System.out.println("cumple 50% o más (1)");
            puntos+=1;
        }else{
            System.out.println("cumple menos de 50% (0)");
        }
        return puntos;
    }
}
