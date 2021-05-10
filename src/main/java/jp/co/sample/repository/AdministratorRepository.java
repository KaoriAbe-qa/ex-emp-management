package jp.co.sample.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jp.co.sample.domain.Administrator;

/** 
 *  administratorsテーブルを操作するリポジトリクラス
 *  @author abe
 *  */

@Repository
public class AdministratorRepository {
	
	/** RowMapperの定義 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
	 = (rs,i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	//NamedParameterJdbcTemplate型のtemplate変数の宣言
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** 管理者情報を挿入するメソッド */
	public void insert(Administrator administrator) {
		SqlParameterSource param
		 = new BeanPropertySqlParameterSource(administrator);
		
		String insertSql
		 = "INSERT INTO administrators(name,mail_address,password)"
		 		+ " VALUES(:name,:mailAddress,:password);";
		template.update(insertSql, param);
	}
	
	/** メールアドレスとパスワードから管理者情報を取得するメソッド 
	 * @return 管理者情報（何もなければnullを返す）*/
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String findSql
		 = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAdress AND password=:password ORDER BY id;";
		
		SqlParameterSource param
		 = new MapSqlParameterSource().addValue("mail_address",mailAddress).addValue("password", password);
		
		List<Administrator> administratorList
		 = template.query(findSql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
		return null;
		}
		return administratorList.get(0);
	}

}
