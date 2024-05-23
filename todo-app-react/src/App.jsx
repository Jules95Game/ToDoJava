import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

// function deleteItem(taskId) {
//   fetch(`http://localhost:8080/api/v1/tasks/${taskId}`, {
//     method: "DELETE"
//   })
//     .then(response => response.json())
//     .then(body => console.log(body))
//     .catch(error => console.log(error));
// }

function App() {
  // const [joke, setjoke] = useState()

  // useEffect(() => {
  //   fetch("https://icanhazdadjoke.com", {
  //     method: "GET",
  //     headers: {
  //       "Accept": "application/json"
  //     }
  //   })
  //     .then(response => response.json())
  //     .then(body => {
  //       setjoke(body.joke);
  //       console.log(body);
  //     })
  //     .catch(error => console.log(error));
  // }, []);

  const [tasks, setTasks] = useState()

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/tasks")
      .then(response => response.json())
      .then(body => {
        setTasks(body);
        console.log(body);
      })
      .catch(error => console.log(error));
  }, []);

  // function deleteTask(taskId) {
  //   fetch(`http://localhost:8080/api/v1/tasks/${taskId}`, {
  //     method: "DELETE"
  //   })
  //     .then(response => response.json())
  //     .then(body => console.log(body))
  //     .catch(error => console.log(error));
  // }

  //ol {tasks.map(task => <li key={task.id}> {task.description})}
  //tasks.map(task=> <h2> {task.description}</h2>)
  return (
    <>
      <ol>
        {tasks && tasks.map(task =>
          <li key={task.id}>
            {task.description}
            {/* <button onClick={deleteItem(task.id)}>x</button> */}
          </li>)}
      </ol>
    </>
  )
}

export default App
