package at.htl.planetshopapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.activity.MainActivity;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.persistence.ShoppingCartFacade;
import at.htl.planetshopapp.viewholder.ShoppingCartViewHolder;

/**
 * Created by robin on 29.01.18.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {

    private static List<PlanetCard> _shoppingCart;

    private static ShoppingCartAdapter shoppingCartAdapter;


    public static ShoppingCartAdapter getShoppingCartAdapter() {
        return shoppingCartAdapter;
    }

    public ShoppingCartAdapter(List<PlanetCard> _shoppingCart) {
        this._shoppingCart = _shoppingCart;
    }

    public static void setShoppingCartAdapter(ShoppingCartAdapter value) {
        shoppingCartAdapter = value;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_edit_shopping_cart, parent, false);
        return new ShoppingCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, int position) {
        final PlanetCard card = _shoppingCart.get(position);

        holder.updateUI(card);

        holder.itemView.findViewById(R.id.shopping_cart_remove_item_button).setOnClickListener(view -> {
            _shoppingCart.remove(card);
            this.notifyDataSetChanged();
            ShoppingCartFacade.getShoppingCartFacade().delete(card.getId());
        });
        holder.itemView.setOnClickListener(view -> MainActivity.getMainActivity().loadDetails(card.getId()));
    }



    @Override
    public int getItemCount() {
        return _shoppingCart.size();
    }
}
