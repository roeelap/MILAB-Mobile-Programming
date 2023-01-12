package com.example.stockgetter;

import android.content.Context;

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
    private final static String REQUEST_URL = "http://10.0.2.2:8080/stock";

    public class StockResponse {
        public boolean isError;
        public double stockPrice;

        public StockResponse(boolean isError, double stockPrice) {
            this.isError = isError;
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
        return new StockResponse(true, -1);
    }

    public void dispatchRequest(final String stockName, final StockResponseListener listener) {
        JSONObject postBody = new JSONObject();
        try {
            postBody.put("stockName", stockName);
        }
        catch (JSONException e) {
            listener.onResponse(createErrorResponse());
            return;
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, REQUEST_URL, postBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            StockResponse res = new StockResponse(false, response.getDouble("price"));
                            listener.onResponse(res);
                        }
                        catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}