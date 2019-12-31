package Services;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import Model.Agenda;

public class AgendaService {

    private static FirebaseFirestore afs = FirebaseFirestore.getInstance();

    public  List<Agenda> listaHorarios(Agenda agenda){
        List<Agenda> list = new ArrayList<>();
        FirebaseFirestore afs = FirebaseFirestore.getInstance();
        afs.collection("/Agenda").document(agenda.getData()).collection("agendado")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e != null){
                            Log.e("Teste",e.getMessage(),e);
                            return;
                        }
                       List<Agenda> list = new ArrayList<>();
                       List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot doc:docs) {
                            Agenda item = doc.toObject(Agenda.class);
                            Log.d("tes",""+item.getData());
                            list.add(item);
                        }
                    }
                });
        return list;
    }
}
