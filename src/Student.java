public class Student {
    private int id;
    private String name;
    private String email;
    private String contact;

    public Student(int id, String name, String email, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String toCSV() {
        return id + "," + name + "," + email + "," + contact;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
}
