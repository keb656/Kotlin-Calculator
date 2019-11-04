package com.example.calculator

import android.content.Context
import android.view.View

open class CustomDialog2(){

    /*
    custom dialog
    */
    fun AlertDialog2(context: Context, getdialogView: View){
        val builder = android.app.AlertDialog.Builder(context)
        val dialogView = getdialogView

        builder.setView(dialogView)
            .setPositiveButton("확인") { _, _ ->

            }.setNegativeButton("") { _, _ ->

            }
            .show()
    }



}