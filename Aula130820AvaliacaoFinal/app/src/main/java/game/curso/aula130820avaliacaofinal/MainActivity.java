package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoInfo);
        button = findViewById(R.id.btnPular);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"
                +R.raw.coronavirusprevencao));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();

    }

    public void click(View view) {
        if (view.getId() == button.getId()) {
            videoView.stopPlayback();
            Intent i = new Intent(getApplicationContext(),FormNomeActivity.class);
            startActivity(i);
            finish();
        }
    }
}
