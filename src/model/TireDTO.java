package model;

public class TireDTO {
    private int tirenum;
    private int brand;
    private int size;
    private String name;
    private int price;
    private int stock;

    public TireDTO(int tirenum, int brand, int size,String name, int price, int stock) {
        this.tirenum = tirenum;
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public TireDTO(int brand, int size, String name, int price, int stock) {
        this.brand = brand;
        this.size = size;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public TireDTO(int tirenum, String name, int price, int stock) {
        this.tirenum = tirenum;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }



    public int getTirenum() {
        return tirenum;
    }

    public void setTirenum(int tirenum) {
        this.tirenum = tirenum;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TireDTO{" +
                "tirenum=" + tirenum +
                ", brand=" + brand +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
