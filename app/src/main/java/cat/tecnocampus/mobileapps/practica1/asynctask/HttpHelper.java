package cat.tecnocampus.mobileapps.practica1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper extends AsyncTask<String, String, String> {
    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String json = "";
        HttpURLConnection connection = null;

        try {
            URL uri = new URL(url);
            connection = (HttpURLConnection) uri.openConnection();
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                json = readIt(is);
            }
        } catch (Exception ex) {
            Log.d("SwA", "ERROR!");
            ex.printStackTrace();
        } finally {
            if (connection != null) {//Si la connection s'ha arribat a obrir, la tanquem per acabar
                connection.disconnect();
            }
        }

        return json;
    }

    private String readIt(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder str = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            str.append(line);
        }

        is.close(); /*Molt important, si deixem l'inputStream obert
         no ens deixarà tancar la connexió*/

        return str.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        if (delegate != null) {
            delegate.setAsyncContentResult(s);
        }
    }

    public interface AsyncResponse {
        void setAsyncContentResult(String result);
    }
}
