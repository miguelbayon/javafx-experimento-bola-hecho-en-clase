import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.animation.Animation;
import java.util.Random;



public class ExperimentoBola extends Application
{
    private int velocidadEnX;
    private int velocidadEnY;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        velocidadEnX = 1;
        velocidadEnY = 1;
        
        Circle circulo = new Circle();
        circulo.setFill(Color.RED);  
        circulo.setRadius(20);
        
        Random aleatorio = new Random();
        
        circulo.setCenterX(20 + aleatorio.nextInt(500 - 40));
        circulo.setCenterY(20 + aleatorio.nextInt(500 - 40));
        contenedor.getChildren().add(circulo);

        Scene escena = new Scene(contenedor, 500, 500);
        escenario.setScene(escena);
        escenario.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            
                        if (circulo.getBoundsInParent().getMinX() <= 0 ||
                            circulo.getBoundsInParent().getMaxX() >= escena.getWidth()) {
                                velocidadEnX = -velocidadEnX;                              
                            }
                            
                        if (circulo.getBoundsInParent().getMinY() <= 0 ||
                            circulo.getBoundsInParent().getMaxY() >= escena.getHeight()) {
                                velocidadEnY = -velocidadEnY;
                                
                            }
                            
            
                        circulo.setTranslateX(circulo.getTranslateX() + velocidadEnX);
                        circulo.setTranslateY(circulo.getTranslateY() + velocidadEnY);

                    }));                
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();            

    }

}


