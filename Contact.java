
package de.davidgollasch.emiexercise2;

/**
 * Created by Oskar on 18.11.2016.
 */
public class Contact  implements java.io.Serializable {

    private String title;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String country;

    public Contact (String title, String firstName, String lastName, String address, String city, String zip, String country){
        this.setTitle(title);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setCity(city);
        this.setZip(zip);
        this.setCountry(country);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString () {
        return this.getTitle() + " " + this.getFirstName() + " " + this.getLastName();
    }
}

