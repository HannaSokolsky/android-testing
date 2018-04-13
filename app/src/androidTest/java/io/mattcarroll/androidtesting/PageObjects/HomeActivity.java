package io.mattcarroll.androidtesting.PageObjects;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.util.Log;

import io.mattcarroll.androidtesting.BaseTest;
import io.mattcarroll.androidtesting.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.mattcarroll.androidtesting.R.id.fab_manage_accounts;
import static java.lang.System.getProperties;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by anna on 4/12/18.
 */

public class HomeActivity  extends BaseTest {

  //  String accountNumber ;

    public HomeActivity() {

        onView(withId(R.id.fab_manage_accounts))
                .check(matches(isDisplayed()));

     //   accountNumber = getProperties().getProperty("accountNumber");

      //  Log.d("info", "Account Number" + accountNumber);
    }


    public  ManageAccountsPage tapFloatingPointButton (){
        onView(withId(R.id.fab_manage_accounts))
                .perform(
                       // scrollTo(),  button_link_account
                        click());

        return new ManageAccountsPage() ;
    }



    public  void verifyLastForDigits( String  accountNumber ){   //String  accountNumber
        //Log.d("info", "Account Number" + getProperties().getProperty("accountNumber"));
      //  String accountNumber = getProperties().getProperty("accountNumber");

        String accountNumberLastfForDigits = accountNumber
                .substring(accountNumber.length()-4, accountNumber.length());
        Log.d("info", "Last 4 digits" + accountNumberLastfForDigits);
        onView(withId(R.id.recyclerview_accounts))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant( withText(accountNumberLastfForDigits))))
                .check(matches(allOf (
                        hasDescendant( allOf (
                                withId(R.id.textview_account_last_digits),
                                withText(accountNumberLastfForDigits)  )) )));
    }


}
