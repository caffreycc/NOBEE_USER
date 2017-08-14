package com.ricelink.interfaceService.ipad.pojo.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricelink.interfaceService.ipad.pojo.Field;


import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 * 客户信息
 */
public class CustomerInfo {
    private List<RLOpportunityModel> data;
    private List<Field> field;
    @JsonProperty("SQL")
    private String sql;
    private Integer total;

    public List<RLOpportunityModel> getData() {
        return data;
    }

    public void setData(List<RLOpportunityModel> data) {
        this.data = data;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> field) {
        this.field = field;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
