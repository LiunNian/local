package cn.org.cpcca.zuulserver.models;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnDataModel {
    private int code = 1;
    private Object data;
    private String message = "";
    private String redirect = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(data==null){
            this.data = new HashMap<String,Object>();
        }
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public ReturnDataModel(int code, String message) {
        this.data = data;
        this.message = message;
    }

    public ReturnDataModel(int code, String message, String redirect) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }

    public ReturnDataModel(int code, List data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ReturnDataModel(int code, List data, String message, String redirect) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }


    public ReturnDataModel(Map<String, Object> data, String message) {
        this.data = data;
        this.message = message;
    }

    public ReturnDataModel(Map<String, Object> data, String message, String redirect) {
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }

    public ReturnDataModel(int code, Map<String, Object> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ReturnDataModel(int code, Map<String, Object> data, String message, String redirect) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }

    public ReturnDataModel(String message) {
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }

    public ReturnDataModel(String message, String redirect) {
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }

    public ReturnDataModel(List data, String message) {
        this.data = data;
        this.message = message;
    }

    public ReturnDataModel(List data, String message, String redirect) {
        this.data = data;
        this.message = message;
        this.redirect = redirect;
    }
}