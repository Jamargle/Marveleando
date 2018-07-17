package jmlb0003.com.marveleando.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import jmlb0003.com.marveleando.R;

public final class MarvelWidget extends AppWidgetProvider {

    static void updateAppWidget(
            @NonNull final Context context,
            @NonNull final AppWidgetManager appWidgetManager,
            final int appWidgetId) {

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.marvel_widget);
        views.setTextViewText(R.id.appwidget_text, context.getString(R.string.widget_title));
        views.setRemoteAdapter(
                R.id.widget_list,   // Id of the layout holding the ListView
                // The intent to start the service to fetch the data shown in the widget
                WidgetViewsFactory.getWidgetViewsFactoryIntent(context));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(
            final Context context,
            final AppWidgetManager appWidgetManager,
            final int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (final int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

}

