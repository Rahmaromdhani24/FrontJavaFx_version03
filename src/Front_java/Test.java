package Front_java;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Axe X (masqué)
        NumberAxis xAxis = new NumberAxis();
        xAxis.setVisible(false);
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        xAxis.setOpacity(0);

        // Axe Y (masqué)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setVisible(false);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        yAxis.setOpacity(0);

        // Création du StackedAreaChart
        StackedAreaChart<Number, Number> stackedAreaChart = new StackedAreaChart<>(xAxis, yAxis);
        stackedAreaChart.setLegendVisible(false); // Désactive la légende automatique
        stackedAreaChart.setHorizontalGridLinesVisible(false);
        stackedAreaChart.setVerticalGridLinesVisible(false);

        // Série 1 (Par défaut)
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>(0, 25));
        series1.getData().add(new XYChart.Data<>(1, 25));
        series1.getData().add(new XYChart.Data<>(2, 25));
        series1.getData().add(new XYChart.Data<>(3, 25));
        series1.getData().add(new XYChart.Data<>(4, 25));

        // Série 2 (Jaune)
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>(0, 25));
        series2.getData().add(new XYChart.Data<>(1, 25)); // Valeur modifiée pour la série 2
        series2.getData().add(new XYChart.Data<>(2, 25));
        series2.getData().add(new XYChart.Data<>(3, 25));
        series2.getData().add(new XYChart.Data<>(4, 25));

        // Série 3 (Par défaut)
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.getData().add(new XYChart.Data<>(0, 25));
        series3.getData().add(new XYChart.Data<>(1, 25));
        series3.getData().add(new XYChart.Data<>(2, 25));
        series3.getData().add(new XYChart.Data<>(3, 25));
        series3.getData().add(new XYChart.Data<>(4, 25));

        // Ajouter les séries au graphique
        stackedAreaChart.getData().addAll(series1, series2, series3);

        // Créer des légendes pour chaque série avec leur valeur (ex : première valeur de chaque série)
        Text legendText1 = new Text(" ");
        legendText1.setStyle("-fx-font-size: 14; -fx-fill: black; -fx-font-weight: bold;");

        Text legendText2 = new Text(" " + series2.getData().get(0).getYValue());
        legendText2.setStyle("-fx-font-size: 14; -fx-fill: black; -fx-font-weight: bold;");

        Text legendText3 = new Text("" + series3.getData().get(0).getYValue());
        legendText3.setStyle("-fx-font-size: 14; -fx-fill: black; -fx-font-weight: bold;");

        // Créer un VBox pour disposer les légendes verticalement
        VBox legendBox = new VBox(110); // 10 pixels d'écart entre les légendes
        legendBox.getChildren().addAll(legendText1, legendText2, legendText3);

        // Organiser les éléments dans un StackPane
        StackPane root = new StackPane(stackedAreaChart, legendBox);
        StackPane.setAlignment(legendBox, javafx.geometry.Pos.TOP_LEFT);

        // Créer la scène
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stacked Area Chart with Legends for Each Series");

        // Utiliser Platform.runLater pour s'assurer que les styles sont appliqués après la création du graphique
        Platform.runLater(() -> {
            stackedAreaChart.lookupAll(".chart-series-line").forEach(node -> {
                if (node.getId().contains("series2")) {
                    node.setStyle("-fx-stroke: yellow; -fx-fill: #ffd103;");
                }
            });
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
