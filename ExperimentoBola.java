import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.Animation;
import java.util.Random;
import javafx.scene.input.KeyCode;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import javafx.scene.shape.Shape;


public class ExperimentoBola extends Application
{
    private int tiempoEnSegundos;
    private ArrayList<Ladrillo> ladrillos;

    private static final int NUM_LADRILLOS_A_MOSTRAR = 4;
    private static final int ALTO_BARRA_SUPERIOR = 20;

    private static final int ANCHO_ESCENA = 500;
    private static final int ALTO_ESCENA = 500;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        tiempoEnSegundos = 0;

        Bola bola = new Bola(ANCHO_ESCENA);
        contenedor.getChildren().add(bola);        

        Plataforma plataforma = new Plataforma(500);
        contenedor.getChildren().add(plataforma);

        //Creacion de la etiqueta para el tiempo
        Label tiempoPasado = new Label("0");
        contenedor.getChildren().add(tiempoPasado);

        Scene escena = new Scene(contenedor, ANCHO_ESCENA,ALTO_ESCENA);        

        //Creacion de los ladrillos
        ladrillos = new ArrayList<>();

        int numeroLadrillosAnadidos = 0;
        while (numeroLadrillosAnadidos < NUM_LADRILLOS_A_MOSTRAR) {

            boolean encontradoLadrilloValido = false;
            while (!encontradoLadrilloValido) {

                //Elegimos un ladrillo aleatorio
                Ladrillo posibleLadrillo = new Ladrillo(ANCHO_ESCENA, ALTO_ESCENA, ALTO_BARRA_SUPERIOR);

                //Comprobamos si el ladrillo es valido
                boolean solapamientoDetectado = false;
                int ladrilloActual = 0;
                while (ladrilloActual < ladrillos.size() && !solapamientoDetectado) {
                    Shape interseccion = Shape.intersect(posibleLadrillo, ladrillos.get(ladrilloActual));
                    if (interseccion.getBoundsInParent().getWidth() != -1) {
                        solapamientoDetectado = true;
                    }                    
                    ladrilloActual++;

                }               

                // si hemos encontrado un ladrillo valido
                if (!solapamientoDetectado) {
                    encontradoLadrilloValido = true;
                    ladrillos.add(posibleLadrillo);
                    contenedor.getChildren().add(posibleLadrillo);
                }

            }  

            numeroLadrillosAnadidos++;
        }

        escenario.setScene(escena);
        escenario.show();

        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), event -> {

                    //Desplazamos la plataforma
                    plataforma.mover();

                    //Desplazamos la bola
                    bola.mover();

                    //Controlamos si la bola rebota en la plataforma
                    bola.controlarSiChocaEnPlataforma(plataforma);


                    //Comprobamos si la bola pega en algun ladrillo
                    for (int i = 0; i < ladrillos.size(); i++) {
                        if (bola.comprobarSiChocaCon(ladrillos.get(i))) {
                            contenedor.getChildren().remove(ladrillos.get(i));
                            ladrillos.remove(ladrillos.get(i));    
                            i--;
                        }
                    }

                    // Actualizamos la etiqueta del tiempo
                    int minutos = tiempoEnSegundos / 60;
                    int segundos = tiempoEnSegundos % 60;
                    tiempoPasado.setText(minutos + ":" + segundos);                        

                    // Comrpobamos si el juego debe detenerse
                    if (bola.getBoundsInParent().getMinY() > escena.getHeight()) {
                        Label mensajeGameOver = new Label("Game over");
                        mensajeGameOver.setTranslateX(escena.getWidth() / 2);
                        mensajeGameOver.setTranslateY(escena.getHeight() / 2);
                        contenedor.getChildren().add(mensajeGameOver);
                        timeline.stop();
                    }

                });  
        timeline.getKeyFrames().add(keyframe);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();     

        escena.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.RIGHT) {
                    plataforma.cambiarDireccionALaDerecha();
                }
                else if (event.getCode() == KeyCode.LEFT) {
                    plataforma.cambiarDireccionALaIzquierda();
                }
            });

        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    tiempoEnSegundos++;
                }                        
            };
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 1000);

    }

}



