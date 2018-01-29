package at.htl.planetshopapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.entity.PlanetCard;

/**
 * Created by robin on 29.01.18.
 */

public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView priceView;
    private TextView nameView;
    private TextView idView;
    private TextView itemCountView;
    private TextView sumView;


    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.image_view);
        priceView = (TextView)itemView.findViewById(R.id.price_text);
        nameView = (TextView)itemView.findViewById(R.id.name_text);
        idView = (TextView)itemView.findViewById(R.id.product_id_hidden_view);
        itemCountView = (TextView)itemView.findViewById(R.id.shopping_cart_item_count_view);
        sumView = (TextView)itemView.findViewById(R.id.shopping_cart_sum_view);
    }

    public void updateUI(PlanetCard card) {
        imageView.setImageBitmap(card.getImageView());
        priceView.setText(Double.toString(card.getPrice()) + '€');
        nameView.setText(card.getName());
        idView.setText(Long.toString(card.getId()));
        itemCountView.setText(Integer.toString(card.getItemCount()));
        sumView.setText(Double.toString(card.getItemCount() * card.getPrice()));
    }
}
