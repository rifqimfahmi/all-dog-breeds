package com.rifqimfahmi.alldogbreeds.ui.about

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import com.rifqimfahmi.alldogbreeds.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, AboutActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        title = "About"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var aboutString = getString(R.string.about_app)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_about_content.text = Html.fromHtml(aboutString, Html.FROM_HTML_MODE_COMPACT)
        } else {
            tv_about_content.text = Html.fromHtml(aboutString)
        }
        tv_about_content.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
