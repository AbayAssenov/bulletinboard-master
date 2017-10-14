package kz.epam.training.bulletin_board.model.advert;

import java.sql.Date;

/**
 * @author Abay Assenov
 */

public class Advert {

    private int id;
    private int userId;
    private String title;
    private int heading;
    private boolean swap;
    private String description;
    private Date date;
    private int districtId;
    private String address;
    private String priceMoney;
    private boolean priceContract;
    private boolean checkAdmin;
    private boolean isArchive;

    //This is constructor contain only values that need for set to DataBase, all except(id,user_id)
    public Advert(int userId, String title, int heading, boolean swap,
                  String description, Date date, int districtId,
                  String address, String priceMoney, boolean priceContract) {
        this.userId = userId;
        this.title = title;
        this.heading = heading;
        this.swap = swap;
        this.description = description;
        this.date = date;
        this.districtId = districtId;
        this.address = address;
        this.priceMoney = priceMoney;
        this.priceContract = priceContract;
    }

    public Advert() {
    }


    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public boolean isCheckAdmin() {
        return checkAdmin;
    }

    public void setCheckAdmin(boolean checkAdmin) {
        this.checkAdmin = checkAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isSwap() {
        return swap;
    }

    public void setSwap(boolean swap) {
        this.swap = swap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(String priceMoney) {
        this.priceMoney = priceMoney;
    }

    public boolean isPriceContract() {
        return priceContract;
    }

    public void setPriceContract(boolean priceContract) {
        this.priceContract = priceContract;
    }


}
