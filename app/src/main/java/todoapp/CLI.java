package todoapp;

import java.util.List;
import java.util.Scanner;


/**
 * Provides a command-line interface for the TO-DO application using Singleton design-pattern.
 * @see <a href="https://refactoring.guru/design-patterns/singleton/">Singleton pattern</a>
 * 
 * @author Mahyar Behzadi, mahyarbhz@gmail.com
 */
public class CLI {
    private static CLI instance;
    Scanner scanner = new Scanner(System.in);
    ORM orm = ORM.getInstance();

    /**
     * Returns the single instance of the CLI class.
     * 
     * @return the single instance of the CLI class
     */
    public static CLI getInstance() {
        if (instance == null) {
            instance = new CLI();
        }
        return instance;
    }

    /**
     * CLI basic introduction
     * 
     * @return void
     */
    public void start() {
        System.out.println("Welcome to the TO-DO CLI application!\nI hope it helps you out.\n\nEnter task editing mode: T\nExit: E");
        String input = scanner.nextLine();
        if (input.equals("T")) {
            this.tasks();
        }
        else if (input.equals("E")) {
            System.exit(0);
        }
    }

    public void tasks() {
        List<Todo> todos = orm.getSession().createQuery("from Todo", Todo.class).list();
//        System.out.println("Enter task name: ");
        for (Todo todo : todos) {
            System.out.println(todo.getTodo());
        }
    }
}
