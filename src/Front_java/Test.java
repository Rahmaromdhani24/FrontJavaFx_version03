package Front_java;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création du GridPane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-grid-lines-visible: true;");

        // Données des lignes
        String[] labels = {
            "Hauteur de sertissage (mm) - C1", 
            "Hauteur de sertissage (mm) - C2", 
            "Code", 
            "N° de Cycle", 
            "Produit", 
            "Série Produite", 
            "Quantité du cycle", 
            "N° Machine", 
            "Date", 
            "Opérateur"
        };

        // Ajouter les titres des colonnes
        gridPane.add(new Label("Paramètres"), 0, 0);
        gridPane.add(new Label("Échantillon 1"), 1, 0);
        gridPane.add(new Label("Échantillon 2"), 2, 0);
        gridPane.add(new Label("Échantillon 3"), 3, 0);
        gridPane.add(new Label("Échantillon Fin"), 4, 0);

        // Remplir le tableau
        for (int i = 0; i < labels.length; i++) {
            // Ajouter les labels de paramètres
            Label label = new Label(labels[i]);
            label.setMinWidth(150);
            label.setStyle("-fx-padding: 5px; -fx-border-color: black; -fx-border-width: 1px;");
            gridPane.add(label, 0, i + 1);

            // Ajouter les TextFields pour la saisie
            for (int j = 1; j <= 4; j++) {
                TextField textField = new TextField();
                textField.setMinWidth(120);
                textField.setId("textField" + i + "_" + j); // ID unique
                textField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                gridPane.add(textField, j, i + 1);
            }
        }

        // Définition de la scène
        Scene scene = new Scene(gridPane, 800, 400);
        primaryStage.setTitle("Tableau GridPane avec Bordures");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
