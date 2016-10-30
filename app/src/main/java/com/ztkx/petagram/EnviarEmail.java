package com.ztkx.petagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by ztkx on 10/30/2015.
 */

//La clase extiende AsyncTask por que hace operaciones de interet
public class EnviarEmail extends AsyncTask<Void,Void,Void> {

    //Declaracion de Variables
    private Context context;
    private Session session;
    private String email;
    private String asunto;
    private String mensaje;

    //Dialogo de Progreso de envío de Email
    private ProgressDialog dialogoProgresoEnvio;

    public EnviarEmail(Context context, String email, String asunto, String mensaje){
        this.context = context;
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        dialogoProgresoEnvio = ProgressDialog.show(context,"Enviando mensaje","Espere por favor...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialogoProgresoEnvio.dismiss();
        Toast.makeText(context,"Mensaje enviado",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        //Propiedades del correo
        Properties props = new Properties();

        //Configuramos variables para el envío desde gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creamos una nueva sesion
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Establecemos el correo y contraseña desde el que realizaremos el envío guardados en la clase DatosCuentaCorreo
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(DatosCuentaCorreo.EMAIL_ORIGEN, DatosCuentaCorreo.PASSWORD);
                    }
                });

        try {
            //Creamos Un objeto de tipo MimeMessage
            MimeMessage mm = new MimeMessage(session);

            mm.setFrom(new InternetAddress(DatosCuentaCorreo.EMAIL_ORIGEN));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(DatosCuentaCorreo.EMAIL_DESTINO));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("ztkx@hotmail.com"));
            mm.setSubject(asunto);
            mm.setText(mensaje);

            //Enviamos el correo
            Transport.send(mm);

            //Cachamos la excepcion y la mostramos en un Toast
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(context,"Error:\n"+e.toString(),Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
