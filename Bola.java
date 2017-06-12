import javafx.scene.shape.Circle;

import javafx.scene.paint.*;
import java.util.Random;
import javafx.scene.shape.Shape;
/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bola extends Circle
{
    private int velocidadEnX;
    private int velocidadEnY; 
    private int anchoDeLaEscena;
    private static int RADIO = 20;

    public Bola(int anchoEscena)
    {
        super();
        setFill(Color.RED);  
        setRadius(RADIO);     
        Random aleatorio = new Random();
        setCenterX(RADIO + aleatorio.nextInt(anchoEscena - RADIO*2));
        setCenterY(50);
        velocidadEnX = 1;
        velocidadEnY = 1;
        this.anchoDeLaEscena = anchoEscena;
    }

    public void mover()
    {
        //Desplazamos la bola
        setTranslateX(getTranslateX() + velocidadEnX);
        setTranslateY(getTranslateY() + velocidadEnY);    

        // Controlamos si la bola rebota a ziquierda o derecha
        if (getBoundsInParent().getMinX() <= 0 ||
        getBoundsInParent().getMaxX() >= anchoDeLaEscena) {
            velocidadEnX = -velocidadEnX;                              
        }

        // Controlamos si la bola rebota arriba
        if (getBoundsInParent().getMinY() <= 0) {
            velocidadEnY = -velocidadEnY;
        }        
    }

    public void controlarSiChocaEnPlataforma(Plataforma plataforma)
    {
        if (getBoundsInParent().getMaxY() == plataforma.getBoundsInParent().getMinY()) {
            double centroEnXDeLaBola = getBoundsInParent().getMinX() + RADIO;
            double minEnXDeLaPlataforma = plataforma.getBoundsInParent().getMinX();
            double maxEnXDeLaPlataforma = plataforma.getBoundsInParent().getMaxX();
            if ((centroEnXDeLaBola >= minEnXDeLaPlataforma) &&
            (centroEnXDeLaBola <= maxEnXDeLaPlataforma)) {
                //La bola esta sobre la plataforma
                velocidadEnY = -velocidadEnY;
            }
        }        
    }

    public boolean comprobarSiChocaCon(Ladrillo ladrilloAComprobar)
    {
        boolean colision = false;
        Shape interseccion = Shape.intersect(this, ladrilloAComprobar);
        if (interseccion.getBoundsInParent().getWidth() != -1) {
            colision = true;
        }        
        return colision;
    }
}




