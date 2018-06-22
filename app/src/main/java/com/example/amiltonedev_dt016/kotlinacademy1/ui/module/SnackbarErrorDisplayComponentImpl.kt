package com.example.amiltonedev_dt016.kotlinacademy1.ui.module

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import com.example.amiltonedev_dt016.kotlinacademy1.R


class SnackbarErrorDisplayComponentImpl(private val context: Context) : ErrorDisplayComponent {
    override fun displayError(errorMessage: String, view: View?) {
        if (view != null) {
            val snackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view

            val textView = snackbarView.findViewById(R.id.snackbar_text) as TextView

            snackbarView.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
            textView.textAlignment = TEXT_ALIGNMENT_CENTER

            snackbar.show()
        }
    }

}