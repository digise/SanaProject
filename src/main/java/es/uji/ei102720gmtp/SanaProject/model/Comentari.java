package es.uji.ei102720gmtp.SanaProject.model;

public class Comentari {

    private String idEspaiPublic;
    private String nifCiutada;
    private long contadorComentaris;
    private String comentari;

    public String getIdEspaiPublic() {
        return idEspaiPublic;
    }

    public void setIdEspaiPublic(String idEspaiPublic) {
        this.idEspaiPublic = idEspaiPublic;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    public long getContadorComentaris() {
        return contadorComentaris;
    }

    public void setContadorComentaris(long contadorComentaris) {
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
