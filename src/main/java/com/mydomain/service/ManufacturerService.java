package com.mydomain.service;

import com.google.gson.reflect.TypeToken;
import com.mydomain.model.Manufacturer;
import com.mydomain.model.Souvenir;
import com.mydomain.util.JsonDataStorage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

public class ManufacturerService {

    public static int addManufacturer(int mfId, Scanner sc) throws IOException {

        Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> mf = JsonDataStorage.readFromJson("manufacturers.json", mfType);

        if (mf != null && !mf.isEmpty()) {
            int highestId = mf.keySet().stream().max(Integer::compare).get();
            mfId = highestId + 1;
        }

        System.out.println("Enter manufacturer's name and country");
        String s = sc.nextLine();
        String[] parts = s.split(" ");
        if(parts.length >= 2) {
            mf.put(mfId, new Manufacturer(parts[0], parts[1]));

            JsonDataStorage.writeToJson("manufacturers.json", mf);

            mfId++;
        } else {
            System.out.println("Invalid input. Please enter the name and country separated by a space.");
        }

        return mfId;
    }

    public static void viewManufacturers() throws IOException {
        Type mfType = new TypeToken<HashMap<Integer,Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> mf = JsonDataStorage.readFromJson("manufacturers.json", mfType);
        System.out.println("All manufacturers: ");
        mf.entrySet().stream()
                .forEach(entry-> System.out.println("ID: " + entry.getKey() + " " + entry.getValue().toString()));
    }

    public static void editManufacturers(Scanner sc) throws IOException {
        viewManufacturers();
        Type mfType = new TypeToken<HashMap<Integer,Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> mf = JsonDataStorage.readFromJson("manufacturers.json", mfType);
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
                mf.put(id, manufacturer);
                System.out.println("com.mydomain.model.Manufacturer details updated.");
            } else {
                System.out.println("Invalid input. You need to enter a name and a country.");
            }
        } else {
            System.out.println("com.mydomain.model.Manufacturer with ID " + id + " not found.");
        }
        JsonDataStorage.writeToJson("manufacturers.json", mf);
    }

    public static void printManufacturersWithSouvenirPriceLessThan(Scanner sc) throws IOException {
        System.out.println("Enter price: ");
        double price = sc.nextDouble();
        Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> allManufacturers = JsonDataStorage.readFromJson("manufacturers.json", mfType);

        Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
        HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

        allSouvenirs.values().stream()
                .filter(souvenir -> souvenir.getPrice() < price)
                .map(Souvenir::getManufacturersCode)
                .distinct()
                .forEach(manufacturerId -> {
                    Manufacturer manufacturer = allManufacturers.get(manufacturerId);
                    if(manufacturer != null) {
                        System.out.println("com.mydomain.model.Manufacturer: " + manufacturer.getName() + ", Country: " + manufacturer.getCountry());
                    }
                });
    }

    public static void printAllManufacturersAndTheirSouvenirs() throws IOException {
        Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> allManufacturers = JsonDataStorage.readFromJson("manufacturers.json", mfType);

        Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
        HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

        allManufacturers.forEach((id, manufacturer) -> {
            System.out.println("com.mydomain.model.Manufacturer: " + manufacturer.getName() + ", Country: " + manufacturer.getCountry());
            allSouvenirs.values().stream()
                    .filter(souvenir -> souvenir.getManufacturersCode() == id)
                    .forEach(System.out::println);
        });
    }

    public static void printManufacturersOfSouvenirMadeInYear(Scanner sc) throws IOException {
        System.out.println("Enter souvenir name: ");
        String souvenirName = sc.nextLine();
        System.out.println("Enter year: ");
        String year = sc.nextLine();
        Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> allManufacturers = JsonDataStorage.readFromJson("manufacturers.json", mfType);

        Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
        HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson("souvenirs.json", svType);

        allSouvenirs.values().stream()
                .filter(souvenir -> souvenir.getName().equalsIgnoreCase(souvenirName) && souvenir.getReleaseDate().startsWith(year))
                .map(Souvenir::getManufacturersCode)
                .distinct()
                .forEach(manufacturerId -> {
                    Manufacturer manufacturer = allManufacturers.get(manufacturerId);
                    if(manufacturer != null) {
                        System.out.println("com.mydomain.model.Manufacturer: " + manufacturer.getName() + ", Country: " + manufacturer.getCountry());
                    }
                });
    }

    public static void deleteManufacturerAndSouvenirs(Scanner sc) throws IOException {
        System.out.println("Enter Manufacturers ID that you want to delete: ");
        int manufacturerId = sc.nextInt();
        sc.nextLine();
        String manufacturersFile = "manufacturers.json";
        String souvenirsFile = "souvenirs.json";
        Type mfType = new TypeToken<HashMap<Integer, Manufacturer>>(){}.getType();
        HashMap<Integer, Manufacturer> allManufacturers = JsonDataStorage.readFromJson(manufacturersFile, mfType);

        Type svType = new TypeToken<HashMap<Integer, Souvenir>>(){}.getType();
        HashMap<Integer, Souvenir> allSouvenirs = JsonDataStorage.readFromJson(souvenirsFile, svType);

        if(allManufacturers.containsKey(manufacturerId)) {
            allManufacturers.remove(manufacturerId);
            JsonDataStorage.writeToJson(manufacturersFile, allManufacturers);

            allSouvenirs.entrySet().removeIf(entry -> entry.getValue().getManufacturersCode() == manufacturerId);
            JsonDataStorage.writeToJson(souvenirsFile, allSouvenirs);

            System.out.println("com.mydomain.model.Manufacturer and their souvenirs have been deleted.");
        } else {
            System.out.println("com.mydomain.model.Manufacturer not found.");
        }
    }




}




