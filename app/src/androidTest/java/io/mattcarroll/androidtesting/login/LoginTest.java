package io.mattcarroll.androidtesting.login;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.R;
import io.mattcarroll.androidtesting.SplashActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/7/18.
 */

public class LoginTest {

    @Rule
    public final ActivityTestRule<SplashActivity> activityRule =
             new ActivityTestRule<>(SplashActivity.class, false , true);


    @Test
    public void userIsAbleToLogInSuccessfully(){

        onView(withId(R.id.edittext_email))
                .perform(typeText("adb@adb"));
        onView(withId(R.id.edittext_password))
                .perform(typeText("password"));
        onView(withId(R.id.button_sign_in))
                .perform(scrollTo())
                .perform(click());
        onView(withId(R.id.textview_no_accounts))
                .check(matches(isDisplayed()));

    }

    @Test
    public void userSignInVerifyRequiredFielsAreRequired(){
        onView(withId(R.id.button_sign_in))
                .perform(scrollTo())
                .perform(click());
        onView(withId(R.id.edittext_email))
                .check(matches(hasErrorText("This field is required")));

    }



}
