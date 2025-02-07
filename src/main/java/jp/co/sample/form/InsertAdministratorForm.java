package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** 
 * 管理者登録時に使用するフォーム
 * @author abe
 *  */
public class InsertAdministratorForm {

	/** 名前 */
	@NotBlank(message="名前は必須です")
	private String name;
	/** メールアドレス */
	@Email(message="Eメールの形式が不正です")
	private String mailAddress;
	/** パスワード */
	@Size(min=4,max=20,message="パスワードは１文字以上２０字以内で記載してください")
	private String password;
	
	//以下ゲッターセッター
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
}
