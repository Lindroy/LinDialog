package com.lindroid.lindialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lindroid.lindialog_lib.CustomDialog
import com.lindroid.lindialog_lib.HintDialog
import com.lindroid.utils.shortToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHint.setOnClickListener(this)
        btnIOS.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when (v?.id) {
        //提示对话框
            R.id.btnHint -> showHintDialog()
            R.id.btnIOS -> showCustomDialog()
        }
    }

    private fun showHintDialog() {
        HintDialog.build(supportFragmentManager)
//                .setThemeStyle(R.style.HintDialogStyle)
                .setTitle(R.string.app_name)
                .setMessage("这是一个提示对话框")
                .setShowNeuButton(true)
                .setNeutralText("不确定")
                .setCanCancel(true)
                .setCanDismiss(false)
                .setPosClickListener {
                    shortToast("确定")
                    it.dismiss()
                }
                .setNegClickListener {
                    shortToast("取消")
                    it.dismiss()
                }
                .setNeuClickListener {
                    shortToast("不确定")
                    it.dismiss()
                }
                .show()
    }

    private fun showCustomDialog() {
        CustomDialog.build(supportFragmentManager)
                .setContentView(R.layout.dialog_ios)
                .setWidthScale(0.8F)
                .setAnimStyle(R.style.ScaleAnimationStyle)
                .setViewHandler { holder, dialog ->
                    holder.setText(R.id.tvTitle, "提示")
                    holder.setText(R.id.tvMessage, "这是一个仿ios风格的对话框")
                    holder.setOnClickListener(R.id.tvCancel, View.OnClickListener {
                        shortToast(R.string.cancel)
                        dialog.dismiss()
                    })
                    holder.setOnClickListener(R.id.tvConfirm, View.OnClickListener {
                        shortToast(R.string.confirm)
                        dialog.dismiss()
                    })
                }
                .show()
    }
}
