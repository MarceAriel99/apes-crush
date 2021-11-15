package com.example.tptdl

//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch

//import kotlinx.coroutines.runBlocking

import android.Manifest
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tptdl.weatherAPI.Weather
//import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var map: MapActivity
    private lateinit var level: LevelActivity
    private lateinit var settings: SettingsActivity

    // Contains all the views
    //TODO poner bien las bindings para acceder a las views
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather =  Weather()

        askForPermissions()

        val intent_act = Intent(this, WeatherService::class.java)
        startService(intent_act)

        /*GlobalScope.launch{
            println(weather.fetchCurrent())
        }*/

        //TODO implementarlo con corutinas

        //val gameBoard: GameBoard = GameBoard(3,3)
        //gameBoard.printBoard()

        val mapButton: Button = findViewById(R.id.map_button)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        val settingsButton: ImageButton = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun askForPermissions(){

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false) -> {
                    println("Background permission granted")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    println("Coarse permission granted")
                } else -> {
                println("No permission granted")
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    fun clickOnLevelButton(view: View) {
        println(view.id)
    }
    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        println("START MAIN")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAIN")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAIN")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAIN")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("DESTROY MAIN")
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAIN")
    }

}

