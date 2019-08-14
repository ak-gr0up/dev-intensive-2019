package ru.skillbranch.devintensive.ui.custom



import android.content.Context
import android.graphics.*

import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R


class TextDrawable(val bgColor: Int=Color.BLACK) : Drawable() {

    var text: String = ""

    var backgroundColor = Color.BLACK

    var textColor = Color.WHITE



    var paint = Paint().apply {

        isAntiAlias = true

        isFakeBoldText = true

        style = Paint.Style.FILL

        textAlign = Paint.Align.CENTER

        alpha = 255

    }


    override fun draw(canvas: Canvas) {

        canvas.drawCircle(

            bounds.centerX().toFloat(),

            bounds.centerY().toFloat(),

            (bounds.width() / 2).toFloat(),

            paint.apply { color = bgColor; alpha = 255 }

        )



        paint.textSize = bounds.width() * 0.5f



        val xPos = bounds.width() / 2f

        val yPos = (bounds.height() / 2 - (paint.descent() + paint.ascent()) / 2)

        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.



        canvas.drawText(

            text,

            xPos, yPos,

            paint.apply { color = textColor }

        )

    }



    override fun setAlpha(alpha: Int) {

        paint.alpha = alpha

    }



    override fun setColorFilter(cf: ColorFilter?) {

        paint.colorFilter = cf

    }



    override fun getOpacity(): Int {

        return PixelFormat.TRANSLUCENT

    }

}