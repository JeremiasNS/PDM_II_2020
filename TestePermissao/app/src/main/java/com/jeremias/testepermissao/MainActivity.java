package com.jeremias.testepermissao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final String PERMISSAO =
            Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
    }

    private void abrirTela() {
        Intent it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }

    private void solicitarPermissao() {
        final String[] permissao = {PERMISSAO};
        int tem_permissao = ContextCompat.checkSelfPermission(
                this, PERMISSAO);
        if (tem_permissao != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{PERMISSAO}, REQUEST_CODE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirTela();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSAO)) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Atenção")
                            .setMessage("A permissão é necessário para...")
                            .setCancelable(false)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(
                                            MainActivity.this,
                                            new String[]{PERMISSAO},
                                            REQUEST_CODE_PERMISSION);
                                }
                            }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,
                                    "Precisamos da permissão",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    finish();
                }
            }
        }
    }

    public void solicitar(View v){
        solicitarPermissao();
    }
}
