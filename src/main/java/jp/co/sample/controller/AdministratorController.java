package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/** 
 * 管理者機能の処理の制御を行うコントローラークラス
 * @author abe
 *  */

@Controller
@RequestMapping
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/** 
	 * InsertAdministratorFormをインスタンス化するメソッド
	 * @return インスタンス化しそのまま返す 
	 * */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/** 
	 * viewにフォワード
	 * @return administrator/insert.html
	 *  */
	@RequestMapping("/toInsert")
	public String toInsert() {
		//管理者登録画面にフォワード
		return "administrator/insert";
	}
	
	/** 管理者情報を登録するメソッド
	 * @return administrator/login.html
	 * */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		//formオブジェクトからadministratorに中身をコピー
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		
		administratorService.insert(administrator);
		//ログイン画面にフォワード
		return "administrator/login"; 
	}
	
	/** 
	 * LoginFormをインスタンス化するメソッド
	 * @return インスタンス化しそのまま返す 
	 * */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/** ログイン画面に遷移するメソッド
	 * @return administrator/login.html
	 * */
	@RequestMapping("/")
	public String toLogin() {
		//ログイン画面にフォワード
		return "administrator/login";
	}
	
	//sessionスコープを使うための設定
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator loginResult = administratorService.login(form.getMailAddress(), form.getPassword()); 
		
		if(loginResult == null) {
			model.addAttribute("message", "メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}
		//sessionスコープに管理者名を格納
		session.setAttribute("administratorName", loginResult.getName());
		//従業員一覧情報ページにフォワード
		return "employee/List";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "administrator/login";
	}

}
