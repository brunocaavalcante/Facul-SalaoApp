package Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Agenda;

public class AgendaService {

    private static FirebaseFirestore afs = FirebaseFirestore.getInstance();

    public  void listaHorarios(){
        FirebaseFirestore afs = FirebaseFirestore.getInstance();
        /*afs.collection("Agenda")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("", "Error getting documents.", task.getException());
                        }
                    }
                });*/
    }
}
