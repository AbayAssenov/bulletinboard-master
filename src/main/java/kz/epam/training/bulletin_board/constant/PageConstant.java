package kz.epam.training.bulletin_board.constant;

/**
 * Created by Abay on 9/19/2017.
 */
public class PageConstant {

    // Pages
    public static final int ADVERT_ON_ONE_PAGE = 3;//Max count advert on one page( SEARCH FORM)
    public static final int USERS_ON_ONE_PAGE = 5;//Max count advert on one page( SEARCH FORM)
    public static final int DEFAULT_SELECTED_PAGE = 1;//id heading label()
    public static final int DEFAULT_DISPLAY_UNCHECK_AD = 5;// This is count of display unchecked adverts
    public static final int DEFAULT_DISPLAY_USER_AD = 5;// This is count of display user's adverts
    public static final String COUNT_PAGE_USERS = "countPageUsers";//Count page display users label in admin cabinet
    public static final String SELECTED_PAGE = "selectedPage";//id heading label()
    public static final String SELECTED_PAGE_USERS = "selectedPageUsers";//
    public static final String SELECTED_PAGE_UNCHECKED_AD = "selectedPageUncheckedAd";// unchecked adverts page
    public static final String ADVERTS_IS_UNCHECKED = "advertsIsUnchecked";// This is unchecked adverts
    public static final String COUNT_PAGE_AD_IS_UNCHECKED = "countPageAdUnchecked";// This is count page unchecked adverts( Pagination)
    public static final String COUNT_PAGE = "countPage";//Count page label
    public static final String COMMON_LINKS =
            "        <link href=\"../../css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "        <link href=\"../../css/main.css\" rel=\"stylesheet\">\n" +
            "        <link href=\"../../css/main-form.css\" rel=\"stylesheet\">\n" +
            "        <script src=\"../../js/jquery-1.11.3.min.js\"></script>\n" +
            "        <script src=\"../../js/bootstrap.min.js\"></script>\n" +
            "\t\t";//Common links on pages
}
