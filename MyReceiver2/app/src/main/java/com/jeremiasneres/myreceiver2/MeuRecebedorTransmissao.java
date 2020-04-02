package com.jeremiasneres.myreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MeuRecebedorTransmissao extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")){
            Bundle extras = intent.getExtras();
            boolean state = extras.getBoolean("state");
            if (state) {
                Toast.makeText(context,"Modo avião ativado", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context,"Modo avião desativado", Toast.LENGTH_LONG).show();
            }
        }
    }
}
