const fs = require('fs');
const express = require('express');

const PORT = 3000;

let app = express();

app.get('/tasks', (req, res) => {
    
    fs.readFile('tasks.json',(err, content) => {
        if (err) {
            console.error(err);
            return;
        }
        res.send(`tasks: ${content}`)
    });
})

app.get('/tasks/new', (req, res) => {
    let id = req.query.id || "<unknown>";
    let task = req.query.task || "<unknown>";

    fs.readFile('tasks.json', (err, data) => {
        if (err) {
            console.log(err);
            return;
        } 
        
        content = JSON.parse(data);
        content.tasks.push({
            id: id,
            task: task
        });
        let jsonContent = JSON.stringify(content);
        fs.writeFile("tasks.json", jsonContent, (err) => {
            if (err) {
                console.log(err);
                return
            }   
        })
    });
    res.send(`Task added: ${task}, with id: ${id}`)
})

app.get('/tasks/remove', (req, res) => {
    let id = req.query.id || "<unknown>";

    fs.readFile('tasks.json', (err, data) => {
        if (err) {
            console.log(err);
            return;
        } 
        
        content = JSON.parse(data);
        let taskWithIdIndex = content.tasks.findIndex((task) => task.id === id);
        if (taskWithIdIndex > -1) {
            content.tasks.splice(taskWithIdIndex, 1);
        }

        let jsonContent = JSON.stringify(content);
        fs.writeFile("tasks.json", jsonContent, (err) => {
            if (err) {
                console.log(err);
                return
            }   
        })
    });
    res.send(`Task deleted: ${id}`)
})

app.listen(PORT, () => {
    console.log('Listening on port 3000!');
})

console.log()