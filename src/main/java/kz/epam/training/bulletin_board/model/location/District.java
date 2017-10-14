package kz.epam.training.bulletin_board.model.location;

/**
 * @author Abay Assenov
 */

public class District {
    private String districtName;
    private int districtId;

    public District() {
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
