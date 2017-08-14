package com.ricelink.interfaceService.ipad.pojo;

/**
 * Created by Administrator on 2017/7/31.
 * 查询条件
 */
public class Criteria {
    public static final String OP_LIKE = "like";
    public static final String OP_EQUALS = "=";
    public static final String PROPERTY_ID = "b.ORG_ID";
    public static final String RULL_ALL = "all";
    private String _value;
    private String _property;
    private String _op;
    private String _likeRule;

    public String get_value() {
        return _value;
    }

    public void set_value(String _value) {
        this._value = _value;
    }

    public String get_property() {
        return _property;
    }

    public void set_property(String _property) {
        this._property = _property;
    }

    public String get_op() {
        return _op;
    }

    public void set_op(String _op) {
        this._op = _op;
    }

    public String get_likeRule() {
        return _likeRule;
    }

    public void set_likeRule(String _likeRule) {
        this._likeRule = _likeRule;
    }
}
