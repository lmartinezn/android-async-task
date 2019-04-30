package cat.tecnocampus.mobileapps.practica1.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements HttpHelper.AsyncResponse{
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
        jsonView.setText(json);
    }
}
