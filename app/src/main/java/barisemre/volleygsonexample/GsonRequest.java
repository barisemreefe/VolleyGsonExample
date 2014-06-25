package barisemre.volleygsonexample;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.ErrorListener;

/**
 * Created by barisemre on 24/06/2014.
 */
public class GsonRequest<T> extends Request<T> {
    private Gson mGson = new Gson();
    private Class<T> _class;
    private Map<String, String> headers;
    private Map<String, String> params;
    private Response.Listener<T> listener;
    private String url;
    private String LOG_TAG = "GsonRequest";

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param _class Relevant class object, for Gson's reflection
     */
    public GsonRequest(int method,
                       String url,
                       Class<T> _class,
                       Response.Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, (Response.ErrorListener) errorListener);
        this._class = _class;
        this.listener = listener;
        this.url = url;
        mGson = new Gson();
        Log.i(LOG_TAG, "<<Request Url Start (GET)");
        Log.e(LOG_TAG, url);
        Log.i(LOG_TAG, "<<Request Url End (GET)");
    }

    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param _class Relevant class object, for Gson's reflection
     */
    public GsonRequest(int method,
                       String url,
                       Class<T> _class,
                       Map<String, String> params,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        this._class = _class;
        this.params = params;
        this.listener = listener;
        this.headers = null;
        this.url = url;
        mGson = new Gson();
        Log.i(LOG_TAG, "<<Request Url Start");
        Log.e(LOG_TAG, url);
        Log.i(LOG_TAG, "<<Request Url End");
        Log.i(LOG_TAG, "<<Post Parameters Start>>");
        Iterator i = params.entrySet().iterator();
        while (i.hasNext()) {

            Map.Entry e = (Map.Entry) i.next();
            Log.e(LOG_TAG, e.getKey() + " = " + e.getValue());
        }
        Log.i(LOG_TAG, "<<Post Parameters End>>");
    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pairs = (Map.Entry)iterator.next();
        }
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String,String> headerParameters = new HashMap<String, String>();

        if(getMethod() == Method.POST){
            headerParameters.put("Content-Type","application/json");
        }
        return headerParameters;
    }


    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    mGson.fromJson(json, _class), HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
    protected byte[] encodePostParameters(String paramsEncoding) {

        StringBuilder encodedParams = new StringBuilder();
        JSONObject jsonObject = new JSONObject();

        try {
            Iterator i = getParams().entrySet().iterator();
            while (i.hasNext()) {

                Map.Entry e = (Map.Entry) i.next();
                jsonObject.put((String) e.getKey(),e.getValue());

            }
        }  catch (JSONException e1) {
            e1.printStackTrace();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        return jsonObject.toString().getBytes();
    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        if (getParams() != null && getParams().size() > 0) {
            return encodePostParameters(getParamsEncoding());
        }
        return null;
    }


}