package sud.barros.com.appcadastro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity  implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Vou querer que va para a
        //Ativity MainActivity
        //depois de alguns milisegundos.
        //para isso vou utilizar uma classe do Android
        //para gerar o atraso e a Intent para ir para
        //próxima tela
        setContentView(R.layout.activity_splash);
        Handler h = new Handler();
        h.postDelayed(this,3500);
    }

    @Override
    public void run() {
        startMainActivity();
        finish();
    }

    public void startMainActivity()
    {
        Intent telaLogin = new Intent(this,LoginActivity.class);
        startActivity(telaLogin);
    }
}
