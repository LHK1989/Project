package model;

public class SizeDTO {
    private int sizeid;
    private String size;

    public SizeDTO(int sizeid, String size) {
        this.sizeid = sizeid;
        this.size = size;
    }

    public int getSizeid() {
        return sizeid;
    }

    public void setSizeid(int sizeid) {
        this.sizeid = sizeid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SizeDTO{" +
                "sizeid=" + sizeid +
                ", size='" + size + '\'' +
                '}';
    }
}
