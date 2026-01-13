package com.ext.android_password_strengthmeter


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat

class PasswordStrengthMeter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    private var strength = 0 // 0-4
    private var showText = true
    private var barHeight = 20f
    private var barCornerRadius = 10f
    private var spacing = 4f

    // Colors
    private var weakColor = Color.parseColor("#FF3B30")
    private var fairColor = Color.parseColor("#FF9500")
    private var goodColor = Color.parseColor("#FFCC00")
    private var strongColor = Color.parseColor("#34C759")
    private var veryStrongColor = Color.parseColor("#007AFF")
    private var backgroundColor = Color.parseColor("#E5E5EA")

    private var targetEditText: EditText? = null
    private var editTextId: Int = -1

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PasswordStrengthMeter,
            0, 0
        ).apply {
            try {
                showText = getBoolean(R.styleable.PasswordStrengthMeter_showStrengthText, true)
                barHeight = getDimension(R.styleable.PasswordStrengthMeter_barHeight, 20f)
                barCornerRadius = getDimension(R.styleable.PasswordStrengthMeter_barCornerRadius, 10f)
                spacing = getDimension(R.styleable.PasswordStrengthMeter_barSpacing, 4f)

                weakColor = getColor(R.styleable.PasswordStrengthMeter_weakColor, weakColor)
                fairColor = getColor(R.styleable.PasswordStrengthMeter_fairColor, fairColor)
                goodColor = getColor(R.styleable.PasswordStrengthMeter_goodColor, goodColor)
                strongColor = getColor(R.styleable.PasswordStrengthMeter_strongColor, strongColor)
                veryStrongColor = getColor(R.styleable.PasswordStrengthMeter_veryStrongColor, veryStrongColor)
                backgroundColor = getColor(R.styleable.PasswordStrengthMeter_meterBackgroundColor, backgroundColor)

                editTextId = getResourceId(R.styleable.PasswordStrengthMeter_targetEditText, -1)
            } finally {
                recycle()
            }
        }

        backgroundPaint.color = backgroundColor
        backgroundPaint.style = Paint.Style.FILL

        textPaint.color = Color.BLACK
        textPaint.textSize = 32f
        textPaint.textAlign = Paint.Align.CENTER
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (editTextId != -1) {
            post {
                val rootView = rootView
                targetEditText = rootView.findViewById(editTextId)
                targetEditText?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        updateStrength(s?.toString() ?: "")
                    }
                })
            }
        }
    }

    private fun updateStrength(password: String) {
        strength = calculatePasswordStrength(password)
        invalidate()
    }

    private fun calculatePasswordStrength(password: String): Int {
        if (password.isEmpty()) return 0

        var score = 0

        // Length check
        when {
            password.length >= 12 -> score += 2
            password.length >= 8 -> score += 1
        }

        // Contains lowercase
        if (password.any { it.isLowerCase() }) score++

        // Contains uppercase
        if (password.any { it.isUpperCase() }) score++

        // Contains digit
        if (password.any { it.isDigit() }) score++

        // Contains special character
        if (password.any { !it.isLetterOrDigit() }) score++

        // Map score to strength level (0-4)
        return when {
            score <= 2 -> 1  // Weak
            score <= 3 -> 2  // Fair
            score <= 4 -> 3  // Good
            score <= 5 -> 4  // Strong
            else -> 5        // Very Strong
        }.coerceIn(0, 5)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = (barHeight + (if (showText) 60f else 20f)).toInt()
        val height = resolveSize(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat() - paddingLeft - paddingRight
        val barWidth = (width - spacing * 4) / 5

        // Draw 5 bars
        for (i in 0 until 5) {
            val left = paddingLeft + (barWidth + spacing) * i
            val top = paddingTop.toFloat()
            val right = left + barWidth
            val bottom = top + barHeight

            rectF.set(left, top, right, bottom)

            if (i < strength) {
                paint.color = when (strength) {
                    1 -> weakColor
                    2 -> fairColor
                    3 -> goodColor
                    4 -> strongColor
                    5 -> veryStrongColor
                    else -> backgroundColor
                }
            } else {
                paint.color = backgroundColor
            }

            canvas.drawRoundRect(rectF, barCornerRadius, barCornerRadius, paint)
        }

        // Draw text
        if (showText && strength > 0) {
            val text = when (strength) {
                1 -> "Weak"
                2 -> "Fair"
                3 -> "Good"
                4 -> "Strong"
                5 -> "Very Strong"
                else -> ""
            }

            textPaint.color = when (strength) {
                1 -> weakColor
                2 -> fairColor
                3 -> goodColor
                4 -> strongColor
                5 -> veryStrongColor
                else -> Color.BLACK
            }

            val textY = paddingTop + barHeight + 45f
            canvas.drawText(text, width / 2 + paddingLeft, textY, textPaint)
        }
    }
}