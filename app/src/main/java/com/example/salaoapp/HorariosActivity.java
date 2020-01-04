package com.example.salaoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Model.Agenda;


public class HorariosActivity extends AppCompatActivity {

    private ListView lvHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        lvHorarios = findViewById(R.id.lvHorarios);
        getHours("27-11-2019");

    }

    public void getHours(String data) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        try {
            db.collection("Agenda")
                    .document(data)
                    .collection("agendado")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<Agenda> list = new ArrayList();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String id = document.getId();
                                    Agenda agenda = new Agenda();
                                    agenda = document.toObject(Agenda.class);
                                    list.add(agenda);
                                    Log.d("teste", document.getId() + " => " + document.getData());
                                }
                                preencheAdapter(list);
                            } else {
                                Log.d("teste", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } catch (Exception e) {
            Log.d("teste", e.getMessage());
        }
    }

    public void preencheAdapter(List<Agenda> list) {
        List<String> listHoras = new ArrayList<>();
        for (Agenda agenda : list) {
            listHoras.add("Das " + agenda.getHora() + " ás " + (agenda.getHora() + 1));
        }
        ArrayAdapter<String> adapterAgenda = new ArrayAdapter<String>(this, R.layout.listview_horas, R.id.list_header, listHoras);
        lvHorarios.setAdapter(adapterAgenda);
    }
}
