package jmlb0003.com.marveleando.presentation.detail;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class CharacterDetailsScreenTest {

    private static final Character EXPECTED_CHARACTER = new Character();

    @Rule
    public ActivityTestRule<CharacterDetailActivity> activityTestRule
            = new ActivityTestRule<>(CharacterDetailActivity.class, false, false);

    @Before
    public void setUp() {
        EXPECTED_CHARACTER.setId(1);
        EXPECTED_CHARACTER.setName("Name");
        EXPECTED_CHARACTER.setDescription("Description");
        EXPECTED_CHARACTER.setImageLandscape("Image landscape");
        EXPECTED_CHARACTER.setImagePortrait("Image portrait");
        EXPECTED_CHARACTER.setUrls(new ArrayList<MarvelUrl>());
    }

    @Test
    public void characterDetailsAreShown() {
        launchActivity();

        // Image
        onView(allOf(
                withId(R.id.character_image),
                IsInstanceOf.instanceOf(ImageView.class)))
                .check(matches(isDisplayed()));

        // Name
        onView(allOf(
                withId(R.id.character_name),
                IsInstanceOf.instanceOf(TextView.class)))
                .check(matches(allOf(
                        isDisplayed(),
                        withText(EXPECTED_CHARACTER.getName()))));
        // Description
        onView(allOf(
                withId(R.id.character_description),
                IsInstanceOf.instanceOf(TextView.class)))
                .check(matches(allOf(
                        isDisplayed(),
                        withText(EXPECTED_CHARACTER.getDescription()))));
    }

    @Test
    public void favoriteButtonIsUnselectedWhenCharacterIsNotFavorite() {
        EXPECTED_CHARACTER.setFavorite(false);
        launchActivity();

        // Image
        onView(allOf(
                withId(R.id.favorite_button),
                IsInstanceOf.instanceOf(FloatingActionButton.class)))
                .check(matches(allOf(
                        isDisplayed(),
                        withTagValue(Matchers.<Object>equalTo(R.drawable.ic_favorite)))));
    }

    @Test
    public void favoriteButtonIsSelectedWhenCharacterIsFavorite() {
        EXPECTED_CHARACTER.setFavorite(true);
        launchActivity();

        // Image
        onView(allOf(
                withId(R.id.favorite_button),
                IsInstanceOf.instanceOf(FloatingActionButton.class)))
                .check(matches(allOf(
                        isDisplayed(),
                        withTagValue(Matchers.<Object>equalTo(R.drawable.ic_not_favorite)))));
    }

    private void launchActivity() {
        final Intent intent = new Intent();
        intent.putExtra(CharacterDetailActivity.CHARACTER_TO_SHOW, EXPECTED_CHARACTER);
        activityTestRule.launchActivity(intent);
    }

}