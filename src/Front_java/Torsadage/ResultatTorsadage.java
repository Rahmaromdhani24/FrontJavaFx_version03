package Front_java.Torsadage;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SoudureInformations;
import Front_java.Modeles.OperateurInfo;
import Front_java.Modeles.TorsadageDTO;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import Front_java.Configuration.TorsadageInformations;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
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


/****************/
    @FXML
    private StackedBarChart<String, Number> chartMoyenne; // Lier le graphique à l'ID du FXML
    @FXML
    private CategoryAxis xAxis; // Lier l'axe X à l'ID du FXML
    @FXML
    private NumberAxis yAxis; // Lier l'axe Y à l'ID du FXML
    @FXML
    private Pane  paneMoyenne;
    @FXML
    private NumberAxis yAxisEtendu;
    @FXML
    private NumberAxis xAxisEtendu;

    @FXML
    private LineChart<Number, Number> chartEtendu;
	@FXML
	public void initialize() {
	

		initialiserDonneesPDEKEnregistrer() ; 
		ajouterPdekAvecTorsadage() ; 		
		afficherInfosOperateur();
		AppInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		//createAndAddChartDataEttendu(chartEttendu);
	   // createAndAddChartDataMoyenne(chartMoyenne);
	   // addPointToChart(chartEttendu);
	
		testerMoyenne(TorsadageInformations.moyenne) ; 
		testerEtendu(TorsadageInformations.ettendu) ; 
		setupChartMoyenne() ;
	      double etenduMax = 2.4 ;
		  final NumberAxis xAxis = new NumberAxis();
	      final NumberAxis yAxis = new NumberAxis();

	        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

	        // Série pour l'étendu (point bleu)
	        XYChart.Series<Number, Number> seriesEtendu = new XYChart.Series<>();
	        seriesEtendu.setName("Etendue de numéro de cycle: " + SoudureInformations.numerCyclePDEK);
	        XYChart.Data<Number, Number> dataPointEtendu = new XYChart.Data<>(15, TorsadageInformations.ettendu);
	        seriesEtendu.getData().add(dataPointEtendu);

	        // Série pour la ligne rouge (etenduMax)
	        XYChart.Series<Number, Number> ligneRougeSeries = new XYChart.Series<>();
	        ligneRougeSeries.setName("Etendu Maximum = " + etenduMax);
	        ligneRougeSeries.getData().add(new XYChart.Data<>(0, etenduMax));
	        ligneRougeSeries.getData().add(new XYChart.Data<>(27, etenduMax));

	        // Ajouter les séries au graphique
	        lineChart.getData().addAll(ligneRougeSeries, seriesEtendu);

	        chartEtendu.getData().clear();  
	        chartEtendu.getData().addAll(lineChart.getData());

	        Platform.runLater(() -> {
	            chartEtendu.layout();  // Rafraîchir la disposition du graphique
	        });
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
	
		TorsadageInformations.projetSelectionner= null ; 
		TorsadageInformations.codeControleSelectionner= null ; 
		TorsadageInformations.specificationsMesure = null ; 
		try {
			// Charger la scène de Dashboard1
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/SelectionInitiale.fxml"));
			Scene dashboard1Scene = new Scene(loader.load());
			dashboard1Scene.getStylesheets()
					.add(getClass().getResource("/Front_java/Torsadage/SelectionInitiale.css").toExternalForm());

			// Créer une nouvelle fenêtre (Stage)
			Stage dashboard1Stage = new Stage();
			dashboard1Stage.setScene(dashboard1Scene);
			dashboard1Stage.setMaximized(true);
			dashboard1Stage.initStyle(StageStyle.UNDECORATED); // Supprimer le titre et les boutons
			Image icon = new Image("/logo_app.jpg");
			dashboard1Stage.getIcons().add(icon);
			dashboard1Stage.show();

			// Fermer la fenêtre actuelle (Dashboard2)
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.close();

		} catch (IOException e) {
			System.out.println("Erreur lors du chargement de la fenêtre dashboard1 : " + e.getMessage());
		}
	}
	

	

	@FXML
	void logout(ActionEvent event) {


    	AppInformations.reset();
    	TorsadageInformations.reset();

    

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
			specificationsMesure.setText(TorsadageInformations.specificationsMesure +" +/- 2 mm");
			nbrEch.setText("3 Piéces");
			nbrEch.getStyleClass().add("bold-label");
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
		nbrCycle.setText(TorsadageInformations.numCourant+"");
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

	 private void ajouterPdekAvecTorsadage() {
			Task<Void> task = new Task<>() {
				@Override
				protected Void call() throws Exception {
					try {
						// Code pour l'ajout du PDEK
						String token = AppInformations.token;
						String encodedProjet = URLEncoder.encode(TorsadageInformations.projetSelectionner,
								StandardCharsets.UTF_8);

						String url = "http://localhost:8281/operations/torsadage/ajouterPDEK" + "?matriculeOperateur="
								+ AppInformations.operateurInfo.getMatricule() + "&projet=" + encodedProjet;

						// Récupération des valeurs saisies et création de l'objet SoudureDTO
						TorsadageDTO torsadage = new TorsadageDTO();
						int x1 = Integer.parseInt(TorsadageInformations.ech1);
						int x2=  Integer.parseInt(TorsadageInformations.ech2);
						int x3 = Integer.parseInt(TorsadageInformations.ech3);
						int x4 = Integer.parseInt(TorsadageInformations.ech4);
						int x5 = Integer.parseInt(TorsadageInformations.ech5);

						// Calcul des valeurs max et min
						int maxValue = Math.max(Math.max(Math.max(x1, x2), Math.max(x3, x4)), x5);
						int minValue = Math.min(Math.min(Math.min(x1, x2), Math.min(x3, x4)), x5);
						double moy = (x1 + x2 + x3 + x4 + x5) / 5.0;
						int R = maxValue - minValue;

						// Remplir l'objet SoudureDTO avec les valeurs
						torsadage.setCode(TorsadageInformations.codeControleSelectionner);
						torsadage.setNumeroCycle(TorsadageInformations.numCourant );
						torsadage.setSpecificationMesure(TorsadageInformations.specificationsMesure);
						torsadage.setDecalageMaxDebutCdec1(Integer.parseInt(TorsadageInformations.decalageDebutC1)); 					
						torsadage.setDecalageMaxDebutCdec2(Integer.parseInt(TorsadageInformations.decalageDebutC2)); 		
						torsadage.setDecalageMaxFinCdec1(Integer.parseInt(TorsadageInformations.decalageFinC1 )); 					
						torsadage.setDecalageMaxFinCdec2(Integer.parseInt(TorsadageInformations.decalageFinC2 )); 									
						torsadage.setEch1(x1);
						torsadage.setEch2(x2);
						torsadage.setEch3(x3);
						torsadage.setEch4(x4 );
						torsadage.setEch5(x5);		
						torsadage.setMoyenne(moy);
						torsadage.setEtendu(R);
						ZoneId zoneId = ZoneId.of("Europe/Paris"); // Remplace par ton fuseau horaire
						LocalDate dateActuelle = Instant.now().atZone(zoneId).toLocalDate();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						torsadage.setDate(dateActuelle.format(formatter));
						torsadage.setQuantiteAtteint(Integer.parseInt(TorsadageInformations.quantiteAtteint));
						torsadage.setQuantiteTotale(Integer.parseInt(TorsadageInformations.quantiteTotal));
						torsadage.setNumerofil(TorsadageInformations.numFils);
						torsadage.setNumCommande(Integer.parseInt(TorsadageInformations.numCommande));
						torsadage.setLongueurBoutDebutCdeC1(Integer.parseInt(TorsadageInformations.lognueurBoutDebutC1));
						torsadage.setLongueurBoutDebutCdeC2(Integer.parseInt(TorsadageInformations.lognueurBoutDebutC2));
						torsadage.setLongueurBoutFinCdeC1(Integer.parseInt(TorsadageInformations.lognueurBoutFinC1));
						torsadage.setLongueurBoutFinCdeC2(Integer.parseInt(TorsadageInformations.lognueurBoutFinC2));
						torsadage.setLongueurFinalDebutCde(Integer.parseInt(TorsadageInformations.longueurFinalDebutCde));
						torsadage.setLongueurFinalFinCde(Integer.parseInt(TorsadageInformations.longueurFinalFinCde));
						torsadage.setLongueurPasFinCde(Double.parseDouble(TorsadageInformations.longueurPasFinCde));

						// Conversion de l'objet SoudureDTO en JSON
						ObjectMapper objectMapper = new ObjectMapper();
						String torsadageJson = objectMapper.writeValueAsString(torsadage);

						HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
								.header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
								.POST(HttpRequest.BodyPublishers.ofString(torsadageJson)).build();

						HttpClient client = HttpClient.newHttpClient();
						HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

						if (response.statusCode() == 200) {
							System.out.println("Succès Ajout PDEK : " + response.body());
						} else {
							System.out.println("Erreur dans l'ajout PDEK, code : " + response.statusCode() + ", message : "
									+ response.body());
							throw new Exception("Erreur dans l'ajout PDEK : " + response.body());
						}

					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("Erreur dans la méthode ajouterPdekAvecSoudure : " + e.getMessage());
					}
					return null;
				}
			};

			task.setOnFailed(event -> {
				Throwable exception = task.getException();
				System.out.println("Erreur lors de l'ajout du PDEK : " + exception.getMessage());
				showErrorDialog("Erreur", "Erreur lors de l'ajout du PDEK : " + exception.getMessage());
			});

			new Thread(task).start();
		}
	 /*********************************          Alerts        ***************************************/


		/*********************************          Alerts        ***************************************/

		private void showErrorDialog(String title, String message) {
			Image errorIcon = new Image(getClass().getResource("/icone_erreur.png").toExternalForm());
			ImageView errorImageView = new ImageView(errorIcon);
			errorImageView.setFitWidth(100);
			errorImageView.setFitHeight(100);

			VBox iconBox = new VBox(errorImageView);
			iconBox.setAlignment(Pos.CENTER);

			Label messageLabel = new Label(message);
			messageLabel.setWrapText(true);
			messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: black;");

			Label titleLabel = new Label(title);
			titleLabel.setStyle("-fx-font-size: 19px; -fx-text-alignment: center;");
			VBox titleBox = new VBox(titleLabel);
			titleBox.setAlignment(Pos.CENTER);

			VBox contentBox = new VBox(10, iconBox, messageLabel, titleBox);
			contentBox.setAlignment(Pos.CENTER);

			JFXDialogLayout content = new JFXDialogLayout();
			content.setBody(contentBox);
			content.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

			JFXButton closeButton = new JFXButton("Fermer");
			closeButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px;-fx-font-weight: bold;");
			content.setActions(closeButton);

			// Utilisation de stackPane ici
			JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
			closeButton.setOnAction(e -> dialog.close());

			dialog.show();

			Platform.runLater(() -> {
				StackPane overlayPane = (StackPane) dialog.lookup(".jfx-dialog-overlay-pane");
				if (overlayPane != null) {
					overlayPane.setStyle("-fx-background-color: transparent;");
				}
			});
		}
/************************ test Moyenne et etendu *************/
		public static int extraireValeur(String input) {
			
		    return Integer.parseInt(input.replaceAll("(\\d+)\\s*mm.*", "$1"));
		}

		public void testerMoyenne(double moyenneEch) {//20.2
		    
			int pas =  extraireValeur(TorsadageInformations.specificationsMesure) ;  // 20
			System.out.println("Pas sans mm = "+pas );
			double valeurMaxRougeSuperieur = pas +2; //22
			double valeurMaxRougeInferieur = pas - 2 ;  //18
			double debutZoneJaune = (pas -2)+0.8 ;  //18.8==>fin zone jaune et debut zone vert 
			double finZoneJaune = (pas+2)-0.8 ;         //21.2 ==> fin zone vert et debut zone jaune 

		    	if ((moyenneEch >= finZoneJaune )&&(moyenneEch < valeurMaxRougeSuperieur ) )  { // Zone jaune
		    	    System.out.println("Zone jaune détectée");
		    	    Platform.runLater(() -> {
		    	        showWarningDialog("La valeur X dépasse les limites d'alarme (zone jaune). \nL'opérateur " 
		    	            + AppInformations.operateurInfo.getPrenom() + " " 
		    	            + AppInformations.operateurInfo.getNom() 
		    	            + " doit informer son supérieur hiérarchique immédiatement.", "Attention - Limite dépassée");
		    	    });
		    	} 
		    	if ((moyenneEch > valeurMaxRougeInferieur) && (moyenneEch <=  debutZoneJaune)  ) { // Zone jaune
		    	    System.out.println("Zone jaune détectée");
		    	    Platform.runLater(() -> {
		    	        showWarningDialog("La valeur X dépasse les limites d'alarme (zone jaune). \nL'opérateur " 
		    	            + AppInformations.operateurInfo.getPrenom() + " " 
		    	            + AppInformations.operateurInfo.getNom() 
		    	            + " doit informer son supérieur hiérarchique immédiatement.", "Attention - Limite dépassée");
		    	    });
		    	} 
		        if ((moyenneEch <= valeurMaxRougeInferieur)) { // Zone rouge
		            System.out.println("Zone rouge détectée");
		    	    Platform.runLater(() -> {
		            showErrorDialog("La valeur X dépasse la limite de contrôle (zone rouge). \nL'opérateur " 
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté");
		    	    });
		    	    } 
		        if ((moyenneEch >=  valeurMaxRougeSuperieur)) { // Zone rouge
		            System.out.println("Zone rouge détectée");
		    	    Platform.runLater(() -> {
		            showErrorDialog("La valeur X dépasse la limite de contrôle (zone rouge). \nL'opérateur " 
		                + AppInformations.operateurInfo.getPrenom() + " " 
		                + AppInformations.operateurInfo.getNom() 
		                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté");
		    	    });
		    	    }else {
		            System.out.println("Aucune alerte déclenchée");
		        }
		   
		}

		/******************   Recuperer valeur max  de etendu  *****************/
		
		

		public void testerEtendu(int etenduEch) {
		    

		    	if (etenduEch >=  2.4) {
		    		  Platform.runLater(() -> {
		  	            showErrorDialog("La valeur R dépasse la limite de contrôle (zone rouge). \nL'opérateur " 
		  	                + AppInformations.operateurInfo.getPrenom() + " " 
		  	                + AppInformations.operateurInfo.getNom() 
		  	                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté");
		  	    	    });	
		               }
                       }
		
		/****/

		private void showWarningDialog(String title, String message) {
			Image warningIcon = new Image(getClass().getResource("/warning.jpg").toExternalForm());
			ImageView warningImageView = new ImageView(warningIcon);
			warningImageView.setFitWidth(150);
			warningImageView.setFitHeight(150);

			VBox iconBox = new VBox(warningImageView);
			iconBox.setAlignment(Pos.CENTER);

			Label messageLabel = new Label(message);
			messageLabel.setWrapText(true);
			messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: black;");

			Label titleLabel = new Label(title);
			titleLabel.setStyle("-fx-font-size: 19px; -fx-text-alignment: center;");
			VBox titleBox = new VBox(titleLabel);
			titleBox.setAlignment(Pos.CENTER);

			VBox contentBox = new VBox(10, iconBox, messageLabel, titleBox);
			contentBox.setAlignment(Pos.CENTER);

			JFXDialogLayout content = new JFXDialogLayout();
			content.setBody(contentBox);
			content.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

			JFXButton closeButton = new JFXButton("Fermer");
			closeButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px; -fx-font-weight: bold;");
			content.setActions(closeButton);

			JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
			closeButton.setOnAction(e -> dialog.close());

			dialog.show();

			Platform.runLater(() -> {
				StackPane overlayPane = (StackPane) dialog.lookup(".jfx-dialog-overlay-pane");
				if (overlayPane != null) {
					overlayPane.setStyle("-fx-background-color: transparent;");
				}
			});
		}
/****************************** chart Moyenne **********************/
		/************************* chart Moyenne **********************/
		public void setupChartMoyenne() {
		    // Configuration des axes
		    yAxis.setLowerBound(0);
		    yAxis.setUpperBound(30);
		    xAxis.setOpacity(0);  // Masquer l'axe X
		    yAxis.setOpacity(0);  // Masquer l'axe Y
	        chartMoyenne.setTitle("La moyenne de X ");
    
		    // Création des séries de données
		    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		    series1.setName("Zone Rouge"); // Première fois affiché
		    series1.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		    series2.setName("Zone Jaune"); // Première fois affiché
		    series2.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		    series3.setName("Zone Verte"); // Première fois affiché
		    series3.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series4 = new XYChart.Series<>();
		    series4.setName(""); // Masquer la légende (car "Zone Jaune" existe déjà)
		    series4.getData().add(new XYChart.Data<>("A", 10));

		    XYChart.Series<String, Number> series5 = new XYChart.Series<>();
		    series5.setName(""); // Masquer la légende (car "Zone Rouge" existe déjà)
		    series5.getData().add(new XYChart.Data<>("A", 10));

		    // Ajout des séries au graphique
		    chartMoyenne.getData().addAll(series1, series2, series3, series4, series5);

		    // Appliquer les couleurs après le rendu du graphique
		    Platform.runLater(() -> {
		        setBarColor(series1, "red");
		        setBarColor(series2, "yellow");
		        setBarColor(series3, "green");
		        setBarColor(series4, "yellow");
		        setBarColor(series5, "red");
		    });

		    // Ajout d'un point noir à une valeur spécifique
		    Platform.runLater(() -> addPointToChart(chartMoyenne, xAxis, yAxis, paneMoyenne, 15));
		}

		// Nouvelle méthode pour appliquer les couleurs aux barres
		private void setBarColor(XYChart.Series<String, Number> series, String color) {
		    Platform.runLater(() -> {
		        for (XYChart.Data<String, Number> data : series.getData()) {
		            Node barNode = data.getNode();
		            if (barNode != null) {
		                barNode.setStyle("-fx-bar-fill: " + color + ";");
		            }
		        }
		    });
		}

		// Méthode pour ajouter un point noir au graphique
		private void addPointToChart(StackedBarChart<String, Number> stackedBarChart, CategoryAxis xAxis, NumberAxis yAxis, Pane overlayPane, double yValue) {
		    double yPosition = getYPositionForPoint(yValue, yAxis);
		    double xPosition = xAxis.getDisplayPosition("A");

		    Circle point = new Circle(5);
		    point.setFill(Color.BLACK);
		    point.setCenterX(xPosition);
		    point.setCenterY(yPosition);

		    overlayPane.getChildren().add(point);
		}

		// Méthode pour calculer la position Y correcte en fonction des tranches de valeurs
		private double getYPositionForPoint(double value, NumberAxis yAxis) {
		    double cumulativeHeight = 0;

		    if (value >= 0 && value <= 10) {
		        cumulativeHeight = value;
		    } else if (value > 10 && value <= 20) {
		        cumulativeHeight = 10 + (value - 10);
		    } else if (value > 20 && value <= 30) {
		        cumulativeHeight = 20 + (value - 20);
		    }

		    return yAxis.getDisplayPosition(cumulativeHeight);
		}
}