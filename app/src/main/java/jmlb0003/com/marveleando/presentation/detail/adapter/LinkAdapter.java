package jmlb0003.com.marveleando.presentation.detail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

public final class LinkAdapter
        extends RecyclerView.Adapter<MarvelUrlViewHolder> {

    private final List<MarvelUrl> urls;
    private final LinkAdapterListener listener;

    public LinkAdapter(
            @NonNull final List<MarvelUrl> urls,
            @NonNull final LinkAdapterListener listener) {

        this.urls = new ArrayList<>();
        this.urls.addAll(urls);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MarvelUrlViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MarvelUrlViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_link, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MarvelUrlViewHolder holder, final int position) {
        final MarvelUrl url = urls.get(position);
        holder.bindLink(url, new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                listener.onLinkClicked(url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public interface LinkAdapterListener {

        void onLinkClicked(MarvelUrl url);

    }

}
