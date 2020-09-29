<<<<<<< HEAD:src/androidTest/java/com/example/sortingapp/ExampleInstrumentedTest.java
package com.example.sortingapp;
=======
package com.example.usage;
>>>>>>> f4bf8bf4ddac0cce6586beaf5deb0b0e087e29a6:src/androidTest/java/com/example/usage/ExampleInstrumentedTest.java

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.test", appContext.getPackageName());
    }
}