package com.leonett.epoxysample.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val contentViewId: Int

    val appContext: Context
        get() = applicationContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)

        initVars()
        initViews()
    }

    protected abstract fun initVars()

    protected abstract fun initViews()

    fun showToast(stringResId: Int, vararg objects: Any) {
        showToast(getString(stringResId, *objects))
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(stringResId: Int, vararg objects: Any) {
        showLongToast(getString(stringResId, *objects))
    }

    fun showLongToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    protected fun setOkResult() {
        setResult(Activity.RESULT_OK)
    }

    protected fun setOkResultWithData(args: Bundle) {
        val intent = Intent()
        intent.putExtras(args)

        setResult(Activity.RESULT_OK, intent)
    }

    protected fun sendOkResult() {
        setOkResult()
        finish()
    }

    protected fun sendOkResultWithData(args: Bundle) {
        setOkResultWithData(args)
        finish()
    }


    protected fun getArgument(key: String): Any? {
        return if (intent.extras != null) {
            intent.extras!!.get(key)
        } else null
    }

    protected fun getArgument(intent: Intent, key: String): Any? {
        return if (intent.extras != null) {
            intent.extras!!.get(key)
        } else null
    }
}