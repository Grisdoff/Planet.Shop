package at.htl.planetshopapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.service.DataService;

/**
 * Created by Daniel on 10.01.2018.
 */

public class PlanetCardDetailsFragment extends Fragment {
    private static PlanetCardDetailsFragment planetCardDetailsFragment;

    public static PlanetCardDetailsFragment getPlanetCardDetailsFragment() {
        return planetCardDetailsFragment;
    }

    public static void setPlanetCardDetailsFragment(PlanetCardDetailsFragment planetCardDetailsFragment) {
        PlanetCardDetailsFragment.planetCardDetailsFragment = planetCardDetailsFragment;
    }

    public static PlanetCardDetailsFragment newInstance(long productId) {
        PlanetCardDetailsFragment fragment = new PlanetCardDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(PRODUCT_ID_VAR_NAME, productId);
        fragment.setArguments(args);
        return fragment;
    }

    private static final String PRODUCT_ID_VAR_NAME = "productId";
    private long productId;




    public long getProductId() {
        return productId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getLong(PRODUCT_ID_VAR_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setPlanetCardDetailsFragment(this);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        PlanetCard card = DataService.getInstance().getById(productId);
        ((ImageView)view.findViewById(R.id.productImageView)).setImageBitmap(card.getImageView());
        ((TextView)view.findViewById(R.id.productNameView)).setText(card.getName());
        ((TextView)view.findViewById(R.id.productPriceView)).setText(Double.toString(card.getPrice()));
        ((TextView)view.findViewById(R.id.productDescriptionView)).setText(card.getDescription());

        return view;
    }
}
