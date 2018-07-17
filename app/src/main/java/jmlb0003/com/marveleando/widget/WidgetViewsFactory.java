package jmlb0003.com.marveleando.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.data.local.CharactersDb;
import jmlb0003.com.marveleando.domain.model.Character;

public final class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Character> dataSet = new ArrayList<>();
    private Context context;

    WidgetViewsFactory(
            final Context context,
            final Intent intent) {

        this.context = context;
    }

    public static Intent getWidgetViewsFactoryIntent(final Context context) {
        final Intent intent = new Intent(context, WidgetService.class);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }

    @Override
    public void onCreate() {
        // Nothing to do here
    }

    @Override
    public void onDataSetChanged() {
        dataSet.clear();

        final List<Character> favoriteCharacters = CharactersDb.getInstance(context)
                .getCharacterDao().getFavoriteCharacters();

        dataSet.addAll(favoriteCharacters);
    }

    @Override
    public void onDestroy() {
        dataSet.clear();
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public RemoteViews getViewAt(final int position) {
        final Character character = dataSet.get(position);

        final RemoteViews characterWidgetItem = new RemoteViews(
                context.getPackageName(),
                R.layout.widget_item_list);
        characterWidgetItem.setTextViewText(R.id.item_label, character.getName());

        return characterWidgetItem;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
