import java.util.HashMap;
import java.util.Scanner;

public class ManufacturerService {

    public static int addManufacturer(int mfId, HashMap<Integer, Manufacturer> mf, Scanner sc) {
        System.out.println("Enter manufacturer's name and country");
        String s = sc.nextLine();
        mf.put(mfId++, new Manufacturer(s.split(" ")[0], s.split(" ")[1]));
        return mfId;
    }

    public static void viewManufacturers(HashMap<Integer, Manufacturer> mf) {
        System.out.println("All manufacturers: ");
        mf.entrySet().stream()
                .forEach(entry-> System.out.println("ID: " + entry.getKey() + " " + entry.getValue().toString()));
    }

    public static void editManufacturers(HashMap<Integer, Manufacturer> mf, Scanner sc) {
        System.out.println("Enter the ID of the manufacturer you want to edit:");
        int id = sc.nextInt();
        sc.nextLine();

        if (mf.containsKey(id)) {
            System.out.println("Enter the new name and country for the manufacturer:");
            String s = sc.nextLine();
            String[] parts = s.split("\\s+", 2);

            if (parts.length == 2) {
                Manufacturer manufacturer = mf.get(id);
                manufacturer.setName(parts[0]);
                manufacturer.setCountry(parts[1]);
                System.out.println("Manufacturer details updated.");
            } else {
                System.out.println("Invalid input. You need to enter a name and a country.");
            }
        } else {
            System.out.println("Manufacturer with ID " + id + " not found.");
        }
    }
}




