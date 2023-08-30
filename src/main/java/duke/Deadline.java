package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static void setDeadline(String userOutput, TaskList inputList) throws Exception {
        try {
            String newDes = userOutput.split("deadline")[1].split("/by")[0].strip();
            String newBy = userOutput.split("/by")[1].strip();
            LocalDate dateBy = LocalDate.parse(newBy);
            Deadline newDeadline = new Deadline(newDes, dateBy);
            inputList.add(newDeadline);
            System.out.println("Got it. I've added this task:");
            System.out.println(newDeadline);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format.");
        } catch (Exception e) {
            throw new EmptyException("deadline");
        }
    }

    public static void newDeadline(String text, ArrayList<Task> tasks) {
        String desc = text.split("\\(")[0].trim();
        String by = text.split("by:")[1].split("\\)")[0].trim();
        LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));
        Deadline updatedDeadline = new Deadline(desc, date);
        tasks.add(updatedDeadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}