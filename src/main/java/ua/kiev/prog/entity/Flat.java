package ua.kiev.prog.entity;

/**
 * Created by Вадим on 02.09.2017.
 */
public class Flat {

    private int id;
    private String district;
    private String address;
    private double square;
    private int rooms;
    private int price;

    public Flat() {
    }

    public Flat(int id, String district, String address, double square, int rooms, int price) {
        this.id = id;
        this.district = district;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public Flat(String district, String address, double square, int rooms, int price) {
        this.district = district;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", square=" + square +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
