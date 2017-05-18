import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ExperimentoBola extends Application
{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        Circle circulo = new Circle();
        circulo.setFill(Color.RED);  
        circulo.setRadius(20);
        circulo.setCenterX(250);
        circulo.setCenterY(250);
        contenedor.getChildren().add(circulo);

        Scene escena = new Scene(contenedor, 500, 500);
        escenario.setScene(escena);
        escenario.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.017), event -> {
                        circulo.setTranslateX(circulo.getTranslateX() + 2);
                        circulo.setTranslateY(circulo.getTranslateY() + 2);
                    }));                
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();      

    }

}