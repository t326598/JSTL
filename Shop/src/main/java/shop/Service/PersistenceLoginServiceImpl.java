package shop.Service;

import java.util.Date;

import shop.DAO.PersistenceLoginsDAO;
import shop.DTO.Persistence_logins;

public class PersistenceLoginServiceImpl implements PersistenceLoginService {

	private PersistenceLoginsDAO persistenceLoginsDAO = new PersistenceLoginsDAO();
	
	@Override
	public Persistence_logins insert(String username) {
		// TODO Auto-generated method stub
		return persistenceLoginsDAO.insert(username);
	}

	@Override
	public Persistence_logins select(String username) {
		
		return persistenceLoginsDAO.select(username);
	}

	@Override
	public Persistence_logins selectByToken(String token) {
		
		return persistenceLoginsDAO.selectBytoken(token);
	}

	@Override
	public Persistence_logins update(String username) {
		
				return persistenceLoginsDAO.update(username);
	}

	@Override
	public Persistence_logins refresh(String username) {
		// 토큰 조회
		Persistence_logins persistenceLogins = persistenceLoginsDAO.select(username);
		if (persistenceLogins == null) {
			// 토큰이 없는 경우 토큰 등록
			persistenceLogins =	persistenceLoginsDAO.insert(username);
			
		}
		else {
			// 토큰이 있는 경우 수정
			persistenceLogins = persistenceLoginsDAO.update(username);
			
		}
		
		return persistenceLogins;
	}

	@Override
	public boolean isValid(String token) {
		
		Persistence_logins persistenceLogins = persistenceLoginsDAO.selectBytoken(token);
		
		//토큰 없음
		if(persistenceLogins == null)
			return false;
		// 토큰 만료
		Date expiryDate = persistenceLogins.getExpiryDate();
		Date today = new Date();
		if(today.after(expiryDate)) {
			return false;
		}
		// 토큰 유효
		return true;
	}

}
