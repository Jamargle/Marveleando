package jmlb0003.com.marveleando.presentation.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;

public final class CharacterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.character_image_container) ImageView characterImageView;
    @BindView(R.id.character_name) TextView characterNameView;

    CharacterViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindCharacter(final Character character) {
        characterNameView.setText(character.getName());
        Picasso.with(itemView.getContext())
                .load(character.getImagePortrait()).fit()
                .placeholder(R.drawable.ic_marvel_balloon)
                .error(R.drawable.ic_marvel_balloon)
                .into(characterImageView);
    }

}
