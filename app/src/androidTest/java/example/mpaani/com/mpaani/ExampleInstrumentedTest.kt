package example.mpaani.com.mpaani

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import example.mpaani.com.mpaani.ui.MainActivity
import org.junit.Rule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.widget.RecyclerView


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val ITEM_BELOW_THE_FOLD = 10

    @get:Rule
    public val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("example.mpaani.com.mpaani", appContext.packageName)
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rvPostList))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(ITEM_BELOW_THE_FOLD, click()))

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = mActivityRule.activity.resources.getString(R.string.app_name)
        System.err.print("Error sample")
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }
}
