package com.ztkx.petagram;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class Contacto extends AppCompatActivity {

    private TextInputEditText tiNombre;
    private TextInputEditText tiEmail;
    private TextInputEditText tiMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.contacto);

        tiNombre    = (TextInputEditText) findViewById(R.id.tiNombre);
        tiEmail     = (TextInputEditText) findViewById(R.id.tiEmail);
        tiMensaje   = (TextInputEditText) findViewById(R.id.tiMensaje);

    }

    public void enviarMail(View v){
        sendEmail();

    }

    private void sendEmail() {
        //Getting content for email
        String nombre   = tiNombre.getText().toString().trim();
        String email    = tiEmail.getText().toString().trim();
        String asunto  = ("Solicitud de contacto de "+ nombre+" ("+email+")").trim();
        String mensaje  = tiMensaje.getText().toString().trim();

        EnviarEmail sm = new EnviarEmail(this, email, asunto, mensaje);
        sm.execute();
    }
}
