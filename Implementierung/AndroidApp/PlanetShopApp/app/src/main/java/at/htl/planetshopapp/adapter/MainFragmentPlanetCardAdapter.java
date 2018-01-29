package at.htl.planetshopapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.activity.MainActivity;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.viewholder.MainFragmentPlanetCardViewHolder;

/**
 * Created by Patrick on 24.11.2017.
 */

public class MainFragmentPlanetCardAdapter extends RecyclerView.Adapter<MainFragmentPlanetCardViewHolder> {
    private List<PlanetCard> cardArrayList = new ArrayList<>();
    public static MainFragmentPlanetCardAdapter planetCardAdapter;

    public static MainFragmentPlanetCardAdapter getPlanetCardAdapter() {
        return planetCardAdapter;
    }

    public static void setPlanetCardAdapter(MainFragmentPlanetCardAdapter planetCardAdapter) {
        MainFragmentPlanetCardAdapter.planetCardAdapter = planetCardAdapter;
    }

    public MainFragmentPlanetCardAdapter(List<PlanetCard> cardArrayList) {
        this.cardArrayList = cardArrayList;
        MainFragmentPlanetCardAdapter.setPlanetCardAdapter(this);
    }

    @Override
    public MainFragmentPlanetCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main,parent,false);
        return new MainFragmentPlanetCardViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(MainFragmentPlanetCardViewHolder holder, int position) {
        final PlanetCard card = cardArrayList.get(position);
        holder.updateUI(card);


        holder.itemView.setOnClickListener((view) -> MainActivity.getMainActivity().loadDetails(Long.parseLong(((TextView)view.findViewById(R.id.product_id_hidden_view)).getText().toString())));
    }



    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }
}
