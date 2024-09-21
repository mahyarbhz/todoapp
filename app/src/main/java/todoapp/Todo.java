package todoapp;

import jakarta.persistence.*;

/**
 * Represents a todo item.
 */
@Entity
@Table(name = "todos")
public class Todo {
    /**
     * The unique identifier of the todo item.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * The description of the todo item.
     */
    @Column(nullable = false)
    private String todo;

    /**
     * The importance level of the todo item.
     */
    @Column(nullable = false)
    private int importance;

    /**
     * Indicates whether the todo item is starred.
     */
    @Column(nullable = false)
    private boolean starred;

    /**
     * Constructs a new Todo object with the specified description, importance, and starred status.
     *
     * @param id
     * @param todo        The description of the todo item.
     * @param importance  The importance level of the todo item.
     * @param starred     Indicates whether the todo item is starred.
     */
    public Todo(int id, String todo, int importance, boolean starred) {
        this.id = id;
        this.todo = todo;
        this.importance = importance;
        this.starred = starred;
    }

    /**
     * Gets the unique identifier of the todo item.
     *
     * @return The unique identifier of the todo item.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the description of the todo item.
     *
     * @return The description of the todo item.
     */
    public String getTodo() {
        return todo;
    }

    /**
     * Gets the importance level of the todo item.
     *
     * @return The importance level of the todo item.
     */
    public int getImportance() {
        return importance;
    }

    /**
     * Indicates whether the todo item is starred.
     *
     * @return True if the todo item is starred, false otherwise.
     */
    public boolean isStarred() {
        return starred;
    }
}