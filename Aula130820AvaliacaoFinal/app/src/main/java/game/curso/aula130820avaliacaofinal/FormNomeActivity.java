package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class FormNomeActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech textToSpeech;
    private Button button;
    private TextInputEditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nome);
        textToSpeech = new TextToSpeech(getApplicationContext(), this);
        button = findViewById(R.id.btnAvancar);
        editText = findViewById(R.id.nomeTxt);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("pt", "br");
            int result = textToSpeech.setLanguage(locale);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
                textToSpeech.speak("digite seu nome",TextToSpeech.QUEUE_FLUSH,null,null);
            }
        } else {
            Log.e("error", "Failed to Initialize");
        }
    }

    public void clickAvancar(View view) {

        if (view.getId() == button.getId()) {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.putExtra("NOME", editText.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}