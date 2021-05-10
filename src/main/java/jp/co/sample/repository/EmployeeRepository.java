package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/** 
 *  employeesテーブルを操作するリポジトリクラス
 *  @author abe
 *  */

@Repository
public class EmployeeRepository {
	
	/** RowMapperの定義 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	 = (rs,i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsConut(rs.getInt("dependentsConut"));
		return employee;
	};
	
	//NamedParameterJdbcTemplate型のtemplate変数の宣言
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** 従業員一覧情報を入社日順(降順)で取得するメソッド
	 * @return  従業員一覧情報(従業員が存在しない場合サイズ0件の従業員一覧を返す)*/
	public List<Employee> findAll(){
		String findSql
		 = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
		 		+ " FROM employees ORDER BY id";
		List<Employee> employeeList
		 = template.query(findSql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/** 主キーから従業員情報を取得するメソッド
	 * @return 従業員情報（従業員が存在しない場合Springが自動的に例外を発生） */
	public Employee load(Integer id) {
		String loadSql
		 = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
		 		+ " FROM employees WHERE id=:id;";
		//プレースホルダにidを埋め込む
		SqlParameterSource param
		 = new MapSqlParameterSource().addValue("id", id);
		
		Employee employee
		 = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/** 従業員情報を変更するメソッド */
	public void update(Employee employee) {
		SqlParameterSource param
		 = new BeanPropertySqlParameterSource(employee);
		
		String updateSql
		 = "UPDATE employees name=:name,image=:image,gender=:gender,hire_date=:hireDate"
		 		+ ",mail_address=:mailAddress,zip_code=:zipCode,address=:address"
		 		+ ",telephone=:telephone,salary=:salary,characteristics=:characteristics,dependents_conut=:dependentsConut";
		template.update(updateSql, param);
	}

}
