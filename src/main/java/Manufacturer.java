public class Manufacturer {
    String name;
    String country;
    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Country: " + this.country;
    }
}
