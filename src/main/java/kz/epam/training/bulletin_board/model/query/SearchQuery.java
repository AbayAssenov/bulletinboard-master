package kz.epam.training.bulletin_board.model.query;

/**
 * @author Abay Assenov
 */

public class SearchQuery {

    private Integer currentPage;
    private Integer countAdvertOnPage;
    private Integer headingId;
    private String searchWord;
    private Boolean inDescription;
    private Integer districtId;
    private Integer priceFrom;
    private Integer priceTo;
    private Boolean orderByNew;
    private Boolean orderByCheap;
    private Boolean orderByExpensive;
    private Boolean onlyWithPhoto;

    public Boolean getOnlyWithPhoto() {
        return onlyWithPhoto;
    }

    public void setOnlyWithPhoto(Boolean onlyWithPhoto) {
        this.onlyWithPhoto = onlyWithPhoto;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCountAdvertOnPage() {
        return countAdvertOnPage;
    }

    public void setCountAdvertOnPage(Integer countAdvertOnPage) {
        this.countAdvertOnPage = countAdvertOnPage;
    }

    public Integer getHeadingId() {
        return headingId;
    }

    public void setHeadingId(Integer headingId) {
        this.headingId = headingId;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Boolean getInDescription() {
        return inDescription;
    }

    public void setInDescription(Boolean inDescription) {
        this.inDescription = inDescription;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public Boolean getOrderByNew() {
        return orderByNew;
    }

    public void setOrderByNew(Boolean orderByNew) {
        this.orderByNew = orderByNew;
    }

    public Boolean getOrderByCheap() {
        return orderByCheap;
    }

    public void setOrderByCheap(Boolean orderByCheap) {
        this.orderByCheap = orderByCheap;
    }

    public Boolean getOrderByExpensive() {
        return orderByExpensive;
    }

    public void setOrderByExpensive(Boolean orderByExpensive) {
        this.orderByExpensive = orderByExpensive;
    }
}
