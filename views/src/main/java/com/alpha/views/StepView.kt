package com.alpha.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlin.math.sin


class StepView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var rectF: RectF
    private var borderWidth = 40// px
    private var stepGroundColor = Color.GRAY
    private var progressColor = Color.BLUE
    private var maxStep = 5000
    private var currentStep = 4000
    private var stepTextSize = 40
    private var stepTextColor = Color.BLACK
    private var textTypeFace = 0
    private var startAngle = 90f
    private var endAngle: Float
    private var isFullCircle = false
    private lateinit var stepGroundPaint: Paint
    private lateinit var progressPaint: Paint
    private lateinit var textPaint: Paint
    private val textBounds = Rect()

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.StepView)
        borderWidth =
            ta.getDimension(R.styleable.StepView_borderWidth, borderWidth.toFloat()).toInt()
        stepGroundColor = ta.getColor(R.styleable.StepView_stepGroundColor, stepGroundColor)
        progressColor = ta.getColor(R.styleable.StepView_progressColor, progressColor)
        maxStep = ta.getInteger(R.styleable.StepView_maxStep, maxStep)
        currentStep =
            ta.getInteger(R.styleable.StepView_currentStep, currentStep)
        stepTextSize = ta.getDimensionPixelSize(R.styleable.StepView_stepTextSize, stepTextSize)
        stepTextColor = ta.getColor(R.styleable.StepView_stepTextColor, stepTextColor)
        textTypeFace = ta.getInt(R.styleable.StepView_textTypeFace, textTypeFace)
        startAngle = ta.getFloat(R.styleable.StepView_startAngle, startAngle)
        isFullCircle = ta.getBoolean(R.styleable.StepView_isFullCircle, isFullCircle)
        ta.recycle()
        if (startAngle <= 90) {
            isFullCircle = true
        }
        if (isFullCircle) {
            startAngle=90f
            endAngle=360f
        } else {
            endAngle = 540-2*startAngle
        }
        rectF = RectF()
        initStepGroundPaint()
        initProgressPaint()
        initTextPaint()
    }

    private fun initTextPaint() {
        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = stepTextSize.toFloat()
        textPaint.color = stepTextColor
        val typeface: Typeface = when (textTypeFace) {
            1 -> {
                Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            }
            2 -> {
                Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
            }
            else -> {
                Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            }
        }
        textPaint.typeface = typeface
    }

    private fun initProgressPaint() {
        stepGroundPaint = Paint()
        stepGroundPaint.isAntiAlias = true
        stepGroundPaint.style = Paint.Style.STROKE
        stepGroundPaint.color = stepGroundColor
        stepGroundPaint.strokeWidth = borderWidth.toFloat()
        stepGroundPaint.strokeCap = Paint.Cap.ROUND
        stepGroundPaint.strokeJoin = Paint.Join.ROUND
    }

    private fun initStepGroundPaint() {
        progressPaint = Paint()
        progressPaint.isAntiAlias = true
        progressPaint.style = Paint.Style.STROKE
        progressPaint.color = progressColor
        progressPaint.strokeWidth = borderWidth.toFloat()
        progressPaint.strokeCap = Paint.Cap.ROUND
        progressPaint.strokeJoin = Paint.Join.ROUND
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val realWidth = minOf(height, width)
        if (isFullCircle) {
            setMeasuredDimension(realWidth, realWidth)
        } else {
            val r = (realWidth - 2 * borderWidth) / 2
            val dy = sin(Math.toRadians((180-startAngle).toDouble()))
            val realHeight = r + borderWidth * 2 + dy * r
            setMeasuredDimension(realWidth, realHeight.toInt())
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val center = (width / 2).toFloat()
        val radius = center - borderWidth / 2
        rectF.set(center - radius, center - radius, center + radius, center + radius)
        canvas.drawArc(rectF, startAngle, endAngle, false, stepGroundPaint)
        if (maxStep == 0) return
        var percent = currentStep.toFloat() / maxStep.toFloat()
        if (percent > 1) percent = 1f
        canvas.drawArc(rectF, startAngle, endAngle * percent, false, progressPaint)
        val strCurrentStep = currentStep.toString()
        textPaint.getTextBounds(strCurrentStep, 0, strCurrentStep.length, textBounds)
        val dx = (width / 2 - textBounds.width() / 2).toFloat()
        val fontMetrics = textPaint.fontMetrics
        val dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseLine = width / 2 + dy
        canvas.drawText(strCurrentStep, dx, baseLine, textPaint)
    }

    @Synchronized
    fun setStepMax(stepMax: Int) {
        this.maxStep = stepMax
    }

    @Synchronized
    fun setCurrentStep(currentStep: Int) {
        this.currentStep = currentStep
        invalidate()
    }

    fun setTextStyle(typeface: Int) {
        this.textPaint.typeface = Typeface.create(Typeface.SANS_SERIF, typeface)
    }

    @Synchronized
    fun setCurrentStepWithAnimator(currentStep: Int, duration: Long = 1000) {
        val valueAnimator = ObjectAnimator.ofFloat(0f, currentStep.toFloat())
        valueAnimator.duration = duration
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener {
            val current = it.animatedValue as Float
            setCurrentStep(current.toInt())
        }
        valueAnimator.start()
    }
}