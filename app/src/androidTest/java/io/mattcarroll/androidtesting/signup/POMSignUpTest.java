package io.mattcarroll.androidtesting.signup;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.squareup.spoon.*;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import io.mattcarroll.androidtesting.BaseTest;
import io.mattcarroll.androidtesting.PageObjects.CredentialsPage;
import io.mattcarroll.androidtesting.PageObjects.InterestsPage;
import io.mattcarroll.androidtesting.PageObjects.PersonalInfoPage;
import io.mattcarroll.androidtesting.R;
import io.mattcarroll.androidtesting.usersession.UserSession;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by anna on 4/10/18.
 */

public class POMSignUpTest extends BaseTest {


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


    @After
    public void teardown (){

        UserSession.getInstance().logout();
    }


    public FailureHandler failureHandler = new FailureHandler() {
        @Override
        public void handle(Throwable throwable, Matcher<View> matcher) {
            Spoon.screenshot(activityRule.getActivity() , "ERROR");
        }
    };





    @Test
    public void screenFailuresTest (){
        onView (withId(R.id.edittext_bank_name))
                .withFailureHandler(failureHandler)
                .perform(click());

    }








@Test
    public void  userSighUpHappyPathPO (){


   Spoon.screenshot(activityRule.getActivity(),  "initial-state");


    new PersonalInfoPage()
            .firstName(getProperties().getProperty("name"))
            .lastName(getProperties().getProperty("lastName"))
            .address1(getProperties().getProperty("address1"))
            .city(getProperties().getProperty("city"))
            .state(getProperties().getProperty("state"))
            .zipcode(getProperties().getProperty("zipcode"))
            .submitAndExpectInterestsPage();

   Spoon.screenshot(activityRule.getActivity(),  "final-state");
}


@Test
    public void userSignUpPersonalInfoVerifyRequiredFieldsAreRequiredPO() {

     final String  REQUIRED_FIELD_ERROR = resources.getString(R.string.input_error_required);

    PersonalInfoPage personalInfoPage = new PersonalInfoPage();
    personalInfoPage .submit();
    personalInfoPage.assertFirstNameHasError(REQUIRED_FIELD_ERROR)
                    .assertLastNameHasError(REQUIRED_FIELD_ERROR)
                    .assertAddressHasError(REQUIRED_FIELD_ERROR)
                    .assertCityHasError(REQUIRED_FIELD_ERROR)
                    .assertStateHasError(REQUIRED_FIELD_ERROR)
                    .assertZipHasError(REQUIRED_FIELD_ERROR);
    }



@Test
    public void userSignUpCredentialsVerifyRequiredFieldsAreRequiredPO() {

    final String REQUIRED_FIELD_ERROR = resources.getString(R.string.input_error_required);

    CredentialsPage credentialsPage = new PersonalInfoPage()
                 .firstName(getProperties().getProperty("name"))
                 .lastName(getProperties().getProperty("lastName"))
                 .address1(getProperties().getProperty("address1"))
                 .city(getProperties().getProperty("city"))
                 .state(getProperties().getProperty("state"))
                 .zipcode(getProperties().getProperty("zipcode"))
                 .submitAndExpectInterestsPage()
                  .selectInterests("Chess", "Astronomy")
                 .tapNextAndExpectCredentialsPage();
        credentialsPage.submit ();
        credentialsPage.asserHasUserNameError(REQUIRED_FIELD_ERROR);

    }




@Test
    public void checkAstronomyPO (){

    PersonalInfoPage personalInfoPage = new PersonalInfoPage()
            .firstName(getProperties().getProperty("name"))
            .lastName(getProperties().getProperty("lastName"))
            .address1(getProperties().getProperty("address1"))
            .city(getProperties().getProperty("city"))
            .state(getProperties().getProperty("state"))
            .zipcode(getProperties().getProperty("zipcode"));
    InterestsPage interestsPage = personalInfoPage.submitAndExpectInterestsPage();
    interestsPage.selectInterests("Astronomy");
    CredentialsPage credentialsPage = interestsPage.tapNextAndExpectCredentialsPage();
    InterestsPage interestsPage1 = credentialsPage.pressBackButton();
    interestsPage1.scrollToItemOnTheListView("Astronomy")
                  .checkIfInterestIsNotSelected("Astronomy");

}






    }
