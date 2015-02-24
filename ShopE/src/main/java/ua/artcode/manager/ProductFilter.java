package ua.artcode.manager;

/**
 * Created by serhii on 24.02.15.
 */
public class ProductFilter {

    private int offset;
    private int length;
    private String type;

    public ProductFilter(int offset, int length, String type) {
        this.offset = offset;
        this.length = length;
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
