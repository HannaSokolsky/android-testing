package io.mattcarroll.androidtesting.PageObjects;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by anna on 4/10/18.
 */

public class InterestsPage {

    public InterestsPage() {
        // verify on the correct page
        onView(withText("Chess"))
                .check(matches(isDisplayed()));

    }



}
