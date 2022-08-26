package com.alpha.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class RatingBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var totalNum = 5
    private var currentNum = 2
    private var emptyDrawable: Bitmap
    private var halfDrawable: Bitmap
    private var fullDrawable: Bitmap
    private var padding = 0f
    private var iconWidth:Int = 0

    init {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar)
        totalNum = ta.getInt(R.styleable.RatingBar_rb_num, totalNum)
        var emptyId = ta.getResourceId(R.styleable.RatingBar_rb_empty, 0)
        if (emptyId == 0) {
            //throw RuntimeException("请设置属性rb_empty")
            emptyId = R.drawable.ic_star_empty
        }
        emptyDrawable = BitmapFactory.decodeResource(resources, emptyId)
        var halfId = ta.getResourceId(R.styleable.RatingBar_rb_half, 0)
        if (halfId == 0) {
            halfId = R.drawable.ic_star_half
            //throw RuntimeException("请设置属性rb_half")
        }
        halfDrawable = BitmapFactory.decodeResource(resources, halfId)
        var fullId = ta.getResourceId(R.styleable.RatingBar_rb_full, 0)
        if (fullId == 0) {
            fullId = R.drawable.ic_star_full
            // throw RuntimeException("请设置属性rb_full")
        }
        fullDrawable = BitmapFactory.decodeResource(resources, fullId)
        padding = ta.getDimension(R.styleable.RatingBar_rb_padding ,padding)
        ta.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        iconWidth = emptyDrawable.width
        val height = emptyDrawable.height
        setMeasuredDimension((iconWidth * totalNum + padding*(totalNum-1)).toInt(), height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.e(javaClass.name,"start draw")
        for (i in 0 until totalNum) {
            val startX = if (i == 0) {
                i * iconWidth.toFloat()
            } else {
                i * iconWidth.toFloat() + i*padding
            }
            if (currentNum > i) {
                canvas.drawBitmap(fullDrawable, startX, 0f, null)
            } else {
                canvas.drawBitmap(emptyDrawable, startX, 0f, null)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action) {
            MotionEvent.ACTION_DOWN-> {}
            MotionEvent.ACTION_UP-> {}
            MotionEvent.ACTION_MOVE-> {
                val moveX = event.x
                val num = (moveX.div(iconWidth + padding) + 1).toInt()
                if (currentNum == num) {
                    return true
                }
                currentNum = if (num<0) {
                    0
                } else if (num > totalNum){
                    totalNum
                } else {
                    num
                }
                invalidate()
                performClick()
            }
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

}