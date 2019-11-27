package ua.com.epam.entity.book.nested;

import java.util.Objects;

public class Additional {
    private Integer pageCount;
    private Size size;

    public Additional(){}

    public Additional(Integer pageCount, Size size) {
        this.pageCount = pageCount;
        this.size = size;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Additional)) return false;
        Additional that = (Additional) o;
        return Objects.equals(getPageCount(), that.getPageCount()) &&
                Objects.equals(getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPageCount(), getSize());
    }
}
