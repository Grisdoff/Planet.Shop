package at.htl.planetshopapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htl.planetshopapp.activity.MainActivity;
import at.htl.planetshopapp.R;
import at.htl.planetshopapp.service.DataService;
import at.htl.planetshopapp.adapter.PlanetAdapter;

public class MainFragment extends Fragment {
    private static MainFragment mainFragment;

    public static MainFragment getMainFragment() {
        return mainFragment;
    }

    public static void setMainFragment(MainFragment mainFragment) {
        MainFragment.mainFragment = mainFragment;
    }

    public static MainFragment newInstance(String param1, String param2) {
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


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_container);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        PlanetAdapter listadapter = new PlanetAdapter(DataService.getInstance().GetAll());
        recyclerView.setAdapter(listadapter);
        return view;
    }

}
