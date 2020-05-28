package com.jeremiasneres.contentproviderapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView lv;
    //Lista em que cada item será um objeto chave valor
    ArrayList<HashMap<String,String>> listaContatoss;
    //Lista em que cada item será um objeto chave valor
    ArrayList<Contato> listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = new ArrayList<>();
        listaContatos  = obterDados();


        //System.out.println(listaContatos.get(0).getNome());
       /*  listaContatos = obterDados();
        lv = findViewById(R.id.list);


       ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, ,
                R.layout.item_list,
                new String[]{"nome","numero"},
                new int[]{R.id.nome,R.id.contato}
        );
        lv.setAdapter(adapter);*/
    }

    public ArrayList<Contato> obterDados() {
        String projection[] = null;
        String select = null;
        String selectionArgs[] = null;
        String sortOrder = null;

        Uri uri = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            uri = ContactsContract.CommonDataKinds.Contactables.CONTENT_URI;
        } else {
            uri = ContactsContract.Data.CONTENT_URI;
        }

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, select, selectionArgs, sortOrder);
        ArrayList<Contato> dados = new ArrayList<>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID));
            String nome = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
            String data = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Contactables.DATA));
            dados.add(new Contato((int) id, nome,data));
        }
        return dados;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        //Contato item = dadosAdapter.getItem(position);
        //Toast.makeText(MainActivity.this,item.toString(), Toast.LENGTH_SHORT).show();
    }

}