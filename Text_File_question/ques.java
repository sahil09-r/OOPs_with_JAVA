import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ques {
    public static void main(String[] args) throws IOException {
        File file = new File("student.txt");
        if (file.createNewFile()) {
            System.out.println("File Created");
        } else {
            System.out.println("File already exists");
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int rollNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        FileWriter fw = new FileWriter(file);
        fw.write("Student Name: " + name + "\n");
        fw.write("Roll Number: " + rollNo + "\n");
        fw.write("Course: " + course + "\n");
        fw.close();
        System.out.println("Data written successfully.");

        System.out.println("\nReading File Content:");
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            System.out.println(fileReader.nextLine());
        }

        fileReader.close();
        sc.close();

        
    }
}
