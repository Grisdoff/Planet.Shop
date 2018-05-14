package at.htl.planetshopapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.activity.MainActivity;
import at.htl.planetshopapp.service.DataService;
import at.htl.planetshopapp.adapter.MainFragmentPlanetCardAdapter;
import at.htl.planetshopapp.viewholder.Decorator;

public class MainFragment extends Fragment {
    private static MainFragment mainFragment;

    public static MainFragment getMainFragment() {
        return mainFragment;
    }

    public static void setMainFragment(MainFragment mainFragment) {
        MainFragment.mainFragment = mainFragment;
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainFragment.setMainFragment(this);
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_container);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DataService.getInstance().loadAllProducts(it -> {
            MainFragmentPlanetCardAdapter listadapter = new MainFragmentPlanetCardAdapter(it);
            recyclerView.addItemDecoration(new Decorator(2,10,false));
            recyclerView.setAdapter(listadapter);
        });

        view.findViewById(R.id.shopping_cart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getMainActivity().loadShoppingCart();
            }
        });


        view.findViewById(R.id.login_button).setOnClickListener(v -> MainActivity.getMainActivity().loadLoginFragment());

        SearchView searchView = view.findViewById(R.id.search_bar);
        if(searchView.getQuery() != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus();
                    DataService.getInstance().filterProducts(searchView.getQuery().toString(), it -> {
                        MainFragmentPlanetCardAdapter listadapter = new MainFragmentPlanetCardAdapter(it);
                        recyclerView.addItemDecoration(new Decorator(2,10,false));
                        recyclerView.setAdapter(listadapter);
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        return view;
    }
}
