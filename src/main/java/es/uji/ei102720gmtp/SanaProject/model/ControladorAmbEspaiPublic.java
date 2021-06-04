package es.uji.ei102720gmtp.SanaProject.model;

public class ControladorAmbEspaiPublic extends Controlador{

    private Controlador controlador;
    private String nomMunicipi;
    private String nomEspaiPublic;

    public ControladorAmbEspaiPublic(Controlador controlador, String nomMunicipi, String nomEspaiPublic){
        this.controlador = controlador;
        this.nomMunicipi = nomMunicipi;
        this.nomEspaiPublic = nomEspaiPublic;


    }

    public String getNomMunicipi() {
        return nomMunicipi;
    }

    public String getNomEspaiPublic() {
        return nomEspaiPublic;
    }

    public void setNomMunicipi(String nomMunicipi) {
        this.nomMunicipi = nomMunicipi;
    }

    public void setNomEspaiPublic(String nomEspaiPublic) {
        this.nomEspaiPublic = nomEspaiPublic;
    }
}
