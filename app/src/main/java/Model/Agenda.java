package Model;

import java.util.Date;
import java.util.List;

import Services.AgendaService;

public class Agenda {

    private int hora;
    private boolean disponivel;
    private Date data;

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getHora() {
        return this.hora;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

   /* public List<Agenda> getHorarios(Date data){
        return AgendaService.listaHorarios(data);
    }*/
}
