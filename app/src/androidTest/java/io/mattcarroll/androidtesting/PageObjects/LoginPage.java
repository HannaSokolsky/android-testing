package io.mattcarroll.androidtesting.PageObjects;

import android.support.annotation.NonNull;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/11/18.
 */

public class LoginPage {

    public LoginPage() {
        // verify on the correct page
        onView(withId(R.id.button_sign_in))
                .check(matches(isDisplayed()));
    }


    public  LoginPage  typeEmail (@NonNull String firstName) {
        onView(withId(R.id.edittext_email))
                .perform(scrollTo(),
                        typeText(firstName));
        return this;
    }

    public  LoginPage  typePassword (@NonNull String password) {
        onView(withId(R.id.edittext_password))
                .perform(scrollTo(),
                        typeText(password));
        return this;
    }



    public HomeActivity  tapSignInAndExpectHomeActivity (){
        onView(withId(R.id.button_sign_in))
                .perform(click());
    return new HomeActivity();
    }



    public LoginPage tapSignIn(){
        onView(withId(R.id.button_sign_in))
                .perform(click());
return this;
    }

    public LoginPage assertEmailHasError (@NonNull String error){
       onView(withId(R.id.edittext_email))
                .check(matches(hasErrorText(error)));

        return this;
    }

}
