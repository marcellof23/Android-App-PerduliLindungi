package com.example.perludilindungi.ui.checkin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.perludilindungi.R
import com.example.perludilindungi.models.checkin.CheckInRequest
import com.example.perludilindungi.models.checkin.CheckInResponse
import com.example.perludilindungi.services.CheckInAPI
import com.example.perludilindungi.utils.Retro
import com.google.android.gms.location.*
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckinActivity : AppCompatActivity(), SensorEventListener {
    lateinit var tvMessage: TextView
    lateinit var tvStatus: TextView
    lateinit var iconStatus: FrameLayout
    lateinit var tvSuhu: TextView
    lateinit var sensorMgr: SensorManager
    lateinit var tempSensor: Sensor
    var isSensorAvailable: Boolean = true
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var latitude: Double? = null
    var longitude: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)
        // Init
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        tvMessage = findViewById(R.id.tv_message)
        tvStatus = findViewById(R.id.tv_status)
        iconStatus = findViewById(R.id.checkin_layout)
        tvSuhu = findViewById(R.id.tv_temperatur)
        sensorMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            isSensorAvailable = true

        } else {
            tvSuhu.setText("N/A")
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

        // Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()
    }

    // Location
    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Get location success", Toast.LENGTH_SHORT).show()
                        longitude = location.longitude
                        latitude = location.latitude
                    }
                }

            } else {
                // open settings
                Toast.makeText(this, "Turn on location access", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            // req permission
            requestPermission()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ), PERMISSION_REQUEST_ACCESS_LOCATION

        )
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Location access granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else {
                Toast.makeText(applicationContext, "Location access denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // QR Scan
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result.contents != null) {
            postCheckInApi(result.contents)
        } else {
            AlertDialog.Builder(this)
                .setMessage("Barcode tidak terdeteksi, silahkan coba lagi.").create().show()
        }
    }

    fun postCheckInApi(qrCode: String) {

        // Request Msg
        val checkinReq = CheckInRequest()
        checkinReq.qrCode = qrCode
        checkinReq.latitude = latitude
        checkinReq.longitude = longitude
        println("============================POST ITEM===================")
        println(checkinReq.qrCode)
        println(checkinReq.latitude)
        println(checkinReq.longitude)
        val retro = Retro().getRetroClientInstance().create(CheckInAPI::class.java)

        retro.sendCheckIn(checkinReq).enqueue(object : Callback<CheckInResponse> {
            override fun onResponse(
                call: Call<CheckInResponse>, response: Response<CheckInResponse>
            ) {
                val res = response.body()
                val success = res?.success

                // Kalau QR code nya ngasal/lokasi salah
                if (success == null) {
                    iconStatus.setBackgroundResource(0)
                    tvStatus.text = ""
                    tvMessage.text =
                        "Terjadi kesalahan, silahkan coba lagi. Pastikan QR Code valid dan izin akses lokasi telah diberikan."
                }
                // QR code valid, berhasil post
                else if (success == true) {
                    val data = res.data

                    if (data != null && data.size() != 0) {

                        val status = data.get("userStatus").asString
                        if (status.equals("black")) {
                            tvStatus.text = "Check-in Gagal"
                            iconStatus.background =
                                resources.getDrawable(R.drawable.cross_circle_black, theme)
                            tvMessage.text = data.get("reason").asString
                        } else if (status.equals("red")) {
                            tvStatus.text = "Check-in Gagal"
                            iconStatus.background =
                                resources.getDrawable(R.drawable.cross_circle_red, theme)
                            tvMessage.text = data.get("reason").asString
                        } else if (status.equals("green")) {
                            tvStatus.text = "Check-in Berhasil"
                            iconStatus.background =
                                resources.getDrawable(R.drawable.check_circle_green, theme)
                            tvMessage.text = data.get("reason").asString
                        } else if (status.equals("yellow")) {
                            tvStatus.text = "Check-in Berhasil"
                            iconStatus.background =
                                resources.getDrawable(R.drawable.check_circle_yellow, theme)
                            tvMessage.text = data.get("reason").asString
                        }
                        // QR code valid, gagal post
                    } else {
                        iconStatus.setBackgroundResource(0)
                        tvStatus.text = ""
                        tvMessage.text = res.message.toString()
                    }

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
                findViewById<TextView>(R.id.tv_temperatur).text = event.values[0].toString() + " °C"
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