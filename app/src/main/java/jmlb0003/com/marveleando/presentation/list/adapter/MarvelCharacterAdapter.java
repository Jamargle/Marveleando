package jmlb0003.com.marveleando.presentation.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;

public final class MarvelCharacterAdapter
        extends RecyclerView.Adapter<CharacterViewHolder> {

    private final List<Character> characterDataSet;
    private final CharacterAdapterListener listener;

    public MarvelCharacterAdapter(
            @NonNull final List<Character> characterList,
            @NonNull final CharacterAdapterListener listener) {

        this.listener = listener;
        characterDataSet = characterList;
        if (characterDataSet.isEmpty()) {
            listener.onCharacterDataSetEmpty();
        }
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

    public void updateCharacters(final List<Character> newDataSet) {
        characterDataSet.clear();
        if (newDataSet != null) {
            characterDataSet.addAll(newDataSet);
        } else {
            listener.onCharacterDataSetEmpty();
        }

        notifyDataSetChanged();
    }

    public interface CharacterAdapterListener {

        void onCharacterDataSetEmpty();

        void onCharacterClicked(Character Character);

    }

}
