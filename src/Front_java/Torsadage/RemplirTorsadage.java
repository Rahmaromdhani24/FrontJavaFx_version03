package Front_java.Torsadage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import Front_java.Configuration.AppInformations;
import Front_java.Modeles.OperateurInfo;
import Front_java.Modeles.TorsadageDTO;
import Front_java.Torsadage.loading.LoadingTorsadage;
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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import Front_java.Configuration.TorsadageInformations;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;




public class RemplirTorsadage {

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
	    private TextField decalageDebutC1;

	    @FXML
	    private TextField decalageDebutC2;

	    @FXML
	    private TextField decalageFinC1;

	    @FXML
	    private TextField decalageFinC2;

	    @FXML
	    private TextField ech1;

	    @FXML
	    private TextField ech2;

	    @FXML
	    private TextField ech3;

	    @FXML
	    private TextField ech4;

	    @FXML
	    private TextField ech5;

	    @FXML
	    private Label heureSystem;

	    @FXML
	    private TextField lognueurBoutDebutC1;

	    @FXML
	    private TextField lognueurBoutDebutC2;

	    @FXML
	    private TextField lognueurBoutFinC1;

	    @FXML
	    private TextField lognueurBoutFinC2;

	    @FXML
	    private TextField longueurFinalDebutCde;

	    @FXML
	    private TextField longueurFinalFinCde;

	    @FXML
	    private TextField longueurPasFinCde;

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
	    private TextField numCommande;

	    @FXML
	    private ComboBox<String> numFils;

	    @FXML
	    private Label operationUser;

	    @FXML
	    private Label plantUser;

	    @FXML
	    private Label posteUser;

	    @FXML
	    private TextField quantiteAtteint;

	    @FXML
	    private TextField quantiteTotal;

	    @FXML
	    private BorderPane rootPane;

	    @FXML
	    private Label segementUser;

	    @FXML
	    private Label specificationsMesure;

	    @FXML
	    private StackPane stackPane;
	    
	

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
		            System.out.println("Texte ajouté : " + buttonText);
		        }
		    }

		    // Méthode pour définir le TextField actif
		    public void setActiveTextField(TextField textField) {
		        this.activeTextField = textField;
		    }

	@FXML
	public void initialize() {
		quantiteAtteint.setDisable(true); 
		longueurFinalFinCde.setDisable(true);
		longueurPasFinCde.setDisable(true);
		decalageFinC1.setDisable(true);
		decalageFinC2.setDisable(true);

		loadNumFils();
		afficherInfosOperateur();
		AppInformations.testTerminitionCommande = 0 ; 
		
		afficherDateSystem();
		afficherHeureSystem();
		loadNumeroCycleMax();
		clearImage.setOnMouseClicked(event -> {
			if (activeTextField != null) {
				activeTextField.clear();
			}
		});

		setActiveOnFocus(numCommande);
		setActiveOnFocus(longueurFinalDebutCde);
		setActiveOnFocus(lognueurBoutDebutC1);
		setActiveOnFocus(lognueurBoutDebutC2);
		setActiveOnFocus(lognueurBoutFinC1);
		setActiveOnFocus(lognueurBoutFinC2);
		

		setActiveOnFocus(decalageDebutC1);
		setActiveOnFocus(decalageDebutC2);
		setActiveOnFocus(decalageFinC1);
		setActiveOnFocus(decalageFinC2);
		setActiveOnFocus(longueurFinalFinCde);
		setActiveOnFocus(longueurPasFinCde);
		setActiveOnFocus(ech1);
		setActiveOnFocus(ech2);
		setActiveOnFocus(ech3);
		setActiveOnFocus(ech4);
		setActiveOnFocus(ech5);
		setActiveOnFocus(quantiteTotal);
		setActiveOnFocus(quantiteAtteint);
	
	}

	private void loadNumFils() {
	    Task<ObservableList<String>> task = new Task<>() {
	        @Override
	        protected ObservableList<String> call() {
	            return FXCollections.observableArrayList(
	                "3Q/3R", "7B/7A", "5B/5A", "5E/5D", "2I/2K", "2AD/2AR"
	            );
	        }
	    };

	    task.setOnSucceeded(event -> {
	        numFils.setItems(task.getValue());
	        numFils.getSelectionModel().clearSelection(); // Désélectionner toute valeur par défaut
	        numFils.setValue(null); // S'assurer qu'aucune valeur n'est affichée au démarrage

	        numFils.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
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
	    return !numCommande.getText().isEmpty() &&
	           !longueurFinalDebutCde.getText().isEmpty() &&
	           !lognueurBoutDebutC1.getText().isEmpty() &&
	           !lognueurBoutDebutC2.getText().isEmpty() &&
	           !lognueurBoutFinC1.getText().isEmpty() &&
	           !lognueurBoutFinC2.getText().isEmpty() &&
	           !decalageDebutC1.getText().isEmpty() &&
	           !decalageDebutC2.getText().isEmpty() &&	          	         
	           !ech1.getText().isEmpty() &&
	           !ech2.getText().isEmpty() &&
	           !ech3.getText().isEmpty() &&
	           !ech4.getText().isEmpty() &&
	           !ech5.getText().isEmpty() &&
	           !quantiteTotal.getText().isEmpty() &&
	            numFils.getValue() != null ;
	}




	@FXML
	public void suivant(ActionEvent event) {
		 // 1. Vérification des champs obligatoires
	    if (numCommande.getText().isEmpty() || longueurFinalDebutCde.getText().isEmpty() || lognueurBoutDebutC1.getText().isEmpty()
	            || lognueurBoutDebutC2.getText().isEmpty() || lognueurBoutFinC1.getText().isEmpty() || lognueurBoutFinC2.getText().isEmpty()
	            || decalageDebutC1.getText().isEmpty() || decalageDebutC2.getText().isEmpty()        
	            || ech1.getText().isEmpty() || ech2.getText().isEmpty() || ech3.getText().isEmpty()|| ech4.getText().isEmpty()|| ech5.getText().isEmpty()
	            || quantiteTotal.getText().isEmpty()
	            || numFils.getValue() == null  ) {

	        showErrorDialog("Veuillez remplir tous les champs avant de continuer !", "Champs obligatoires");
	        return; // Arrêt si un champ est vide
	    }
	    // 3. Si tous les champs sont remplis, afficher l'alerte de confirmation
        if (checkOtherFields() && !decalageFinC1.getText().isEmpty()&& !decalageFinC2.getText().isEmpty() && !longueurFinalFinCde.getText().isEmpty() 
        		               && !longueurPasFinCde.getText().isEmpty() && !quantiteAtteint.getText().isEmpty()) {
            // Préparer le message de confirmation avec les données saisies
            String message = "Veuillez confirmer les données saisies ? \n\n";

            // Appeler la méthode showConfirmationDialog
            showConfirmationDialog(message, "Confirmation", () -> {
             	
            	    TorsadageInformations.longueurFinalFinCde = longueurFinalFinCde.getText();
            	    TorsadageInformations.longueurPasFinCde = longueurPasFinCde.getText();
            	    TorsadageInformations.decalageFinC1 = decalageFinC1.getText();
            	    TorsadageInformations.decalageFinC2 = decalageFinC2.getText();
            	    TorsadageInformations.quantiteAtteint = quantiteAtteint.getText();
            	    
            		TorsadageInformations.numCommande = numCommande.getText(); 
                	TorsadageInformations.longueurFinalDebutCde = longueurFinalDebutCde.getText(); 
                	TorsadageInformations.lognueurBoutDebutC1 = lognueurBoutDebutC1.getText(); 
                	TorsadageInformations.lognueurBoutDebutC2 = lognueurBoutDebutC2.getText(); 
                	TorsadageInformations.lognueurBoutFinC1 = lognueurBoutFinC1.getText(); 
                	TorsadageInformations.lognueurBoutFinC2 = lognueurBoutFinC2.getText(); 
                	TorsadageInformations.decalageDebutC1 = decalageDebutC1.getText();
                	TorsadageInformations.decalageDebutC2 = decalageDebutC2.getText(); 
                	TorsadageInformations.decalageFinC1 = decalageFinC1.getText(); 
                	TorsadageInformations.decalageFinC2 = decalageFinC2.getText(); 
                	TorsadageInformations.numFils =numFils.getValue() ; 
                	TorsadageInformations.longueurFinalFinCde = longueurFinalFinCde.getText() ; 
                	TorsadageInformations.longueurPasFinCde = longueurFinalFinCde.getText() ; 
                	TorsadageInformations.ech1 = ech1.getText() ; 
                	TorsadageInformations.ech2 = ech2.getText() ; 
                	TorsadageInformations.ech3 = ech3.getText() ; 
                	TorsadageInformations.ech4 = ech4.getText() ; 
                	TorsadageInformations.ech5 = ech5.getText() ; 
                	TorsadageInformations.quantiteTotal  = quantiteTotal.getText() ; 
                	TorsadageInformations.quantiteAtteint = quantiteAtteint.getText() ; 
                	TorsadageInformations.numCourant = Integer.parseInt( nbrCycle.getText() ) ; 

              ajouterPDEKTorsadage()  ; 


                // Affichage direct de la fenêtre SoudureResultat
                try {
                	  // Chargement de la nouvelle fenêtre
            	    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.fxml"));
            	    Scene resultScene = new Scene(loader2.load());
            	    resultScene.getStylesheets().add(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.css").toExternalForm());                   	    
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
        	TorsadageInformations.numCommande = numCommande.getText(); 
        	TorsadageInformations.longueurFinalDebutCde = longueurFinalDebutCde.getText(); 
        	TorsadageInformations.lognueurBoutDebutC1 = lognueurBoutDebutC1.getText(); 
        	TorsadageInformations.lognueurBoutDebutC2 = lognueurBoutDebutC2.getText(); 
        	TorsadageInformations.lognueurBoutFinC1 = lognueurBoutFinC1.getText(); 
        	TorsadageInformations.lognueurBoutFinC2 = lognueurBoutFinC2.getText(); 
        	TorsadageInformations.decalageDebutC1 = decalageDebutC1.getText();
        	TorsadageInformations.decalageDebutC2 = decalageDebutC2.getText(); 
        	TorsadageInformations.numFils =numFils.getValue() ; 
        	TorsadageInformations.ech1 = ech1.getText() ; 
        	TorsadageInformations.ech2 = ech2.getText() ; 
        	TorsadageInformations.ech3 = ech3.getText() ; 
        	TorsadageInformations.ech4 = ech4.getText() ; 
        	TorsadageInformations.ech5 = ech5.getText() ; 
        	TorsadageInformations.quantiteTotal  = quantiteTotal.getText() ; 
        	TorsadageInformations.numCourant = Integer.parseInt( nbrCycle.getText() ) ; 
        	
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/loading/LoadingTorsadage.fxml"));
                Scene loadingScene = new Scene(loader.load());
                String cssPath = "/Front_java/Torsadage/loading/LoadingTorsadage.css";
                if (getClass().getResource(cssPath) != null) {
                    loadingScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
                } else {
                    System.out.println("❌ Fichier CSS introuvable : " + cssPath);
                }

                LoadingTorsadage loadingController = loader.getController();
                loadingController.setParentController(this);

                // Définir l'action à exécuter lorsque le bouton "Terminer" est cliqué
                loadingController.setOnTerminerAction(() -> {
                    // Rendre le champ "quantité atteinte" activé
                	longueurFinalFinCde.setDisable(false); 
                	longueurPasFinCde.setDisable(false);
                	decalageFinC1.setDisable(false);
                	decalageFinC2.setDisable(false);            	
                	quantiteAtteint.setDisable(false);    

                    // Si tous les champs sont remplis, passer à la fenêtre de résultats
                    if (checkOtherFields()) {
                    	try {
                    	    // Vérification et conversion des valeurs
                    	
                    		TorsadageInformations.numCommande = numCommande.getText(); 
                        	TorsadageInformations.longueurFinalDebutCde = longueurFinalDebutCde.getText(); 
                        	TorsadageInformations.lognueurBoutDebutC1 = lognueurBoutDebutC1.getText(); 
                        	TorsadageInformations.lognueurBoutDebutC2 = lognueurBoutDebutC2.getText(); 
                        	TorsadageInformations.lognueurBoutFinC1 = lognueurBoutFinC1.getText(); 
                        	TorsadageInformations.lognueurBoutFinC2 = lognueurBoutFinC2.getText(); 
                        	TorsadageInformations.decalageDebutC1 = decalageDebutC1.getText();
                        	TorsadageInformations.decalageDebutC2 = decalageDebutC2.getText(); 
                        	TorsadageInformations.numFils =numFils.getValue() ; 
                        	TorsadageInformations.ech1 = ech1.getText() ; 
                        	TorsadageInformations.ech2 = ech2.getText() ; 
                        	TorsadageInformations.ech3 = ech3.getText() ; 
                        	TorsadageInformations.ech4 = ech4.getText() ; 
                        	TorsadageInformations.ech5 = ech5.getText() ; 
                        	TorsadageInformations.quantiteTotal  = quantiteTotal.getText() ; 
                        	TorsadageInformations.numCourant = Integer.parseInt( nbrCycle.getText() ) ; 
                        	
                    	    // Chargement de la nouvelle fenêtre
                    	    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.fxml"));
                    	    Scene resultScene = new Scene(loader2.load());
                    	    resultScene.getStylesheets().add(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.css").toExternalForm());
                    	    
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
	
/*
	@FXML
	public void suivant(ActionEvent event) {
	    // Vérification des champs obligatoires initiaux
	    if (numCommande.getText().isEmpty() || longueurFinalDebutCde.getText().isEmpty() || lognueurBoutDebutC1.getText().isEmpty()
	            || lognueurBoutDebutC2.getText().isEmpty() || lognueurBoutFinC1.getText().isEmpty() || lognueurBoutFinC2.getText().isEmpty()
	            || decalageDebutC1.getText().isEmpty() || decalageDebutC2.getText().isEmpty()
	            || ech1.getText().isEmpty() || ech2.getText().isEmpty() || ech3.getText().isEmpty() || ech4.getText().isEmpty() || ech5.getText().isEmpty()
	            || quantiteTotal.getText().isEmpty() || numFils.getValue() == null) {

	        showErrorDialog("Veuillez remplir tous les champs avant de continuer !", "Champs obligatoires");
	        return;
	    }

	    // Vérification des champs supplémentaires pour passer directement à la fenêtre de résultats
	    if (checkOtherFields() && !decalageFinC1.getText().isEmpty() && !decalageFinC2.getText().isEmpty()
	            && !longueurFinalFinCde.getText().isEmpty() && !longueurPasFinCde.getText().isEmpty() && !quantiteAtteint.getText().isEmpty()) {

	        showConfirmationDialog("Veuillez confirmer les données saisies ? \n\n", "Confirmation", () -> {
	            ajouterPDEKTorsadage() ; 
	            ouvrirFenetreResultat();
	        });

	    } else {
	     
	        // Ouvrir la fenêtre de chargement
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/loading/LoadingTorsadage.fxml"));
	            Scene loadingScene = new Scene(loader.load());
	            String cssPath = "/Front_java/Torsadage/loading/LoadingTorsadage.css";
	            if (getClass().getResource(cssPath) != null) {
	                loadingScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
	            }

	            LoadingTorsadage loadingController = loader.getController();
	            loadingController.setParentController(this);

	            // Action lors de la fermeture du LoadingTorsadage
	            loadingController.setOnTerminerAction(() -> {
	            	   // Activer les champs désactivés
	    	        longueurFinalFinCde.setDisable(false);
	    	        longueurPasFinCde.setDisable(false);
	    	        decalageFinC1.setDisable(false);
	    	        decalageFinC2.setDisable(false);
	    	        quantiteAtteint.setDisable(false);

	                // Vérifier si les champs activés sont bien remplis
	                if (longueurFinalFinCde.getText().isEmpty() || longueurPasFinCde.getText().isEmpty()
	                        || decalageFinC1.getText().isEmpty() || decalageFinC2.getText().isEmpty() || quantiteAtteint.getText().isEmpty()) {

	                    showErrorDialog("Veuillez remplir tous les champs activés avant de continuer !", "Champs manquants");
	                } else {
	                    ajouterPDEKTorsadage() ; 
	                    ouvrirFenetreResultat();
	                }
	            });

	            // Affichage de la fenêtre de chargement
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


	private void ouvrirFenetreResultat() {
	    try {
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.fxml"));
	        Scene resultScene = new Scene(loader2.load());
	        resultScene.getStylesheets().add(getClass().getResource("/Front_java/Torsadage/ResultatTorsadage.css").toExternalForm());

	        Stage resultStage = new Stage();
	        resultStage.setScene(resultScene);
	        resultStage.setMaximized(true);
	        resultStage.initStyle(StageStyle.UNDECORATED);

	        // Ajout de l'icône
	        Image icon = new Image("/logo_app.jpg");
	        resultStage.getIcons().add(icon);

	        resultStage.show();

	        // Fermer la fenêtre actuelle
	        Stage currentStage = (Stage) btnSuivant.getScene().getWindow();
	        currentStage.close();

	    } catch (IOException ex) {
	        System.out.println("❌ Erreur lors du chargement de la fenêtre ResultatTorsadage : " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
*/
	@FXML
	void precedant(ActionEvent event) {
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


	/**** recuperation numero de cycle de pdek ****/
	private int getNumeroCycleMaxFromApi() throws Exception {
	    String token = AppInformations.token;

	    // Encodage correct des paramètres pour éviter tout problème
	    String encodedSpecificationMesure = URLEncoder.encode(TorsadageInformations.specificationsMesure, StandardCharsets.UTF_8);
	    String encodedNomProjet = URLEncoder.encode(TorsadageInformations.projetSelectionner, StandardCharsets.UTF_8);
	    String encodedSegmentPDEK = URLEncoder.encode(String.valueOf(AppInformations.operateurInfo.getSegment()), StandardCharsets.UTF_8);
	    String encodedPlantPDEK = URLEncoder.encode(AppInformations.operateurInfo.getPlant(), StandardCharsets.UTF_8);

	    String url = "http://localhost:8281/operations/torsadage/numCycleMax?specificationMesure=" + encodedSpecificationMesure 
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


	private void loadNumeroCycleMax() {
		Task<Integer> task = new Task<>() {
			@Override
			protected Integer call() throws Exception {
				return getNumeroCycleMaxFromApi(); // Appelle la méthode corrigée avec encodage
			}
		};

		task.setOnSucceeded(event -> {
			int numeroCycleMax = task.getValue();
			if(numeroCycleMax == 25) {
				nbrCycle.setText(String.valueOf( 1));
			}
			else if(numeroCycleMax < 25) {
				nbrCycle.setText(String.valueOf(numeroCycleMax + 1));
			}
			TorsadageInformations.numCourant = numeroCycleMax + 1;
			System.out.println("Numéro de cycle max récupéré : " + numeroCycleMax);
		});

		task.setOnFailed(event -> {
			Throwable e = task.getException();
			nbrCycle.setText("Erreur");
			System.out.println("Erreur lors de la récupération du numéro de cycle : " + e.getMessage());
		});

		// Lance la tâche dans un thread séparé
		new Thread(task).start();
	}

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

	
	/****************** Extraire valeur depuis section fil ****************/
	public double extraireValeurNumerique(String sectionFil) {
	    return Double.parseDouble(sectionFil.trim().split(" ")[0] );
	}
	public int extraireValeurNumeriqueInteger(String sectionFil) {
	    return Integer.parseInt(sectionFil.trim().split(" ")[0] );
	}

	/****************************************/
	public void actualiserFenetreMere() {
	    if (TorsadageInformations.testTerminitionCommande == 1) {
	        longueurFinalFinCde.setDisable(false); 
	        longueurFinalFinCde.getStyleClass().add("textfield-blue-border");
	        longueurPasFinCde.setDisable(false);
	        longueurPasFinCde.getStyleClass().add("textfield-blue-border");
	        decalageFinC1.setDisable(false);
	        decalageFinC1.getStyleClass().add("textfield-blue-border");
	        decalageFinC2.setDisable(false);
	        decalageFinC2.getStyleClass().add("textfield-blue-border");
	        quantiteAtteint.setDisable(false);   
	        quantiteAtteint.getStyleClass().add("textfield-blue-border");
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

	/********************************************* Ajout PDEK  ***************************************************************/
    private void ajouterPdekAvecTorsadage(String torsadageJson) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    // Code pour l'ajout du PDEK
                    String token = AppInformations.token;
                    String encodedProjet = URLEncoder.encode(TorsadageInformations.projetSelectionner, StandardCharsets.UTF_8);

                    String url = "http://localhost:8281/operations/torsadage/ajouterPDEK" + "?matriculeOperateur="
                            + AppInformations.operateurInfo.getMatricule() + "&projet=" + encodedProjet;

                    // Vérification si le JSON est vide ou invalide, et gestion des erreurs si nécessaire
                    if (torsadageJson == null || torsadageJson.isEmpty()) {
                        throw new Exception("Le JSON du torsadage est vide ou invalide.");
                    }

                    // Envoi de la requête avec le JSON passé en paramètre
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                            .header("Authorization", "Bearer " + token)
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(torsadageJson))
                            .build();

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

  private void ajouterPDEKTorsadage() {


					// Récupération des valeurs saisies et création de l'objet SoudureDTO
					TorsadageDTO torsadage = new TorsadageDTO();
					int x1 = Integer.parseInt(ech1.getText());
					int x2 = Integer.parseInt(ech2.getText());
					int x3 = Integer.parseInt(ech3.getText());
					int x4 = Integer.parseInt(ech4.getText());
					int x5 = Integer.parseInt(ech5.getText());

					// Calcul des valeurs max et min
					int maxValue = Math.max(Math.max(Math.max(x1, x2), Math.max(x3, x4)), x5);
					int minValue = Math.min(Math.min(Math.min(x1, x2), Math.min(x3, x4)), x5);
					double moy = (x1 + x2 + x3 + x4 + x5) / 5.0;
					int R = maxValue - minValue;

					// Remplir l'objet SoudureDTO avec les valeurs
					torsadage.setCode(TorsadageInformations.codeControleSelectionner);
					torsadage.setNumeroCycle(TorsadageInformations.numCourant );
					torsadage.setSpecificationMesure(TorsadageInformations.specificationsMesure);
					torsadage.setDecalageMaxDebutCdec1(Integer.parseInt( decalageDebutC1.getText())); 					
					torsadage.setDecalageMaxDebutCdec2(Integer.parseInt( decalageDebutC2.getText())); 					
					torsadage.setDecalageMaxFinCdec1(Integer.parseInt(TorsadageInformations.decalageFinC1 )); 					
					torsadage.setDecalageMaxFinCdec2(Integer.parseInt(TorsadageInformations.decalageFinC2 )); 									
					torsadage.setEch1(x1);
					torsadage.setEch2(x2);
					torsadage.setEch3(x3);
					torsadage.setEch4(x4 );
					torsadage.setEch5(x5);		
					torsadage.setMoyenne(moy);
					TorsadageInformations.moyenne = moy;
					torsadage.setEtendu(R);
					TorsadageInformations.ettendu = R;
					LocalDate dateActuelle = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					torsadage.setDate(dateActuelle.format(formatter));
					torsadage.setQuantiteAtteint(Integer.parseInt(TorsadageInformations.quantiteAtteint));
					TorsadageInformations.quantiteAtteint = quantiteAtteint.getText();
					torsadage.setQuantiteTotale(Integer.parseInt(quantiteTotal.getText()));
					TorsadageInformations.quantiteTotal = quantiteTotal.getText();
					torsadage.setNumerofil(numFils.getValue());
					torsadage.setNumCommande(Integer.parseInt( numCommande.getText()));
					torsadage.setLongueurBoutDebutCdeC1(Integer.parseInt( lognueurBoutDebutC1.getText()));
					torsadage.setLongueurBoutDebutCdeC2(Integer.parseInt( lognueurBoutDebutC2.getText()));
					torsadage.setLongueurBoutFinCdeC1(Integer.parseInt( lognueurBoutFinC1.getText()));
					torsadage.setLongueurBoutFinCdeC2(Integer.parseInt( lognueurBoutFinC2.getText()));
					torsadage.setLongueurFinalDebutCde(Integer.parseInt(longueurFinalDebutCde.getText()));
					torsadage.setLongueurFinalFinCde(Integer.parseInt(TorsadageInformations.longueurFinalFinCde));
					torsadage.setLongueurPasFinCde(Integer.parseInt(TorsadageInformations.longueurPasFinCde ));

				
	}
}