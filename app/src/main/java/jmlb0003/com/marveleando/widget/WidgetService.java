package jmlb0003.com.marveleando.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public final class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(final Intent intent) {
        return new WidgetViewsFactory(getApplicationContext(), intent);
    }

}
