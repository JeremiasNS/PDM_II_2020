package game.curso.apptextvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Locale;

public class SegundaActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private VideoView videoView;
    private ImageButton imageButtonPlay;
    private ImageButton imageButtonPause;
    private ImageButton imageButtonStop;
    private Uri uri;
    private TextToSpeech textToSpeech;
    private String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        String nome = getIntent().getStringExtra("nome");
        msg = nome + "seu video está pronto!";
        textToSpeech = new TextToSpeech(getApplicationContext(), this);

        videoView = findViewById(R.id.videoView);
        imageButtonPlay = findViewById(R.id.btnPlay);
        imageButtonPause = findViewById(R.id.btnPause);
        imageButtonStop = findViewById(R.id.btnStop);

        //uri = Uri.parse("android.resource//"+getPackageName()+"/"+R.raw.bookpages);
        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tallkeyboard);
        videoView.setVideoURI(uri);
    }

    public void click(View view) {
        if (view.getId() == R.id.btnPlay) {
            int duracao = videoView.getDuration();
            Toast.makeText(getApplicationContext(), "Tempo total: " + duracao, Toast.LENGTH_LONG).show();
            videoView.start();
        }else if (view.getId() == R.id.btnPause) {
            videoView.pause();
            int posicao = videoView.getCurrentPosition();
            Toast.makeText(getApplicationContext(), "Tempo atual: " + posicao, Toast.LENGTH_LONG).show();
        }else if (view.getId() == R.id.btnStop) {
            videoView.stopPlayback();
            Toast.makeText(getApplicationContext(), "Vídeo não pode ser mais reproduzido", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("pt", "br");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(0.5f);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("Problemas", "problemas com o idioma");
            } else {
                textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } else {
            Log.e("Problemas", "problemas com o textToSpeech");
        }
    }
}
