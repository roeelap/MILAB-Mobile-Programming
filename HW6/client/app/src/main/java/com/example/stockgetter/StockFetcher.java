package com.example.stockgetter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class StockFetcher {
    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.0.2:8080/stocks";

    public class StockResponse {
        public boolean isError;
        public String stockName;
        public String stockPrice;

        public StockResponse(boolean isError, String stockName, String stockPrice) {
            this.isError = isError;
            this.stockName = stockName;
            this.stockPrice = stockPrice;
        }
    }

    public interface StockResponseListener {
        void onResponse(StockResponse response);
    }

    public StockFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private StockResponse createErrorResponse() {
        return new StockResponse(true, null, null);
    }

    public void dispatchRequest(final String stockName, final StockResponseListener listener) {
        String url = REQUEST_URL + "?stockName=" + stockName;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("StockGetter", "Got response: " + response.toString());
                        try {
                            String stockName = response.getString("stockName");
                            String stockPrice = response.getString("stockPrice");
                            StockResponse res = new StockResponse(false, stockName, stockPrice);
                            listener.onResponse(res);
                        }
                        catch (JSONException e) {
                            Log.e("StockGetter", "Error while parsing response: " + e.getMessage());
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("StockGetter", "Error while parsing response: " + error.getMessage());
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}