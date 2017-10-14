package kz.epam.training.bulletin_board.model.advert;

/**
 * @author Abay Assenov
 */

public class PhoneAdvert {

    private int id;
    private String phoneNumber;
    private int advertId;

    public PhoneAdvert(String phoneNumber, int advertId) {
        this.phoneNumber = phoneNumber;
        this.advertId = advertId;
    }
    public PhoneAdvert(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAdvertId() {
        return advertId;
    }
}
