package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;

import uk.co.deanwild.flowtextview.FlowTextView;

public class RecomendacaoTuberculoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacao_tuberculose);

        FlowTextView ftvTuberc = (FlowTextView) findViewById(R.id.ftv);
        Spanned html = Html.fromHtml(getString(R.string.rec_tuberculose_txt1));

        ftvTuberc.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.opensansregular));
        ftvTuberc.setTextSize(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics()));
        ftvTuberc.setText(html);

    }
}