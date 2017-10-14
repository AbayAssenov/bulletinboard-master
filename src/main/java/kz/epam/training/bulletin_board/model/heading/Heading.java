package kz.epam.training.bulletin_board.model.heading;

/**
 * @author Abay Assenov
 */

public class Heading {

    private int headingId;
    private String headingName;
    private String headingImage;

    public Heading(int headingId, String headingName, String headingImage) {
        this.headingId = headingId;
        this.headingName = headingName;
        this.headingImage = headingImage;
    }

    public int getHeadingId() {
        return headingId;
    }

    public void setHeadingId(int headingId) {
        this.headingId = headingId;
    }

    public String getHeadingName() {
        return headingName;
    }

    public void setHeadingName(String headingName) {
        this.headingName = headingName;
    }

    public String getHeadingImage() {
        return headingImage;
    }

    public void setHeadingImage(String headingImage) {
        this.headingImage = headingImage;
    }
}

