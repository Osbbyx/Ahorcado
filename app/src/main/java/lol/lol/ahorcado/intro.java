package lol.lol.ahorcado;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.MobileAds;


public class intro extends AppCompatActivity {

    private MediaPlayer mp;
    private Button b1,b2,b3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        MobileAds.initialize(this, "ca-app-pub-8718810071299222/9806833029");
        mp = MediaPlayer.create(this,R.raw.puntos);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);



    }

    public void onBackPressed() {
        finish();
    }

    public void Facil(View view){
        String dificultad = "facil";
        mp.start();
        Intent in = new Intent(this,MainActivity.class);
        in.putExtra("dificultad",dificultad);
        startActivity(in);
        b1.setBackgroundResource(R.drawable.pres);
    }

    public void Normal(View view){
        String dificultad = "normal";
        mp.start();
        Intent in = new Intent(this,MainActivity.class);
        in.putExtra("dificultad",dificultad);
        startActivity(in);
            b2.setBackgroundResource(R.drawable.pres);
    }

    public void Dificil(View view){
        String dificultad = "dificil";
        mp.start();
        Intent in = new Intent(this,MainActivity.class);
        in.putExtra("dificultad",dificultad);
        startActivity(in);
        b3.setBackgroundResource(R.drawable.pres);
    }


}
