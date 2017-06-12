import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

/**
 * Write a description of class Plataforma here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plataforma extends Rectangle
{
    private int velocidadX;
    private int limiteDesplazamientoEnX;

    /**
     * Constructor for objects of class Plataforma
     */
    public Plataforma(int limiteEnX)
    {
        super();
        setWidth(50);
        setHeight(5);
        setTranslateX(225);
        setTranslateY(480);
        setFill(Color.BLUE);  
        velocidadX = 1;
        limiteDesplazamientoEnX = limiteEnX;
    }
    
    
    public void cambiarDireccionALaDerecha() 
    {
        if (getBoundsInParent().getMaxX() != limiteDesplazamientoEnX) {
           velocidadX = 1;
        }
    }

    
    public void cambiarDireccionALaIzquierda() 
    {
        if (getBoundsInParent().getMinX() != 0) {
            velocidadX = -1;
        }
    }
    
    
    public void mover()
    {
        setTranslateX(getTranslateX() + velocidadX);
        if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == limiteDesplazamientoEnX) {
            velocidadX = 0;
        }
        
    }
  
    
}













