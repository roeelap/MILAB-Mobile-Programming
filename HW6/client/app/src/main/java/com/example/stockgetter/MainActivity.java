package com.example.stockgetter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchStock(final View view) {
        final StockFetcher fetcher = new StockFetcher(view.getContext());
        final String stockName = ((EditText)findViewById(R.id.editTextStockName)).getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching stock price for " + stockName + "...");
        progressDialog.show();

        fetcher.dispatchRequest(stockName, new StockFetcher.StockResponseListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(StockFetcher.StockResponse response) {
                progressDialog.hide();

                if (response.isError) {
                    Toast.makeText(view.getContext(), "Error while fetching stock price", Toast.LENGTH_LONG);
                    return;
                }

                ((TextView)MainActivity.this.findViewById(R.id.stockPriceOutput)).setText(Double.toString(response.stockPrice));
            }
        });
    }
}