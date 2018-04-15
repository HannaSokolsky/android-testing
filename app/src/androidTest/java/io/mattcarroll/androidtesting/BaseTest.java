package io.mattcarroll.androidtesting;

import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.view.View;

import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by anna on 4/9/18.
 */

public class BaseTest {


    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    @Before
    public void baseSetUp() throws IOException {
        properties = new Properties();
// how to pass content of .properties file to  properties var
        AssetManager testAssetManager = InstrumentationRegistry.getContext().getAssets();
        AssetManager.AssetInputStream assetStream = (AssetManager.AssetInputStream) testAssetManager.open("user.properties");
        properties.load(assetStream);

    }






}
