package com.rifqimfahmi.alldogbreeds.data

import com.rifqimfahmi.alldogbreeds.data.db.DbHelper
import com.rifqimfahmi.alldogbreeds.data.network.ApiHelper
import com.rifqimfahmi.alldogbreeds.data.prefs.PreferencesHelper

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */


interface DataManager : PreferencesHelper, ApiHelper, DbHelper {

}