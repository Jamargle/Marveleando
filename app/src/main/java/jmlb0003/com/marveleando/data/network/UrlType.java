package jmlb0003.com.marveleando.data.network;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static jmlb0003.com.marveleando.data.network.UrlType.COMIC_LINK;
import static jmlb0003.com.marveleando.data.network.UrlType.DETAIL;
import static jmlb0003.com.marveleando.data.network.UrlType.WIKI;

@Retention(RetentionPolicy.SOURCE)
@StringDef({DETAIL, WIKI, COMIC_LINK})
public @interface UrlType {

    String DETAIL = "detail";
    String WIKI = "wiki";
    String COMIC_LINK = "comiclink";

}
