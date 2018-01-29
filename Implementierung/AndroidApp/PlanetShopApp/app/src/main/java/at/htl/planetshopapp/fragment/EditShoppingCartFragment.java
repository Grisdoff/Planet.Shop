package at.htl.planetshopapp.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.adapter.ShoppingCartAdapter;
import at.htl.planetshopapp.persistence.ShoppingCartFacade;
import at.htl.planetshopapp.viewholder.Decorator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditShoppingCartFragment#newInstance} factory method to
 * create an planetCardAdapter of this fragment.
 */
public class EditShoppingCartFragment extends Fragment {


    public EditShoppingCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new planetCardAdapter of
     * this fragment using the provided parameters.
     *
     * @return A new planetCardAdapter of fragment EditShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditShoppingCartFragment newInstance() {
        EditShoppingCartFragment fragment = new EditShoppingCartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View shoppingCartView = inflater.inflate(R.layout.fragment_edit_shopping_cart, container, false);


        RecyclerView recyclerView = (RecyclerView)shoppingCartView.findViewById(R.id.recyclerview_container);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        ShoppingCartFacade.getShoppingCartFacade().get(p -> {
            ShoppingCartAdapter adapter = new ShoppingCartAdapter(p);
            //recyclerView.addItemDecoration(new Decorator());
            recyclerView.setAdapter(adapter);
        });

        return shoppingCartView;
    }

}
