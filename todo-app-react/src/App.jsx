import { useEffect, useState } from 'react'
import './App.css'
import Task from './components/Task';

function App() {
  const [tasks, setTasks] = useState();
  const [newTask, setNewTask] = useState("");

  useEffect(getTasks, []);

  function getTasks() {
    fetch("http://localhost:8080/api/v1/tasks")
      .then(response => response.json())
      .then(body => {
        setTasks(body);
        console.log(body);
      })
      .catch(error => console.log(error));
  }

  function deleteTask(taskId) {
    fetch("http://localhost:8080/api/v1/tasks/" + taskId, {
      method: 'DELETE'
    }).then(() => getTasks());
  }

  function handleInputChange(event) {
    setNewTask(event.target.value);
  }

  function submitHandler() {
    const taskToSubmit = { description: newTask };

    fetch("http://localhost:8080/api/v1/tasks", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(taskToSubmit)
    }).then(() => getTasks());
  }

  return (
    <>
      <h1>Todo app</h1>
      <h2>Add item:</h2>
      <input type="text" onChange={(event) => handleInputChange(event)} value={newTask} />
      <button onClick={() => submitHandler()}>Submit</button>
      <h2>List</h2>
      <ol>
        {tasks && tasks.map(task =>
          <li key={task.id}>
            <Task taskItem={task} onDeleteClick={() => deleteTask(task.id)}></Task>
          </li>)}
      </ol>
    </>
  )
}

export default App
