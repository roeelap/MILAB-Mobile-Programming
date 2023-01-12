"use strict";

const express = require("express");
const request = require("request");
const bodyParser = require('body-parser');

const PORT = 8080;
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
				let name = data["Global Quote"]["01. symbol"];
				let price = data["Global Quote"]["05. price"];
				return cb(null, { stockName: name, stockPrice: price });
			}
		}
	);
}


let app = express();
app.use(bodyParser.json());

app.get("/stocks", (req, res) => {
    console.log("RECEIVED GET REQUEST");
    let stockName = req.query.stockName;
	console.log("stock requested:", stockName);


    if (stockName == null) {
        res.send("Please provide a valid stock name!");
        return;
    }
    
    fetchStockPrice(stockName, (err, data) => {
        if (err) return res.status(500).json({err: err.message});
        console.log("requested data:", data);
        return res.json(data);
    });
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}!`);
});
