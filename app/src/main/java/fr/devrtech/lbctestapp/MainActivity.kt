package fr.devrtech.lbctestapp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import fr.devrtech.lbctestapp.ui.main.MainFragment

/**
 * Main Activity (Launcher Activity)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout_global, MainFragment.newInstance()).commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the nav_drawer; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
