import java.io.*;

public class DataStorage {
    public String readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
           return br.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeToFile(String fileName, String string) throws FileNotFoundException {
         try (PrintWriter pw = new PrintWriter(fileName)) {
             pw.write(string);
         }
    }

}
