package jmlb0003.com.marveleando.presentation.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.data.network.UrlType;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

final class MarvelUrlViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.link_view) ViewGroup linkView;
    @BindView(R.id.link_icon) ImageView linkImageView;
    @BindView(R.id.link_type_label) TextView linkType;

    public MarvelUrlViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindLink(final MarvelUrl url, final View.OnClickListener clickListener) {
        bindIcon(url.getType());
        bindLabel(url.getType());
        linkView.setOnClickListener(clickListener);
    }

    private void bindIcon(final String type) {
        switch (type) {
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

    private void bindLabel(final String type) {
        switch (type) {
            case UrlType.COMIC_LINK:
                linkType.setText(R.string.comic);
                break;
            case UrlType.WIKI:
                linkType.setText(R.string.wiki);
                break;
            case UrlType.DETAIL:
            default:
                linkType.setText(R.string.detail);
        }
    }

}
