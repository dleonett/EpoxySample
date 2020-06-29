package com.leonett.photofeed.ui.util

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.leonett.photofeed.R

class LoaderDialog {

    private var txtMessage: TextView? = null

    fun create(context: Context, message: String?): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
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