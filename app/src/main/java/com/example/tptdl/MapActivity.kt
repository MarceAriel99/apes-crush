package com.example.tptdl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.tptdl.weatherAPI.WeatherState

class MapActivity : AppCompatActivity() {

    private val levelTexts : MutableList<TextView> = mutableListOf()
    private val levelButtons : MutableList<ImageButton> = mutableListOf()
    private lateinit var upMapButton : ImageButton
    private lateinit var downMapButton : ImageButton
    private var mapFase = 0
    private val lastAvailableLevel : Int = 15
    private val amountOfVisibleLevels = 10
    private lateinit var currentWeather: WeatherState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar?.hide()

        //Setting background according to weather
        currentWeather = intent.getSerializableExtra("weather") as WeatherState
        val background: ImageView = findViewById(R.id.backgroundImage)
        background.setImageResource(resources.getIdentifier(currentWeather.getMapBackgroundIdName(), "drawable", this.packageName))

        for (i in 1 until amountOfVisibleLevels + 1) {
            levelButtons.add(findViewById<View>(resources.getIdentifier("buttonLevel$i", "id", this.packageName)) as ImageButton)
            levelTexts.add(findViewById<View>(resources.getIdentifier("buttonTextLevel$i", "id", this.packageName)) as TextView)
        }

        upMapButton = findViewById(R.id.upMapButton)
        downMapButton = findViewById(R.id.downMapButton)

        this.upadateAvailableLevels()
    }

    fun clickOnLevelButton(view: View) {
        //TODO crear un levelActivity

        val levelNumber = view.contentDescription.toString().toInt() + mapFase * amountOfVisibleLevels

        println("Se clickeo nivel: $levelNumber")

        val intent = Intent(this, LevelActivity::class.java)
        intent.putExtra("weather", currentWeather)
        intent.putExtra("levelNumber", levelNumber)
        startActivity(intent)
    }

    private fun reachedTop(): Boolean {
        return levelTexts.last().text.toString().toInt() > lastAvailableLevel
    }

    private fun reachedBottom(): Boolean {
        return levelTexts.first().text == "1"
    }

    private fun upadateAvailableLevels() {
        for (i in 0 until amountOfVisibleLevels) {
            if (levelTexts[i].text.toString().toInt() > lastAvailableLevel) {
                levelButtons[i].isClickable = false
                levelButtons[i].alpha = 0.5f
            }
            else {
                levelButtons[i].isClickable = true
                levelButtons[i].alpha = 1f
            }
        }

        upMapButton.isVisible = !reachedTop()
        downMapButton.isVisible = !reachedBottom()
    }

    private fun levelUp(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) + amountOfVisibleLevels).toString()
    }

    private fun levelDown(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) - amountOfVisibleLevels).toString()
    }

    fun upMap(view: View) {

        if (reachedTop()) return

        for (levelText in levelTexts) levelUp(levelText)

        mapFase += 1

        this.upadateAvailableLevels()
    }

    fun downMap(view: View) {
        if (reachedBottom()) return

        for (levelText in levelTexts) levelDown(levelText)

        mapFase -= 1

        this.upadateAvailableLevels()
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        println("START MAP")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAP")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAP")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("DESTROY MAP")
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAP")
    }
}

