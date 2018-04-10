package io.mattcarroll.androidtesting.signup;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.BaseTest;
import io.mattcarroll.androidtesting.PageObjects.InterestsPage;
import io.mattcarroll.androidtesting.PageObjects.PersonalInfoPage;
import io.mattcarroll.androidtesting.usersession.UserSession;

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



@Test
    public void  userSighUpHappyPath (){

    PersonalInfoPage personalInfoPage =  new PersonalInfoPage()
            .firstName(getProperties().getProperty("name"))
            .lastName(getProperties().getProperty("lastName"))
            .address1(getProperties().getProperty("address1"))
            .city(getProperties().getProperty("city"))
            .state(getProperties().getProperty("state"))
            .zipcode(getProperties().getProperty("zipcode"));
    InterestsPage interestsPage = personalInfoPage.tapOnNextButton();


}





    }
