import java.time.*;
import java.lang.*;

public class Souvenir {
    private String name;
    private int manufacturersCode;
    private LocalDate releaseDate;
    private double price;


    public Souvenir(String name, int manufacturersCode, LocalDate releaseDate, double price) {
        this.name = name;
        this.manufacturersCode = manufacturersCode;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManufacturer() {
        return manufacturersCode;
    }

    public void setManufacturer(int manufacturersCode) {
        this.manufacturersCode = manufacturersCode;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.time.LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", manufacturersCode=" + manufacturersCode +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
