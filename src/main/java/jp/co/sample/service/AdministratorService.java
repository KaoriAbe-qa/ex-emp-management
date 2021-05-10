package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/** 
 * 管理者関連機能の業務処理を行うサービスクラス
 * @author abe
 *  */

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	/** 管理者情報を挿入するメソッド */
	public void insert(Administrator administrator) {
		//administratorRepositoryのinsert()メソッドを呼ぶ
		administratorRepository.insert(administrator);
	}
	
	/** ログイン処理するメソッド
	 * @return 管理者情報 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
}
