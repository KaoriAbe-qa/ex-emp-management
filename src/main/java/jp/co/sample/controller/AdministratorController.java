package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.form.InsertAdministratorForm;
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
	
	

}
