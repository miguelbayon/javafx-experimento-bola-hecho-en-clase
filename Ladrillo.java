import javafx.scene.shape.Rectangle;
import java.util.Random;
/**
 * Write a description of class Ladrillo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ladrillo extends Rectangle
{
    private static final int ANCHO_LADRILLO = 40;
    private static final int ALTO_LADRILLO = 20;

    /**
     * Crea un ladrillo en una posicion aleatoria
     */
    public Ladrillo(int anchoEscena, int altoEscena, int altoBarraSuperior) 
    {
        super();
        Random aleatorio = new Random();
        int posXLadrillo = aleatorio.nextInt(anchoEscena - ANCHO_LADRILLO);
        int posYLadrillo = aleatorio.nextInt(altoEscena/2 - ALTO_LADRILLO + altoBarraSuperior);        
        setHeight(ALTO_LADRILLO);
        setWidth(ANCHO_LADRILLO);  
        setTranslateX(posXLadrillo);
        setTranslateY(posYLadrillo);        
    }

}
