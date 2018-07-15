package jmlb0003.com.marveleando.presentation.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;

public final class MarvelCharacterAdapter
        extends RecyclerView.Adapter<CharacterViewHolder> {

    private final List<Character> characterDataSet;
    private final CharacterAdapterListener listener;

    public MarvelCharacterAdapter(@NonNull final CharacterAdapterListener listener) {
        this.listener = listener;
        characterDataSet = new ArrayList<>();
        listener.onCharacterDataSetEmpty();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType) {

        return new CharacterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_character, parent, false));
    }

    @Override
    public void onBindViewHolder(
            @NonNull final CharacterViewHolder viewHolder,
            final int position) {

        if (characterDataSet.size() >= position) {
            viewHolder.bindCharacter(characterDataSet.get(position));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    listener.onCharacterClicked(characterDataSet.get(viewHolder.getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return characterDataSet.size();
    }

    public void addCharacters(@NonNull final List<Character> newCharacters) {
        final int startPosition = getItemCount();
        characterDataSet.addAll(newCharacters);
        notifyItemRangeInserted(startPosition, newCharacters.size());
        if (characterDataSet.isEmpty()) {
            listener.onCharacterDataSetEmpty();
        }
    }

    public void showCharacters(@NonNull final List<Character> newCharacters) {
        characterDataSet.clear();
        characterDataSet.addAll(newCharacters);
        notifyDataSetChanged();
        if (characterDataSet.isEmpty()) {
            listener.onCharacterDataSetEmpty();
        }
    }

    public interface CharacterAdapterListener {

        void onCharacterDataSetEmpty();

        void onCharacterClicked(Character Character);

    }

}
