package kz.epam.training.bulletin_board.model.user;

import kz.epam.training.bulletin_board.model.BoardImage;

import java.io.InputStream;

/**
 * @author Abay Assenov
 */

public class PhotoUser extends BoardImage {

    private int userId;
    private InputStream photoAdvertStream;//This is use for reading coming photo

    public PhotoUser(int userId, InputStream photoAdvertStream) {
        this.userId = userId;
        this.photoAdvertStream = photoAdvertStream;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public InputStream getPhotoAdvertStream() {
        return photoAdvertStream;
    }

    public void setPhotoAdvertStream(InputStream photoAdvertStream) {
        this.photoAdvertStream = photoAdvertStream;
    }
}
