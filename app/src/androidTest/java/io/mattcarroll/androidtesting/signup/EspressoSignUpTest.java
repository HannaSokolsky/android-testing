package io.mattcarroll.androidtesting.signup;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.rule.ActivityTestRule;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.hasToString;

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

    private static void scrollToandTapNext(){
       onView(withId(R.id.button_next))   // create matcher
               .perform(scrollTo(),
                       click()
               );
    }

    private static void tapNext (){
        onView(withId(R.id.button_next)).perform(
                click()
        );
    }


    private void checkFieldHasError(int fieldId, int errorId){
       onView(withId(fieldId)).check(matches(hasErrorText(resources.getString(errorId))));
    }


    private static void pressBackOnActivity(){
        //hide keyboard
        closeSoftKeyboard();
        //press back button
        pressBack();
    }


    private static void scrollToAndFill(int fieldId, String textToType ){
            //  this is Example how to input any value from keyboard giton screen
//        EspressoKey underscore = new EspressoKey.Builder()
//                .withShiftPressed(true)
//                    .withKeyCode(KEYCODE_MINUS)
//                .build();

        onView(withId(fieldId)).perform(
                scrollTo(),
//                pressKey(underscore),
                typeText(textToType)
        );

    }

private void fillPersonalInfo(String fname, String lname, String address, String city,
                              String state, String zip  ){
    scrollToAndFill(R.id.edittext_first_name, fname );
    scrollToAndFill(R.id.edittext_last_name, lname);
    scrollToAndFill(R.id.edittext_address_line_1, address);
    scrollToAndFill(R.id.edittext_address_city, city);
    scrollToAndFill(R.id.edittext_address_state, state);
    scrollToAndFill(R.id.edittext_address_zip, zip);
    scrollToandTapNext();

}

private void selectInterest (String interest){
    closeSoftKeyboard();
    onView(withText(interest))
            .perform(
                    scrollTo(),
                    click());
}





    private void checkIfInterestIsSelected( String interest){
        onView(withText(interest)).check(matches(isChecked()));
    }
    private void checkIfInterestIsNotSelected( String interest){
        onView(withText(interest)).check(matches(isNotChecked()));
    }

    private static void clickItemOnTheListView(String viewName) {
        onData(hasToString(viewName))
                 .perform(click());
//   or
//        onData(hasToString(is("Astronomy")))
//                .inAdapterView(withId(R.id.listview_interests))
//                .check(matches(isNotChecked()));

    }

    private static void scrollToItemOnTheListView(String viewName) {
        onData(hasToString(viewName))
                .perform(scrollTo());
    }

    private void fillCredentials(String email, String password){
        scrollToAndFill(R.id.autocompletetextview_email, email);
        scrollToAndFill(R.id.edittext_password, password);
    }



@Test
public void userSignUpPositiveTest(){
    fillPersonalInfo("Hanna","Sokolsky", "777 Good way", "Polo Alto","CA" , "94040");
// Select interest on Activity
    clickItemOnTheListView("Astronomy");
    checkIfInterestIsSelected("Astronomy");
    tapNext ();

    pressBackOnActivity();
// Check if Interest is not selected
    scrollToItemOnTheListView("Astronomy");
    checkIfInterestIsNotSelected("Astronomy");
// Select interest  second time
    clickItemOnTheListView("Astronomy");
    tapNext ();

    fillCredentials("sokolsky@adb.com", "sokol" );
    tapNext ();
// Verify user successfully Signed Up
    onView(withText("Signing Up..."))
            .check(matches(isDisplayed()));

  //  onView(withText("Signup successful!")).check(matches(isDisplayed()));
    // on pop up    alertTitle

//    idlingResource.increment();
//    onView(withId(R.id.alertTitle))
//            .check(matches(isDisplayed()));
//
////        onView(withText("OK"))
////                .inRoot(isDialog())
////                .check(matches(isDisplayed()));
//
//    idlingResource.decrement();

}



   @Test
    public void userSignUpVerifyBackWorksOnEachPAge(){
        // Fill personal info


        scrollToAndFill(R.id.edittext_first_name, "Hanna");
        scrollToAndFill(R.id.edittext_last_name, "Sokolsky");
        scrollToAndFill(R.id.edittext_address_line_1, "777 Good way");
        scrollToAndFill(R.id.edittext_address_city, "Polo Alto");
        scrollToAndFill(R.id.edittext_address_state, "CA");
        scrollToAndFill(R.id.edittext_address_zip, "94040");
        scrollToandTapNext();

        // Select interest
        onView(withText("Chess"))
                .perform(click());

        //tap Next button
        tapNext ();

        // first press Back button
        pressBackOnActivity();
        onView(withText("Basketball"))
                .check(matches(isDisplayed()));
        //second press of back button
        pressBackOnActivity();
        onView(withId(R.id.edittext_first_name))
                .check(matches(isDisplayed()));


        boolean activityFinisshed = false;
        try{
            pressBackOnActivity();
        }
        catch (NoActivityResumedException e){
            activityFinisshed = true;
        }
            assertTrue(activityFinisshed);

    }

   @Test
    public void userSignUpPersonalInfoVerifyRequiredFieldsAreRequired (){
       scrollToandTapNext();
      checkFieldHasError(R.id.edittext_first_name, R.string.input_error_required);
      checkFieldHasError(R.id.edittext_last_name, R.string.input_error_required);
      checkFieldHasError(R.id.edittext_address_line_1, R.string.input_error_required);
      checkFieldHasError(R.id.edittext_address_city, R.string.input_error_required);
      checkFieldHasError(R.id.edittext_address_state, R.string.input_error_required);
      checkFieldHasError(R.id.edittext_address_zip, R.string.input_error_required);

   }



} //EndOf Class
