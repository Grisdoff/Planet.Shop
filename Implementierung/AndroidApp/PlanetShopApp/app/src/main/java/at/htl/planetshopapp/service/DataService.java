package at.htl.planetshopapp.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.htl.planetshopapp.adapter.PlanetAdapter;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.fragment.MainFragment;

/**
 * Created by Patrick on 24.11.2017.
 */

public class DataService {
    private static final String TAG = DataService.class.getSimpleName();
    private static String Base = "http://10.0.2.2:8080/PlanetShop/rs/planet";
    private ArrayList<PlanetCard> planetCards = new ArrayList<>();
    private PlanetCard planetCard;


    private static final DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    public PlanetCard getById(final Long searchId) {
        String url = Base + "/getProductById/" + searchId;
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            Long id = (long)jsonObject.getInt("id");
                            double price = jsonObject.getDouble("price");
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String map = jsonObject.getString("image");
                            Bitmap newmap = stringToBitmap(map);

                            Log.v(TAG, id + ":" + price + ":" + name);

                            planetCard = new PlanetCard(id, price, name, newmap);
                            planetCard.setDescription(description);
                            PlanetAdapter.getMplanetAdapter().notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        VolleyLog.d(TAG, "Error" + error.getMessage());
                        Toast.makeText(MainFragment.getMainFragment().getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueueRepository.getInstance(MainFragment.getMainFragment().getActivity()).addToRequestQueue(jsonArrayRequest);
        return planetCard;
    }

    public ArrayList<PlanetCard> getAllProducts() {
        String url = Base + "/getAllProducts";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        // Get current json object
                        JSONObject planetCard = response.getJSONObject(i);
                        Long id = (long)planetCard.getInt("id");
                        double price = planetCard.getDouble("price");
                        String name = planetCard.getString("name");
                        String map = planetCard.getString("image");
                        Bitmap newmap = stringToBitmap(map);

                        Log.v(TAG, id + ":" + price + ":" + name);

                        planetCards.add(
                                new PlanetCard(id, price, name, newmap)
                        );
                    }
                    PlanetAdapter.getMplanetAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        VolleyLog.d(TAG, "Error" + error.getMessage());
                        Toast.makeText(MainFragment.getMainFragment().getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueueRepository.getInstance(MainFragment.getMainFragment().getActivity()).addToRequestQueue(jsonArrayRequest);
        return planetCards;
    }
    private Bitmap stringToBitmap(String image) {
        byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
    }
}

