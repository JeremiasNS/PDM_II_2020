package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListActivity extends AppCompatActivity {

    Spinner[] spinners = new Spinner[12];
    TextView[] textViews = new TextView[12];
    String[] opcoesSelecionadas = new String[12];
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    private final String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String[] perguntasText = getResources().getStringArray(R.array.perguntas);
        toolbar = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.floating_action_button);

        for (int i = 0; i < spinners.length; i++) {
            Integer spinnerId = getResources().getIdentifier("spin" + i,"id", getPackageName());
            spinners[i] = findViewById(spinnerId);
            popularSpinners(spinners[i]);
        }
        for (int i = 0; i < textViews.length; i++) {
            Integer textViewId = getResources().getIdentifier("txtList" + i,"id", getPackageName());
            textViews[i] = findViewById(textViewId);
            textViews[i].setText(perguntasText[i]);
        }

        Intent i = getIntent();
        toolbar.setTitle(i.getStringExtra("NOME"));
        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criarNotificacao();

                /*Intent i = new Intent(getApplicationContext(), RecomendacaoCovidActivity.class);
                startActivity(i);
                finish();*/
                for (int i = 0; i < opcoesSelecionadas.length; i++){
                    opcoesSelecionadas[i] = (String) spinners[i].getSelectedItem();
                }

                Log.e("Codigo ",String.valueOf(ondePalavras("Sim", opcoesSelecionadas)));

                Intent intent;
                if (ondePalavras("Sim", opcoesSelecionadas).equals("1011") ||
                        ondePalavras("Sim", opcoesSelecionadas).equals("01241011")){
                    intent = new Intent(getApplicationContext(), RecomendacaoTuberculoseActivity.class);
                    criarNotificacao("Tuberculose", intent);
                    mostrarNotificacao();
                }else if (ondePalavras("Sim", opcoesSelecionadas).equals("012") ||
                        ondePalavras("Sim", opcoesSelecionadas).equals("02")){
                    intent = new Intent(getApplicationContext(), RecomendacaoCovidActivity.class);
                    criarNotificacao("CORONAVÍRUS", intent);
                    mostrarNotificacao();
                }else if (ondePalavras("Sim", opcoesSelecionadas).equals("3456")){
                    intent = new Intent(getApplicationContext(), RecomendacaoResfriadoActivity.class);
                    criarNotificacao("RESFRIADO", intent);
                    mostrarNotificacao();
                }else if (ondePalavras("Sim", opcoesSelecionadas).equals("1248")){
                    intent = new Intent(getApplicationContext(), RecomendacaoGripeActivity.class);
                    criarNotificacao("GRIPE", intent);
                    mostrarNotificacao();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Infelizmente não foi possivel diagnosticar!\n",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void mostrarNotificacao() {
        Toast.makeText(getApplicationContext(), "Dados avaliados com sucesso!\n" +
                "verifique a notificação.", Toast.LENGTH_LONG).show();
    }

    public static String ondePalavras(String palavra, String[] vetor){
        String quant = "";
        for(int i=0;i<vetor.length;i++){
            if(vetor[i].equals(palavra)){
                quant=quant+String.valueOf(i);
            }
        }
        return quant;
    }


    public void popularSpinners(Spinner spinner) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.opcoesSpin, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void criarNotificacao(String mensagem, Intent i) {
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

          createNotificationChannel();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle("Possíveis sintomas do/da "+mensagem)
                            .setContentText("Cique e descubra as recomendações.")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("De acordo com os sintomas apresentados,\n" +
                                                " há de ser um(a) possível "+ mensagem))
                            .setPriority(Notification.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}