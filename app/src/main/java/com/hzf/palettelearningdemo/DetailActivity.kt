package com.hzf.palettelearningdemo

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import com.hzf.palettelearningdemo.data.CharacterInfo
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CHARACTER_INFO = "CHARACTER_INFO"

        fun actionStart(info: CharacterInfo,originActivity: MainActivity){
            originActivity.startActivity(Intent(originActivity,DetailActivity::class.java).apply {
                putExtra(CHARACTER_INFO,info)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val headerPictureIV = findViewById<CircleImageView>(R.id.civ_header_picture)
        val nameTV = findViewById<TextView>(R.id.tv_name)
        val describeTV = findViewById<TextView>(R.id.tv_describe)

        val vibrantTV = findViewById<TextView>(R.id.tv_vibrant)
        val vibrantLightTV = findViewById<TextView>(R.id.tv_vibrant_light)
        val vibrantDarkTV = findViewById<TextView>(R.id.tv_vibrant_dark)
        val mutedTV = findViewById<TextView>(R.id.tv_muted)
        val mutedLightTV = findViewById<TextView>(R.id.tv_muted_light)
        val mutedDarkTV = findViewById<TextView>(R.id.tv_muted_dark)

        intent?.getParcelableExtra<CharacterInfo>(CHARACTER_INFO)?.let { characterInfo ->
            val bitmapRes = BitmapFactory.decodeResource(resources,characterInfo.imgResId)
            Palette.from(bitmapRes).generate { paletteOut ->
                paletteOut?.let { paletteIn ->
                    headerPictureIV.setImageResource(characterInfo.imgResId)
                    nameTV.text = characterInfo.name
                    describeTV.text = characterInfo.desc

                    paletteIn.vibrantSwatch?.rgb?.let { rgb ->
                        val bgView = findViewById<View>(R.id.view_bg_header_picture)
                        bgView.background = ColorDrawable(rgb)
                    }
                    paletteIn.lightMutedSwatch?.titleTextColor?.let { textColor ->
                        nameTV.setTextColor(textColor)
                    }
                    paletteIn.darkMutedSwatch?.bodyTextColor?.let { textColor ->
                        describeTV.setTextColor(textColor)
                    }

                    paletteIn.vibrantSwatch?.rgb?.let { bgColor ->
                        vibrantTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.vibrantSwatch?.titleTextColor?.let { textColor ->
                        vibrantTV.setTextColor(textColor)
                    }

                    paletteIn.lightVibrantSwatch?.rgb?.let { bgColor ->
                        vibrantLightTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.lightVibrantSwatch?.titleTextColor?.let { textColor ->
                        vibrantLightTV.setTextColor(textColor)
                    }

                    paletteIn.darkVibrantSwatch?.rgb?.let { bgColor ->
                        vibrantDarkTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.darkVibrantSwatch?.titleTextColor?.let { textColor ->
                        vibrantDarkTV.setTextColor(textColor)
                    }

                    paletteIn.mutedSwatch?.rgb?.let { bgColor ->
                        mutedTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.mutedSwatch?.titleTextColor?.let { textColor ->
                        mutedTV.setTextColor(textColor)
                    }

                    paletteIn.lightMutedSwatch?.rgb?.let { bgColor ->
                        mutedLightTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.lightMutedSwatch?.titleTextColor?.let { textColor ->
                        mutedLightTV.setTextColor(textColor)
                    }

                    paletteIn.darkMutedSwatch?.rgb?.let { bgColor ->
                        mutedDarkTV.setBackgroundColor(bgColor)
                    }
                    paletteIn.darkMutedSwatch?.titleTextColor?.let { textColor ->
                        mutedDarkTV.setTextColor(textColor)
                    }

                }
            }
        }
    }
}