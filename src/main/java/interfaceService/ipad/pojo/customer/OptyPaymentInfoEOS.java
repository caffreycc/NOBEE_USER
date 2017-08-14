package interfaceService.ipad.pojo.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import interfaceService.ipad.pojo.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 * EOS返回付款记录实体
 */
public class OptyPaymentInfoEOS implements Serializable {
    @JsonProperty("SQL")
    private String sql;
    private String total;
    private List<Field> field;
    private List<OptyPaymentEos> data;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> field) {
        this.field = field;
    }

    public List<OptyPaymentEos> getData() {
        return data;
    }

    public void setData(List<OptyPaymentEos> data) {
        this.data = data;
    }
}
