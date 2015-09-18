package com.ggtf.guesswhat.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ggtf on 2015/9/18.
 * Author:GGTF
 * Email:15170069952@163.com
 * Time:2015/9/18
 * ProjectName:GuessWhat
 */
public class GuessLoader extends AsyncTaskLoader<String> {
    public static final String PATH="http://apis.baidu.com/myml/c1c/c1c?";
    public static final String ID="id=";
    private int id;
    private static final String API_KEY="8f56fd250f487c469c6d838e3f0a2a18";

    public GuessLoader(Context context,int id) {
        super(context);
        this.id = id;
    }

    @Override
    public String loadInBackground() {
        String json = null;
        String httpUrl = PATH;
        if (id!=-1){
            httpUrl = httpUrl+ID+id;
        }else {
            httpUrl = httpUrl+ID+(-1);
        }

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("apikey", API_KEY);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);

            if (connection.getResponseCode() == 200){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                InputStream inputStream = connection.getInputStream();
                int len=0;
                byte[] arr = new byte[128];
                while ((len = inputStream.read(arr))!= -1){
                    outputStream.write(arr,0,len);
                }
                arr = null;
                inputStream.close();
                json = new String(outputStream.toByteArray(),"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
