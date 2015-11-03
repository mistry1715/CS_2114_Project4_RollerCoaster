package rollercoaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QueueReader {

    public QueueReader(String str) throws FileNotFoundException {
        new CoasterWindow(readQueueFile(str));
    }

    public RollerCoasterQueue readQueueFile(String str) throws FileNotFoundException {
        RollerCoasterQueue queue = new RollerCoasterQueue();

        @SuppressWarnings("resource")
        Scanner file = new Scanner(new File(str));
        int line = 1;
        boolean willSplit = true;

        while (file.hasNextLine()) {
            if (line % 2 == 1) {
                willSplit = Boolean.parseBoolean(file.nextLine().trim());
            }
            else {
                String[] persons = file.nextLine().split(",");
                WaitingParty party = new WaitingParty(willSplit);

                for (int i = 0; i < persons.length; i += 2) {
                    party.add(new Person(persons[i].trim(), Integer.valueOf(persons[i+1].trim())));
                }

                queue.enqueueParty(party);
            }

            line++;
        }

        return queue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            new QueueReader("input.txt");
        }
        else {
            new QueueReader(args[0]); 
        }
    }
}
