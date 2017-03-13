package fan.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rock on 17-2-28.
 */
public class BaseEntity implements Serializable {

    private Date createTime;
    private Date modTime;
    private Integer modNo;
    private String modPin;

    private String sortCondition;

    private int pageSize = 10;

    private long offset;

    private Long pageNum = 1L;

    private Integer yn;

    private Date createTimeStart;

    private Date createTimeEnd;

    private Date modTimeStart;

    private Date modTimeEnd;

    public void setPageSize(int pageSize) {
        if (pageSize < 1)
            pageSize = 1;
        this.pageSize = pageSize;
        offset = (getPageNum() - 1) * getPageSize();
    }

    public long getOffset() {
        return offset;
    }

    public Long getPageNum() {
        if (pageNum == null)
            pageNum = 1L;
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        if (pageNum == null || pageNum < 1)
            pageNum = 1L;
        this.pageNum = pageNum;
        offset = (getPageNum() - 1) * getPageSize();
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Integer getModNo() {
        return modNo;
    }

    public void setModNo(Integer modNo) {
        this.modNo = modNo;
    }

    public String getModPin() {
        return modPin;
    }

    public void setModPin(String modPin) {
        this.modPin = modPin;
    }

    public String getSortCondition() {
        return sortCondition;
    }

    public void setSortCondition(String sortCondition) {
        this.sortCondition = sortCondition;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getModTimeStart() {
        return modTimeStart;
    }

    public void setModTimeStart(Date modTimeStart) {
        this.modTimeStart = modTimeStart;
    }

    public Date getModTimeEnd() {
        return modTimeEnd;
    }

    public void setModTimeEnd(Date modTimeEnd) {
        this.modTimeEnd = modTimeEnd;
    }
}
