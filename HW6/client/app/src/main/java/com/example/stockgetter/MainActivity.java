package com.example.stockgetter;

import androidx.appcompat.app.AppCompatActivity;

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

    // This method is called when the user clicks the "Fetch" button
    public void fetchStock(final View view) {
        final StockFetcher fetcher = new StockFetcher(view.getContext());
        final String stockName = ((EditText)findViewById(R.id.editTextStockName)).getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching stock price for " + stockName + "...");
        progressDialog.show();

        fetcher.dispatchRequest(stockName, new StockFetcher.StockResponseListener() {
            // This method is called when the response is received
            @Override
            public void onResponse(StockFetcher.StockResponse response) {
                progressDialog.hide();

                if (response.isError) {
                    Toast.makeText(view.getContext(), "Error while fetching stock price", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Display the stock price
                String output = "Stock price for " + response.stockName + " is " + response.stockPrice + "$";
                ((TextView)findViewById(R.id.stockPriceOutput)).setText(output);
            }
        });
    }
}