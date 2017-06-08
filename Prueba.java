import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * Write a description of class Prueba here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Prueba extends Application
{
    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        Rectangle a = new Rectangle();
        a.setWidth(50);
        a.setHeight(20);
        a.setTranslateX(225);
        a.setTranslateY(200);

        Rectangle b = new Rectangle();
        b.setWidth(50);
        b.setHeight(20);
        b.setTranslateX(235);
        b.setTranslateY(300);
        b.setVisible(false);

        contenedor.getChildren().add(a);
        contenedor.getChildren().add(b);
        Shape c = Shape.intersect(a, b);

        double ancho = c.getBoundsInParent().getWidth();
        System.out.println(ancho);
        
        //Y ahora habria que hacer visible el ladrillo si
        //no se superpone...
        
        Scene escena = new Scene(contenedor, 500, 500);
        escenario.setScene(escena);
        escenario.show();



    }
}
