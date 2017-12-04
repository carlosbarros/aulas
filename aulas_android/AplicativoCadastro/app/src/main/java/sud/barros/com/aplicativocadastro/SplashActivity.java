package sud.barros.com.aplicativocadastro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity implements Runnable{



    @AfterViews
    void initBookmarkList() {


        Handler h = new Handler();
        h.postDelayed(this,300);

    }

    @Background
    void carregando(String searchString, String userId) {

    }

    @UiThread
    void updateLoadd() {

    }



    @Override
    public void run() {
        Intent telaLogin = new Intent(this,LoginActivity.class);
        startActivity(telaLogin);

    }
}
