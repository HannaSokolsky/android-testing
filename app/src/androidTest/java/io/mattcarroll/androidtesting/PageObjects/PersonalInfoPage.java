package io.mattcarroll.androidtesting.PageObjects;

import android.support.annotation.NonNull;

import io.mattcarroll.androidtesting.BaseTest;
import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/10/18.
 */

public class PersonalInfoPage   extends BaseTest {

    public PersonalInfoPage(){
   // verify on the correct page
        onView(withId(R.id.edittext_first_name))
                .check(matches(isDisplayed()));
    }


public PersonalInfoPage firstName (@NonNull String firstName){
    onView(withId(R.id.edittext_first_name))
            .perform(scrollTo(),
                    typeText(firstName));
    return this;
}

    public PersonalInfoPage lastName (@NonNull String lastName){
        onView(withId(R.id.edittext_last_name))
                .perform(scrollTo(),
                        typeText(lastName));
        return this;
    }

    public PersonalInfoPage address1 (@NonNull String address){
        onView(withId(R.id.edittext_address_line_1))
                .perform(scrollTo(),
                        typeText(address));
        return this;
    }

    public PersonalInfoPage city (@NonNull String city){
        onView(withId(R.id.edittext_address_city))
                .perform(scrollTo(),
                        typeText(city));
        return this;
    }


    public PersonalInfoPage state (@NonNull String state){
        onView(withId(R.id.edittext_address_state))
                .perform(scrollTo(),
                        typeText(state));
        return this;
    }

    public PersonalInfoPage zipcode (@NonNull String zip){
        onView(withId(R.id.edittext_address_zip))
                .perform(scrollTo(),
                        typeText(zip));
        return this;
    }



     public InterestsPage submitAndExpectInterestsPage(){
         onView(withId(R.id.button_next))
                 .perform(scrollTo(),
                         click()
                 );
         return new InterestsPage();
     }

    public void submit(){
        onView(withId(R.id.button_next))
                .perform(scrollTo(),
                        click()
                );

    }


     public void back (){
     pressBack();
     }



     public PersonalInfoPage assertFirstNameHasError (@NonNull String error){
         onView(withId(R.id.edittext_first_name))
                 .check(matches(hasErrorText(error)));
         return this;
     }

    public PersonalInfoPage assertLastNameHasError (@NonNull String error){
        onView(withId(R.id.edittext_last_name))
                .check(matches(hasErrorText(error)));
        return this;
    }

    public PersonalInfoPage assertAddressHasError (@NonNull String error){
        onView(withId(R.id.edittext_address_line_1))
                .check(matches(hasErrorText(error)));
        return this;
    }

    public PersonalInfoPage assertCityHasError (@NonNull String error){
        onView(withId(R.id.edittext_address_city))
                .check(matches(hasErrorText(error)));
        return this;
    }

    public PersonalInfoPage assertStateHasError (@NonNull String error){
        onView(withId(R.id.edittext_address_state))
                .check(matches(hasErrorText(error)));
        return this;
    }

    public PersonalInfoPage assertZipHasError (@NonNull String error){
        onView(withId(R.id.edittext_address_zip))
                .check(matches(hasErrorText(error)));
        return this;
    }








}
