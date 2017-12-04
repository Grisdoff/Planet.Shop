package at.htl.planetshopapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.viewholder.PlanetViewHolder;

/**
 * Created by Patrick on 24.11.2017.
 */

public class PlanetAdapter extends RecyclerView.Adapter<PlanetViewHolder> {
    ArrayList<PlanetCard> cardArrayList = new ArrayList<>();
    public static PlanetAdapter mplanetAdapter;

    public static PlanetAdapter getMplanetAdapter() {
        return mplanetAdapter;
    }

    public static void setMplanetAdapter(PlanetAdapter mplanetAdapter) {
        PlanetAdapter.mplanetAdapter = mplanetAdapter;
    }

    public PlanetAdapter(ArrayList<PlanetCard> cardArrayList) {
        this.cardArrayList = cardArrayList;
        PlanetAdapter.setMplanetAdapter(this);
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_test,parent,false);
        return new PlanetViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        final PlanetCard card = cardArrayList.get(position);
        holder.updateUI(card);
    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }
}
