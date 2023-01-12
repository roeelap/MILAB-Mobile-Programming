"use strict";

const express = require("express");
const request = require("request");
const bodyParser = require('body-parser');

const PORT = 3000;
const API_KEY = "0WA8CR48NTOIU8XF"


function fetchStockPrice(stockName, cb) {
    let url = `https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=${stockName}&apikey=${API_KEY}`;

    request.get({
        url: url,
        json: true,
        headers: {'User-Agent': 'request'}
      }, (err, res, data) => {
        if (err) {
          return cb(err, null);
        } else {
          // data is successfully parsed as a JSON object
          let price = data["Global Quote"]["05. price"];
          return cb(null, price);
        }
    });
}


let app = express();
app.use(bodyParser.json());

app.get("/stocks", (req, res) => {
    let stockName = req.query.stockName || null;

    if (stockName == null) {
        res.send("Please provide a valid stock name!");
        return;
    }
    
    fetchStockPrice(stockName, (err, data) => {
        if (err) return res.status(500).json({err: err.message});
        return res.json(data);
    });
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}!`);
});
