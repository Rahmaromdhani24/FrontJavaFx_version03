package Front_java;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("La Moyenne X̄");

        // Création des axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(70, 130, 10);
        xAxis.setLabel("Date");
        yAxis.setLabel("Valeur");

        // Création du graphique
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("La Moyenne X̄");
        lineChart.setLegendVisible(false);

        // Ajouter les bandes colorées
        addStripLines(lineChart);

        // Série de données
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("2025-10-23", 102));
        series.getData().add(new XYChart.Data<>("2025-11-23", 109));
        series.getData().add(new XYChart.Data<>("2025-12-23", 95));
        series.getData().add(new XYChart.Data<>("2025-13-23", 106));
        series.getData().add(new XYChart.Data<>("2025-14-23", 98));
        series.getData().add(new XYChart.Data<>("2025-15-23", 102));
        series.getData().add(new XYChart.Data<>("2025-16-23", 90));
        series.getData().add(new XYChart.Data<>("2025-17-23", 105));
        series.getData().add(new XYChart.Data<>("2025-18-23", 100));

        lineChart.getData().add(series);

        StackPane root = new StackPane();
        root.getChildren().add(lineChart);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void addStripLines(LineChart<String, Number> chart) {
        chart.applyCss(); // Appliquer les styles CSS pour que lookup() fonctionne

        Region plotArea = (Region) chart.lookup(".chart-plot-background");
        if (plotArea != null) {
            double chartHeight = plotArea.getHeight();
            double yMin = 70, yMax = 130;

            // Définition des bandes colorées
            double[][] ranges = {
                {70, 80, 0.43, 255, 0, 0},    // Rouge
                {80, 88, 0.64, 255, 255, 0},  // Jaune
                {88, 112, 0.53, 0, 200, 0},   // Vert
                {112, 120, 0.64, 255, 255, 0},// Jaune
                {120, 130, 0.43, 255, 0, 0}   // Rouge
            };

            for (double[] range : ranges) {
                double start = range[0], end = range[1];
                double opacity = range[2];
                Color color = Color.rgb((int) range[3], (int) range[4], (int) range[5], opacity);

                // Calculer la hauteur en fonction de l'échelle de l'axe Y
                double rectHeight = ((end - start) / (yMax - yMin)) * chartHeight;
                double translateY = ((yMax - end) / (yMax - yMin)) * chartHeight;

                Rectangle rect = new Rectangle(plotArea.getWidth(), rectHeight, color);
                rect.setTranslateY(translateY);
                plotArea.getChildrenUnmodifiable().add(rect);  // Ajout de la bande colorée
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
