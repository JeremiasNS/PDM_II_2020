package game.curso.apptextvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edTxtNome);
        button = findViewById(R.id.btnProximo);

    }

    public void click(View view) {
        if (view.getId() == R.id.btnProximo) {
            String nome = editText.getText().toString();
            Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
            intent.putExtra("nome", nome);
            startActivity(intent);
        }
    }
}
