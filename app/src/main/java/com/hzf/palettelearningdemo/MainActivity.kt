package com.hzf.palettelearningdemo

import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.hzf.palettelearningdemo.data.CharacterInfo
import com.hzf.palettelearningdemo.data.CharacterUIBean
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private val mModelBeanList: List<CharacterUIBean> by lazy {
        val luffyTV = findViewById<TextView>(R.id.tv_luffy)
        val zoroTV = findViewById<TextView>(R.id.tv_zoro)
        val sanjiTV = findViewById<TextView>(R.id.tv_sanji)
        val jinbeTV = findViewById<TextView>(R.id.tv_jinbe)

        val luffyIV = findViewById<ImageView>(R.id.iv_luffy)
        val zoroIV = findViewById<ImageView>(R.id.iv_zoro)
        val sanjiIV = findViewById<ImageView>(R.id.iv_sanji)
        val jinbeIV = findViewById<ImageView>(R.id.iv_jinbe)

        val luffy = CharacterInfo("路飞","我是要成为海贼王的男人",R.mipmap.luffy)
        val zoro = CharacterInfo("索隆","什么也没发生",R.mipmap.zoro)
        val sanji = CharacterInfo("山治","我不会打女人",R.mipmap.sanji)
        val jinbe = CharacterInfo("甚平","输我的血吧",R.mipmap.jinbe)
        mutableListOf(
            CharacterUIBean(luffy,luffyTV,luffyIV),
            CharacterUIBean(zoro,zoroTV,zoroIV),
            CharacterUIBean(sanji,sanjiTV,sanjiIV),
            CharacterUIBean(jinbe,jinbeTV,jinbeIV)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mModelBeanList.forEach {
            val bitmap = BitmapFactory.decodeResource(resources,it.characterInfo.imgResId)
            Palette.from(bitmap).generate { paletteOut ->
                paletteOut?.let { paletteIn ->
                    paletteIn.vibrantSwatch?.rgb?.let { rgb ->
                        it.nameTV.background = ColorDrawable(rgb)
                    }
                    paletteIn.vibrantSwatch?.titleTextColor?.let { textColor ->
                        it.nameTV.setTextColor(textColor)
                    }
                }
            }

            it.imgIV.setOnLongClickListener { _ ->
                DetailActivity.actionStart(it.characterInfo,this)
                return@setOnLongClickListener true
            }

            it.imgIV.setOnClickListener { _ ->
                val bitmapRes = BitmapFactory.decodeResource(resources,it.characterInfo.imgResId)
                Palette.from(bitmapRes).generate { paletteOut ->
                    paletteOut?.let { paletteIn ->
                        val headerPicture = findViewById<CircleImageView>(R.id.civ_header_picture)
                        headerPicture.setImageResource(it.characterInfo.imgResId)

                        val nameTV = findViewById<TextView>(R.id.tv_name)
                        nameTV.text = it.characterInfo.name
                        val describeTV = findViewById<TextView>(R.id.tv_describe)
                        describeTV.text = it.characterInfo.desc

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
                    }
                }
            }
        }

        val luffyIV = findViewById<ImageView>(R.id.iv_luffy)
        luffyIV.performClick()
    }

}