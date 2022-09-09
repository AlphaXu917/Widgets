package com.alpha.myviews

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.alpha.views.FloatView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFloatView()
        initStepView()
        initShapeView()
    }

    private fun initStepView() {
        mStepView.setStepMax(10000);
        mStepView.setCurrentStepWithAnimator(6000, 4000);
    }

    private fun initShapeView() {
        Thread {
            while (!this.isFinishing) {
                runOnUiThread(mShapeView::exchangeShape)
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun createFloatView() {
        val contentView =
            this.window.decorView.findViewById<View>(android.R.id.content) as FrameLayout
        contentView.addView(FloatView(this))
    }
}