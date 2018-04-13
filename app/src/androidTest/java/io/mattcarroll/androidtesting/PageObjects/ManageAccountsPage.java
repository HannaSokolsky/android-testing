package io.mattcarroll.androidtesting.PageObjects;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/13/18.
 */

public class ManageAccountsPage {

    public ManageAccountsPage() {

        onView(withId (R.id.button_link_account))
                .check(matches(isDisplayed()));
    }

    public LinkAccountPage  tapLinkAccountButton (){
        onView(withId(R.id.button_link_account))
                .perform(click());

  return new LinkAccountPage();
    }




}
