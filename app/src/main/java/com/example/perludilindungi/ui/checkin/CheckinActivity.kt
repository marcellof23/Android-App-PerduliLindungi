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
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.perludilindungi.R
import com.example.perludilindungi.models.checkin.CheckInRequest
import com.example.perludilindungi.models.checkin.CheckInResponse
import com.example.perludilindungi.services.CheckInAPI
import com.example.perludilindungi.utils.Retro
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckinActivity : AppCompatActivity(), SensorEventListener {
    lateinit var tvSuhu: TextView
    lateinit var sensorMgr: SensorManager
    lateinit var tempSensor: Sensor
    var isSensorAvailable: Boolean = true


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

        } else {
            tvSuhu.setText("Temperature sensor is not available :(")
            isSensorAvailable = false
        }

        // QR
        val qrButton: ImageButton = findViewById(R.id.qr_btn)
        qrButton.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.initiateScan()
        }
    }

    // QR Scan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result.contents != null) {
            // result.contents // POST KE API
            postCheckInApi(result.contents)
        } else {
            AlertDialog.Builder(this)
                .setMessage("Barcode tidak terdeteksi, silahkan coba lagi.").create().show()
        }
    }

    fun postCheckInApi(qrCode: String) {

        val checkinReq = CheckInRequest()
        checkinReq.qrCode = qrCode
        checkinReq.latitude = -6.1351855  // nanti set
        checkinReq.longitude = 11.0323457

        println(checkinReq.latitude)
        val retro = Retro().getRetroClientInstance().create(CheckInAPI::class.java)

        retro.sendCheckIn(checkinReq).enqueue(object : Callback<CheckInResponse> {
            override fun onResponse(
                call: Call<CheckInResponse>, response: Response<CheckInResponse>
            ) {
                val res = response.body()
                val success = res?.success

                if (success == true) {
                    val data = res.data
                    if (data != null && data.size() != 0) {
                        val tvUserStatus: TextView = findViewById(R.id.tv_userStatus)
                        val tvMessage: TextView = findViewById(R.id.tv_message)
                        tvUserStatus.text = data.get("userStatus").asString
                        tvMessage.text = data.get("reason").asString
                    }
                } else {
                    val tvMessage: TextView = findViewById(R.id.tv_message)
                    tvMessage.text = res?.message.toString()
                }

            }

            override fun onFailure(call: Call<CheckInResponse>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }
        })

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
            sensorMgr.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isSensorAvailable) {
            sensorMgr.unregisterListener(this)
        }
    }
}