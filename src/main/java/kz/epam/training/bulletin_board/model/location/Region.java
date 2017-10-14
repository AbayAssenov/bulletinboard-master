package kz.epam.training.bulletin_board.model.location;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class Region {

    private List<District> districts = new ArrayList<District>();
    private String regionName;
    private int regionId;

    public Region() {
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public void addDistrict(District newDistrict) {
        districts.add(newDistrict);
    }

    public List<District> getDistricts() {
        return districts;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
