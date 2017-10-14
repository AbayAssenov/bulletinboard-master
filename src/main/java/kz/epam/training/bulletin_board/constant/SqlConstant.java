package kz.epam.training.bulletin_board.constant;

/**
 * Created by Abay on 9/19/2017.
 */
public class SqlConstant {
    //SQL
    public static final String SQL_PHOTO_AD_BY_AD_ID = "SELECT id,photo FROM photo_adverts WHERE adverts_id=?"; //
    public static final String SQL_SELECT_COUNT_IS_UNCHECKED_AD = "SELECT count(*) FROM adverts WHERE check_admin=FALSE "; //
    public static final String SQL_READ_AD_IS_UNCHECKED_AD = "SELECT id,title,date,user_id FROM adverts WHERE check_admin=FALSE LIMIT "; //
    public static final String SQL_SELECT_AD_BY_USER_ID_WITH_LIMIT = "SELECT id,title,date,archive,user_id FROM adverts WHERE user_id=? LIMIT ?,?"; //
    public static final String SQL_SELECT_ALL_HEADINGS_BY_LOCALE_1 = "SELECT id,heading_name_"; //
    public static final String SQL_SELECT_ALL_HEADINGS_BY_LOCALE_2 = ",heading_photo FROM heading"; //
    public static final String SQL_SELECT_ALL_REGIONS_1 = "SELECT id ,name_"; //
    public static final String SQL_SELECT_ALL_REGIONS_2 = " FROM location WHERE id=id_parent"; //
    public static final String SQL_SELECT_ALL_DISTRICT_BY_REGION_ID_1 = "SELECT id,name_"; //
    public static final String SQL_SELECT_ALL_DISTRICT_BY_REGION_ID_2 = " FROM location WHERE id<>location.id_parent AND id_parent="; //
    public static final String SQL_INSERT_AD_PHONE = "INSERT INTO phone(phone_number, advert_id) VALUES (?, ?)"; //
    public static final String SQL_INSERT_USER = "INSERT INTO users (user_name,user_email,user_password) VALUES (?, ?, ?)"; //
    public static final String SQL_SELECT_USER_EMAIL = "SELECT user_email FROM users WHERE user_email=?"; //
    public static final String SQL_SELECT_USER_PASSWORD = "SELECT user_password FROM users WHERE id=?"; //
    public static final String SQL_SELECT_PHONE_BY_AD_ID = "SELECT phone_number FROM phone WHERE advert_id=?"; //
    public static final String SQL_SELECT_USERS_WITH_LIMIT = "SELECT id,user_name,user_email,user_photo FROM users LIMIT "; //
    public static final String SQL_FIRST_PHOTO_AD_BY_AD_ID = "SELECT photo FROM photo_adverts WHERE adverts_id=? LIMIT 1"; //
    public static final String SQL_INSERT_PHOTO_AD = "INSERT INTO photo_adverts(photo, adverts_id) VALUES (?, ?)"; //
    public static final String SQL_DELETE_AD_BY_ID = "DELETE FROM adverts WHERE id=?"; // delete advert by id
    public static final String SQL_UPDATE_CHECK_ADMIN_AD_BY_ID = "UPDATE adverts SET check_admin=? WHERE id=?"; // update "check admin" advert by id
    public static final String SQL_UPDATE_ARCHIVE_AD_BY_ID = "UPDATE adverts SET archive=? WHERE id=?"; // update "is archive" advert by id
    public static final String SQL_UPDATE_USER_PHOTO = "UPDATE users SET user_photo=? WHERE id=?"; // update user photo by id
    public static final String SQL_UPDATE_USER_PASSWORD = "UPDATE users SET user_password=? WHERE id=?"; // update user password by id
    public static final String SQL_SELECT_AD_BY_ID = "SELECT id,title, heading_id, swap, description,date, district_id, address, price_money,price_contract,user_id,check_admin FROM adverts WHERE adverts.id=?"; //
    public static final String SQL_SELECT_ALL_AD = "SELECT DISTINCT adverts.id,title, heading_id, swap, description,date, district_id, address, price_money,price_contract,user_id,check_admin FROM adverts "; //
    public static final String SQL_SELECT_USER_BY_EMAIL_PASSWORD = "SELECT id,user_name,user_role FROM users WHERE user_email=? AND user_password=?"; //
    public static final String SQL_SELECT_USER_BY_ID = "SELECT id,user_name,user_email,user_photo FROM users WHERE id=?"; //
    public static final String SQL_WHERE_ONLY_CHECKED_AND_IS_NOT_ARCHIVE = "WHERE check_admin=TRUE AND archive=FALSE "; //
    public static final String SQL_INNER_JOIN_BY_PHOTO_AD = "INNER JOIN photo_adverts ON photo_adverts.adverts_id = adverts.id "; //
    public static final String SQL_AND_HEADING_ID = " AND heading_id="; //
    public static final String SQL_AND_TITLE_LIKE = " AND title LIKE '%"; //
    public static final String SQL_AND_TITLE_LIKE_WITH_BRACKET = " AND (title LIKE '%"; //
    public static final String SQL_OR_DESCRIPTION_LIKE = " OR description LIKE '%"; //
    public static final String SQL_AND_DISTRICT_ID = " AND district_id="; //
    public static final String SQL_AND_PRICE_MONEY_FROM = " AND price_money>="; //
    public static final String SQL_AND_PRICE_MONEY_TO = " AND price_money<="; //
    public static final String SQL_ORDER_BY = " ORDER BY "; //
    public static final String SQL_SORT_BY_NEW = "year(date),month(date),day(date) "; //
    public static final String SQL_SORT_BY_CHEAP = "CAST(price_money AS SIGNED) ASC "; //
    public static final String SQL_SORT_BY_EXPENSIVE = "CAST(price_money AS SIGNED) DESC "; //
    public static final String SQL_END_LIKE = "%'"; //
    public static final String SQL_END_LIKE_WITH_BRACKET = "%')"; //
    public static final String SQL_LIMIT = " LIMIT "; //
    public static final String SQL_COMMA = ","; //
    public static final String SQL_COUNT_AD = "SELECT DISTINCT adverts.id FROM adverts "; //
    public static final String SQL_SELECT_USER_ID_BY_AD_ID = "SELECT user_id FROM adverts WHERE id=?"; //
    public static final String SQL_COUNT_USERS = "SELECT count(*) FROM users"; //count(*)
    public static final String SQL_COUNT_AD_BY_USER = "SELECT DISTINCT adverts.id FROM adverts INNER JOIN users ON adverts.user_id = "; //count(*)
    public static final String SQL_INSERT_AD = "INSERT INTO adverts(title, heading_id, swap, description, date, district_id, address, price_money, price_contract, user_id) VALUES (?,?,?,?,?,?,?,?,?,?)"; //
    public static final String SQL_SELECT_LOCATION_BY_ID_1 = "SELECT name_"; //
    public static final String SQL_SELECT_LOCATION_BY_ID_2 = ",id_parent FROM location WHERE id=?"; //
    public static final String ORDER_BY_NEW = "order_by_new"; // order result first new adverts
    public static final String ORDER_BY_CHEAP = "order_by_cheap"; // order result first cheap adverts
    public static final String ORDER_BY_EXPENSIVE = "order_by_expensive"; // order result first expensive adverts
}
