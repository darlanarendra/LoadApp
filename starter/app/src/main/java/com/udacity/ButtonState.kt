package com.udacity

import androidx.annotation.StringRes

/**
 *
 * @author Narendra Darla
 */
enum class ButtonState(@StringRes val text:Int) {
    LOADING(R.string.loading),
    DOWNLOADING(R.string.downloading),
    DOWNLOAD(R.string.download)
}