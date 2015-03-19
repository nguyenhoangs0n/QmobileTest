package dbiz.vn.qmobile.Model;

/**
 * Created by nguyenhoang on 3/4/2015.
 */
public class ItemInfor {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String img_url;
    private String price;
}
