export default function Task({ taskItem, onDeleteClick }) {

    return (
        <>
            {taskItem.description}
            <button onClick={onDeleteClick}>Delete</button>
        </>
    )
}