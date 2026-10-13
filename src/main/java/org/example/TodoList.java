package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Task> items = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(new Task(item));
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    // Очистка всех задач
    public void clear() {
        items.clear();
    }

    // Отметить задачу выполненной
    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).markDone();
            return true;
        }
        return false;
    }

    // Поиск задач по подстроке
    public List<Task> search(String query) {
        List<Task> result = new ArrayList<>();
        if (query == null || query.isBlank()) {
            return result;
        }
        String lowerQuery = query.toLowerCase().trim();
        for (Task task : items) {
            if (task.getTitle().toLowerCase().contains(lowerQuery)) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }
}