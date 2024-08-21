package todoapp;

/**
 * Provides a command-line interface for the TO-DO application using Singleton design-pattern.
 * @see <a href="https://refactoring.guru/design-patterns/singleton/">Singleton pattern</a>
 * 
 * @author Mahyar Behzadi, mahyarbhz@gmail.com
 */

public class CLI {
    private static CLI instance;

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
    public void intro() {
        System.out.println("Welcome to the TO-DO CLI application!\nI hope it helps you out.");
    }
}
