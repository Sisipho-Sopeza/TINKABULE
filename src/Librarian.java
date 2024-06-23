public class Librarian {
    private String name;
    private String password;
    private String email;
    private String address;
    private String city;
    private String contact;

    public Librarian(String name, String password, String email, String address, String city, String contact) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getContact() {
        return contact;
    }

    public String toCSV() {
        return name + "," + password + "," + email + "," + address + "," + city + "," + contact;
    }
}
