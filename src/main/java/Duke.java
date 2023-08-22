import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello! I'm Snow!");
        System.out.println("What can I do for you?");
        horizontalLine();


            Scanner userInput = new Scanner(System.in);  // Create a Scanner object
            String userOutput = userInput.nextLine();  // Read user input
            ArrayList<Task> inputList = new ArrayList<>();
            while (!userOutput.equals("bye")) {
                try {
                    String[] splitOutput = userOutput.split(" ");
                    if (userOutput.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < inputList.size(); i++) {
                            System.out.println(i + 1 + ". " + inputList.get(i).toString());
                        }
                    } else if (splitOutput[0].equals("deadline")) {
                        try {
                            String newDes = userOutput.split("deadline")[1].split("/by")[0].strip();
                            String newBy = userOutput.split("/by")[1].strip();
                            Deadline newDeadline = new Deadline(newDes, newBy);
                            inputList.add(newDeadline);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newDeadline);
                            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                        } catch (Exception e) {
                            throw new EmptyException("deadline");
                        }
                    } else if (splitOutput[0].equals("todo")) {
                        try {
                            Todo newTodo = new Todo(userOutput.split("todo")[1].strip());
                            inputList.add(newTodo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTodo);
                            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                        } catch (Exception e) {
                            throw new EmptyException("todo");
                        }
                    } else if (splitOutput[0].equals("event")) {
                        System.out.println("Got it. I've added this task:");
                        String newDes = userOutput.split("event")[1].split("/from")[0].strip();
                        String newFrom = userOutput.split("/from")[1].split("/to")[0].strip();
                        String newTo = userOutput.split("/to")[1].strip();
                        Event newEvent = new Event(newDes, newFrom, newTo);
                        inputList.add(newEvent);
                        System.out.println(newEvent);
                        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                    } else if (splitOutput[0].equals("mark")) {
                        System.out.println("Nice! I've marked this task as done:");
                        Task selectedTask = inputList.get(Integer.parseInt(splitOutput[1]) - 1);
                        selectedTask.mark();
                        System.out.println(selectedTask.toString());
                    } else if (splitOutput[0].equals("unmark")) {
                        System.out.println(" OK, I've marked this task as not done yet:");
                        Task selectedTask = inputList.get(Integer.parseInt(splitOutput[1]) - 1);
                        selectedTask.unmark();
                        System.out.println(selectedTask.toString());
                    } else if (userOutput.equals("bye")) {
                        break;
                    } else {
                        throw new InvalidInputException();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                userOutput = userInput.nextLine();  // Read user input
            }
        printBye();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
