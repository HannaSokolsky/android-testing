package io.mattcarroll.androidtesting.signup;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/7/18.
 */

public class EspressoSignUpTest {

@Rule
    public final ActivityTestRule<SignUpActivity> activityRule =
        new ActivityTestRule<>(SignUpActivity.class, false, true);

   private Resources resources;

   @Before
   public void setup() {
      // getTargetContext() operates on the application under test
      // getContext() operates on the test APK context
      resources = InstrumentationRegistry.getTargetContext().getResources();
   }





   @Test
    public void userSignUpPersonalInfoVerifyRequiredFieldsAreRequired (){
       onView(withId(R.id.button_next))   // create matcher
               .perform(scrollTo())
               .perform(click());

       onView(withId(R.id.edittext_first_name))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));
       onView(withId(R.id.edittext_last_name))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));
       onView(withId(R.id.edittext_address_line_1))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));
       onView(withId(R.id.edittext_address_city))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));
       onView(withId(R.id.edittext_address_state))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));
       onView(withId(R.id.edittext_address_zip))
               .check(matches(hasErrorText(resources.getString(R.string.input_error_required))));


   }



} //EndOf Class
