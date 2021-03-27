package com.malpvaplicaciones.app4_lifecycle

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    // Instancia MediaPlayer
    private var mediaPlayer:MediaPlayer? = null
    private var position:Int = 0

    //region Proceso basico de lifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCheck).setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }
        // mediaPlayer= MediaPlayer.create(this, R.raw.ai_2)
        Log.i("LifeCycle", " onCreate ")
    }

    override fun onStart() {
        super.onStart()

        // Mejor opcion y mas optimizado, luego de liberar mediaPlayer y volver a este estado se vuelve a crear
        mediaPlayer= MediaPlayer.create(this, R.raw.ai_2)

        Log.i("LifeCycle", " onStart ")
    }

    // Para comenzar las consultas a la base de datos, servicios otros similares
    override fun onResume() {
        super.onResume()

        mediaPlayer?.seekTo(position)
        // if (mediaPlayer != null) es equivalente a mediaPlayer?.start()
        mediaPlayer?.start() // Si es null no se ejecuta el metodo
        Log.i("LifeCycle", " onResume ")
    }

    // FIXME: 27-03-2021 Cuando nuestra actividad pierde el foco
    // Para pausar servicios, consultas etc.
    override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()

        if (mediaPlayer != null){
            // Guardamos la posicion en que quedo la cancion
            position = mediaPlayer!!.currentPosition // Le indicamos que media player no sera nulo
        }

        Log.i("LifeCycle", " onPause ")
    }
    //endregion

    // FIXME: 27-03-2021 Cuando nuestra actividad ya no es visible
    override fun onStop() {
        super.onStop()

        // Liberamos la memoria y el uso de los recursos
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.i("LifeCycle", " onStop ")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("LifeCycle", " onDestroy ")
    }
}