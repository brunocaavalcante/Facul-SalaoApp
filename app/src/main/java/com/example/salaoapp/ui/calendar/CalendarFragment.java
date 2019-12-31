package com.example.salaoapp.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.salaoapp.HorariosActivity;
import com.example.salaoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Model.Agenda;
import Model.Usuario;
import Services.UserService;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private CalendarView calendar;
    private Button btnCalendar;
    private TextView myDate;
    private Spinner spnCabeleleiro;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendar = (CalendarView) root.findViewById(R.id.calendarView);
        myDate = (TextView) root.findViewById(R.id.myDate);
        btnCalendar = root.findViewById(R.id.btnCalendar);
        spnCabeleleiro = root.findViewById(R.id.spCabeleleiro);

        getCabeleleiro();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String mes = ((i1 + 1) <= 9 ? "0" + (i1 + 1) : "" + (i1 + 1));
                String dia = (i2 <= 9 ? "0" + i2 : "" + i2);
                myDate.setText(dia + "/" + mes + "/" + i);
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth af = FirebaseAuth.getInstance();

                Agenda agenda = new Agenda();
                agenda.setData(myDate.getText().toString());
                agenda.setId_user(af.getUid());
                Intent it = new Intent(getContext(), HorariosActivity.class);
                it.putExtra("Agenda", myDate.getText().toString());
                startActivity(it);
            }
        });
        return root;
    }

    public void getCabeleleiro() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Usuarios")
                .whereEqualTo("funcionario", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<Usuario> list = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Usuario user = new Usuario();
                                user = document.toObject(Usuario.class);
                                list.add(user);
                                Log.d("teste", document.getId() + " => " + document.getData());
                            }
                            preencheSpinner(spnCabeleleiro,list);

                        } else {
                            Log.d("teste", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void preencheSpinner(Spinner sp,List<Usuario> list) {
        List<String> listNomes = new ArrayList<>() ;
        for (Usuario user : list){
            listNomes.add(user.getNome());
        }
        ArrayAdapter<String> adapterFuncionarios = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,listNomes);
        spnCabeleleiro.setAdapter(adapterFuncionarios);
    }
}