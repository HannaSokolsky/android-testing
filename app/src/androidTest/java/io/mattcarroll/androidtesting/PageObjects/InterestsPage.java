package io.mattcarroll.androidtesting.PageObjects;

import android.support.annotation.NonNull;


import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;

/**
 * Created by anna on 4/10/18.
 */

public class InterestsPage {

    public InterestsPage() {
        // verify on the correct page
        onView(withId(R.id.button_next))
                .check(matches(isDisplayed()));

    }

    public void submit(){
        onView(withId(R.id.button_next))
                .perform(
                        scrollTo(),
                        click()
                );

    }


    public  void  clickItemOnTheListView(String viewName) {
        onData(hasToString(viewName))
                .perform(click());

    }
    private void checkIfInterestIsSelected( String interest){
        onView(withText(interest)).check(matches(isChecked()));
    }


    public void checkIfInterestIsNotSelected( String interest){
        onView(withText(interest))
                .check(matches(isNotChecked()));
    }

    public  InterestsPage scrollToItemOnTheListView(String viewName) {
        onData(hasToString(viewName))
                .perform(scrollTo());
    return this;
    }


    public InterestsPage selectInterestOnListView (String interest){
    clickItemOnTheListView(interest);
    checkIfInterestIsSelected(interest);
    return this;
}

    public InterestsPage selectInterests (@NonNull String ... interests){
        for (String interest : interests){
            onData(is(interest))
                    .perform(click());

        }

        return this;
    }




    public CredentialsPage tapNextAndExpectCredentialsPage(){
    onView(withId(R.id.button_next))
            .perform(
                    //scrollTo(),
                    click()
            );
    return new CredentialsPage();
}

}
