package Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertService {

    private AlertDialog alerta;

    public void exibirAlerta(String titulo, String msg, final Context contexto) {

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        //define o titulo
        builder.setTitle(titulo);
        //define a mensagem
        builder.setMessage(msg);
        //define um botão como positivo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(contexto, "Ok=" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        builder.create();
        //Exibe
        builder.show();
    }
}
