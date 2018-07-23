package jmlb0003.com.marveleando.presentation.list;

import android.app.FragmentTransaction;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.presentation.BaseActivity;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailActivity;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragment;
import jmlb0003.com.marveleando.presentation.list.adapter.CharacterTransitionObject;
import jmlb0003.com.marveleando.widget.MarvelWidget;

public final class CharacterListActivity
        extends BaseActivity<CharacterListActivityPresenter> implements
        CharacterListActivityPresenter.CharacterListActivityView,
        CharacterListFragment.Callback,
        CharacterDetailFragment.Callback {

    private static final int REQUEST_CODE_CHARACTER_DETAILS = 123;

    @Inject CharacterListActivityPresenter presenter;

    private CharacterListFragment listFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_character_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            final SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                initSearchViewQueryTextListener(searchView);
            }
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_favorites:
                showFavoriteCharacters();
                return true;
            case R.id.action_show_everyone:
                showEveryCharacters();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFavoriteCharacters() {
        final CharacterListFragment listFragment = (CharacterListFragment) getFragmentManager()
                .findFragmentById(R.id.character_list_fragment);

        listFragment.showFavoriteCharacters();
    }

    private void showEveryCharacters() {
        final CharacterListFragment listFragment = (CharacterListFragment) getFragmentManager()
                .findFragmentById(R.id.character_list_fragment);

        listFragment.showEveryCharacters();
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHARACTER_DETAILS && resultCode == RESULT_OK) {
            updateCharacter(data);
        }
    }

    private void updateCharacter(final Intent data) {
        final int characterId = data.getIntExtra(CharacterDetailActivity.CHARACTER_ID_FOR_RESULT, -1);
        if (characterId > 0) {
            final boolean isFavorite = data.getBooleanExtra(CharacterDetailActivity.CHARACTER_STATUS_FOR_RESULT, false);
            final CharacterListFragment listFragment = (CharacterListFragment) getFragmentManager()
                    .findFragmentById(R.id.character_list_fragment);

            listFragment.updateCharacter(characterId, isFavorite);
        }
    }

    @NonNull
    @Override
    protected CharacterListActivityPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onNavigateToCharacterDetails(final CharacterTransitionObject transitionData) {
        if (getResources().getBoolean(R.bool.is_tablet)) {
            showCharacterDetails(transitionData);
        } else {
            openCharacterDetails(transitionData);
        }
    }

    @Override
    public void openDeviceSettings() {
        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
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

    private void showCharacterDetails(final CharacterTransitionObject transitionData) {
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragmentTransaction
                    .addSharedElement(transitionData.getImageForTransition().first, transitionData.getImageForTransition().second);
        }
        fragmentTransaction
                .replace(R.id.character_details_container, CharacterDetailFragment.newInstance(transitionData.getCharacter()))
                .addToBackStack(transitionData.getCharacter().getName())
                .commit();
    }

    private void openCharacterDetails(final CharacterTransitionObject transitionData) {
        final Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.putExtras(CharacterDetailActivity.newBundle(transitionData.getCharacter()));

        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                transitionData.getImageForTransition(),
                transitionData.getNameForTransition());
        final Bundle bundle = options.toBundle();

        startActivityForResult(intent, REQUEST_CODE_CHARACTER_DETAILS, bundle);
    }

    private void initSearchViewQueryTextListener(@NonNull final SearchView searchView) {
        listFragment = (CharacterListFragment) getFragmentManager()
                .findFragmentById(R.id.character_list_fragment);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                listFragment.searchCharacter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.length() > 1) {
                    listFragment.searchCharacter(newText);
                }
                return false;
            }
        });
    }

}

