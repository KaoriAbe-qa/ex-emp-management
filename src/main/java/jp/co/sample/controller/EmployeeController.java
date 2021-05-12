package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	/** 従業員詳細(ここでは扶養人数のみ)を更新する
	 * @return  「/employee/showList」にリダイレクト*/
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form
			, BindingResult result
			, RedirectAttributes redirectAttributes
			, Model model) {
		
		if(result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		
		//確認用
		System.out.println(form.getId());
		System.out.println(form.getDependentsCount());
		
		//Employee ドメインを主キー検索する
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		//扶養人数を検索してきたEmployee ドメインにセットし上書き
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		
		employeeService.update(employee);
		//リダイレクト
		return "redirect:/employee/showList";
	}
}
