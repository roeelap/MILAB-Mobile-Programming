const fs = require('fs');
const express = require('express');

const PORT = 3000;

let app = express();

const isPathExists = (path) => {
    return fs.existsSync(path);
}

app.get('/files/:file_name*', (req, res) => {
    let file_name = req.params.file_name || null;
    let path = `./files/${file_name}`;
    
    // check that an valid path is given
    if (file_name === null || !isPathExists(path)) {
        res.send('<h1>PLEASE PROVIDE A VALID FILE NAME</h1>');
        return;
    }

    fs.createReadStream(path).pipe(res);
})

app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}!`)
})

