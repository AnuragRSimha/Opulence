package opulence.homepage;

import java.awt.*;

public class homepage_M {
    private String image;
    private Image img;

    public void setImage(String image) {
        this.image = image;
    }

    public Image getImg() {
        img = Toolkit.getDefaultToolkit().getImage(image);
        return img;
    }

}
