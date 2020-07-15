package com.leonett.photofeed.ui.util

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.leonett.photofeed.R

object LoaderDialog {

    private var txtMessage: TextView? = null

    fun create(context: Context, message: String?, cancelable: Boolean): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(cancelable)
        dialog.setContentView(R.layout.dialog_loader)

        txtMessage = dialog.findViewById(R.id.txtLoader)

        message?.let {
            txtMessage?.text = message
            txtMessage?.visibility = View.VISIBLE
        } ?: run {
            txtMessage?.visibility = View.GONE
        }

        return dialog
    }

}