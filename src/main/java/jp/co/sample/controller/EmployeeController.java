package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員関連機能の処理の制御を行うコントローラークラス
 * @author abe
 * */

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	/** 従業員一覧を出力する
	 * @return  employee/list.html */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> list = employeeService.showList();
		
		model.addAttribute("employeeList", list);
		
		return "employee/list";
	}
	
	/** UpdateEmployeeFormをインスタンス化するメソッド */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/** idで検索、従業員を出力する
	 * @return  employee/detail.html */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee info = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", info);
		return "employee/detail";
	}
}
