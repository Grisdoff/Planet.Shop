package at.htl.planetshopapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.activity.MainActivity;
import at.htl.planetshopapp.persistence.ShoppingCartFacade;
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
        View view = inflater.inflate(R.layout.fragment_detailspage, container, false);
        DataService.getInstance().loadPlanetCard(productId, card -> {
            ((ImageView)view.findViewById(R.id.image_view)).setImageBitmap(card.getImageView());
            ((TextView)view.findViewById(R.id.name_text)).setText(card.getName());
            ((TextView)view.findViewById(R.id.price_text)).setText(Double.toString(card.getPrice()) + '€');
            ((TextView)view.findViewById(R.id.product_description_view)).setText(card.getDescription());
            ((TextView)view.findViewById(R.id.product_id_hidden_view)).setText(Long.toString(card.getId()));
        });

        view.findViewById(R.id.add_to_shopping_cart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartFacade.getShoppingCartFacade().incrementItemCountOf(productId);
            }
        });

        view.findViewById(R.id.contactSellerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:"
                        + "test@test.com"
                        + "?subject=" + "Kaufanfrage" + "&body=" + "");
                intent.setData(data);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.shopping_cart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getMainActivity().loadShoppingCart();
            }
        });
        return view;
    }
}
