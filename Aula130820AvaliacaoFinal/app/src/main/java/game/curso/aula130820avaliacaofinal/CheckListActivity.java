package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CheckListActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private final String CHANNEL_ID = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        listView = findViewById(R.id.checkList);
        floatingActionButton = findViewById(R.id.floating_action_button);

        Intent i = getIntent();
        toolbar.setTitle(i.getStringExtra("NOME"));
        setSupportActionBar(toolbar);

        ArrayList perguntasList = getListData();

        listView.setAdapter(new CustomListAdapter(this, perguntasList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*createNotificationChannel();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle("Possíveis sintomas do COVID")
                            .setContentText("Cique e descubra as recomendações.")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("De acordo com os sintomas apresentados,\n" +
                                                " há de ser um possível resfriado"))
                            .setPriority(Notification.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, builder.build());

                Intent i = new Intent(getApplicationContext(), RecomendacaoCovidActivity.class);
                startActivity(i);
                finish();*/

                ListItem user = (ListItem) listView.getItemAtPosition(0);
                Log.e("Name Tag: ", user.getNome() );

            }
        });

    }

    private ArrayList getListData() {
        ArrayList<ListItem> results = new ArrayList<>();
        String[] perguntasText = getResources().getStringArray(R.array.perguntas);
        String[] perguntasIcone = getResources().getStringArray(R.array.iconePerguntas);
        ListItem[] perguntas = new ListItem[perguntasText.length];
        Spinner[] spinner = new Spinner[perguntasText.length];

        //Integer perguntaId = getResources().getIdentifier("pergunta" + j,"string", getPackageName());
        for (int j = 0; j < perguntasText.length; j++) {
            perguntas[j] = new ListItem(perguntasText[j],
                    getResources().getIdentifier(perguntasIcone[j],
                            "drawable", getPackageName()), spinner[j]);
            results.add(perguntas[j]);
        }
        return results;
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