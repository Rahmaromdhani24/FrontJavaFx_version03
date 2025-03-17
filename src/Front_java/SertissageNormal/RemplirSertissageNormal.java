package Front_java.SertissageNormal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import Front_java.Configuration.AppInformations;
import Front_java.Configuration.SertissageNormaleInformations;
import Front_java.Modeles.OperateurInfo;
import Front_java.SertissageNormal.loading.LoadingSertissageNormal;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import Front_java.Configuration.TorsadageInformations;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;




public class RemplirSertissageNormal {

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
	    private Button btnPrecedant;

	    @FXML
	    private Button btnSuivant;

	    @FXML
	    private ImageView clearImage;

	    @FXML
	    private Label codeControleSelectionner;

	    @FXML
	    private Label dateSystem;

	    @FXML
	    private TextField hauteurIsolantEch1;

	    @FXML
	    private TextField hauteurIsolantFinCde;

	    @FXML
	    private TextField hauteurSertissageEch1;

	    @FXML
	    private TextField hauteurSertissageEch2;

	    @FXML
	    private TextField hauteurSertissageEch3;

	    @FXML
	    private TextField hauteurSertissageFinCommande;

	    @FXML
	    private Label heureSystem;

	    @FXML
	    private Label labelHauteurIsolant;

	    @FXML
	    private Label labelHauteurSertissage;

	    @FXML
	    private Label labelLargeurIsolant;

	    @FXML
	    private Label labelLargeurSertissage;

	    @FXML
	    private Label labelTraction;

	    @FXML
	    private TextField tractionEch1;
	    
	    @FXML
	    private TextField largeurIsolantEch1;

	    @FXML
	    private TextField largeurIsolantFinCde;

	    @FXML
	    private TextField largeurSertissageEch1;

	    @FXML
	    private TextField largeurSertissageFinCde;


	    @FXML
	    private TextField machineTractionEch2;

	    @FXML
	    private TextField machineTractionEch3;

	    @FXML
	    private TextField machineTractionFinCde;

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
	    private TextField numProduit;

	    @FXML
	    private Label operationUser;

	    @FXML
	    private Label plantUser;

	    @FXML
	    private Label posteUser;

	    @FXML
	    private TextField quantiteCycle;

	    @FXML
	    private TextField quantiteCycleEch2;

	    @FXML
	    private TextField quantiteCycleEch3;

	    @FXML
	    private TextField quantiteCycleFinCde;

	    @FXML
	    private BorderPane rootPane;

	    @FXML
	    private Label segementUser;

	    @FXML
	    private TextField serieProduit;


	    @FXML
	    private Label sectionFil;

	    @FXML
	    private StackPane stackPane;

	    @FXML
	    private ComboBox<String> listeMachinesTraction;

	    @FXML
	    private TextField tractionFinCde;

		public TextField activeTextField;
		
		public TextField getActiveTextField() {
			return activeTextField;
		}
		public void setActiveOnFocus(TextField textField) {
			textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
				if (newVal) {
					activeTextField = textField;
				}
			});
		}
		  @FXML
		    public void handleButtonClick(ActionEvent event) {
		        if (activeTextField != null) {
		            Button clickedButton = (Button) event.getSource();
		            String buttonText = clickedButton.getText();
		            activeTextField.appendText(buttonText);
		        }
		    }

		    // Méthode pour définir le TextField actif
		    public void setActiveTextField(TextField textField) {
		        this.activeTextField = textField;
		    }

	@FXML
	public void initialize() throws Exception {
	hauteurSertissageFinCommande.setDisable(true); 
	largeurSertissageFinCde.setDisable(true);
	hauteurIsolantFinCde.setDisable(true);
    quantiteCycle.setDisable(true);

        loadListeMachinesTractions() ; 
		afficherInfosOperateur();
        SertissageNormaleInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		//loadNumeroCycleMax();
		/*clearImage.setOnMouseClicked(event -> {
			if (activeTextField != null) {
				activeTextField.clear();
			}
		});*/

		setActiveOnFocus(hauteurSertissageEch1);
		setActiveOnFocus(hauteurSertissageEch2);
		setActiveOnFocus(hauteurSertissageEch3);
		setActiveOnFocus(hauteurSertissageFinCommande);
		setActiveOnFocus(largeurSertissageEch1);
		setActiveOnFocus(largeurSertissageFinCde);
		

		setActiveOnFocus(hauteurIsolantEch1);
		setActiveOnFocus(hauteurIsolantFinCde);
		setActiveOnFocus(largeurIsolantEch1);		

		setActiveOnFocus(tractionEch1);
		setActiveOnFocus(numProduit);
		setActiveOnFocus(serieProduit);
		setActiveOnFocus(quantiteCycle);
	
	
	}

	private void loadListeMachinesTractions() {
	    Task<ObservableList<String>> task = new Task<>() {
	        @Override
	        protected ObservableList<String> call() {
	            return FXCollections.observableArrayList(
	                "D-128", "D-101", "D-103"
	            );
	        }
	    };

	    task.setOnSucceeded(event -> {
	      listeMachinesTraction.setItems(task.getValue());
	      listeMachinesTraction.getSelectionModel().clearSelection(); // Désélectionner toute valeur par défaut
	      listeMachinesTraction.setValue(null); // S'assurer qu'aucune valeur n'est affichée au démarrage

	      listeMachinesTraction.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
	          if (newValue != null) {
	               System.out.println("Numéro de fil sélectionné : " + newValue);
	          }
	      });
	    });

	    task.setOnFailed(event -> {
	        System.out.println("Erreur lors du chargement des numéros de fils : " + task.getException().getMessage());
	    });

	    new Thread(task).start();
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
	
	private boolean checkOtherFields() {
	    return !hauteurSertissageEch1.getText().isEmpty() &&
	           !hauteurSertissageEch2.getText().isEmpty() &&
	           !hauteurSertissageEch3.getText().isEmpty() &&
	           !largeurSertissageEch1.getText().isEmpty() &&
	           !hauteurIsolantEch1.getText().isEmpty() &&
	           !largeurIsolantEch1.getText().isEmpty() &&
	           !tractionEch1.getText().isEmpty() &&
	           !numProduit.getText().isEmpty() &&	          	         
	           !serieProduit.getText().isEmpty() &&
	           listeMachinesTraction.getValue() != null ;
	}

	@FXML
	public void suivant(ActionEvent event) {
		
		 // 1. Vérification des champs obligatoires
	    if (hauteurSertissageEch1.getText().isEmpty() || hauteurSertissageEch2.getText().isEmpty() || hauteurSertissageEch3.getText().isEmpty()
	            || largeurSertissageEch1.getText().isEmpty() || hauteurIsolantEch1.getText().isEmpty() || largeurIsolantEch1.getText().isEmpty()
	            || tractionEch1.getText().isEmpty() || numProduit.getText().isEmpty()        
	            || serieProduit.getText().isEmpty() 
	            || listeMachinesTraction.getValue() == null  ) {

	        showErrorDialog("Veuillez remplir tous les champs avant de continuer !", "Champs obligatoires");
	        return; // Arrêt si un champ est vide
	    }
	    // 3. Si tous les champs sont remplis, afficher l'alerte de confirmation
        if (checkOtherFields() && !hauteurIsolantFinCde.getText().isEmpty()&& !largeurSertissageFinCde.getText().isEmpty() 
        		&& !hauteurIsolantFinCde.getText().isEmpty()  && !quantiteCycle.getText().isEmpty()) {
            // Préparer le message de confirmation avec les données saisies
            String message = "Veuillez confirmer les données saisies ? \n\n";

            // Appeler la méthode showConfirmationDialog
            showConfirmationDialog(message, "Confirmation", () -> {
            	
            	SertissageNormaleInformations.numCycle = nbrCycle.getText(); 
            	SertissageNormaleInformations.hauteurSertissageEch1 = hauteurSertissageEch1.getText(); 
            	SertissageNormaleInformations.hauteurSertissageEch2 = hauteurSertissageEch2.getText(); 
            	SertissageNormaleInformations.hauteurSertissageEch3 = hauteurSertissageEch3.getText(); 
            	SertissageNormaleInformations.hauteurSertissageFinal = hauteurSertissageFinCommande.getText(); 
            	SertissageNormaleInformations.largeurSertissage = largeurSertissageEch1.getText(); 
            	SertissageNormaleInformations.largeurSertissageFinal = largeurSertissageFinCde.getText(); 
            	SertissageNormaleInformations.hauteurIsolant = hauteurIsolantEch1.getText();
            	SertissageNormaleInformations.hauteurIsolantFinal = hauteurIsolantFinCde.getText();
            	SertissageNormaleInformations.largeurIsolant = largeurIsolantEch1.getText();
            	SertissageNormaleInformations.traction = tractionEch1.getText();
            	SertissageNormaleInformations.produit = numProduit.getText();
            	SertissageNormaleInformations.quantiteCycle = quantiteCycle.getText();
            	SertissageNormaleInformations.machineTraction = listeMachinesTraction.getValue();
            	SertissageNormaleInformations.serieProduit = serieProduit.getText() ; 


                // Si l'utilisateur confirme, exécuter la méthode ajoutPDEK()
            	ajouterPdekAvecSoudure();

                // Affichage direct de la fenêtre SoudureResultat
                try {
                	  // Chargement de la nouvelle fenêtre
            	    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Front_java/SertissageNormal/SertissageNormalResultat.fxml"));
            	    Scene resultScene = new Scene(loader2.load());
            	    resultScene.getStylesheets().add(getClass().getResource("/Front_java/SertissageNormal/SertissageNormalResultat.css").toExternalForm());                   	    
            	    Stage resultStage = new Stage();
            	    resultStage.setScene(resultScene);
            	    resultStage.setMaximized(true);
            	    resultStage.initStyle(StageStyle.UNDECORATED);                    	    
            	    // Ajout d'une icône
            	    Image icon = new Image("/logo_app.jpg");
            	    resultStage.getIcons().add(icon);                   	    
            	    // Affichage de la nouvelle fenêtre
            	    resultStage.show();
            	    // Fermeture de la fenêtre actuelle
            	    Stage currentStage = (Stage) btnSuivant.getScene().getWindow();
                    currentStage.close();
                } catch (IOException ex) {
                    System.out.println("Erreur lors du chargement de la fenêtre verification : " + ex.getMessage());
                    ex.printStackTrace();
                }
            });
        } else {
            // Si les champs ne sont pas remplis ou si "quantité atteinte" est vide, afficher la fenêtre de chargement
        	SertissageNormaleInformations.numCycle = nbrCycle.getText(); 
        	SertissageNormaleInformations.hauteurSertissageEch1 = hauteurSertissageEch1.getText(); 
        	SertissageNormaleInformations.hauteurSertissageEch2 = hauteurSertissageEch2.getText(); 
        	SertissageNormaleInformations.hauteurSertissageEch3 = hauteurSertissageEch3.getText(); 
        	SertissageNormaleInformations.hauteurSertissageFinal = hauteurSertissageFinCommande.getText(); 
        	SertissageNormaleInformations.largeurSertissage = largeurSertissageEch1.getText(); 
        	SertissageNormaleInformations.largeurSertissageFinal = largeurSertissageFinCde.getText(); 
        	SertissageNormaleInformations.hauteurIsolant = hauteurIsolantEch1.getText();
        	SertissageNormaleInformations.hauteurIsolantFinal = hauteurIsolantFinCde.getText();
        	SertissageNormaleInformations.largeurIsolant = largeurIsolantEch1.getText();
        	SertissageNormaleInformations.traction = tractionEch1.getText();
        	SertissageNormaleInformations.produit = numProduit.getText();
        	SertissageNormaleInformations.quantiteCycle = quantiteCycle.getText();
        	SertissageNormaleInformations.machineTraction = listeMachinesTraction.getValue();
        	SertissageNormaleInformations.serieProduit = serieProduit.getText() ; 
        	

        	
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/SertissageNormal/loading/LoadingSertissageNormal.fxml"));
                Scene loadingScene = new Scene(loader.load());
                String cssPath = "/Front_java/SertissageNormal/loading/LoadingSertissageNormal.css";
                if (getClass().getResource(cssPath) != null) {
                    loadingScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
                } else {
                    System.out.println("❌ Fichier CSS introuvable : " + cssPath);
                }

                LoadingSertissageNormal loadingController = loader.getController();
                loadingController.setParentController(this);

                // Définir l'action à exécuter lorsque le bouton "Terminer" est cliqué
                loadingController.setOnTerminerAction(() -> {
                    // Rendre le champ "quantité atteinte" activé
                	hauteurSertissageFinCommande.setDisable(false); 
                	largeurSertissageFinCde.setDisable(false);
                	hauteurIsolantFinCde.setDisable(false);
                    quantiteCycle.setDisable(false);

                    // Si tous les champs sont remplis, passer à la fenêtre de résultats
                    if (checkOtherFields()) {
                    	try {
                    		SertissageNormaleInformations.numCycle = nbrCycle.getText(); 
                        	SertissageNormaleInformations.hauteurSertissageEch1 = hauteurSertissageEch1.getText(); 
                        	SertissageNormaleInformations.hauteurSertissageEch2 = hauteurSertissageEch2.getText(); 
                        	SertissageNormaleInformations.hauteurSertissageEch3 = hauteurSertissageEch3.getText(); 
                        	SertissageNormaleInformations.hauteurSertissageFinal = hauteurSertissageFinCommande.getText(); 
                        	SertissageNormaleInformations.largeurSertissage = largeurSertissageEch1.getText(); 
                        	SertissageNormaleInformations.largeurSertissageFinal = largeurSertissageFinCde.getText(); 
                        	SertissageNormaleInformations.hauteurIsolant = hauteurIsolantEch1.getText();
                        	SertissageNormaleInformations.hauteurIsolantFinal = hauteurIsolantFinCde.getText();
                        	SertissageNormaleInformations.largeurIsolant = largeurIsolantEch1.getText();
                        	SertissageNormaleInformations.traction = tractionEch1.getText();
                        	SertissageNormaleInformations.produit = numProduit.getText();
                        	SertissageNormaleInformations.quantiteCycle = quantiteCycle.getText();
                        	SertissageNormaleInformations.machineTraction = listeMachinesTraction.getValue();
                        	SertissageNormaleInformations.serieProduit = serieProduit.getText() ; 
                        	
                    	    // Chargement de la nouvelle fenêtre
                    	    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Front_java/SertissageNormal/loading/LoadingSertissageNormal.fxml"));
                    	    Scene resultScene = new Scene(loader2.load());
                    	    resultScene.getStylesheets().add(getClass().getResource("/Front_java/SertissageNormal/loading/LoadingSertissageNormal.css").toExternalForm());
                    	    
                    	    Stage resultStage = new Stage();
                    	    resultStage.setScene(resultScene);
                    	    resultStage.setMaximized(true);
                    	    resultStage.initStyle(StageStyle.UNDECORATED);
                    	    
                    	    // Ajout d'une icône
                    	    Image icon = new Image("/logo_app.jpg");
                    	    resultStage.getIcons().add(icon);
                    	    
                    	    // Affichage de la nouvelle fenêtre
                    	    resultStage.show();

                    	    // Fermeture de la fenêtre actuelle
                    	    Stage currentStage = (Stage) btnSuivant.getScene().getWindow();
                            currentStage.close();

                    	} catch (IOException ex) {
                    	    System.out.println("Erreur lors du chargement de la fenêtre SoudureResultat : " + ex.getMessage());
                    	    ex.printStackTrace();
                    	}

                    }
                });

                Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage loadingStage = new Stage();
                loadingStage.setScene(loadingScene);
                loadingStage.initStyle(StageStyle.UNDECORATED);
                loadingStage.initModality(Modality.APPLICATION_MODAL);
                loadingStage.initOwner(parentStage);
                loadingStage.show();

            } catch (IOException ex) {
                System.out.println("❌ Erreur lors du chargement de la fenêtre de chargement : " + ex.getMessage());
                ex.printStackTrace();
            }
        }
}
	

	    
	@FXML
	void precedant(ActionEvent event) {
		try {
			// Charger la scène de Dashboard1
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/SertissageNormal/SelectionSertissageNormal.fxml"));
			Scene dashboard1Scene = new Scene(loader.load());
			dashboard1Scene.getStylesheets()
					.add(getClass().getResource("/Front_java/SertissageNormal/SelectionSertissageNormal.css").toExternalForm());

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
			nbrEch.setText("5 Piéces");
			codeControleSelectionner.setText(SertissageNormaleInformations.codeControleSelectionner);
			labelHauteurSertissage.setText("/ ToL :"+getElementFromSection(SertissageNormaleInformations.sectionFil , "Hauteur_Sertissage")+" mm");
			labelLargeurSertissage.setText("/ ToL :"+getElementFromSection(SertissageNormaleInformations.sectionFil , "Largeur_Sertissage")+" mm");
			labelHauteurIsolant.setText("/ ToL :"+getElementFromSection(SertissageNormaleInformations.sectionFil , "Hauteur_Isolant") +" mm");
			labelLargeurIsolant.setText("/ ToL :"+getElementFromSection(SertissageNormaleInformations.sectionFil , "Largeur_Isolant"));
			labelTraction.setText(": >= "+getElementFromSection(SertissageNormaleInformations.sectionFil , "Traction")+" N");


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


	/**** recuperation numero de cycle de pdek ****/
	private int getNumeroCycleMaxFromApi() throws Exception {
	    String token = AppInformations.token;

	    // Encodage correct des paramètres pour éviter tout problème
	    String encodedSectionFil = URLEncoder.encode(AppInformations.sectionFilSelectionner, StandardCharsets.UTF_8);
	    String encodedNomProjet = URLEncoder.encode(AppInformations.projetSelectionner, StandardCharsets.UTF_8);
	    String encodedSegmentPDEK = URLEncoder.encode(String.valueOf(AppInformations.operateurInfo.getSegment()), StandardCharsets.UTF_8);
	    String encodedPlantPDEK = URLEncoder.encode(AppInformations.operateurInfo.getPlant(), StandardCharsets.UTF_8);

	    String url = "http://localhost:8281/operations/soudure/numCycleMax?sectionFil=" + encodedSectionFil 
	            + "&segment=" + encodedSegmentPDEK
	            + "&nomPlant=" + encodedPlantPDEK  // Correction ici
	            + "&nomProjet=" + encodedNomProjet;


	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(url))
	            .header("Authorization", "Bearer " + token)
	            .build();

	    HttpClient client = HttpClient.newHttpClient();
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    if (response.statusCode() == 200) {
	        String responseBody = response.body().trim();
	        System.out.println("Numéro de cycle reçu : " + responseBody);

	        try {
	            return Integer.parseInt(responseBody); // Conversion de la réponse en int
	        } catch (NumberFormatException e) {
	            throw new Exception("Réponse inattendue de l'API : " + responseBody);
	        }
	    } else {
	        throw new Exception("Erreur lors de la récupération du numéro de cycle : " + response.statusCode() + " - " + response.body());
	    }
	}


/*	private void loadNumeroCycleMax() {
		Task<Integer> task = new Task<>() {
			@Override
			protected Integer call() throws Exception {
				return getNumeroCycleMaxFromApi(); // Appelle la méthode corrigée avec encodage
			}
		};

		task.setOnSucceeded(event -> {
			int numeroCycleMax = task.getValue();
			valeurNumeroCycle.setText(String.valueOf(numeroCycleMax + 1));
			SoudureInformations.numeroCycle = numeroCycleMax + 1;
			System.out.println("Numéro de cycle max récupéré : " + numeroCycleMax);
		});

		task.setOnFailed(event -> {
			Throwable e = task.getException();
			valeurNumeroCycle.setText("Erreur");
			System.out.println("Erreur lors de la récupération du numéro de cycle : " + e.getMessage());
		});

		// Lance la tâche dans un thread séparé
		new Thread(task).start();
	}*/

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

	private void showConfirmationDialog(String title, String message, Runnable onConfirm) {
		// Créer l'icône de confirmation
		Image confirmIcon = new Image(getClass().getResource("/confirmation.png").toExternalForm());
		ImageView confirmImageView = new ImageView(confirmIcon);
		confirmImageView.setFitWidth(200);
		confirmImageView.setFitHeight(200);

		// Créer le conteneur pour l'icône
		VBox iconBox = new VBox(confirmImageView);
		iconBox.setAlignment(Pos.CENTER);

		// Créer le message
		Label messageLabel = new Label(message);
		messageLabel.setWrapText(true);
		messageLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-alignment: center;-fx-text-fill: black;");

		// Créer le titre
		Label titleLabel = new Label(title);
		titleLabel.setStyle("-fx-font-size: 19px; -fx-text-alignment: center;");
		VBox titleBox = new VBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);

		// Créer le conteneur principal pour le corps du dialog
		VBox contentBox = new VBox(10, iconBox, messageLabel, titleBox);
		contentBox.setAlignment(Pos.CENTER);

		// Créer la mise en page du JFXDialog
		JFXDialogLayout content = new JFXDialogLayout();
		content.setBody(contentBox);
		content.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

		// Créer les boutons de confirmation
		JFXButton confirmButton = new JFXButton("Confirmer");
		confirmButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px; -fx-font-weight: bold;");
		JFXButton cancelButton = new JFXButton("Annuler");
		cancelButton.setStyle("-fx-font-size: 19px; -fx-padding: 10px 20px;");

		// Ajouter les boutons à l'action du JFXDialog
		content.setActions(confirmButton, cancelButton);

		// Utilisation de StackPane pour afficher le dialog
		JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

		// Gérer l'action du bouton "Confirmer"
		confirmButton.setOnAction(e -> {
			onConfirm.run(); // Exécute l'action passée en paramètre
			dialog.close();
		});

		// Gérer l'action du bouton "Annuler"
		cancelButton.setOnAction(e -> dialog.close());

		// Afficher le dialog
		dialog.show();

		// Rendre l'overlay transparent (comme dans la méthode pour l'erreur)
		Platform.runLater(() -> {
			StackPane overlayPane = (StackPane) dialog.lookup(".jfx-dialog-overlay-pane");
			if (overlayPane != null) {
				overlayPane.setStyle("-fx-background-color: transparent;");
			}
		});
	}

	
	private void showWarningDialog(String title, String message) {
		Image warningIcon = new Image(getClass().getResource("/warning.png").toExternalForm());
		ImageView warningImageView = new ImageView(warningIcon);
		warningImageView.setFitWidth(100);
		warningImageView.setFitHeight(100);

		VBox iconBox = new VBox(warningImageView);
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
	/********************************************* Ajout PDEK  ***************************************************************/
	private void ajouterPdekAvecSoudure() {
	
	}
	
	/****************** Extraire valeur depuis section fil ****************/
	public double extraireValeurNumerique(String sectionFil) {
	    return Double.parseDouble(sectionFil.trim().split(" ")[0] );
	}
	public int extraireValeurNumeriqueInteger(String sectionFil) {
	    return Integer.parseInt(sectionFil.trim().split(" ")[0] );
	}

	/****************************************/
	public void actualiserFenetreMere() {
	   if (SertissageNormaleInformations.testTerminitionCommande == 1) {
		    hauteurSertissageFinCommande.setDisable(false); 
		    hauteurSertissageFinCommande.getStyleClass().add("textfield-blue-border");
	       	largeurSertissageFinCde.setDisable(false);
	       	largeurSertissageFinCde.getStyleClass().add("textfield-blue-border");
         	hauteurIsolantFinCde.setDisable(false);
         	hauteurIsolantFinCde.getStyleClass().add("textfield-blue-border");
            quantiteCycle.setDisable(false);
            quantiteCycle.getStyleClass().add("textfield-blue-border");
	       
	    }
	}

    public void afficherNotification(String message) {
        System.out.println("📢 Affichage d'une notification : " + message);

        if (stackPane == null) {
            System.out.println("❌ Erreur : stackPane est null.");
            return;
        }

        // Chargement de l'icône avec vérification
        ImageView infoImageView = new ImageView();
        URL iconURL = getClass().getResource("/icone_info.png");
        if (iconURL != null) {
            infoImageView.setImage(new Image(iconURL.toExternalForm()));
        } else {
            System.out.println("❌ Image non trouvée : /icone_info.png");
        }

        infoImageView.setFitWidth(80);
        infoImageView.setFitHeight(80);
        VBox iconBox = new VBox(infoImageView);
        iconBox.setAlignment(Pos.CENTER);

        // Titre et message
        Label titleLabel = new Label("Notification");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-alignment: center;");

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setStyle("-fx-font-size: 18px; -fx-text-alignment: center; -fx-text-fill: black;-fx-font-weight: bold; ");

        VBox contentBox = new VBox(10, iconBox, titleLabel, messageLabel);
        contentBox.setAlignment(Pos.CENTER);

        // Création du layout de la boîte de dialogue
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(contentBox);
        content.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 20px;");

        // Bouton de fermeture stylisé
        JFXButton closeButton = new JFXButton("Fermer");
        closeButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px; -fx-font-weight: bold; -fx-background-color: #007BFF; -fx-text-fill: white;");

        // Conteneur pour centrer le bouton
        HBox buttonBox = new HBox(closeButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0)); // Optionnel : Ajoute un peu d'espace en haut

        // Ajout du bouton centré dans la boîte de dialogue
        content.setActions(buttonBox);

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        closeButton.setOnAction(e -> {
            dialog.close();
            System.out.println("Notification fermée par l'utilisateur.");
        });

        dialog.show();

        // Fermeture automatique après 5 secondes
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            if (dialog.isVisible()) {
                dialog.close();
                System.out.println(" Notification fermée automatiquement après 5 secondes.");
            }
        });
        pause.play();

        // Supprimer l'overlay gris foncé
        Platform.runLater(() -> {
            Node overlayPane = dialog.lookup(".jfx-dialog-overlay-pane");
            if (overlayPane != null) {
                overlayPane.setStyle("-fx-background-color: transparent;");
            }
        });
    }
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