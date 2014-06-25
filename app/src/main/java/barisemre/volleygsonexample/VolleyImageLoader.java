package barisemre.volleygsonexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by barisemre on 24/06/2014.
 */
public class VolleyImageLoader {
    private static VolleyImageLoader mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public VolleyImageLoader(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(5);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {

                return mCache.get(url);

            }
        });
    }


    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }



}
