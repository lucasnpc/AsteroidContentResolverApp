package com.example.contentresolverapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contentresolverapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val cursor = contentResolver.query(
            Uri.parse("content://$AUTHORITY/asteroids"),
            null,
            null,
            null,
            null,
            null
        )


        binding.buttonGetProvider.setOnClickListener {
            cursor?.let {
                while (cursor.moveToNext()) {
                    cursor.getColumnIndex("_id").let { id -> println(cursor.getLong(id)) }

                    cursor.getColumnIndex("codename")
                        .let { codename -> println(cursor.getString(codename)) }

                    cursor.getColumnIndex("closeApproachDate")
                        .let { closeApproachDate -> println(cursor.getString(closeApproachDate)) }

                    cursor.getColumnIndex("absoluteMagnitude")
                        .let { absoluteMagnitude -> println(cursor.getDouble(absoluteMagnitude)) }

                    cursor.getColumnIndex("estimatedDiameter")
                        .let { estimatedDiameter -> println(cursor.getDouble(estimatedDiameter)) }

                    cursor.getColumnIndex("relativeVelocity")
                        .let { relativeVelocity -> println(cursor.getDouble(relativeVelocity)) }

                    cursor.getColumnIndex("distanceFromEarth")
                        .let { distanceFromEarth -> println(cursor.getDouble(distanceFromEarth)) }

                    cursor.getColumnIndex("isPotentiallyHazardous").let { isPotentiallyHazardous ->
                        println(
                            cursor.getInt(isPotentiallyHazardous) != 0
                        )
                    }
                }
                cursor.close()
            } ?: println("Cursor null")
        }
    }

    private companion object {
        const val AUTHORITY = "com.udacity.asteroidradar.provider"
    }
}