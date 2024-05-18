package khamroev001.e_learn.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import khamroev001.e_learn.R

class BottomDialogLogout(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.logout_bottom_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.DialogAnimation)
        window?.attributes?.apply {
            dimAmount = 0.6f
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        }
        var api=API.newInstance(context)
        findViewById<Button>(R.id.no).setOnClickListener {


            api.setDialog("no")
            // Perform logout action here
            dismiss()
        }
        findViewById<Button>(R.id.yes).setOnClickListener {

            api.setDialog("yes")

            dismiss()
        }
    }
}
