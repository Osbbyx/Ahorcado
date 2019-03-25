package lol.lol.ahorcado;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    private String OcultoFacil[] = {"Humano", "Persona", "Amigo", "Dios", "Dedo", "Gris", "Guia", "Frio", "Fiel", "Baul", "Azul", "Bola", "Casa","Agua","Ayer","Cama","Calle","Ropa","Niño","Niña","Leer","Malo","Mar","Hijo","Hija","Flor","Feo"};
    private String OcultoNormal[] = {"babosa", "cabeza", "cabello", "caballo", "fabrica", "nacer", "rabieta", "chocolate","Bonito","Cabeza","Cancion","Colegio","Comida","Contento","Familia","Hermano","Hermana","Mañana","Numero","Nombre","Rapido","Responder","Trabajar","Tienda"};
    private String OcultoDificil[] = {"seguridad", "internacional", "condiciones", "produccion", "deferentes", "investigacion", "organizacion","inteligente","Television","Habitacion","Boligrafo","Fotocopiadora","Documentos","Portafolio","Calculadora"};
    private TextView respuesta;
    private String dificultad;
    private String palabraOculta = "computadora";
    private int numeroDeFallos = 0;
    private ImageView imagen;
    private LinearLayout nu,nd,nt;
    private Button btni,btns,btn;
    private MediaPlayer mp,mp1,mp2;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-8718810071299222/1479133905");

        respuesta = (TextView) findViewById(R.id.txt);
        dificultad = getIntent().getStringExtra("dificultad");
        imagen = (ImageView)findViewById(R.id.imageView);
        nu = (LinearLayout)findViewById(R.id.nu);
        nd = (LinearLayout)findViewById(R.id.nd);
        nt = (LinearLayout)findViewById(R.id.nt);
        btni = (Button)findViewById(R.id.inicio);
        btns = (Button)findViewById(R.id.siguiente);
        mp = MediaPlayer.create(this,R.raw.puntos);
        mp1 = MediaPlayer.create(this,R.raw.efectou);
        mp2 = MediaPlayer.create(this,R.raw.efectot);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAdView = findViewById(R.id.adView1);
       AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       //palabra , imagen y barras
        imagen.setImageResource(R.mipmap.ahorcado_cero);
        palabraOculta = escogePalabra();
        checkbarras();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }
    public void onBackPressed() {

    }



    //Metodo que crea las lineas

    private void checkbarras() {
        String barras = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            barras += "_ ";
        }
        respuesta.setText(barras);
    }

    //Chequear letra
    public void SaberLetra(View view) {
        pressed(view);
    }

    //coje la palabra
    private String escogePalabra() {
        if (dificultad.equals("facil")) {
            String palabra;
            String[] palabrasas = OcultoFacil;
            Random random = new Random();
            palabra = palabrasas[random.nextInt(palabrasas.length)];
            palabra = palabra.toUpperCase();
            return palabra;
        } else if (dificultad.equals("normal")) {
            String palabra;
            String[] palabrasas = OcultoNormal;
            Random random = new Random();
            palabra = palabrasas[random.nextInt(palabrasas.length)];
            palabra = palabra.toUpperCase();
            return palabra;
        } else  {
            String palabra;
            String[] palabrasas = OcultoDificil;
            Random random = new Random();
            palabra = palabrasas[random.nextInt(palabrasas.length)];
            palabra = palabra.toUpperCase();
            return palabra;
        }

    }


    //-----------------------------------------

    private void chequeaLetra(String letra){
        letra = letra.toUpperCase();
        String palabraConGuiones = respuesta.getText().toString();
        boolean acierto = false;
        for (int i=0; i<palabraOculta.length(); i++){
            if (palabraOculta.charAt(i) == letra.charAt(0)){
                //TODO quita el guión bajo de la letra correspondiente
                palabraConGuiones = palabraConGuiones.substring(0, 2*i)
                        + letra
                        + palabraConGuiones.substring(2*i+1);
                acierto = true;
            }
        }
        if (!palabraConGuiones.contains("_")){
            imagen.setImageResource(R.mipmap.acertaste);
            nu.setVisibility(View.GONE);
            nd.setVisibility(View.GONE);
            nt.setVisibility(View.GONE);
            btns.setVisibility(View.VISIBLE);
            mp2.start();

        }
        //actualizo el valor que se muestra en la pantalla con las letras
        //adivinadas
        respuesta.setText(palabraConGuiones);

        if (!acierto){
            numeroDeFallos++;
            dibujaImagen(numeroDeFallos);
            if(numeroDeFallos == 6){
                nu.setVisibility(View.GONE);
                nd.setVisibility(View.GONE);
                nt.setVisibility(View.GONE);
                btni.setVisibility(View.VISIBLE);
                respuesta.setText(palabraOculta);
                mp1.start();
            }
        }
    }

    //Metodo de saber cual se preciono
    public void pressed(View view) {
        switch (view.getId()) {
            case R.id.a:
                    view.setEnabled(false);
                    chequeaLetra("a");
                btn = (Button)findViewById(R.id.a);
                   btn.setBackgroundResource(R.drawable.redondo_dos);
                    mp.start();
                break;
            case R.id.b:
                view.setEnabled(false);
                chequeaLetra("b");
                btn = (Button)findViewById(R.id.b);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.c:
                view.setEnabled(false);
                chequeaLetra("c");
                btn = (Button)findViewById(R.id.c);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.d:
                view.setEnabled(false);
                chequeaLetra("d");
                btn = (Button)findViewById(R.id.d);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.e:
                view.setEnabled(false);
                chequeaLetra("e");
                btn = (Button)findViewById(R.id.e);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.f:
                view.setEnabled(false);
                chequeaLetra("f");
                btn = (Button)findViewById(R.id.f);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.g:
                view.setEnabled(false);
                chequeaLetra("g");
                btn = (Button)findViewById(R.id.g);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.h:
                view.setEnabled(false);
                chequeaLetra("h");
                btn = (Button)findViewById(R.id.h);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.i:
                view.setEnabled(false);
                chequeaLetra("i");
                btn = (Button)findViewById(R.id.i);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.j:
                view.setEnabled(false);
                chequeaLetra("j");
                btn = (Button)findViewById(R.id.j);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.k:
                view.setEnabled(false);
                chequeaLetra("k");
                btn = (Button)findViewById(R.id.k);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.l:
                view.setEnabled(false);
                chequeaLetra("l");
                btn = (Button)findViewById(R.id.l);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.m:
                view.setEnabled(false);
                chequeaLetra("m");
                btn = (Button)findViewById(R.id.m);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.n:
                view.setEnabled(false);
                chequeaLetra("n");
                btn = (Button)findViewById(R.id.n);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.ñ:
                view.setEnabled(false);
                chequeaLetra("ñ");
                btn = (Button)findViewById(R.id.ñ);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.o:
                view.setEnabled(false);
                chequeaLetra("o");
                btn = (Button)findViewById(R.id.o);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.p:
                view.setEnabled(false);
                chequeaLetra("p");
                btn = (Button)findViewById(R.id.p);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.q:
                view.setEnabled(false);
                chequeaLetra("q");
                btn = (Button)findViewById(R.id.q);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.r:
                view.setEnabled(false);
                chequeaLetra("r");
                btn = (Button)findViewById(R.id.r);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.s:
                view.setEnabled(false);
                chequeaLetra("s");
                btn = (Button)findViewById(R.id.s);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.t:
                view.setEnabled(false);
                chequeaLetra("t");
                btn = (Button)findViewById(R.id.t);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.u:
                view.setEnabled(false);
                chequeaLetra("u");
                btn = (Button)findViewById(R.id.u);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.v:
                view.setEnabled(false);
                chequeaLetra("v");
                btn = (Button)findViewById(R.id.v);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.w:
                view.setEnabled(false);
                chequeaLetra("w");
                btn = (Button)findViewById(R.id.w);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.x:
                view.setEnabled(false);
                chequeaLetra("x");
                btn = (Button)findViewById(R.id.x);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.y:
                view.setEnabled(false);
                chequeaLetra("y");
                btn = (Button)findViewById(R.id.y);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
            case R.id.z:
                view.setEnabled(false);
                chequeaLetra("z");
                btn = (Button)findViewById(R.id.z);
                btn.setBackgroundResource(R.drawable.redondo_dos);
                mp.start();
                break;
        }
    }


    private void dibujaImagen(int numeroImagen) {
        switch (numeroImagen) {
            case 0:
                imagen.setImageResource(R.mipmap.ahorcado_cero);
                break;
            case 1:
                imagen.setImageResource(R.mipmap.ahorcado_uno);
                break;
            case 2:
                imagen.setImageResource(R.mipmap.ahorcado_dos);
                break;
            case 3:
                imagen.setImageResource(R.mipmap.ahorcado_tres);
                break;
            case 4:
                imagen.setImageResource(R.mipmap.ahorcado_cuatro);
                break;
            case 5:
                imagen.setImageResource(R.mipmap.ahorcado_cinco);
                break;
            default:
                imagen.setImageResource(R.mipmap.ahorcado_finn);
                break;
        }

    }

    public void Siguiente(View view){
        Intent in = new Intent(this,MainActivity.class);
        in.putExtra("dificultad",dificultad);
        startActivity(in);
    }

    public void Inicio(View view){
        Intent in = new Intent(this,intro.class);
        startActivity(in);
    }

}
