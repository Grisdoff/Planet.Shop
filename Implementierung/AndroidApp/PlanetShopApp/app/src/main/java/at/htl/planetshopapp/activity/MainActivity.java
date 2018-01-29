package at.htl.planetshopapp.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.fragment.EditShoppingCartFragment;
import at.htl.planetshopapp.fragment.MainFragment;
import at.htl.planetshopapp.fragment.PlanetCardDetailsFragment;
import at.htl.planetshopapp.persistence.ShoppingCartFacade;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivity.setMainActivity(this);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container,mainFragment)
                    .commit();
        }
        ShoppingCartFacade.setShoppingCartFacade(new ShoppingCartFacade(this.getApplicationContext()));
    }

    public void loadDetails(long productId) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_container, PlanetCardDetailsFragment.newInstance(productId)).commit();
    }

    public void loadShoppingCart() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_container, EditShoppingCartFragment.newInstance()).commit();
    }
}
