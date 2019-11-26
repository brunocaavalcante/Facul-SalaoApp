package Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import Model.Usuario;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserService {

    private FirebaseAuth auth;

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

}
