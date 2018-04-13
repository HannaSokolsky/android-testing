package io.mattcarroll.androidtesting.PageObjects;

import android.support.annotation.NonNull;

import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/13/18.
 */

public class LinkAccountPage {


    public LinkAccountPage() {
        onView(withId (R.id.edittext_bank_name))
                .check(matches(isDisplayed()
                ));
    }

    public LinkAccountPage typeBankName (@NonNull String bankName){
        onView(withId(R.id.edittext_bank_name))
                .perform( //scrollTo(),
                        typeText(bankName));
        return this;
    }


    public LinkAccountPage typeAccountNumber (@NonNull String number){
        onView(withId(R.id.edittext_account_number))
                .perform( //scrollTo(),
                        typeText(number));
        return this;
    }

    public LinkAccountPage typeBankPassword (@NonNull String password){
        onView(withId(R.id.edittext_password))
                .perform( //scrollTo(),
                        typeText(password));
        return this;
    }


    public LinkAccountPage clickLinkAccountButton (){
        onView(withId(R.id.button_link_account))
                .perform(
                        click());

        return this;
    }


    public HomeActivity goBackToOverViewScreen(){
        pressBack();
        return new HomeActivity();
    }




}
