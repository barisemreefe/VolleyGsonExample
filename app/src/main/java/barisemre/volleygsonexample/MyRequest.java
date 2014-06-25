package barisemre.volleygsonexample;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;

/**
 * Created by barisemre on 24/06/2014.
 */
public class MyRequest {
    private MyRequestInterface requestListener;
    private static final String URL = "http://date.jsontest.com/";

    public MyRequest(MyRequestInterface aboutUsInterface) {
        this.requestListener = aboutUsInterface;

    }
    public void request(){


        GsonRequest<MyResponse> jsObjRequest = new GsonRequest<MyResponse>(
                Request.Method.POST,
                URL,
                MyResponse.class,new HashMap<String, String>(),
                onSuccessListener(),
                onErrorListener());
        AppManager.requestQueue.add(jsObjRequest);

    }
    private Response.Listener<MyResponse> onSuccessListener() {
        return new Response.Listener<MyResponse>() {
            @Override
            public void onResponse(MyResponse response) {
                Log.d("BEE",response.toString());
                try {

                    if(response!=null)
                        requestListener.onSuccessMyRequest(response);
                    else{
                        requestListener.onErrorMyRequest();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("BEE","ERR "+e.toString());
                    requestListener.onErrorMyRequest();
                }
            };
        };
    }

    private Response.ErrorListener onErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BEE","ERR "+error.toString());
                requestListener.onNetworkErrorMyRequest();
            }
        };
    }
}
