package com.kromer.nytimes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kromer.nytimes.data.source.local.ArticlesDao
import com.kromer.nytimes.data.source.local.MyDatabase
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.presentation.ui.main.MainActivity
import com.kromer.nytimes.presentation.ui.main.list.ArticleViewHolder
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var dao: ArticlesDao
    private lateinit var db: MyDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java).build()
        dao = db.getArticlesDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertArticlesAndReadResult() {
        val article1 = Article(
            1,
            "Donald Trump’s “Stop the Steal” campaign recalls one of the most disastrous political lies of the 20th century.",
            "World War I (1914-18);Conspiracy Theories;Presidential Election of 2020;Hitler, Adolf;Trump, Donald J;Germany",
            "By Jochen Bittner",
            ArrayList(),
            "2020-11-30",
            "Opinion",
            "New York Times",
            "",
            "1918 Germany Has a Warning for America",
            "https://www.nytimes.com/2020/11/30/opinion/trump-conspiracy-germany-1918.html"
        )
        val article2 = Article(
            2,
            "A photographer said four men dismantled the mysterious shiny object that has captivated the country. On Tuesday, a local outdoorsman with a penchant for stunts claimed credit for the disappearance.",
            "Sculpture;Deserts;Federal Lands;Art;McCracken, John;Utah",
            "By Serge F. Kovaleski, Deborah Solomon and Zoe Rosenberg",
            ArrayList(),
            "2020-12-01",
            "Arts",
            "New York Times",
            "Art & Design",
            "Earthlings, It Seems, Not Aliens, Removed the Utah Monolith",
            "https://www.nytimes.com/2020/12/01/arts/design/utah-monolith-removed-instagram.html"
        )
        val articles = arrayListOf(article1, article2)
        runBlocking {
            dao.insert(articles)
            val result = dao.get(1)
            assertEquals(result?.section, "Opinion")
        }
    }

    @Test
    fun recyclerViewTest() {
        /* clicking the item */
        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<ArticleViewHolder>(
                0,
                click()
            )
        )

        /* showing details */
        onView(withId(R.id.details)).check(matches(isDisplayed()))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kromer.nytimes", appContext.packageName)
    }
}