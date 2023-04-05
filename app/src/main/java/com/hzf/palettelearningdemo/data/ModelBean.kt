package com.hzf.palettelearningdemo.data

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import kotlinx.parcelize.Parcelize

/**
 * @ClassName: CharacterInfo
 * @Description: 角色信息类
 * @Author: Nicholas.hzf
 * @Date: 2023/3/25 21:16 Created
 */
@Parcelize
data class CharacterInfo(
    val name: String,
    val desc: String,
    val imgResId: Int
) : Parcelable

/**
 * @ClassName: CharacterUIBean
 * @Description: 角色 UI 类
 * @Author: Nicholas.hzf
 * @Date: 2023/3/25 21:12 Created
 */
data class CharacterUIBean(
    val characterInfo: CharacterInfo,
    val nameTV: TextView,
    val imgIV: ImageView
)