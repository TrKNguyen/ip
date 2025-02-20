package nguyen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testAddTodo() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }

    @Test
    void testAddDeadline() throws NguyenException{
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(deadline);
        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.get(0));
        assertTrue(deadline.toString().contains("[D]"));
    }

    @Test
    void testAddEvent() throws NguyenException{
        TaskList taskList = new TaskList();
        Task event = new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019");
        taskList.add(event);
        assertEquals(1, taskList.size());
        assertEquals(event, taskList.get(0));
        assertTrue(event.toString().contains("[E]"));
    }

    @Test
    void testDeleteTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.delete(1);
        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.get(0));
    }

    @Test
    void testDeleteTaskOutOfRange() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.delete(2);
        });
        assertEquals("Out of range in list of task", exception.getMessage());
    }

    @Test
    void testMarkTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task event = new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019");
        taskList.add(event);
        taskList.mark(1);
        assertTrue(event.isMarked());
    }

    @Test
    void testMarkTaskOutOfRange() throws NguyenException{
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(deadline);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.mark(2);
        });
        assertEquals("Out of range in list of task", exception.getMessage());
    }

    @Test
    void testUnMarkTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        taskList.mark(1);
        taskList.unMark(1);
        assertFalse(todo.isMarked());
    }

    @Test
    void testUnMarkTaskOutOfRange() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.unMark(2);
        });
        assertEquals("Out of range in list of task", exception.getMessage());
    }

    @Test
    void testPrintList() throws NguyenException{
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Meet Friends"));
        taskList.add(new Deadline("Do Competitive Programming", "Dec 2 2019"));
        taskList.add(new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019"));
        taskList.printList();
    }
}
