const fs = require('fs');
const express = require('express');

const PORT = 3000;

let app = express();

const isPathExists = (path) => {
    return fs.existsSync(path);
}

app.get('/files', (req, res) => {
    let file_name = req.query.file_name || null;
    let path = `./files/${file_name}`
    // check that an id and a task is given
    if (file_name === null || !isPathExists(path)) {
        res.send('<h1>Please provide a valid file name</h1>');
        return;
    }

    fs.createReadStream(path).pipe(res);
})

app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}!`)
})

