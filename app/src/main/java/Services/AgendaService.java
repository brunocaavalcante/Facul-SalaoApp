package Services;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Agenda;

public class AgendaService {

    private static FirebaseFirestore afs = FirebaseFirestore.getInstance();

    public static List<Agenda> listaHorarios(Date data){
        List<Agenda> lista = new ArrayList<Agenda>();
        afs.collection("agenda").document(data.toString()).get();

        return lista;
    }
}
