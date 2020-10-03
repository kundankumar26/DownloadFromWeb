package com.example.downloadingfromweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    public class DownloadTasks extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection httpURLConnection = null;

            try {

                url = new URL(urls[0]);

                httpURLConnection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

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

            } catch (Exception e) {

                e.printStackTrace();

                return "Failed";

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTasks task = new DownloadTasks();
        String result = null;
        try {

            result = task.execute("https://en.wikipedia.org/wiki/Website").get();

        } catch (ExecutionException | InterruptedException e) {

            e.printStackTrace();

        }
        assert result != null;

        TextView text = findViewById(R.id.text_view);
        text.setText(result);

        //Log.i("Retuned Result ", result);
    }
}