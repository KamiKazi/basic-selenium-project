package config

import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.*


class TestStatusLogger : TestWatcher, BeforeEachCallback {

    private val ANSI_RESET = "\u001B[0m"

    private val ANSI_RED = "\u001B[31m"
    private val ANSI_GREEN = "\u001B[32m"
    private val ANSI_BLUE = "\u001B[34m"
    private fun color(string: String, ansi: String) = "$ansi$string$ANSI_RESET"

    override fun beforeEach(context: ExtensionContext?) {
        println(color(">>> test ${context!!.testMethod.get().annotations} started", ANSI_BLUE))
    }

    override fun testSuccessful(context: ExtensionContext?) {
        println(color("<<< test ${context!!.displayName} succeeded", ANSI_GREEN))
    }

    override fun testFailed(context: ExtensionContext?, cause: Throwable?) {
        println(color("<<< test ${context!!.displayName} failed", ANSI_RED))
    }

    override fun testDisabled(context: ExtensionContext?, reason: Optional<String>?) {
        println(color("<<< test ${context!!.displayName} disabled", ANSI_BLUE))
    }

    override fun testAborted(context: ExtensionContext?, cause: Throwable?) {
        println(color("<<< test ${context!!.displayName} aborted", ANSI_BLUE))
    }
}