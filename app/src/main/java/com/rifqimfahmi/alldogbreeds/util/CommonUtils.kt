package com.rifqimfahmi.alldogbreeds.util

import android.support.v4.app.FragmentManager
import com.rifqimfahmi.alldogbreeds.dialog.CommonLoadingDialog

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class CommonUtils {
    companion object {
        fun showLoadingDialog(fm: FragmentManager): CommonLoadingDialog {
            val commonDialog: CommonLoadingDialog = CommonLoadingDialog()
            commonDialog.show(fm, CommonLoadingDialog.TAG)
            return CommonLoadingDialog()
        }
    }
}