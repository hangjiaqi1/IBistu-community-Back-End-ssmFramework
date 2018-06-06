package cn.yzy.testServer.entity;
/*
 * created by yzy on 2017/11/8
 * 
 */
public class DataResult {
	private int errno;
	private Object data;
	private String error;

	public DataResult() {
	}

	public DataResult(Object data) {
		this.errno = 0;
		this.data = data;
	}

	public DataResult(int errno, String error) {
		this.errno = errno;
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

}