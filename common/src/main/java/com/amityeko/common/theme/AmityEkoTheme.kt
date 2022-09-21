package com.amityeko.common.theme

import kotlin.properties.Delegates

object AmityEkoTheme {
    var colorPrimary by Delegates.notNull<Int>()

    fun setup(colorPrimary: Int) {
        this.colorPrimary = colorPrimary
    }

}