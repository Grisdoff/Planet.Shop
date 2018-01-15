package at.htl.planetshopapp.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.entity.PlanetCard;

/**
 * Created by Patrick on 24.11.2017.
 */

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView price;
    public TextView name;
    public TextView id;
    public PlanetViewHolder(View itemView) {
        super(itemView);

        price = (TextView) itemView.findViewById(R.id.price_text);
        name = (TextView)itemView.findViewById(R.id.name_text);
        imageView = (ImageView)itemView.findViewById(R.id.image_ID);
        id = (TextView)itemView.findViewById(R.id.productIdHiddenView);
    }

    public void updateUI(PlanetCard card){
        String toPrice = new Double(card.getPrice()).toString();
        price.setText(toPrice);
        name.setText(card.getName());
        imageView.setImageBitmap(card.getImageView());
        id.setText(Long.toString(card.getId()));
    }
}


