package cat.tecnocampus.mobileapps.practica1.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements HttpHelper.AsyncResponse {
    private TextView jsonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonView = findViewById(R.id.json_view);

        HttpHelper httpHelper = new HttpHelper();
        httpHelper.delegate = this;
        String url = "https://jsonplaceholder.typicode.com/users/";
        httpHelper.execute(url);
    }

    @Override
    public void setAsyncContentResult(String json) {
        //jsonView.setText(json);
        //jsonView.setText(getFirstUser(json));
        jsonView.setText(getAllNames(json));
    }

    private String getFirstUser(String json) {
        try {
            JSONArray users = new JSONArray(json);
            return users.get(0).toString();
        } catch (Exception ex) {
            Log.d("SwA", "JSON exception");
            ex.printStackTrace();
        }

        return "";
    }

    private String getAllNames(String json) {
        String result = "";

        try {
            JSONArray users = new JSONArray(json);

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                result = result + user.getString("name") + "\n"; /*Si no sabem si existeix
                un camp "name" o no, podem utilitzar el mètode has per evitar excepcions*/
            }
        } catch (Exception ex) {
            Log.d("SwA", "JSON exception");
            ex.printStackTrace();
        }

        return result;
    }
}
