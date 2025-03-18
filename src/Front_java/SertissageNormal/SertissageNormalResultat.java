package Front_java.SertissageNormal;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SertissageNormaleInformations;
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


public class SertissageNormalResultat{

	

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
    private Label hauteurIsolantEch1;

    @FXML
    private Label hauteurIsolantFinCde;

    @FXML
    private Label hauteurSertissageEch1;

    @FXML
    private Label hauteurSertissageEch3;

    @FXML
    private Label hauteurSertissageFinCde;

    @FXML
    private Label hauteurSertissageEch2;

    @FXML
    private Label heureSystem;

    @FXML
    private Label largeurIsolant;

    @FXML
    private Label largeurSertissageEch1;

    @FXML
    private Label largeurSertissageFinCde;

    @FXML
    private Label longueurFinCde;

    @FXML
    private Label longueurPasFinCde;

    @FXML
    private Label machineTraction;

    @FXML
    private Label matriculeUser;

    @FXML
    private Label nbrCycle;

    @FXML
    private Label nbrEch;

    @FXML
    private Label nomPrenomUser;

    @FXML
    private Label nomProjet;

    @FXML
    private Label numFil;

    @FXML
    private Label numProduit;

    @FXML
    private Label operationUser;

    @FXML
    private Label plantUser;

    @FXML
    private Label posteUser;

    @FXML
    private Label quantiteCycle;

    @FXML
    private Label quantiteTotal;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label sectionFil;

    @FXML
    private Label segementUser;

    @FXML
    private Label serieProduit;

    @FXML
    private StackPane stackPane;

    @FXML
    private Label traction;

	    
    @FXML
    public void initialize() throws Exception {
        afficherInfosOperateur();
        afficherDateSystem();
        afficherHeureSystem();
        initialiserDonneesPDEKEnregistrer();
       

      
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
    void suivant(ActionEvent event) {  
    	
    	/*AppInformations.testTerminitionCommande = 0 ; 
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
    	SoudureInformationsCodeB.traction = null ; */
    	
        try {
        	AppInformations.sectionFilSelectionner = null ; 
        	AppInformations.codeControleSelectionner = null  ; 
        	AppInformations.projetSelectionner = null ; 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/SertissageNormal/SelectionSertissageNormal.fxml"));
            Scene dashboardScene = new Scene(loader.load());
            dashboardScene.getStylesheets().add(getClass().getResource("/Front_java/SertissageNormal/SelectionSertissageNormal.css").toExternalForm());

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
    	

    public void afficherInfosOperateur() throws Exception {
		// Vérifier si les informations de l'opérateur existent
		if (AppInformations.operateurInfo != null) {
			OperateurInfo operateurInfo = AppInformations.operateurInfo;

			// Mettre à jour les labels avec les informations de l'opérateur
			matriculeUser.setText(String.valueOf(operateurInfo.getMatricule()));
			nomPrenomUser.setText(operateurInfo.getNom() + " " + operateurInfo.getPrenom());
			operationUser.setText("Sertissage ");
			plantUser.setText(operateurInfo.getPlant());
			posteUser.setText(operateurInfo.getPoste());
			segementUser.setText(operateurInfo.getSegment());
			nomProjet.setText(SertissageNormaleInformations.projetSelectionner);
			sectionFil.setText(SertissageNormaleInformations.sectionFil);
			nbrEch.setText("3 Piéces");
			
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
	   
	   hauteurSertissageEch1.setText("       "+SertissageNormaleInformations.hauteurSertissageEch1);
	   hauteurSertissageEch2.setText(" "+SertissageNormaleInformations.hauteurSertissageEch2);
	   hauteurSertissageEch3.setText(" "+SertissageNormaleInformations.hauteurSertissageEch3);
	   hauteurSertissageFinCde.setText("    "+SertissageNormaleInformations.hauteurSertissageFinal);
	   largeurSertissageEch1.setText("       "+SertissageNormaleInformations.largeurSertissage );
	   largeurSertissageFinCde.setText("   "+SertissageNormaleInformations.largeurSertissageFinal );
       hauteurIsolantEch1.setText("       "+SertissageNormaleInformations.hauteurIsolant);
	   hauteurIsolantFinCde.setText("  "+SertissageNormaleInformations.hauteurIsolantFinal);

	   largeurIsolant.setText("       "+SertissageNormaleInformations.largeurIsolant);
	   traction.setText("       "+SertissageNormaleInformations.traction);
	   numProduit.setText("       "+SertissageNormaleInformations.produit);
	   serieProduit.setText("       "+SertissageNormaleInformations.serieProduit);
	   quantiteCycle.setText("       "+SertissageNormaleInformations.quantiteCycle);
	   machineTraction.setText("       "+SertissageNormaleInformations.machineTraction);

	

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
	
	

	
	/******************   Recuperer valeur max  de etendu  *****************/
	
	
	/*********************  Recupere des detailes des elements *****/
    private String getElementFromSection(String sectionFil, String element) throws Exception {
        String token = AppInformations.token;

        // Encodage correct des paramètres pour éviter tout problème
        String encodedSectionFil = URLEncoder.encode(sectionFil, StandardCharsets.UTF_8);
        // Remplacer les '+' par '%20' pour éviter que '+' soit interprété comme un espace
        encodedSectionFil = encodedSectionFil.replace("+", "%20");

        String encodedElement = URLEncoder.encode(element, StandardCharsets.UTF_8);

        // URL de l'API avec la section et l'élément spécifiques
        String url = "http://localhost:8281/operations/SertissageNormal/sections/" + encodedSectionFil + "/" + encodedElement;


        // Construction de la requête HTTP avec le token d'authentification
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .build();

        // Création du client HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Vérification du code de statut HTTP
        if (response.statusCode() == 200) {
            String responseBody = response.body().trim();

            // Retourner la valeur de l'élément demandé
            return responseBody;
        } else {
            throw new Exception("Erreur lors de la récupération de l'élément : "
                + response.statusCode() + " - " + response.body());
        }
    }

	
	
	
}	

