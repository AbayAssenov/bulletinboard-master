package kz.epam.training.bulletin_board.constant;

/**
 * Created by Abay on 9/19/2017.
 */
public class CabinetConstant {
    //Actions and commands
    public static final String ACTION = "action";//action administrator
    public static final String REMOVE_AD = "remove_advert";//action (remove advert)
    public static final String CHECKED_AD = "checked_advert";//action administrator (check true in db)
    public static final String UNCHECKED_AD = "unchecked_advert";//action administrator (block advert)
    public static final String SET_PHOTO_USER = "set_photo_user";//action photo user user
    public static final String SET_AD_IS_ARCHIVE = "set_advert_is_archive";//
    public static final String CHANGE_PASSWORD = "change_password";//action change current password
    public static final String USER_CABINET_COMMAND= "user_cabinet_action";
    public static final String ADMIN_CABINET_COMMAND= "admin_cabinet_action";
    public static final String CABINET_COMMAND_PROFILE= "profile";
    public static final String CABINET_COMMAND_USER_ADVERTS= "user_adverts";
    public static final String CABINET_COMMAND_ADVERTS_IS_UNCHECKED= "adverts_is_unchecked";
    public static final String CABINET_COMMAND_USERS_ON_ONE_PAGE= "users_on_one_page";

    //This is section in cabinet user or admin
    public static final String BLOCK = "block";//block this is name of items menu in cabinets(user's,admin's)
}
