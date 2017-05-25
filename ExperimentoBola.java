import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.animation.Animation;
import java.util.Random;
import javafx.scene.input.KeyCode;


public class ExperimentoBola extends Application
{
    private int velocidadEnX;
    private int velocidadEnY;
    private int velocidadPlataforma;
    private static int RADIO = 20;

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
        circulo.setRadius(RADIO);
        
        Rectangle plataforma = new Rectangle();
        plataforma.setWidth(50);
        plataforma.setHeight(5);
        plataforma.setTranslateX(225);
        plataforma.setTranslateY(480);
        plataforma.setFill(Color.BLUE);
        contenedor.getChildren().add(plataforma);
        
        velocidadPlataforma = 1;

        Random aleatorio = new Random();

        circulo.setCenterX(20 + aleatorio.nextInt(500 - 40));
        circulo.setCenterY(50);
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
                        
                        if (circulo.getBoundsInParent().getMaxY() == plataforma.getBoundsInParent().getMinY()) {
                            double centroEnXDeLaBola = circulo.getBoundsInParent().getMinX() + RADIO;
                            double minEnXDeLaPlataforma = plataforma.getBoundsInParent().getMinX();
                            double maxEnXDeLaPlataforma = plataforma.getBoundsInParent().getMaxX();
                            if ((centroEnXDeLaBola >= minEnXDeLaPlataforma) &&
                                (centroEnXDeLaBola <= maxEnXDeLaPlataforma)) {
                                    //La bola esta sobre la plataforma
                                    velocidadEnY = -velocidadEnY;
                                }
                        }

                        circulo.setTranslateX(circulo.getTranslateX() + velocidadEnX);
                        circulo.setTranslateY(circulo.getTranslateY() + velocidadEnY);

                        plataforma.setTranslateX(plataforma.getTranslateX() + velocidadPlataforma);
                    }));      
                    
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();     
        
        escena.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                velocidadPlataforma = 1;
            }
            else if (event.getCode() == KeyCode.LEFT) {
                velocidadPlataforma = -1;
            }
        });

    }

}









