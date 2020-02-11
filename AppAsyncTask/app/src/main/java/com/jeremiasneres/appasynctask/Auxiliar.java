package com.jeremiasneres.appasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Auxiliar {
    //recebe url
    public static Bitmap baixarImagem(String url){
        try {
            //pega url cria um objeto url
            URL endereco = new URL(url);
            //cria uma conexão(openStream()), e devolve em fluxo de bytes
            InputStream is = endereco.openStream();
            //pega o fluxo de bytes e constroi o bitmap
            Bitmap imagem = BitmapFactory.decodeStream(is);
            //fecha a conexão
            is.close();
            //retorna uma imagem
            return imagem;
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
