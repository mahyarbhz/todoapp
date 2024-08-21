package todoapp;

public class CLI {
    private static CLI instance;

    public static CLI getInstance() {
        if (instance == null) {
            instance = new CLI();
        }
        return instance;
    }

    public void intro() {
        System.out.println("Welcome to the TO-DO CLI application!\nI hope it helps you out.");
    }
}
