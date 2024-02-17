package pl.ejdev.dixittogether.features.core.shared

import android.util.Log

internal object LogWrapper {
    val logger: () -> Unit = {}

    inline infix fun <reified T : Any> T.get(fn: () -> Unit): Logger {
        fn()
        val caller: String = T::class.java.simpleName
        return Logger(caller)
    }
}

internal data class Logger(val tag: String) {
    fun info(msg: Any) = Log.i(tag, "$msg")
    fun warn(msg: Any) = Log.w(tag, "$msg")
    fun error(msg: Any) = Log.e(tag, "$msg")
}
