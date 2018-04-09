package io.mattcarroll.androidtesting.signup;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.action.EspressoKey;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.view.KeyEvent.KEYCODE_MINUS;
import static junit.framework.Assert.assertTrue;

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


    private static void pressBackOnactivity(){
        //hide keyboard
        closeSoftKeyboard();
        //press back button
        pressBack();
    }


    private static void scrolLToAndFill( int fieldId,  String textToType ){
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

    @Test
    public void userSignUpVerifyBackWorksOnEachPAge(){
        // Fill personal info
        scrolLToAndFill(R.id.edittext_first_name, "Hanna");
        scrolLToAndFill(R.id.edittext_last_name, "Sokolsky");
        scrolLToAndFill(R.id.edittext_address_line_1, "777 Good way");
        scrolLToAndFill(R.id.edittext_address_city, "Polo Alto");
        scrolLToAndFill(R.id.edittext_address_state, "CA");
        scrolLToAndFill(R.id.edittext_address_zip, "94040");
        scrollToandTapNext();

        // Select interest
        onView(withText("Chess"))
                .perform(click());

        //tap Next button
        tapNext ();

        // first press Back button
        pressBackOnactivity();
        onView(withText("Basketball"))
                .check(matches(isDisplayed()));
        //second press of back button
        pressBackOnactivity();
        onView(withId(R.id.edittext_first_name))
                .check(matches(isDisplayed()));


        boolean activityFinisshed = false;
        try{
            pressBackOnactivity();
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
