package com.rifqimfahmi.alldogbreeds.util

import android.support.v4.app.FragmentManager
import com.rifqimfahmi.alldogbreeds.dialog.CommonLoadingDialog

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class CommonUtils {
    companion object {
        fun showLoadingDialog(fm: FragmentManager, message: String?): CommonLoadingDialog {
            val commonDialog: CommonLoadingDialog = CommonLoadingDialog.newInstance(message)
            commonDialog.show(fm, CommonLoadingDialog.TAG)
            return commonDialog
        }

        fun uppercaseTheFirstLetter(string: String): String {
            return "${string.substring(0,1).toUpperCase()}${string.substring(1)}"
        }

        fun getBreedFromLink(link: String): String {
            var modLink = link.substring(24)
            var lastPathIndex = modLink.indexOf("/")

            modLink = modLink.substring(0, lastPathIndex)
            return modLink
        }
    }
}