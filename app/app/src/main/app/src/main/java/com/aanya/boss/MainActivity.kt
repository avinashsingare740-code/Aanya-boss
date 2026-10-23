
package com.aanya.boss

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.speech.tts.TextToSpeech
import java.util.Locale

class MainActivity : Activity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(5, 5, 35)
        window.navigationBarColor = Color.rgb(5, 5, 35)

        tts = TextToSpeech(this, this)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
            setBackgroundColor(Color.rgb(5, 5, 35))
        }

        val title = TextView(this).apply {
            text = "✦ AANYA AI ✦"
            textSize = 32f
            setTextColor(Color.rgb(130, 190, 255))
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "B O S S"
            textSize = 23f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val galaxy = TextView(this).apply {
            text = "\n✦       ·       ✧\n\n       🌌\n\n✧       ★       ✦\n\n"
            textSize = 28f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(180, 160, 255))
        }

        val message = TextView(this).apply {
            text = "Namaste! Main Aanya hoon.\nAapki personal AI assistant."
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val speakButton = Button(this).apply {
            text = "AANYA SE BAAT KAREIN"
            setOnClickListener {
                tts?.speak(
                    "Namaste! Main Aanya hoon. Bataiye, main aapki kya madad kar sakti hoon?",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "aanya_greeting"
                )
            }
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(galaxy)
        root.addView(message)

        root.addView(
            speakButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 35
            }
        )

        setContentView(root)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("hi", "IN")
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
