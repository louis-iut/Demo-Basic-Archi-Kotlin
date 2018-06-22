package com.example.amiltonedev_dt016.kotlinacademy1.ui.module

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity

class LoadingComponentImpl(private val activity: AppCompatActivity?): LoadingComponent {

    private var currentDialog: AlertDialog? = null

    override fun showLoading() {
        if (currentDialog == null || currentDialog?.isShowing == false) {
            currentDialog = AlertDialog.Builder(activity).setTitle("Loading")
                    .setMessage("Loading data...").setCancelable(false).create()
            currentDialog?.show()
        }
    }

    override fun hideLoading() {
        currentDialog?.dismiss()
    }
}