public class Visitor {
    private int id;
    private String name;
    private String purpose;
    private String contact;
    private String checkin;

    public Visitor(int id, String name, String purpose, String contact, String checkin) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.contact = contact;
        this.checkin = checkin;
    }

    public Visitor(String name, String purpose, String contact) {
        this.name = name;
        this.purpose = purpose;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPurpose() { return purpose; }
    public String getContact() { return contact; }
    public String getCheckin() { return checkin; }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s", id, name, purpose, contact, checkin);
    }
}
