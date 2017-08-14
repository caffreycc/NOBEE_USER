package interfaceService.ipad.pojo.dict;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/8/2.
 * 业务字典实体
 */
public class DictInfo {
    private String typeCode;
    private String code;
    private String value;
    private Integer orderBy;
    private Integer recordStatus;
    private Integer rank;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parentId;
    private String seqno;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filter1;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filter2;


    @JsonProperty(value = "typeCode")
    public String getTypeCode() {
        return typeCode;
    }

    @JsonProperty(value = "DICTTYPEID")
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @JsonProperty(value = "code")
    public String getCode() {
        return code;
    }

    @JsonProperty(value = "DICTID")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty(value = "value")
    public String getValue() {
        return value;
    }

    @JsonProperty(value = "DICTNAME")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty(value = "orderBy")
    public Integer getOrderBy() {
        return orderBy;
    }

    @JsonProperty(value = "SORTNO")
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

   /* @JsonProperty(value = "recordStatus")
    public Integer getRecordStatus() {
        return recordStatus;
    }*/

    @JsonProperty(value = "STATUS")
    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    /*@JsonProperty(value = "rank")
    public Integer getRank() {
        return rank;
    }*/

    @JsonProperty(value = "RANK")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty(value = "PARENTID")
    public String getParentId() {
        return parentId;
    }

    @JsonProperty(value = "PARENTID")
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

   /* @JsonProperty(value = "SEQNO")
    public String getSeqno() {
        return seqno;
    }*/

    @JsonProperty(value = "SEQNO")
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    @JsonProperty(value = "FILTER1")
    public String getFilter1() {
        return filter1;
    }

    @JsonProperty(value = "FILTER1")
    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    @JsonProperty(value = "FILTER2")
    public String getFilter2() {
        return filter2;
    }

    @JsonProperty(value = "FILTER2")
    public void setFilter2(String filter2) {
        this.filter2 = filter2;
    }
}
