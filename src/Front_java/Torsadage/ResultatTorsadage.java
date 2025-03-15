package Front_java.Torsadage;

import java.io.IOException;
import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SoudureInformations;
import Front_java.Modeles.OperateurInfo;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import Front_java.Configuration.TorsadageInformations;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class ResultatTorsadage {

	// Variables pour stocker la position de la souris
	private double xOffset = 0;
	private double yOffset = 0;
 
	
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnSuivant;

    @FXML
    private Label codeControleSelectionner;

    @FXML
    private Label dateSystem;

    @FXML
    private Label decalageMaxDebutCdeC1;

    @FXML
    private Label decalageMaxDebutCdeC2;

    @FXML
    private Label decalageMaxFinCdeC1;

    @FXML
    private Label decalageMaxFinCdeC2;

    @FXML
    private Label ech1;

    @FXML
    private Label ech2;

    @FXML
    private Label ech3;

    @FXML
    private Label ech4;

    @FXML
    private Label ech5;

    @FXML
    private Label ettendu;

    @FXML
    private Label heureSystem;

    @FXML
    private Label longueurBoutDebutC1;

    @FXML
    private Label longueurBoutDebutC2;

    @FXML
    private Label longueurBoutFinC1;

    @FXML
    private Label longueurBoutFinC2;

    @FXML
    private Label longueurFinCde;

    @FXML
    private Label longueurPasFinCde;

    @FXML
    private Label longueurfinalDebutCde;

    @FXML
    private Label matriculeUser;

    @FXML
    private Label moyenne;

    @FXML
    private Label nbrCycle;

    @FXML
    private Label nbrEch;

    @FXML
    private Label nomPrenomUser;

    @FXML
    private Label nomProjet;

    @FXML
    private Label numCommande;

    @FXML
    private Label numFil;

    @FXML
    private Label operationUser;

    @FXML
    private Label plantUser;

    @FXML
    private Label posteUser;

    @FXML
    private Label quantiteAtteint;

    @FXML
    private Label quantiteTotal;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label segementUser;

    @FXML
    private Label specificationsMesure;

    @FXML
    private StackPane stackPane;


    @FXML
    private StackedAreaChart<Number, Number> chartEttendu;

    @FXML
    private StackedAreaChart<Number, Number> chartMoyenne;
		
	@FXML
	public void initialize() {
	
		initialiserDonneesPDEKEnregistrer() ; 
		afficherInfosOperateur();
		AppInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		createAndAddChartDataEttendu(chartEttendu);
	    createAndAddChartDataMoyenne(chartMoyenne);
	    addPointToChart(chartEttendu);
		//loadNumeroCycleMax();
		/*clearImage.setOnMouseClicked(event -> {
			if (activeTextField != null) {
				activeTextField.clear();
			}
		});*/

	
	}




	@FXML
	private void close(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void minimize(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	@FXML
	private void scanne(ActionEvent event) {
	
	}
	

	@FXML
	public void suivant(ActionEvent event) {
	
	}
	

	

	@FXML
	void logout(ActionEvent event) {

    	AppInformations.reset();
    	//TorsadageInformations.reset();
    

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/Login.fxml"));
			Scene loginScene = new Scene(loader.load());
			loginScene.getStylesheets().add(getClass().getResource("/Front_java/loginDesign.css").toExternalForm());

			Stage loginStage = new Stage();
			loginStage.initStyle(StageStyle.UNDECORATED); // Placer cette ligne avant show()
			loginStage.setResizable(false); // Désactiver le redimensionnement
			Image icon = new Image("/logo_app.jpg");
			loginStage.getIcons().add(icon);

			loginStage.setScene(loginScene);
			loginStage.show();
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de la fenêtre login : " + e.getMessage());
		}
	}

	public void afficherInfosOperateur() {
		// Vérifier si les informations de l'opérateur existent
		if (AppInformations.operateurInfo != null) {
			OperateurInfo operateurInfo = AppInformations.operateurInfo;

			// Mettre à jour les labels avec les informations de l'opérateur
			matriculeUser.setText(String.valueOf(operateurInfo.getMatricule()));
			nomPrenomUser.setText(operateurInfo.getNom() + " " + operateurInfo.getPrenom());
			operationUser.setText(operateurInfo.getOperation());
			plantUser.setText(operateurInfo.getPlant());
			posteUser.setText(operateurInfo.getPoste());
			segementUser.setText(operateurInfo.getSegment());
			nomProjet.setText(TorsadageInformations.projetSelectionner);
			specificationsMesure.setText(TorsadageInformations.specificationsMesure);
			nbrEch.setText("5 Piéces");
			codeControleSelectionner.setText(TorsadageInformations.codeControleSelectionner);

		} else {
			System.out.println("Aucune information d'opérateur disponible.");
		}
	}

	private void afficherDateSystem() {
		LocalDate dateActuelle = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		dateSystem.setText(dateActuelle.format(formatter));
	}

	private void afficherHeureSystem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			LocalTime heureActuelle = LocalTime.now();
			heureSystem.setText(heureActuelle.format(formatter));
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // Répéter indéfiniment
		timeline.play(); // Démarrer l'animation
	}
	public void initialiserDonneesPDEKEnregistrer() {
		
		numCommande.setText(TorsadageInformations.numCommande ); 
		longueurfinalDebutCde.setText(TorsadageInformations.longueurFinalDebutCde ); 
		longueurBoutDebutC1.setText(TorsadageInformations.lognueurBoutDebutC1 ); 
		longueurBoutDebutC2.setText(TorsadageInformations.lognueurBoutDebutC2 ); 
		longueurBoutFinC1.setText(TorsadageInformations.lognueurBoutFinC1 ); 
		longueurBoutFinC2.setText(TorsadageInformations.lognueurBoutFinC2 ); 
		decalageMaxDebutCdeC1.setText(TorsadageInformations.decalageDebutC1 ); 
		decalageMaxDebutCdeC2.setText(TorsadageInformations.decalageDebutC2 ); 
		decalageMaxFinCdeC1.setText(TorsadageInformations.decalageFinC1 ); 
		decalageMaxFinCdeC2.setText(TorsadageInformations.decalageFinC2 ); 
		moyenne.setText(TorsadageInformations.moyenne +""); 
		numFil.setText(TorsadageInformations.numFils ); 
		longueurFinCde.setText(TorsadageInformations.longueurFinalFinCde ); 
		longueurPasFinCde.setText(TorsadageInformations.longueurPasFinCde ); 
		ech1.setText(TorsadageInformations.ech1 ); 
		ech2.setText(TorsadageInformations.ech2 ); 
		ech3.setText(TorsadageInformations.ech3 ); 
		ech4.setText(TorsadageInformations.ech4 ); 
		ech5.setText(TorsadageInformations.ech5 ); 
		quantiteTotal.setText(TorsadageInformations.quantiteTotal ); 
		quantiteAtteint.setText(TorsadageInformations.quantiteAtteint ); 
		ettendu.setText(TorsadageInformations.ettendu +""); 
				
	}

	private void createAndAddChartDataMoyenne(StackedAreaChart<Number, Number> chart) {
		    // Fixer les axes
		    NumberAxis xAxis = (NumberAxis) chart.getXAxis();
		    NumberAxis yAxis = (NumberAxis) chart.getYAxis();
		    
		    yAxis.setAutoRanging(false);
		    yAxis.setLowerBound(0);
		    yAxis.setUpperBound(30); // Diviser en 3 zones égales
		    yAxis.setTickUnit(10);

		    // Création des séries pour chaque zone
		    XYChart.Series<Number, Number> seriesRed = new XYChart.Series<>();
		    XYChart.Series<Number, Number> seriesYellow = new XYChart.Series<>();
		    XYChart.Series<Number, Number> seriesGreen = new XYChart.Series<>();

		    for (int i = 0; i <= 4; i++) {
		        seriesRed.getData().add(new XYChart.Data<>(i, 10));    // Rouge : 0 - 10
		        seriesYellow.getData().add(new XYChart.Data<>(i, 10)); // Jaune commence à 10
		        seriesGreen.getData().add(new XYChart.Data<>(i, 10));  // Vert commence à 10+10 = 20
		    }

		    // Ajouter les séries
		    chart.getData().addAll(seriesRed, seriesYellow, seriesGreen);

		    // Appliquer les styles
		    Platform.runLater(() -> {
		        chart.lookupAll(".chart-series-area-fill").forEach(node -> {
		            if (node.getStyleClass().contains("series0")) {
		                node.setStyle("-fx-fill: #f5c6cb;"); // Rouge
		            } else if (node.getStyleClass().contains("series1")) {
		                node.setStyle("-fx-fill: #ffeeba;"); // Jaune
		            } else if (node.getStyleClass().contains("series2")) {
		                node.setStyle("-fx-fill: #d4edda;"); // Vert
		            }
		        });

		        // Supprimer les points visibles des zones
		        for (XYChart.Series<Number, Number> series : chart.getData()) {
		            for (XYChart.Data<Number, Number> data : series.getData()) {
		                data.getNode().setStyle("-fx-background-color: transparent;");
		            }
		        }
		    });
		}

	private void addPointToChart(StackedAreaChart<Number, Number> chart) {
	    int xValue = 2;  // Milieu du graphique
	    double yValue = 20; // Dans la zone jaune

	    XYChart.Series<Number, Number> pointSeries = new XYChart.Series<>();
	    XYChart.Data<Number, Number> pointData = new XYChart.Data<>(xValue, yValue);
	    pointSeries.getData().add(pointData);

	    chart.getData().add(pointSeries);

	    // Forcer l'affichage après le rendu du graphique
	    Platform.runLater(() -> {
	        if (pointData.getNode() != null) {
	            pointData.getNode().setStyle(
	                "-fx-background-color: black; " +
	                "-fx-border-color: black; " +
	                "-fx-shape: 'M 0 0 L 4 4 L 8 0 L 4 -4 Z';" + // Assurer un losange bien visible
	                "-fx-padding: 5px;" // Augmenter la taille du point
	            );
	        } else {
	            System.out.println("Le nœud du point est NULL !");
	        }
	    });
	}

	private void createAndAddChartDataEttendu(StackedAreaChart<Number, Number> chart) {

		    // Création de l'axe X (masqué)
		    NumberAxis xAxis = new NumberAxis();
		    xAxis.setVisible(false);
		    xAxis.setTickLabelsVisible(false);
		    xAxis.setTickMarkVisible(false);
		    xAxis.setOpacity(0);

		    // Création de l'axe Y (masqué)
		    NumberAxis yAxis = new NumberAxis();
		    yAxis.setVisible(false);
		    yAxis.setTickLabelsVisible(false);
		    yAxis.setTickMarkVisible(false);
		    yAxis.setOpacity(0);

		    // Création du StackedAreaChart avec les axes
		    chart.setTitle("L'etendu R");
		    chart.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-fill: black;");
		    chart.setLegendVisible(false); // Désactive la légende automatique
		    chart.setHorizontalGridLinesVisible(false);
		    chart.setVerticalGridLinesVisible(false);
		    chart.setAnimated(false);

		    // Série 1 (Par défaut)
		    XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
		    series1.getData().add(new XYChart.Data<>(0, 26));
		    series1.getData().add(new XYChart.Data<>(1, 26));
		    series1.getData().add(new XYChart.Data<>(2, 26));
		    series1.getData().add(new XYChart.Data<>(3, 26));
		    series1.getData().add(new XYChart.Data<>(4, 26));

		    // Série 2 (Jaune)
		    XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
		    series2.getData().add(new XYChart.Data<>(0, 26));
		    series2.getData().add(new XYChart.Data<>(1, 26)); // Valeur modifiée pour la série 2
		    series2.getData().add(new XYChart.Data<>(2, 26));
		    series2.getData().add(new XYChart.Data<>(3, 26));
		    series2.getData().add(new XYChart.Data<>(4, 26));

		    // Série 3 (Par défaut)
		    XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
		    series3.getData().add(new XYChart.Data<>(0, 26));
		    series3.getData().add(new XYChart.Data<>(1, 26));
		    series3.getData().add(new XYChart.Data<>(2, 26));
		    series3.getData().add(new XYChart.Data<>(3, 26));
		    series3.getData().add(new XYChart.Data<>(4, 26));

		    // Ajouter les séries au graphique
		    chart.getData().addAll(series1, series2, series3);

		    // Utiliser Platform.runLater pour s'assurer que les styles sont appliqués après la création du graphique
		    Platform.runLater(() -> {
		        // Appliquer les styles sur les séries
		        chart.lookupAll(".chart-series-line").forEach(node -> {
		            if (node.getId() != null && node.getId().contains("series2")) {
		                node.setStyle("-fx-stroke: yellow; -fx-fill: #ffd103;");
		            }
		        });

		        // Masquer les nœuds des données (les points sur les lignes)
		        for (XYChart.Series<Number, Number> series : chart.getData()) {
		            for (XYChart.Data<Number, Number> data : series.getData()) {
		                // Cache le nœud de chaque point (symbole de la série)
		                data.getNode().setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		            }
		        }
		    });
		}


}