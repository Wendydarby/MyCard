package com.wendydarby.mycard.view;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.wendydarby.mycard.R;
import com.wendydarby.mycard.view.MyCardView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyCardViewContactEmailTest {

    @Rule
    public ActivityTestRule<MyCardView> mActivityTestRule = new ActivityTestRule<>(MyCardView.class);

    @Test
    public void myCardViewContactEmailTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(ViewMatchers.withId(R.id.send),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());

        onView(allOf(withId(R.id.newcontactname),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));

        onView(withId(R.id.newcontactname)).perform(replaceText("Tester3"), closeSoftKeyboard());

        onView(allOf(withId(R.id.contact_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_email)).perform(click());

        onView(
                allOf(withId(R.id.contact_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_email)).perform(replaceText("tester3@tespreso.net"), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.contact_event),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_event)).perform(replaceText("Launch your brand network event."), closeSoftKeyboard());

        onView(
                allOf(withId(R.id.sendcontact),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
//        onView(withId(R.id.sendcontact)).perform(click());

//        onView(
//                allOf(withId(android.R.id.title), withText("Share with"),
//                        childAtPosition(
//                                childAtPosition(
//                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
//                                        0),
//                                0),
//                        isDisplayed()));
//        onView(withId(R.id.title)).check(matches(withText("Share with")));

        /*
        onView(
                allOf(withText("Tester3"),
                        childAtPosition(
                                allOf(withId(R.id.newcontactname),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        onView(withId(R.id.title)).check(matches(withText("Tester3")));

        onView(
                allOf(withText("tester3@tespreso.net"),
                        childAtPosition(
                                allOf(withId(R.id.contact_email),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_email)).check(matches(withText("tester3@tespreso.net")));

        ViewInteraction editText3 = onView(
                allOf(withText("Met at Launch your brand network event.. Sent them my ecard using MyCard."),
                        childAtPosition(
                                allOf(withId(R.id.contact_event),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("Met at Launch your brand network event.. Sent them my ecard using MyCard.")));
        */

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
