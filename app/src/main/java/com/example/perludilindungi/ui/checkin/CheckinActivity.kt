package com.example.perludilindungi.ui.checkin

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.perludilindungi.R
import com.google.zxing.integration.android.IntentIntegrator

class CheckinActivity : AppCompatActivity(), SensorEventListener {
    lateinit var tvSuhu: TextView
    lateinit var sensorMgr: SensorManager
    lateinit var tempSensor: Sensor
    var isSensorAvailable: Boolean = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)
        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // TempSensor
        tvSuhu = findViewById(R.id.tv_suhu)
        sensorMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            isSensorAvailable = true

        }
        else {
            tvSuhu.setText("Temperature sensor is not available :(")
            isSensorAvailable = false
        }

        // QR
        val tvCheckin: TextView = findViewById(R.id.tvcheckin)
        val qrButton: ImageButton = findViewById(R.id.qr_btn)
        qrButton.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.initiateScan()
        }
    }

    // QR
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            AlertDialog.Builder(this)
                .setMessage("Would you like to go to ${result.contents}?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(Intent.ACTION_WEB_SEARCH)
                    intent.putExtra(SearchManager.QUERY,result.contents)
                    startActivity(intent)
                })
                .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->  })
                .create()
                .show()

        }
    }

    // Temperatur
    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                findViewById<TextView>(R.id.tv_suhu).text = event.values[0].toString() + " °C"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        if (isSensorAvailable) {
            sensorMgr.registerListener(this,tempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isSensorAvailable) {
            sensorMgr.unregisterListener(this)
        }
    }
}