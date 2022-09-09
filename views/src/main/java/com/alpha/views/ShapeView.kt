package com.alpha.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.alpha.views.enum.Shape
import kotlin.math.sin

class ShapeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint()
    private var currentShape: Shape = Shape.Circle

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val realWidth = minOf(width, height)
        setMeasuredDimension(realWidth, realWidth)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when(currentShape) {
            Shape.Circle -> drawCircle(canvas)
            Shape.Square -> drawSquare(canvas)
            Shape.Triangle -> drawTriangle(canvas)
         }
    }

    fun exchangeShape() {
        currentShape = when(currentShape) {
            Shape.Circle -> Shape.Square
            Shape.Square -> Shape.Triangle
            Shape.Triangle -> Shape.Circle
        }
        invalidate()
    }

    private fun drawTriangle(canvas: Canvas) {
        mPaint.color = Color.RED
        val path = android.graphics.Path()
        val dy:Float = (width * sin(Math.toRadians(60.0))).toFloat()
        path.moveTo((width / 2).toFloat(), 0f)
        path.lineTo(0f, dy)
        path.lineTo(width.toFloat(), dy)
        path.close()
        canvas.drawPath(path, mPaint)
    }

    private fun drawSquare(canvas: Canvas) {
        mPaint.color = Color.GREEN
        canvas.drawRect(0f, 0f, width.toFloat(), width.toFloat(), mPaint)
    }

    private fun drawCircle(canvas: Canvas) {
        mPaint.color = Color.BLUE
        val center = (width / 2).toFloat()
        canvas.drawCircle(center, center, center, mPaint)
    }
}