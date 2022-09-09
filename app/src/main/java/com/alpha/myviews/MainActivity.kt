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
        mStepView.setOnClickListener {
            val contentView = this.window.decorView.findViewById<View>(android.R.id.content) as FrameLayout
            contentView.addView(FloatView(this))
        }
    }
}