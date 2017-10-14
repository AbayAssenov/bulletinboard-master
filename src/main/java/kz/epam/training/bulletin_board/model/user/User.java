package kz.epam.training.bulletin_board.model.user;

/**
 * @author Abay Assenov
 */

public class User {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String photoUser;
    private int userId;
    private int userRole;


    public User() {
    }

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.userPassword = password;
    }

    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = password;
    }

    public User(String userName, String userEmail, String password, String photoUser) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = password;
        this.photoUser = photoUser;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return userPassword;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }
}
