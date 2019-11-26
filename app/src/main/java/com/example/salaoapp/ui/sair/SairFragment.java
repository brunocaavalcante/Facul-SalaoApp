package com.example.salaoapp.ui.sair;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.salaoapp.LoginActivity;
import com.example.salaoapp.R;

import Services.UserService;

public class SairFragment extends Fragment {

    private SairViewModel sairViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sairViewModel =
                ViewModelProviders.of(this).get(SairViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sair, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sairViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        UserService userService = new UserService();
        userService.logOut();
        Intent it = new Intent(root.getContext(), LoginActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //Faz com que a activity que foi chamada seja a principal desta forma o user não podera retornar para tela de login
        startActivity(it);
        return root;
    }
}