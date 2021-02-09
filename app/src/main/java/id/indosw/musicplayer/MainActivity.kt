package id.indosw.musicplayer

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jem.liquidswipe.LiquidSwipeViewPager
import id.indosw.musicplayer.app.activities.ListLaguActivity
import id.indosw.musicplayer.onboarding.CustomFragmentPagerAdapter
import id.indosw.musicplayer.onboarding.titleArray

class MainActivity : AppCompatActivity() {

    private var viewpager: LiquidSwipeViewPager? = null

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set Transparent Statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            ListLaguActivity.setWindowFlag(
                this,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                false
            )
            window.statusBarColor = Color.TRANSPARENT
        }

        viewpager = findViewById(R.id.viewpager)
        viewpager!!.adapter = CustomFragmentPagerAdapter(supportFragmentManager)

        /*
        // The following code can be used to dynamically change the waveCenterY
        // based on the touch position (on the LiquidSwipeViewPager).

        // The reason this isn't done internally is because,
        // sometimes the viewpager layouts don't get the touch events
        // when said touch events are consumed directly by the viewpager.

        // Create an array of LiquidSwipeCPP, one for each layout in the PagerAdapter
        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
            LiquidSwipeClipPathProvider()
        }

        // Similar logic can also be applied for your custom FragmentPagerAdapter/FragmentStatePagerAdapter
        viewpager.adapter = CustomPagerAdapter(this, liquidSwipeClipPathProviders)

        // Listen to onTouch events on the viewpager and update the waveCenterY value of the LiquidSwipeCPPs
        viewpager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }
        */

        // Note that this is not a perfect solution,
        // in fact some artifacts might occur due to the quick waveCenterY value jumps.
        // But for now, this is the cleanest solution I can think of.
        // Anyone else with a better solution is welcome to fork and submit a pull request. :)

        // Create 20 times the number of actual pages, and start in the middle.
        // This way users can swipe left or right from the start.
        // Definitely not a good idea for production, but good enough for a demo app.
        viewpager!!.setCurrentItem(titleArray.count() * 10, false)
    }
}