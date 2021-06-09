package es.uji.ei102720gmtp.SanaProject.model;

public class ControladorAmbEspaiPublic extends Controlador{

    private Controlador controlador;
    private String nomEspaiPublic;

    public ControladorAmbEspaiPublic(Controlador controlador, String nomEspaiPublic){
        this.controlador = controlador;
        this.nomEspaiPublic = nomEspaiPublic;


    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getNomEspaiPublic() {
        return nomEspaiPublic;
    }

    public void setNomEspaiPublic(String nomEspaiPublic) {
        this.nomEspaiPublic = nomEspaiPublic;
    }

    @Override
    public String toString() {
        return "ControladorAmbEspaiPublic{" +
                "controlador=" + controlador +
                ", nomEspaiPublic='" + nomEspaiPublic + '\'' +
                '}';
    }
}
