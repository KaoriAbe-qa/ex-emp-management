package jp.co.sample.form;

/**
 * 従業員更新時に使用するフォーム
 * @author abe
 * */

public class UpdateEmployeeForm {
	/** 従業員ID */
	private String id;
	/** 扶養人数 */
	private String dependendsCount;
	
	//以下ゲッターセッター
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependendsConut() {
		return dependendsCount;
	}
	public void setDependendsCount(String dependendsCount) {
		this.dependendsCount = dependendsCount;
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependendsCount=" + dependendsCount + "]";
	}
	

}
