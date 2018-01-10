package at.htl.planetshopapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public static PlanetCardDetailsFragment newInstance() {
        PlanetCardDetailsFragment fragment = new PlanetCardDetailsFragment();
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
        return null;

    }
}
