package Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

import Model.Usuario;


public class UserService {

    private FirebaseAuth auth;
    private List<Usuario> list = new ArrayList<Usuario>();

    public boolean createUser(Usuario usuario){

        this.auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        this.auth.createUserWithEmailAndPassword(usuario.getLogin(),usuario.getSenha());

        if(this.auth.getCurrentUser() != null){
            usuario.setId(this.auth.getCurrentUser().getUid());
            db.collection("Usuarios").document(usuario.getId()).set(usuario);
            return  true;
        }else{
            return false;
        }
    }

    public void logOut(){
        auth = FirebaseAuth.getInstance();
        auth.signOut();
    }

    public boolean singInUser(Usuario usuario){
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(usuario.getLogin(),usuario.getSenha());
        if(auth.getCurrentUser() != null){
            return  true;
        }else{
            return false;
        }
    }

    public boolean verifyAuthentication(){
        if(FirebaseAuth.getInstance().getUid() != null ){
            return true;
        }else{
            return false;
        }
    }

    public void getUsers(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        try {
            db.collection("Usuarios")
                    .whereEqualTo("funcionario", false)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String id = document.getId();
                                    Log.d("teste", document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.d("teste", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }catch (Exception e){
            Log.d("teste",  e.getMessage());
        }
    }

}
