package com.rifqimfahmi.alldogbreeds.ui.base

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

interface MvpPresenter<V : MvpView> {

    fun onAttach(view: V)
    fun onDetach()
    fun requestThisPermissions(activity: BaseActivity, requestCode: Int, permissions: Array<String>): Boolean

}