import java.io.*;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        String name, breed;
        int age, count = 0;

        Dog[] dogPound = new Dog[10];

        Scanner keyboard = new Scanner(System.in);

        // reading from binary file Dog.dat
        File binaryFile = new File("Dog.dat");
        // check to see if file exists AND non zero size
        System.out.println("\nPreviously saved Dogs from binary file: ");
        if(binaryFile.exists() && binaryFile.length() > 1L)
        {
            // TODO magic - read from binary file

            try
            {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // read the entire array into dogPound
                // readObject returns Object
                // Dog[] = Object --> has to downcast
                dogPound = (Dog[]) fileReader.readObject();
                // loop through array and print out all objects
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else
            System.out.println("\n[None, please enter new dog data]");

        do
        {
            // prompt for name, breed, age
            System.out.println("\nPlease enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if(name.equalsIgnoreCase("quit"))
                break;
            System.out.println("Please enter dog's breed (or \"quit\" to exit): ");
            breed = keyboard.nextLine();
            System.out.println("Please enter dog's age (or \"quit\" to exit): ");
            age = keyboard.nextInt();

            // TODO 1) create a new Dog object 2) add to array
            dogPound[count++] = new Dog(name, breed, age);

            // get rid of dangling \n
            keyboard.nextLine();

        }while(true);

        // After the user enters quit, write the dogPound array to binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
