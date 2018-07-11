package jmlb0003.com.marveleando.presentation.list;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.presentation.BaseActivity;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailActivity;
import jmlb0003.com.marveleando.presentation.list.adapter.CharacterTransitionObject;

import static android.Manifest.permission.READ_CONTACTS;

public final class CharacterListActivity
        extends BaseActivity<CharacterListActivityPresenter> implements
        CharacterListActivityPresenter.CharacterListActivityView,
        CharacterListFragment.Callback {

    private static final int REQUEST_READ_CONTACTS = 0;

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

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                        }
//                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
            }
        }
    }

    @NonNull
    @Override
    protected CharacterListActivityPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onNavigateToCharacterDetails(final CharacterTransitionObject transitionData) {
        final Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.putExtras(CharacterDetailActivity.newBundle(transitionData.getCharacter()));

        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                transitionData.getImageForTransition(),
                transitionData.getNameForTransition());
        final Bundle bundle = options.toBundle();

        startActivity(intent, bundle);
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

