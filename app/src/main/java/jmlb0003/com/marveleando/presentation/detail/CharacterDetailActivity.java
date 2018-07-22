package jmlb0003.com.marveleando.presentation.detail;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseActivity;
import jmlb0003.com.marveleando.widget.MarvelWidget;

public final class CharacterDetailActivity
        extends BaseActivity<CharacterDetailActivityPresenter> implements
        CharacterDetailActivityPresenter.CharacterDetailActivityView,
        CharacterDetailFragment.Callback {

    public static final String CHARACTER_STATUS_FOR_RESULT = "Key:CharacterDetailActivity_Character_status_for_result";
    public static final String CHARACTER_ID_FOR_RESULT = "Key:CharacterDetailActivity_Character_id_for_result";
    public static final String CHARACTER_TO_SHOW = "Key:CharacterDetailActivity_Character";

    @Inject CharacterDetailActivityPresenter presenter;

    public static Bundle newBundle(final Character character) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(CHARACTER_TO_SHOW, character);
        return bundle;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null) {
            final Character character = getIntent().getParcelableExtra(CHARACTER_TO_SHOW);
            getFragmentManager().beginTransaction()
                    .add(R.id.character_details_fragment, CharacterDetailFragment.newInstance(character))
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        setCharacterStatusForResult();
        super.onBackPressed();
    }

    @NonNull
    @Override
    protected CharacterDetailActivityPresenter getPresenter() {
        return presenter;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setCharacterStatusForResult();
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCharacterStatusForResult() {
        final CharacterDetailFragment fragment = (CharacterDetailFragment) getFragmentManager()
                .findFragmentById(R.id.character_details_fragment);
        final Character character = fragment.getCharacterStatus();
        if (character != null) {
            final Intent data = new Intent();
            data.putExtra(CHARACTER_ID_FOR_RESULT, character.getId());
            data.putExtra(CHARACTER_STATUS_FOR_RESULT, character.isFavorite());
            setResult(RESULT_OK, data);
        } else {
            setResult(RESULT_CANCELED);
        }
    }

    @Override
    public void onFavoriteStateChanged(final boolean isFavorite) {
        final Intent intentToUpdateWidget = new Intent(this, MarvelWidget.class);
        intentToUpdateWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        final ComponentName widgetProvider = new ComponentName(getApplicationContext(), MarvelWidget.class);
        final int[] widgetIds = AppWidgetManager.getInstance(getApplicationContext())
                .getAppWidgetIds(widgetProvider);
        intentToUpdateWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds);

        sendBroadcast(intentToUpdateWidget);
    }

    @Override
    public void openLink(final String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}

