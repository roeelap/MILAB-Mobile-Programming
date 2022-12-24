const fs = require('fs');
const express = require('express');

const PORT = 3000;
const FILE_NAME = 'tasks.json';

let app = express();

const getContentString = (content) => {
    output = "<h1>Tasks:</h1>"
    content.tasks.forEach(task => {
        output += `<h2>${task.id}: ${task.task}</h2>`
    });
    return output;
}

const returnAsHtml = (str) => {
    return `<h1>${str}</h1>`
}

const findTaskIndex = (tasks, id) => {
    return tasks.findIndex((task) => task.id === id);
}

const sortTasksById = (tasks) => {
    return tasks.sort((task1, task2) => (task1.id > task2.id) ? 1 : -1)
}

const writeJsonFile = (jsonContent) => {
    fs.writeFile(FILE_NAME, jsonContent, (err) => {
        if (err) {
            console.log(err);
            return
        }   
    })
}

const addTaskToFile = (id, task) => {
    return new Promise((resolve) => {
        fs.readFile(FILE_NAME, (err, data) => {
            if (err) {
                console.log(err);
                resolve(false);
                return;
            }
            
            content = JSON.parse(data);
            
            // if the id already exists, don't do anything and return a failure message
            if (findTaskIndex(content.tasks, id) > -1) {
                resolve(false);
                return;
            }
    
            content.tasks.push({
                id: id,
                task: task
            });

            content.tasks = sortTasksById(content.tasks);
            writeJsonFile(JSON.stringify(content));
            resolve(true);
        });
    })
}

const removeTaskFromFile = (id) => {
    return new Promise((resolve) => {
        fs.readFile(FILE_NAME, (err, data) => {
            if (err) {
                console.log(err);
                resolve(false);
                return;
            } 
            
            content = JSON.parse(data);
    
            // if the id doesn't exist, don't do anything and return a failure message
            let taskWithIdIndex = findTaskIndex(content.tasks, id);
            if (taskWithIdIndex < 0) {
                resolve(false);
                return;
            }
    
            content.tasks.splice(taskWithIdIndex, 1);
            writeJsonFile(JSON.stringify(content));
            resolve(true);
        });
    })
}


app.get('/tasks', (_req, res) => {
    fs.readFile(FILE_NAME,(err, content) => {
        if (err) {
            console.error(err);
            return;
        }
        res.send(getContentString(JSON.parse(content)));
    });
})


app.get('/tasks/new', async (req, res) => {
    let id = req.query.id || null;
    let task = req.query.task || null;

    // check that an id and a task is given
    if (id === null) {
        res.send(returnAsHtml(`Please provide an id`));
        return;
    } else if (task === null) {
        res.send(returnAsHtml(`Please provide a task`));
        return;
    }

    const success = await addTaskToFile(id, task)

    if (success) {
        res.send(returnAsHtml(`Task added: ${task}, with id: ${id}`));
        return;
    }
    res.send(returnAsHtml(`This id already exists, please choose a different one`));
})


app.get('/tasks/remove', async (req, res) => {
    let id = req.query.id || null;
   
    // check that an id is given
    if (id === null) {
        res.send(returnAsHtml(`Please provide an id`));
        return;
    }

    const success = await removeTaskFromFile(id);

    if (success) {
        res.send(returnAsHtml(`Task with id: ${id} was removed`));
        return;
    }
    res.send(returnAsHtml(`This id doesn't exist, please choose a different one.`));
})

app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}!`);
})
