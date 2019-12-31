package Model;

public class Agenda {

    private int hora;
    private String data;
    private String id_user;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    private String id_cabeleleiro;

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getHora() {
        return this.hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
