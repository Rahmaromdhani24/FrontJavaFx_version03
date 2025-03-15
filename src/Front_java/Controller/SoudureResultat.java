package Front_java.Controller;

import java.io.IOException;
import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SoudureInformations;
import Front_java.Configuration.SoudureInformationsCodeB;
import Front_java.Modeles.OperateurInfo;
import Front_java.Services.ServiceSoudure;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.KeyFrame;
import javafx.util.Duration;


public class SoudureResultat{

	 private ServiceSoudure serviceSoudure = new ServiceSoudure();
	 @FXML
	 private BorderPane rootPane; 
	 
	 @FXML
	 private StackPane stackPane;
	 
    @FXML
     private LineChart<Number, Number> chartEtendu;

    @FXML
    private LineChart<Number, Number> chartMoyenne;
  
	@FXML
	private Button btnSuivant;

	@FXML
	private Button btnLogout ;
    @FXML
    private Label dateSystem;

    @FXML
    private Label heureSystem;

    @FXML
    private Label matriculeUser;

    @FXML
    private Label nomPrenomUser;

    @FXML
    private Label operationUser;
    
    @FXML
    private Label segementUser;
    @FXML
    private Label plantUser;

    @FXML
    private Label posteUser;
    
    @FXML
    private Label nomProjet;
    
    @FXML
    private Label sectionFilPDEK;
    
    @FXML
    private Label nbrpelage;
    
    @FXML
    private Label valeurNumeroCycle ; 
    @FXML
    private Label nbrEch ;
    @FXML
    private Label freqControle ;
    
    @FXML
    private Label valeurDistanceBC;

    @FXML
    private Label valeurEtendu;

    @FXML
    private Label valeurGrandeurLot;

    @FXML
    private Label valeurMoyenne;

    @FXML
    private Label valeurNumKanban;

    @FXML
    private Label valeurNumNoeud;

    @FXML
    private Label valeurPelageX1;

    @FXML
    private Label valeurPelageX2;

    @FXML
    private Label valeurPelageX3;

    @FXML
    private Label valeurPelageX4;

    @FXML
    private Label valeurPelageX5;

    @FXML
    private Label valeurPliage;

    @FXML
    private Label valeurQuantite;

    @FXML
    private Label valeurTraction; 
    @FXML
    private Label codeControleSelectionner ;   
    @FXML
    private NumberAxis yAxisEtendu;
    @FXML
    private NumberAxis xAxisEtendu;
    @FXML
    private NumberAxis yAxisMoyenne;
    @FXML
    private NumberAxis xAxisMoyenne;
    @FXML
    public void initialize() {
        afficherInfosOperateur();
        afficherDateSystem();
        afficherHeureSystem();
        initialiserDonneesPDEKEnregistrer();
        testerMoyenne(SoudureInformations.moyenne);
        testerEtendu(SoudureInformations.etendu);

        /************************ Charts **************************/
        // Récupérer les valeurs nécessaires
        int etendu = SoudureInformations.etendu;
        int moyenne = SoudureInformations.moyenne;
        int etenduMax = serviceSoudure.fetchValeurEtenduMax();
        int moyenneVertMin = serviceSoudure.fetchValeurMoyenneMin();
        int moyenneRougeMin = Integer.parseInt(serviceSoudure.getPelageFromApi()) ; 
        
        /**** chart etendu ****/
        
        System.out.println("etenduMax est = " + etenduMax);
        System.out.println("moyenneVertMin est = " + moyenneVertMin);
        System.out.println("moyenneRougeMin est = " + moyenneRougeMin);

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Série pour l'étendu (point bleu)
        XYChart.Series<Number, Number> seriesEtendu = new XYChart.Series<>();
        seriesEtendu.setName("Etendue de numéro de cycle: " + SoudureInformations.numerCyclePDEK);
        XYChart.Data<Number, Number> dataPointEtendu = new XYChart.Data<>(15, etendu);
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

        /*******************************  Chart moyenne ************************/
       
        final NumberAxis xAxisMoyenne = new NumberAxis();
        final NumberAxis yAxisMoyenne = new NumberAxis();

        final LineChart<Number, Number> lineChartMoyenne = new LineChart<>(xAxisMoyenne, yAxisMoyenne);

        // Série pour la moyenne (point oranger)
        XYChart.Series<Number, Number> seriesMoyenne = new XYChart.Series<>();
        seriesMoyenne.setName("Moyenne de numéro de cycle: " + SoudureInformations.numerCyclePDEK+" ");
        XYChart.Data<Number, Number> dataPointMoyenne = new XYChart.Data<>(15, moyenne);
        seriesMoyenne.getData().add(dataPointMoyenne);

        // Série pour la ligne rouge (Moyenne min)
        XYChart.Series<Number, Number> ligneRougeSeriesMoyenne = new XYChart.Series<>();
        ligneRougeSeriesMoyenne.setName("Moyenne Minimum = " + moyenneRougeMin+" ");
        ligneRougeSeriesMoyenne.getData().add(new XYChart.Data<>(0, moyenneRougeMin));
        ligneRougeSeriesMoyenne.getData().add(new XYChart.Data<>(27, moyenneRougeMin));

        // Série pour la ligne verte (Moyenne normale)
        XYChart.Series<Number, Number> ligneVertSeries = new XYChart.Series<>();
        ligneVertSeries.setName("Moyenne Normal = " + moyenneVertMin+" ");
        ligneVertSeries.getData().add(new XYChart.Data<>(0, moyenneVertMin));
        ligneVertSeries.getData().add(new XYChart.Data<>(27, moyenneVertMin));

        // Ajouter les séries au graphique
        lineChartMoyenne.getData().addAll(ligneRougeSeriesMoyenne, seriesMoyenne, ligneVertSeries);

        chartMoyenne.getData().clear();
        chartMoyenne.getData().addAll(lineChartMoyenne.getData());

        Platform.runLater(() -> {
            chartMoyenne.layout();  // Rafraîchir la disposition du graphique
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
	void logout(ActionEvent event) {
		
		AppInformations.reset();
    	SoudureInformations.reset();
    	SoudureInformationsCodeB.reset();
    	
    	Stage currentStage = (Stage) btnLogout.getScene().getWindow();
        currentStage.close();
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

    @FXML
    void submit(ActionEvent event) {  
    	
    	AppInformations.testTerminitionCommande = 0 ; 
    	SoudureInformations.quantiteAtteint = 0 ; 
    	SoudureInformations.numeroKanban = 0 ; 
    	//SoudureInformations.numerCyclePDEK = SoudureInformations.numerCyclePDEK+1 ;
    	SoudureInformations.pliage = null ; 
    	SoudureInformations.distanceBC= null ; 
    	SoudureInformations.traction= null ; 
    	SoudureInformations.pelageX1=0 ; 
    	SoudureInformations.pelageX2=0 ; 
    	SoudureInformations.pelageX3=0 ; 
    	SoudureInformations.pelageX4=0 ; 
    	SoudureInformations.pelageX5=0 ; 
    	SoudureInformations.grandeurLot=0 ; 
    	SoudureInformations.numNoeud= null ;
    	SoudureInformations.moyenne = 0 ;
    	SoudureInformations.etendu=0 ;
    	SoudureInformations.EtenduValueMax=0 ; 
    	SoudureInformations.MoyenneVertMin=0 ; 
    	SoudureInformationsCodeB.codecontroleselectionner = null ; 
    	SoudureInformationsCodeB.quantiteAtteintCodeB = 0 ; 
    	SoudureInformationsCodeB.testTerminitionCommandeCodeB = 0 ; 
    	SoudureInformationsCodeB.pelageX1 = 0 ; 
    	SoudureInformationsCodeB.pliage = null ; 
    	SoudureInformationsCodeB.distanceBC = null ; 
    	SoudureInformationsCodeB.traction = null ; 
    	
        try {
        	AppInformations.sectionFilSelectionner = null ; 
        	AppInformations.codeControleSelectionner = null  ; 
        	AppInformations.projetSelectionner = null ; 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/FXML/dashboard1.fxml"));
            Scene dashboardScene = new Scene(loader.load());
            dashboardScene.getStylesheets().add(getClass().getResource("/Front_java/CSS/dashboard1.css").toExternalForm());

            Stage dashboardStage = new Stage();
            dashboardStage.setScene(dashboardScene);
            dashboardStage.setMaximized(true);
            dashboardStage.initStyle(StageStyle.UNDECORATED);
            Image icon = new Image("/logo_app.jpg");
            dashboardStage.getIcons().add(icon);
            dashboardStage.show();

            Stage currentStage = (Stage) btnSuivant.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la fenêtre dashboard : " + e.getMessage());
            showErrorDialog( "Erreur lors du chargement du tableau de bord !" , "Erreur");
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
            nomProjet.setText(AppInformations.projetSelectionner);
            sectionFilPDEK.setText(AppInformations.sectionFilSelectionner);
            nbrEch.setText("5 Piéces");
            codeControleSelectionner.setText(AppInformations.codeControleSelectionner);
            
            
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


 
  
/*************************** 	  initialiser les informations ajouter precedament     **********************************/
   public void initialiserDonneesPDEKEnregistrer () {
	   
	   valeurNumeroCycle.setText(SoudureInformations.numerCyclePDEK +"");
	   valeurPliage.setText(SoudureInformations.pliage);
	   valeurTraction.setText(SoudureInformations.traction +"N");
	   valeurQuantite.setText(SoudureInformations.quantiteAtteint+"");
	   valeurNumKanban.setText(SoudureInformations.numeroKanban+"");
	   valeurGrandeurLot.setText(SoudureInformations.grandeurLot +"");
       valeurNumNoeud.setText(SoudureInformations.numNoeud);
	   valeurMoyenne.setText(SoudureInformations.moyenne+"");

	   valeurDistanceBC.setText(SoudureInformations.distanceBC);
	   valeurPelageX1.setText(SoudureInformations.pelageX1+"");
	   valeurPelageX2.setText(SoudureInformations.pelageX2+"");
	   valeurPelageX3.setText(SoudureInformations.pelageX3+"");
	   valeurPelageX4.setText(SoudureInformations.pelageX4+"");
	   valeurPelageX5.setText(SoudureInformations.pelageX5+"");
	   valeurEtendu.setText(SoudureInformations.etendu +"");
	   nbrpelage.setText(SoudureInformations.nbrPelage);

	

   }
   
      /*********************   Alerts  Erreur et warning    ***********/
   
private void showErrorDialog(String title, String message) {
		Image errorIcon = new Image(getClass().getResource("/icone_erreur.png").toExternalForm());
		ImageView errorImageView = new ImageView(errorIcon);
		errorImageView.setFitWidth(100);
		errorImageView.setFitHeight(100);

		VBox iconBox = new VBox(errorImageView);
		iconBox.setAlignment(Pos.CENTER);

		Label messageLabel = new Label(message);
		messageLabel.setWrapText(true);
		messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center;");

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

	/**** extraire N depuis valeur Pelage *******/
	public int extractNumber(String input) {
	    String numericPart = input.substring(0, input.length() - 1);
	    return Integer.parseInt(numericPart); 
	}

	/****************** extraire mm depuis section fil ****/
	public double extraireValeurNumeriqueSectionFil(String sectionFil) {
	    return Double.parseDouble(sectionFil.trim().split(" ")[0] );
	}
	
	/******************   Recuperer valeur vert   min de moyenne *****************/
	
	

	public void testerMoyenne(int moyenneEch) {
	    int minPelage = extractNumber(AppInformations.nbrPelage);
	    
	    CompletableFuture<String> future = serviceSoudure.getValeurMoyenneMinFromApi();

	    future.thenApply(result -> {
	        System.out.println("Valeur reçue de l'API : " + result); // Debugging
	        try {
	            return Integer.parseInt(result.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Erreur lors de la conversion de la valeur reçue de l'API : " + result);
	            return 0; // Retourne 0 en cas d'erreur
	        }
	    }).thenAccept(parsedValueMoyenne -> {
	    	SoudureInformations.MoyenneVertMin = parsedValueMoyenne ; 
	    	System.out.println("Vérification des valeurs :");
	    	System.out.println("moyenneEch : " + moyenneEch);
	    	System.out.println("minPelage : " + minPelage);
	    	System.out.println("parsedValue : " + parsedValueMoyenne);

	    	if ((moyenneEch < parsedValueMoyenne) && (minPelage < moyenneEch)) { // Zone jaune
	    	    System.out.println("Zone jaune détectée");
	    	    Platform.runLater(() -> {
	    	        showWarningDialog("La valeur X dépasse les limites d'alarme (zone jaune). \nL'opérateur " 
	    	            + AppInformations.operateurInfo.getPrenom() + " " 
	    	            + AppInformations.operateurInfo.getNom() 
	    	            + " doit informer son supérieur hiérarchique immédiatement.", "Attention - Limite dépassée");
	    	    });
	    	} 
	        if (moyenneEch < minPelage) { // Zone rouge
	            System.out.println("Zone rouge détectée");
	    	    Platform.runLater(() -> {
	            showErrorDialog("La valeur X dépasse la limite de contrôle (zone rouge). \nL'opérateur " 
	                + AppInformations.operateurInfo.getPrenom() + " " 
	                + AppInformations.operateurInfo.getNom() 
	                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté");
	    	    });
	    	    } else {
	            System.out.println("Aucune alerte déclenchée");
	        }
	    });
	}

	/******************   Recuperer valeur max  de etendu  *****************/
	
	

	public void testerEtendu(int etenduEch) {
	    
	    CompletableFuture<String> future = serviceSoudure.getValeurEtenduFromApi();

	    future.thenApply(result -> {
	        System.out.println("Valeur reçue de l'API : " + result); // Debugging
	        try {
	            return Integer.parseInt(result.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Erreur lors de la conversion de la valeur reçue de l'API : " + result);
	            return 0; // Retourne 0 en cas d'erreur
	        }
	    }).thenAccept(parsedEtenduValue -> {
	    	System.out.println("Vérification des valeurs :");
	    	System.out.println("etenduEch : " + etenduEch);
	    	System.out.println("parsedEtenduValue : " + parsedEtenduValue);
	    	
	    	

	    	if (etenduEch >=  parsedEtenduValue) {
	    		  Platform.runLater(() -> {
	  	            showErrorDialog("La valeur X dépasse la limite de contrôle (zone rouge). \nL'opérateur " 
	  	                + AppInformations.operateurInfo.getPrenom() + " " 
	  	                + AppInformations.operateurInfo.getNom() 
	  	                + " doit appliquer l'arrêt 1er défaut.", "Problème détecté");
	  	    	    });	
	               }
	           }) ;
	}
	
	
}	
/*
 * AppInformations.testTerminitionCommande = 0 ; 
    	SoudureInformations.quantiteAtteint = 0 ; 
    	SoudureInformations.numeroKanban = 0 ; 
    	//SoudureInformations.numerCyclePDEK = SoudureInformations.numerCyclePDEK+1 ;
    	SoudureInformations.pliage = null ; 
    	SoudureInformations.distanceBC= null ; 
    	SoudureInformations.traction= null ; 
    	SoudureInformations.pelageX1=0 ; 
    	SoudureInformations.pelageX2=0 ; 
    	SoudureInformations.pelageX3=0 ; 
    	SoudureInformations.pelageX4=0 ; 
    	SoudureInformations.pelageX5=0 ; 
    	SoudureInformations.grandeurLot=0 ; 
    	SoudureInformations.numNoeud= null ;
    	SoudureInformations.moyenne = 0 ;
    	SoudureInformations.etendu=0 ;
    	SoudureInformations.EtenduValueMax=0 ; 
    	SoudureInformations.MoyenneVertMin=0 ; 
    	SoudureInformationsCodeB.codecontroleselectionner = null ; 
    	SoudureInformationsCodeB.quantiteAtteintCodeB = 0 ; 
    	SoudureInformationsCodeB.testTerminitionCommandeCodeB = 0 ; 
    	SoudureInformationsCodeB.pelageX1 = 0 ; 
    	SoudureInformationsCodeB.pliage = null ; 
    	SoudureInformationsCodeB.distanceBC = null ; 
    	SoudureInformationsCodeB.traction = null ; 
    	*/
