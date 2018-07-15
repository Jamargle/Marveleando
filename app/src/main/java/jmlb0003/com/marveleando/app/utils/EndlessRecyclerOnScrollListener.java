package jmlb0003.com.marveleando.app.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private static final int DEFAULT_VISIBLE_THRESHOLD = 4;
    private final GridLayoutManager layoutManager;
    private final int visibleThreshold;

    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int currentPage = 1;

    protected EndlessRecyclerOnScrollListener(@NonNull final GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        visibleThreshold = DEFAULT_VISIBLE_THRESHOLD * layoutManager.getSpanCount();
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void onScrolled(
            final RecyclerView recyclerView,
            final int dx,
            final int dy) {

        super.onScrolled(recyclerView, dx, dy);

        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        visibleItemCount = layoutManager.getChildCount();
        totalItemCount = layoutManager.getItemCount();

        if (loading) {
            if (totalItemCount > previousTotalItemCount) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }
        }
        if (endHasBeenReached()) {
            // Reset current page and previous total item count if the list has been restarted
            // and has less items
            if (previousTotalItemCount > totalItemCount) {
                previousTotalItemCount = 0;
                currentPage = 0;
            }
            currentPage++;
            loading = true;

            onLoadMore(currentPage);
        }
    }

    private boolean endHasBeenReached() {
        return !loading
                && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold);
    }

    public abstract void onLoadMore(final int currentPage);

}
