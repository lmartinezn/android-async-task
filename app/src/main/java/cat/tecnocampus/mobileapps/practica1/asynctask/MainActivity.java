package cat.tecnocampus.mobileapps.practica1.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpHelper httpHelper = new HttpHelper();
        httpHelper.delegate = this;
        String url = "https://jsonplaceholder.typicode.com/users/";
        httpHelper.execute(url);
    }
}
