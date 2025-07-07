
package librarysystem;

import java.io.*;
import java.util.List;

public class FileHandler implements Serializable {
    public static void saveData(List<?> data, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public static List<?> loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
           System.out.println("Data loaded successfully from " + fileName);
            return (List<?>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
            return null;
        }
    }
}
