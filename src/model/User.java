package model;

public class User {
    private int id;
    private String name;
    private int age;
    private String bloodGroup;
    private int heartRate;
    private String bloodPressure;
    private String emergencyContact;

    public User() {}

    public User(String name, int age, String bloodGroup, 
                int heartRate, String bloodPressure,
                String emergencyContact) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.emergencyContact = emergencyContact;
    }

    public User(int id, String name, int age, String bloodGroup,
                int heartRate, String bloodPressure,
                String emergencyContact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.emergencyContact = emergencyContact;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "User [id=" + id + 
               ", name=" + name + 
               ", age=" + age + 
               ", bloodGroup=" + bloodGroup + 
               ", heartRate=" + heartRate +
               ", bloodPressure=" + bloodPressure +
               ", emergencyContact=" + emergencyContact + "]";
    }
}
