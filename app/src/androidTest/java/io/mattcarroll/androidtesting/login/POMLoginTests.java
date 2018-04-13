package io.mattcarroll.androidtesting.login;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.BaseTest;
import io.mattcarroll.androidtesting.PageObjects.HomeActivity;
import io.mattcarroll.androidtesting.PageObjects.LoginPage;
import io.mattcarroll.androidtesting.R;
import io.mattcarroll.androidtesting.SplashActivity;
import io.mattcarroll.androidtesting.signup.SignUpActivity;
import io.mattcarroll.androidtesting.usersession.UserSession;

/**
 * Created by anna on 4/11/18.
 */

public class POMLoginTests extends BaseTest{



    @Rule
    public final ActivityTestRule<SplashActivity> activityRule =
            new ActivityTestRule<>(SplashActivity.class, false, true);

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
    public void userIsAbleToLogInSuccessfully (){

       new LoginPage()
            .typeEmail( getProperties().getProperty("email"))
            .typePassword( getProperties().getProperty("password"))
            .tapSignInAndExpectHomeActivity();

}


    @Test
    public void userSignInVerifyRequiredFielsAreRequired(){
        final String  REQUIRED_FIELD_ERROR = resources.getString(R.string.error_field_required);

        new LoginPage()
                .tapSignIn()
                .assertEmailHasError(REQUIRED_FIELD_ERROR);

    }



    @Test
    public void userIsAbleToAddBankingAccount (){

        new LoginPage()
                .typeEmail( getProperties().getProperty("email"))
                .typePassword( getProperties().getProperty("password"))
                .tapSignInAndExpectHomeActivity()
                .tapFloatingPointButton()
                .tapLinkAccountButton ()
                        .typeBankName(getProperties().getProperty("bankName"))
                        .typeAccountNumber( getProperties().getProperty("accountNumber"))
                        .typeBankPassword(getProperties().getProperty("passwordForBankAccount"))
                        .clickLinkAccountButton()
                .goBackToOverViewScreen()
                .verifyLastForDigits(getProperties().getProperty("accountNumber"));




    }


}
