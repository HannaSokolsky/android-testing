package io.mattcarroll.androidtesting.login;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mattcarroll.androidtesting.BaseTest;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by anna on 4/7/18.
 */

public class LoginTest extends BaseTest{

    @Rule
    public final ActivityTestRule<SplashActivity> activityRule =
             new ActivityTestRule<>(SplashActivity.class, false , true);


    public void typeIntoTextField (int idElement, String text){
        onView(withId(idElement))
                .perform(
                        //scrollTo(),
                        typeText(text));
  }

  public void scrollAndClickElement( int idElement){
      onView(withId(idElement))
             // .perform(scrollTo())
              .perform(click());
  }

    public void verifyIsElementPresentOnActivity(int idElement){
        onView(withId(idElement)) //  R.id.textview_no_accounts
                .check(matches(isDisplayed()));
    }

    public void verifyActivityIsCorrect(String activity){
        onView(withText(activity))
                .check(matches(isDisplayed()));
    }

    // Custom methods

    public void tapSignInButton(){
        scrollAndClickElement(R.id.button_sign_in);
    }

    public void tapFloatingActionButton(){
        scrollAndClickElement(R.id.fab_manage_accounts);
    }
    public void tapLinkAccountBatton(){
        scrollAndClickElement(R.id.button_link_account);
    }

    @Before
    public void loginToAccount (){
// login to app
        typeIntoTextField(R.id.edittext_email, getProperties().getProperty("email"));
        typeIntoTextField(R.id.edittext_password, getProperties().getProperty("password"));
        tapSignInButton();  // fab_manage_accounts
        verifyIsElementPresentOnActivity(R.id.fab_manage_accounts);
        verifyActivityIsCorrect("HomeActivity");
    }


    @Test
    public void userIsAbleToAddBankingAccount (){
   // on Home View
        tapFloatingActionButton();
   // on Android Testing
        tapLinkAccountBatton();
        verifyActivityIsCorrect("Android Testing");
   //  Enter Bank Account info
        typeIntoTextField(R.id.edittext_bank_name, getProperties().getProperty("bankName"));
        typeIntoTextField(R.id.edittext_account_number, getProperties().getProperty("accountNumber"));
        typeIntoTextField(R.id.edittext_password, getProperties().getProperty("passwordForBankAccount"));

        tapLinkAccountBatton();

    }


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
