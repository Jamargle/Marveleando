package jmlb0003.com.marveleando.presentation.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.data.network.UrlType;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

final class MarvelUrlViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.link_icon) ImageView linkImageView;

    public MarvelUrlViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindLink(final MarvelUrl url) {
        switch (url.getType()) {
            case UrlType.COMIC_LINK:
                linkImageView.setImageResource(R.drawable.ic_comic);
                break;
            case UrlType.WIKI:
                linkImageView.setImageResource(R.drawable.ic_wiki);
                break;
            case UrlType.DETAIL:
            default:
                linkImageView.setImageResource(R.drawable.ic_details);
        }
    }

}
