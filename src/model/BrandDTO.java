package model;

public class BrandDTO {
    private int brand;
    private String brandname;

    public BrandDTO(int brand, String brandname) {
        this.brand = brand;
        this.brandname = brandname;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "brand=" + brand +
                ", brandname='" + brandname + '\'' +
                '}';
    }
}
