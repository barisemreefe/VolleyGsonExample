package barisemre.volleygsonexample;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by barisemre on 24/06/2014.
 */
public class AppManager extends Application {
    public static RequestQueue requestQueue;
    public static VolleyImageLoader volleyImageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        volleyImageLoader = new VolleyImageLoader(this);
    }
}
