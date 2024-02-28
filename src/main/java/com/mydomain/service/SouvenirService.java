package com.mydomain.service;

import com.google.gson.reflect.TypeToken;
import com.mydomain.model.Manufacturer;
import com.mydomain.model.Souvenir;
import com.mydomain.util.JsonDataStorage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SouvenirService {
     public static int addSouvenir(Scanner sc, int svId) throws IOException {

          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> sv = JsonDataStorage.readFromJson("souvenirs.json", svType);

          if (!sv.isEmpty()) {
               svId = sv.keySet().stream().max(Integer::compareTo).orElse(svId - 1) + 1;
          }

          ManufacturerService.viewManufacturers();

          System.out.println("Enter ID of manufacturer you want to add souvenir to");
          int mfId = sc.nextInt();
          sc.nextLine();

          System.out.println("Enter the name, release date in format YYYY-MM-DD, and price of the souvenir:");
          String souvenirDetails = sc.nextLine();
          String[] details = souvenirDetails.split("\\s+");

          if (details.length >= 3) {
               String name = details[0];
               String date = details[1];
               double price = Double.parseDouble(details[2]);

               Souvenir newSouvenir = new Souvenir(name, mfId, date, price);
               sv.put(svId, newSouvenir);

               svId++;

               JsonDataStorage.writeToJson("souvenirs.json", sv);
          } else {
               System.out.println("Invalid input. Please enter the name, release date, and price separated by spaces.");
          }

          return svId;
     }



     public static void viewSouvenirs() throws IOException {
          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> sv = JsonDataStorage.readFromJson("souvenirs.json", svType);
          System.out.println("All souvenirs: ");
          sv.entrySet().stream()
                  .forEach(entry-> System.out.println("ID: " + entry.getKey() + " " + entry.getValue().toString()));
     }

     public static void editSouvenirs(Scanner sc) throws IOException {
          SouvenirService.viewSouvenirs();
          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> sv = JsonDataStorage.readFromJson("souvenirs.json", svType);
          System.out.println("Enter the ID of the souvenir you want to edit:");
          int id = sc.nextInt();
          sc.nextLine();

          if (sv.containsKey(id)) {
               System.out.println("Enter the new name, ID of manufacturer, release date and price for the souvenir:");
               String s = sc.nextLine();
               String[] parts = s.split("\\s+", 4);

               if (parts.length == 4) {
                    Souvenir souvenir = sv.get(id);
                    souvenir.setName(parts[0]);
                    souvenir.setManufacturer(Integer.parseInt(parts[1]));
                    souvenir.setReleaseDate((parts[2]));
                    souvenir.setPrice(Integer.parseInt(parts[3]));
                    sv.put(id, souvenir);
                    System.out.println("com.yourdomain.model.Souvenir details updated.");
               } else {
                    System.out.println("Invalid input. You need to enter a name and a country.");
               }
          } else {
               System.out.println("com.mydomain.model.Manufacturer with ID " + id + " not found.");
          }
          JsonDataStorage.writeToJson("souvenirs.json", sv);

     }

     public static void printSouvenirsOfManufacturer(Scanner sc) throws IOException {
          System.out.println("Enter manufacturers ID: ");
          int manufacturerId = sc.nextInt();
          sc.nextLine();
          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

          allSouvenirs.values().stream()
                  .filter(souvenir -> souvenir.getManufacturersCode() == manufacturerId)
                  .forEach(System.out::println);
     }

     public static void printSouvenirsFromCountry(Scanner sc) throws IOException {
          System.out.println("Enter country: ");
          String country = sc.nextLine();
          Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
          HashMap<Integer, Manufacturer> allManufacturers = JsonDataStorage.readFromJson("manufacturers.json", mfType);

          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

          allManufacturers.entrySet().stream()
                  .filter(entry -> entry.getValue().getCountry().equalsIgnoreCase(country))
                  .forEach(entry -> {
                       Integer manufacturerId = entry.getKey();
                       Manufacturer manufacturer = entry.getValue();
                       System.out.println("com.mydomain.model.Manufacturer ID: " + manufacturerId + " - " + manufacturer.getName() + " from " + country);
                       allSouvenirs.values().stream()
                               .filter(souvenir -> souvenir.getManufacturersCode() == manufacturerId)
                               .forEach(System.out::println);
                  });
     }

     public static void printSouvenirsByYear() throws IOException {
          Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
          HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

          allSouvenirs.values().stream()
                  .collect(Collectors.groupingBy(souvenir -> souvenir.getReleaseDate().substring(0, 4)))
                  .forEach((year, souvenirs) -> {
                       System.out.println("Year: " + year);
                       souvenirs.forEach(System.out::println);
                  });
     }
}
