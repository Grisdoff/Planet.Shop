package at.htl.planetshopapp.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Patrick on 26.11.2017.
 */

public class RequestQueueRepository {

    private static RequestQueueRepository instance;
    private RequestQueue requestQueue;
    private static Context ctx;



    private RequestQueueRepository(Context ctx) {

        this.ctx = ctx;
        this.requestQueue = getRequestQueue();

    }

    /**
     * Aufrufen mit
     * <code>RequestQueueRepository.getInstance(this).addToRequestQueue(stringRequest);</code>
     * @param context
     * @return
     */
    public static synchronized RequestQueueRepository getInstance(Context context) {

        if (instance == null) {
            instance = new RequestQueueRepository(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this.ctx.getApplicationContext());
        }
        return this.requestQueue;
    }

    /**
     * Hier wird ein generischer Typ T verwendet, damit die Methode sowohl für JsonArrayRequest und
     * JsonObjectRequest verwendet werden kann.
     * https://docs.oracle.com/javase/tutorial/java/generics/types.html
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
