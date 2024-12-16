package model;

public class ServiceDTO {
    private int serviceid;
    private String servicename;
    private int price;

    public ServiceDTO(int serviceid, String servicename, int price) {
        this.serviceid = serviceid;
        this.servicename = servicename;
        this.price = price;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "serviceid=" + serviceid +
                ", servicename='" + servicename + '\'' +
                ", price=" + price +
                '}';
    }
}
