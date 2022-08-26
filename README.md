# How to get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.AlphaXu917:Widgets:1.0.0'
	}


# ShapeView

![1111](https://user-images.githubusercontent.com/16589390/186829205-0d783fbf-a35e-4e2d-85f6-3ca7dd82993c.png)
![2222](https://user-images.githubusercontent.com/16589390/186829207-67cbf575-297d-48cb-9398-93168a1fd069.png)


# XML

<com.alpha.views.StepView
        android:id="@+id/mStepView"
        android:layout_marginTop="10dp"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        app:borderWidth="20dp"
        app:stepGroundColor="@color/teal_200"
        app:progressColor="@color/purple_200"
        app:maxStep="10000"
        app:currentStep="1000"
        app:stepTextSize="30sp"
        app:textTypeFace="bold"
        app:stepTextColor="@color/black"/>

app:borderWidth 圆环宽度<br>
app:stepGroundColor 圆环底部颜色<br>
app:progressColor 圆环进度颜色<br>
app:maxStep 最大进度（步数）<br>
app:currentStep 当前进度（步数）<br>
app:stepTextSize 文字字体大小<br>
app:stepTextColor 文字颜色<br>
app:textTypeFace 字体样式（normal、bold、italic）<br>
app:isFullCircle 是否完整的圆<br>
app:startAngle 起始角度（90-180，小于90度显示整个圆）

# Java

设置最大进度（步数）<br>
setStepMax(stepMax: Int)<br>
设置当前进度（步数）<br>
setCurrentStep(currentStep: Int)<br>
设置当前进度（步数）添加动画<br>
setCurrentStepWithAnimator(currentStep: Int, duration: Long = 1000)
