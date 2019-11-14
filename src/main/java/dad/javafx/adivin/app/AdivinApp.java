package dad.javafx.adivin.app;

import java.util.Random;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	// private Label textoLabel;
	private TextField numeroText;
	private Button comprobarButton;
	private Label textoLabel;
	private Alert fallo;
	private int contador;
	private int aleatorio;

	public void start(Stage primaryStage) throws Exception {
		
		textoLabel = new Label("Introduce un número del 1 al 100");		
		numeroText = new TextField();
		//numeroText.setPromptText("Introduce un nmero del 1 al 100"); NO NECESARIO
		numeroText.setMaxWidth(150);

		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);

		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(textoLabel,numeroText, comprobarButton);

		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

		//////// Configuraci�n de Alerts///////////

	}

	private void onComprobarButtonAction(ActionEvent e) {

		int numero = 0; // Variable que almacenar� el n�mero procedente del textfield

		/*
		 * Si el contador=0 almacena en el paramentro aleatorio un valor ramdom
		 */
		if (contador == 0) {
			Random rndNumber = new Random();
			aleatorio = rndNumber.nextInt(100);
		}

		/*
		 * Convertimos el campo te texto en un Integer y comprobamos con un Try catch
		 * que no es una palabra Mediante condicionales se comprueba si coincide el
		 * n�mero y despliega los objetos de alerta seg�n su condici�n
		 */
		try {
			String texto = this.numeroText.getText();
			numero = Integer.parseInt(texto);
			// System.out.println("N�mero aleatorio = " + aleatorio);
			contador++;

			if (numero == aleatorio) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Has ganado");
				alert.setContentText(
						"Solo has necesitado " + contador + " intentos" + "\n" + "Vuelve jugar y hazlo mejor");
				alert.showAndWait();
				contador = 0; // Devuelve el contador a 0 para que empiece el juego

			} else {
				if (aleatorio > numero) {					
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("Has fallado");
					alert.setContentText("El número a adivinar es mayor a " + numero + "\n" + "Vuelve a intentarlo");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("Has fallado");
					alert.setContentText("El número a adivinar es menor a " + numero + "\n" + "Vuelve a intentarlo");
					alert.showAndWait();
				}
			}

		} catch (NumberFormatException nfe) {			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es v�lido");
			alert.showAndWait();
		}

	}

	public static void main(String[] args) {

		launch(args);

	}

}
