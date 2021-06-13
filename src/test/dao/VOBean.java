package test.dao;

import java.util.List;

public class VOBean {
	int code;
	String msg;
	int count;
	List<Object> data;
	
	public VOBean(){
		
		}
	
	public VOBean(int code, String msg, int count, List<Object> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	@Override
	public String toString() {
		return "VOBean [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
}
