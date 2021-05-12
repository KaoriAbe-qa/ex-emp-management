package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

/**
 * 従業員更新時に使用するフォーム
 * @author abe
 * */

public class UpdateEmployeeForm {
	/** 従業員ID */
	private String id;
	/** 扶養人数 */
	@NotBlank(message="扶養人数は必須です")
	private String dependentsCount;
	
	//以下ゲッターセッター
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	

}
