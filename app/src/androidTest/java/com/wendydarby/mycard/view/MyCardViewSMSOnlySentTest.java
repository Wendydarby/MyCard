package com.wendydarby.mycard.view;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.wendydarby.mycard.R;
import com.wendydarby.mycard.view.MyCardView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyCardViewSMSOnlySentTest {

    @Rule
    public ActivityTestRule<MyCardView> mActivityTestRule = new ActivityTestRule<>(MyCardView.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.SEND_SMS");

    @Test
    public void myCardViewSMSOnlySentTest() {
        ViewInteraction textView = onView(
                allOf(withText("MyCard"),
                        childAtPosition(
                                allOf(ViewMatchers.withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("MyCard")));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.send),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction webView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        1),
                        isDisplayed()));
        webView.check(matches(isDisplayed()));

       /* ViewInteraction webView2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        1),
                        isDisplayed()));
        webView2.check(matches(isDisplayed()));*/

       /* ViewInteraction webView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                1),
                        0),
                        isDisplayed()));
        webView.check(matches(isDisplayed()));

        ViewInteraction frameLayout = onView(
                allOf(withId(android.R.id.content),
                        childAtPosition(
                                allOf(withId(R.id.decor_content_parent),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));*/

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.send),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());

        onView(allOf(withId(R.id.newcontactname), withText("New Contact Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


       onView(allOf(withId(R.id.contact_email), withText("email"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        onView(allOf(withId(R.id.contact_mobile), withText("mobile"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        onView(allOf(withId(R.id.contact_event), withText("Where did you meet?"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.sendcontact),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));



       onView(withId(R.id.newcontactname)).perform(replaceText("Tester SMSMSOnly"), closeSoftKeyboard());

       onView(withId(R.id.contact_mobile)).perform(replaceText("9193765377"), closeSoftKeyboard());

       onView(withId(R.id.contact_event)).perform(replaceText("Developers R US networking event."), closeSoftKeyboard());





      onView(allOf(withId(R.id.newcontactname), withText("Tester SMSMSOnly"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


       onView(allOf(withId(R.id.contact_email), withText("email"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        onView(allOf(withId(R.id.contact_mobile), withText("9193765377"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        onView(allOf(withId(R.id.contact_event), withText("Developers R US networking event."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));


        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.sendcontact),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton2.perform(click());
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
