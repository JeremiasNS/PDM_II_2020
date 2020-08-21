package game.curso.aula130820avaliacaofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;

import uk.co.deanwild.flowtextview.FlowTextView;

public class RecomendacaoGripeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacao_gripe);

        FlowTextView ftvGripe = (FlowTextView) findViewById(R.id.gripeTxt3parag2);
        Spanned html = Html.fromHtml(getString(R.string.rec_gripe_txt3parag2));

        ftvGripe.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.opensansregular));
        ftvGripe.setTextSize(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics()));

        ftvGripe.setText(html);
    }
}