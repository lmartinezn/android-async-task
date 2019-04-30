package cat.tecnocampus.mobileapps.practica1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper extends AsyncTask<String, String, String> {
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
            if (connection != null) {
                connection.disconnect();
            }
        }

        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
