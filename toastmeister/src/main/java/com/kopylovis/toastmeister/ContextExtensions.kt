package com.kopylovis.toastmeister

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.kopylovis.toastmeister.ToastMeister.TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT
import com.kopylovis.toastmeister.ToastMeister.TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT
import com.kopylovis.toastmeister.ToastMeister.TOASTERMEISTER_ICON_SIZE_DEFAULT
import com.kopylovis.toastmeister.ToastMeister.TOASTERMEISTER_TEXT_SIZE_DEFAULT

fun Context.errorToast(@StringRes message: Int, duration: Int, gravity: Int): Toast {
    return errorToast(getString(message), duration, gravity)
}

fun Context.errorToast(message: String, duration: Int, gravity: Int): Toast {
    return toastUtils(
        this, message, duration, gravity, TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT,
        this.getColor(R.color.toastmeister_red), TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT, this.getColor(R.color.white), TOASTERMEISTER_TEXT_SIZE_DEFAULT,
        ContextCompat.getDrawable(this, R.drawable.ic_baseline_block_24), this.getColor(R.color.white), TOASTERMEISTER_ICON_SIZE_DEFAULT, TOASTERMEISTER_ICON_SIZE_DEFAULT
    )
}

fun Context.infoToast(@StringRes message: Int, duration: Int, gravity: Int): Toast {
    return infoToast(getString(message), duration, gravity)
}

fun Context.infoToast(message: String, duration: Int, gravity: Int): Toast {
    return toastUtils(
        this, message, duration, gravity, TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT,
        this.getColor(R.color.toastmeister_yellow), TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT, this.getColor(R.color.white), TOASTERMEISTER_TEXT_SIZE_DEFAULT,
        ContextCompat.getDrawable(this, R.drawable.ic_baseline_info_24), this.getColor(R.color.white), TOASTERMEISTER_ICON_SIZE_DEFAULT, TOASTERMEISTER_ICON_SIZE_DEFAULT
    )
}

fun Context.successToast(@StringRes message: Int, duration: Int, gravity: Int): Toast {
    return successToast(getString(message), duration, gravity)
}

fun Context.successToast(message: String, duration: Int, gravity: Int): Toast {
    return toastUtils(
        this, message, duration, gravity, TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT,
        this.getColor(R.color.toastmeister_green), TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT, this.getColor(R.color.white), TOASTERMEISTER_TEXT_SIZE_DEFAULT,
        ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_circle_24), this.getColor(R.color.white), TOASTERMEISTER_ICON_SIZE_DEFAULT, TOASTERMEISTER_ICON_SIZE_DEFAULT
    )
}

fun Context.normalToast(@StringRes message: Int, duration: Int, gravity: Int): Toast {
    return normalToast(getString(message), duration, gravity)
}

fun Context.normalToast(message: String, duration: Int, gravity: Int): Toast {
    return toastUtils(
        this, message, duration, gravity, TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT,
        this.getColor(R.color.toastmeister_blue), TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT, this.getColor(R.color.white), TOASTERMEISTER_TEXT_SIZE_DEFAULT,
        ContextCompat.getDrawable(this, R.drawable.ic_baseline_stars_24), this.getColor(R.color.white), TOASTERMEISTER_ICON_SIZE_DEFAULT, TOASTERMEISTER_ICON_SIZE_DEFAULT
    )
}

fun Context.customToast(
    message: String,
    duration: Int,
    gravity: Int,
    offset: Int? = null,
    backgroundColor: Int,
    backgroundCornerRadius: Int? = null,
    textColor: Int,
    textSize: Int? = null,
    icon: Drawable?,
    iconColor: Int,
    iconWidth: Int? = null,
    iconHeight: Int? = null
): Toast {
    return toastUtils(
        this, message, duration, gravity, offset,
        backgroundColor, backgroundCornerRadius, textColor, textSize,
        icon, iconColor, iconWidth, iconHeight
    )
}

fun Context.customToast(
    message: Int,
    duration: Int,
    gravity: Int,
    offset: Int? = null,
    backgroundColor: Int,
    backgroundCornerRadius: Int? = null,
    textColor: Int,
    textSize: Int? = null,
    icon: Drawable?,
    iconColor: Int,
    iconWidth: Int? = null,
    iconHeight: Int? = null
): Toast {
    return toastUtils(
        this, getString(message), duration, gravity, offset,
        backgroundColor, backgroundCornerRadius, textColor, textSize,
        icon, iconColor, iconWidth, iconHeight
    )
}

private var currentToast: Toast? = null

@SuppressLint("InflateParams")
private fun toastUtils(
    context: Context,
    message: CharSequence,
    duration: Int,
    gravity: Int,
    offset: Int?,
    backgroundColor: Int,
    backgroundCornerRadius: Int?,
    textColor: Int,
    textSize: Int?,
    icon: Drawable?,
    iconColor: Int,
    iconWidth: Int?,
    iconHeight: Int?
): Toast {
    val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = layoutInflater.inflate(
        R.layout.view_custom_toastmeister, null
    )
    val imageView = layout.findViewById<ImageView>(R.id.vctImageViewIcon)
    val textView = layout.findViewById<TextView>(R.id.vctTextViewText)
    val cardView = layout.findViewById<CardView>(R.id.vctCardViewBackground)
    val customToast = Toast(context)
    textView.apply {
        text = message
        setTextColor(textColor)
        val size = textSize ?: TOASTERMEISTER_TEXT_SIZE_DEFAULT
        setTextSize(size.toFloat())
    }
    cardView.apply {
        radius = context.convertDpToPixelsFloat(backgroundCornerRadius ?: TOASTERMEISTER_CARDVIEW_CORNER_RADIUS_DEFAULT)
        setCardBackgroundColor(backgroundColor)
    }
    imageView.apply {
        setImageDrawable(icon)
        setColorFilter(iconColor)
        val params = this.layoutParams
        params.width = context.convertDpToPixelsInt(iconWidth ?: TOASTERMEISTER_ICON_SIZE_DEFAULT)
        params.height = context.convertDpToPixelsInt(iconHeight ?: TOASTERMEISTER_ICON_SIZE_DEFAULT)
        this.layoutParams = params
    }
    customToast.apply {
        val gravityOffset = if (gravity == ToastMeister.GRAVITY_TOP) Gravity.TOP else Gravity.BOTTOM
        setGravity(gravityOffset, 0, context.convertDpToPixelsInt(offset ?: TOASTERMEISTER_GRAVITY_OFFSET_DEFAULT))
        this.duration = duration
        view = layout
        currentToast?.cancel()
        currentToast = customToast
    }
    return customToast
}


private fun Context.convertDpToPixelsInt(dp: Int): Int {
    val metrics = resources.displayMetrics
    return (dp.toFloat() * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

private fun Context.convertDpToPixelsFloat(dp: Int): Float {
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

private fun Context.convertPixelsToDp(pixels: Int): Int {
    val metrics = resources.displayMetrics
    return ((pixels * DisplayMetrics.DENSITY_DEFAULT) / metrics.densityDpi.toFloat()).toInt()
}

private fun Context.convertPixelsToDp(pixels: Float): Float {
    val metrics = resources.displayMetrics
    return (pixels * DisplayMetrics.DENSITY_DEFAULT) / metrics.densityDpi.toFloat()
}