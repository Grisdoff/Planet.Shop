package at.htl.planetshopapp.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import at.htl.planetshopapp.adapter.MainFragmentPlanetCardAdapter;
import at.htl.planetshopapp.entity.PlanetCard;
import at.htl.planetshopapp.fragment.MainFragment;

/**
 * Created by Patrick on 24.11.2017.
 */

public class DataService {
    private static final String TAG = DataService.class.getSimpleName();
    private static String BASE = "http://10.0.2.2:8080/planetshop/rs/product";
//    private ArrayList<PlanetCard> planetCards = new ArrayList<>();
//    private PlanetCard planetCard;


    private static final DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private final Object lock = new Object();

    private DataService() {
    }

    public void loadPlanetCard(final Long searchId, final Consumer<PlanetCard> callback) {
        String url = BASE + "/getProductById/" + searchId;
        final JsonObjectRequest productDetailsRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Long id = (long)response.getLong("id");
                            double price = response.getDouble("price");
                            String name = response.getString("name");
                            String description = response.getString("description");
                            String map = response.getString("image");
                            Bitmap newmap = stringToBitmap(map);

                            Log.v(TAG, id + ":" + price + ":" + name);

                            PlanetCard planetCard = new PlanetCard(id, price, name, newmap);
                            planetCard.setDescription(description);
                            callback.accept(planetCard);
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
//                        synchronized (lock) {
//                            lock.notify();
//                        }
                    }
                }
        );
        //Volley.newRequestQueue(MainActivity.getMainActivity()).add(productDetailsRequest);
        RequestQueueRepository.getInstance(MainFragment.getMainFragment().getActivity()).addToRequestQueue(productDetailsRequest);
//        synchronized (lock) {
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void loadPlanetCards(final List<Long> ids, final Consumer<List<PlanetCard>> callback) {
        StringBuilder urlBuilder = new StringBuilder(BASE + "/getProductsByIds?");
        for (Long id: ids) {
            urlBuilder.append("ids=").append(id).append('&');
        }

        final JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                urlBuilder.toString(),
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<PlanetCard> planetCards = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject planetCard = response.getJSONObject(i);
                                Long id = (long) planetCard.getInt("id");
                                double price = planetCard.getDouble("price");
                                String name = planetCard.getString("name");
                                String map = planetCard.getString("image");
                                Bitmap newmap = stringToBitmap(map);

                                Log.v(TAG, id + ":" + price + ":" + name);

                                planetCards.add(
                                        new PlanetCard(id, price, name, newmap)
                                );

                            }
                            callback.accept(planetCards);
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
                });
        RequestQueueRepository.getInstance(MainFragment.getMainFragment().getActivity()).addToRequestQueue(request);
    }

    public void loadAllProducts(final Consumer<List<PlanetCard>> callback) {
        String url = BASE + "/getAllProducts";
        getProducts(url, callback);
    }

    public void getProducts(String url, final Consumer<List<PlanetCard>> callback){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through the array elements
                            List<PlanetCard> planetCards = new ArrayList<>();
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
                            callback.accept(planetCards);
                            MainFragmentPlanetCardAdapter.getPlanetCardAdapter().notifyDataSetChanged();
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
        //return planetCards;
    }

    public void filterProducts(String filter, final Consumer<List<PlanetCard>> callback){
        String url = BASE + "/filter/" + filter;
        getProducts(url, callback);

    }
    private Bitmap stringToBitmap(String image) {
        byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
    }
}

