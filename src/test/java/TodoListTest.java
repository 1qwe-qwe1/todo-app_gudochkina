import org.example.Task;
import org.example.TodoList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add("  task1  ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().get(0).getTitle());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add("   ");
        assertEquals(0, t.size());
    }

    @Test
    void clearRemovesAllTasks() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.add("c");
        assertEquals(3, t.size());
        t.clear();
        assertEquals(0, t.size());
    }

    @Test
    void markDoneChangesStatus() {
        TodoList t = new TodoList();
        t.add("task1");
        assertFalse(t.getAll().get(0).isDone());
        assertTrue(t.markDone(0));
        assertTrue(t.getAll().get(0).isDone());
    }

    @Test
    void markDoneInvalidIndexReturnsFalse() {
        TodoList t = new TodoList();
        t.add("task1");
        assertFalse(t.markDone(5));
        assertFalse(t.markDone(-1));
    }

    @Test
    void searchFindsBySubstring() {
        TodoList t = new TodoList();
        t.add("buy milk");
        t.add("buy bread");
        t.add("walk the dog");

        List<Task> result = t.search("buy");
        assertEquals(2, result.size());

        List<Task> result2 = t.search("dog");
        assertEquals(1, result2.size());
        assertEquals("walk the dog", result2.get(0).getTitle());
    }

    @Test
    void searchIsCaseInsensitive() {
        TodoList t = new TodoList();
        t.add("Buy Milk");
        List<Task> result = t.search("buy");
        assertEquals(1, result.size());
    }

    @Test
    void searchEmptyQueryReturnsEmptyList() {
        TodoList t = new TodoList();
        t.add("task1");
        assertTrue(t.search("").isEmpty());
        assertTrue(t.search(null).isEmpty());
    }

    @Test
    void searchNoMatchReturnsEmptyList() {
        TodoList t = new TodoList();
        t.add("task1");
        assertTrue(t.search("xyz").isEmpty());
    }
}