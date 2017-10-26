package sud.barros.com.aplicativocadastro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements Runnable{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h = new Handler();
        h.postDelayed(this,300);
    }

    @Override
    public void run() {
        Intent telaLogin = new Intent(this,LoginActivity.class);
        startActivity(telaLogin);

    }
}
