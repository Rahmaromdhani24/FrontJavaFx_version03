package Front_java.Configuration;

import javafx.scene.control.TextField;

public class SertissageIDCData {
    private final String param;
    private final TextField echantillon1;
    private final TextField echantillon2;
    private final TextField echantillon3;
    private final TextField echantillonFin;

    public SertissageIDCData(String param) {
        this.param = param;
        this.echantillon1 = new TextField();
        this.echantillon2 = new TextField();
        this.echantillon3 = new TextField();
        this.echantillonFin = new TextField();

        this.echantillon1.setPromptText("Valeur 1");
        this.echantillon2.setPromptText("Valeur 2");
        this.echantillon3.setPromptText("Valeur 3");
        this.echantillonFin.setPromptText("Valeur Fin");
    }

    public String getParam() {
        return param;
    }

    public TextField getEchantillon1() {
        return echantillon1;
    }

    public TextField getEchantillon2() {
        return echantillon2;
    }

    public TextField getEchantillon3() {
        return echantillon3;
    }

    public TextField getEchantillonFin() {
        return echantillonFin;
    }
}
