package com.wendydarby.mycard.view;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MyCardViewContactOverloadedFieldsTest {

    @Rule
    public ActivityTestRule<MyCardView> mActivityTestRule = new ActivityTestRule<>(MyCardView.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.SEND_SMS");

    @Test
    public void myCardViewContactOverloadedFieldsTest() {
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
       // appCompatAutoCompleteTextView.perform(click());

        onView(withId(R.id.newcontactname)).perform(replaceText("Tester Overloademmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"), closeSoftKeyboard());

        onView(allOf(withId(R.id.contact_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));

       onView(allOf(withId(R.id.contact_mobile),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_mobile)).perform(replaceText("9193765377"), closeSoftKeyboard());

        onView(allOf(withId(R.id.contact_event),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        onView(withId(R.id.contact_event)).perform(replaceText("Wedding O Oct 2019. 123456789012345678901234567890123456789012345678901234567890"), closeSoftKeyboard());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.sendcontact),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        /*onView(
                allOf(withText("Tester"),
                        childAtPosition(
                                allOf(withId(R.id.newcontactname),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("Tester")));

        ViewInteraction editText2 = onView(
                allOf(withText("Overloademmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"),
                        childAtPosition(
                                allOf(withId(R.id.contact_email),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("Overloademmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm")));

        ViewInteraction editText3 = onView(
                allOf(withText("(919) 376-5377"),
                        childAtPosition(
                                allOf(withId(R.id.contact_mobile),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("(919) 376-5377")));

        ViewInteraction editText4 = onView(
                allOf(withText("overload@espresso.net"),
                        childAtPosition(
                                allOf(withId(R.id.contact_email),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText4.check(matches(withText("overload@espresso.net")));

        ViewInteraction editText5 = onView(
                allOf(withText("Met at Wedding O Oct 2019. 123456789012345678901234567890123456789012345678901234567890. Sent them my ecard using MyCard."),
                        childAtPosition(
                                allOf(withId(R.id.contact_event),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText5.check(matches(withText("Met at Wedding O Oct 2019. 123456789012345678901234567890123456789012345678901234567890. Sent them my ecard using MyCard.")));
*/
        /*ViewInteraction textView = onView(
                allOf(withId(com.google.android.apps.messaging.R.id.message_text), withText("Hi Tester Overloademmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm, I enjoyed meeting you! Here is the link to my e-card. https://ClickMy.info/i/8lGZ/Wendy_Darby"),
                        childAtPosition(
                                allOf(withId(com.google.android.apps.messaging.R.id.message_text_and_info),
                                        childAtPosition(
                                                withId(com.google.android.apps.messaging.R.id.message_content),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Hi Tester Overloademmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm, I enjoyed meeting you! Here is the link to my e-card. https://ClickMy.info/i/8lGZ/Wendy_Darby")));*/
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
