package kz.epam.training.bulletin_board.model.advert;

import kz.epam.training.bulletin_board.model.BoardImage;

import java.io.InputStream;

/**
 * @author Abay Assenov
 */

public class PhotoAdvert extends BoardImage{

    private int advertId;
    private InputStream photoAdvertStream;//This is use for reading coming photo

    public PhotoAdvert() {}

    public PhotoAdvert(InputStream photoAdvertStream, int advertId) {
        this.advertId = advertId;
        this.photoAdvertStream = photoAdvertStream;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    public InputStream getPhotoAdvertStream() {
        return photoAdvertStream;
    }

    public void setPhotoAdvertStream(InputStream photoAdvertStream) {
        this.photoAdvertStream = photoAdvertStream;
    }

    public int getAdvertId() {
        return advertId;
    }
}
