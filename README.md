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

![180903315-24c6da6e-c358-41ea-9365-6f96d0354818](https://user-images.githubusercontent.com/16589390/186828755-8cf7444e-8c6b-45c6-8aeb-83b67a4667f3.png)
![2022-07-28_102757](https://user-images.githubusercontent.com/16589390/181407142-6a1b08df-c4f0-4f1d-be81-8110e9f8f4b1.png)

# XML

app:borderWidth 圆环宽度<br>
app:stepGroundColor圆环底部颜色<br>
app:progressColor圆环进度颜色<br>
app:maxStep最大进度（步数）<br>
app:currentStep当前进度（步数）<br>
app:stepTextSize文字字体大小<br>
app:stepTextColor文字颜色<br>
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
