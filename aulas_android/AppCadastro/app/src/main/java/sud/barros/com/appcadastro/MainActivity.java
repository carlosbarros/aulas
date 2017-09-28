package sud.barros.com.appcadastro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNome;
    private EditText etEmail;
    private Button btSalvar;
    private Button btChoose;
    private RadioButton rbMasc;
    private RadioButton rbFem;
    private CheckBox cbLer;
    private CheckBox cbViajar;
    private CheckBox cbDancar;
    private ImageView imgFoto;
    private Uri filePath;
    //Esta é a tela principal
    //Agora vou alterar para que a principal fique sendo
    //a SplashActivity

    private static final int PICK_IMAGE_REQUEST = 234;

    private Spinner spTimes;

    //Outra maneira de popular o Spinner
    private List<String> times = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etNome = (EditText)findViewById(R.id.etNome);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        btChoose = (Button) findViewById(R.id.btChoose);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        rbFem = (RadioButton) findViewById(R.id.rbFem);
        cbLer = (CheckBox) findViewById(R.id.cbler);
        cbViajar = (CheckBox) findViewById(R.id.cbViajar);
        cbDancar = (CheckBox) findViewById(R.id.cbDancar);
        imgFoto = (ImageView)findViewById(R.id.imgFoto);

        Intent i = getIntent();

        String email = i.getStringExtra("email");
        etEmail.setText(email);

        spTimes = (Spinner)findViewById(R.id.spTimes);
     //   GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);

        //Outra maneira de popular o Spinner
        //Adicionando Nomes no ArrayList
        times.add("Atlético MG");
        times.add("Atlético PR");
        times.add("Botafogo");
        times.add("Corinthians");
        times.add("Cruzeiro");
        times.add("Flamengo");
        times.add("Internacional");
        times.add("Santos");
        times.add("Vasco");
        times.add("Vitória");

        //Outra maneira de popular o Spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
     //  ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
       // spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTimes.setAdapter(arrayAdapter);

         //deixa comentado
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        //spTimes.setAdapter(adapter);

        btChoose.setOnClickListener(this);

        btSalvar.setOnClickListener(this);

    }
    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                float scaleWidth = ((float) 176) / width;
                float scaleHeight = ((float) 144) / height;
                // create a matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                matrix.postScale(scaleWidth, scaleHeight);
                // recreate the new Bitmap
                Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);

                imgFoto.setImageBitmap(resizedBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btSalvar:
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String time = spTimes.getSelectedItem().toString();


                Toast.makeText(getApplicationContext(),"Ola"+ etNome.getText().toString(),Toast.LENGTH_LONG).show();





                break;
            case R.id.btChoose:
                showFileChooser();
                break;
        }

    }

}
