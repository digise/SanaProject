package es.uji.ei102720gmtp.SanaProject.model;

public class Comentari {

    private int idEspaiPublic;
    private String nifCiutada;
    private int contadorComentaris;
    private String comentari;

    public int getIdEspaiPublic() {
        return idEspaiPublic;
    }

    public void setIdEspaiPublic(int idEspaiPublic) {
        this.idEspaiPublic = idEspaiPublic;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    public int getContadorComentaris() {
        return contadorComentaris;
    }

    public void setContadorComentaris(int contadorComentaris) {
        this.contadorComentaris = contadorComentaris;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    @Override
    public String toString() {
        return "Comentari{" +
                "idEspaiPublic='" + idEspaiPublic + '\'' +
                ", nifCiutada='" + nifCiutada + '\'' +
                ", contadorComentaris=" + contadorComentaris +
                ", comentari='" + comentari + '\'' +
                '}';
    }
}
