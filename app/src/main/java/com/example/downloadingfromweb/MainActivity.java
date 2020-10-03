package com.example.downloadingfromweb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button buttonText;
    ImageView imageView;

    public void downloadImage(View view) {
        Bitmap bitmapimage = null;
        String url = "https://www.clipartmax.com/png/middle/6-64496_popeye-the-sailor-man-popeye-the-sailor-man.png";

        DownloadTasks task = new DownloadTasks();
        try {

            bitmapimage = task.execute(url).get();

        } catch (Exception e) {

            e.printStackTrace();

        }
        imageView.setImageBitmap(bitmapimage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

//        DownloadTasks task = new DownloadTasks();
//        String result = null;
//        try {
//
//
//
//            result = task.execute("https://en.wikipedia.org/wiki/Website").get();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        TextView text = findViewById(R.id.text_view);
//        text.setText(result);
//
//        Log.i("Retuned Result ", result);
    }

    public class DownloadTasks extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            //StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection httpURLConnection = null;

            try {

                url = new URL(urls[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                //httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;


               /* This is used to read the string contents of a web page.


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();

                while (line != null) {

                    result.append(line);
                    //Log.i("Returned Text ", line);

                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

                return result.toString();
                */

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;
        }
    }
}