package at.htl.planetshopapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.entity.PlanetCard;

/**
 * Created by Patrick on 24.11.2017.
 */

public class MainFragmentPlanetCardViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView price;
    public TextView name;
    public TextView id;
    public MainFragmentPlanetCardViewHolder(View itemView) {
        super(itemView);

        price = (TextView) itemView.findViewById(R.id.price_text);
        name = (TextView)itemView.findViewById(R.id.name_text);
        imageView = (ImageView)itemView.findViewById(R.id.image_view);
        id = (TextView)itemView.findViewById(R.id.product_id_hidden_view);
    }

    public void updateUI(PlanetCard card){
        price.setText(Double.toString(card.getPrice()) + '€');
        name.setText(card.getName());
        imageView.setImageBitmap(card.getImageView());
        id.setText(Long.toString(card.getId()));

    }
}


