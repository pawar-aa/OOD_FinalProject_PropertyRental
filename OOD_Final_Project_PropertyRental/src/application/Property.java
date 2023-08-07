package application;

public class Property {
    private String address;
    private String type;
    private double rent;

    public Property(String address, String type, double rent) {
        this.address = address;
        this.type = type;
        this.rent = rent;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Address: " + address + "\nType: " + type + "\nRent: $" + rent;
    }
}

