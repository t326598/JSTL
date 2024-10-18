package shop.Service;

import shop.DAO.UserDAO;
import shop.DTO.Users;
import shop.utils.PasswordUtils;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAO();
	
	@Override
	public int signup(Users user) {
		// 비밀번호 암호화
		// * 암호화 알고리즘 : SHA-256, Bcrypt ...
		// 123456 ---> F123465465461/@329
		
		String encodedPassword = PasswordUtils.encoded(user.getPassword());
		user.setPassword(encodedPassword);
		// 회원 정보 등록 요청
		int result = userDAO.signup(user);
		if(result > 0) System.out.println("회원가입 성공!");
		else System.out.println("회원 가입 실패!");
		
		// 회권 기본 권학 등록....
		return result;
	}

	@Override
	public Users login(Users user) {
		String username = user.getUsername();
		Users selectedUser = userDAO.select(username);
		// 회원 가입이 안 된 아이디
		if(selectedUser == null) {			
			return null;
		}
		// 비밀번호 일치 여부 확인
		String loginPassword = user.getPassword();
		String password = selectedUser.getPassword();
		
		// Bcrypt.check(로그인, 비밀번호, 회원가입된 아호화된 비밀번호)
		boolean check = PasswordUtils.check(loginPassword, password);
		
		if(!check) {
			return null;
		}
		return selectedUser;
		
		
		
	}

	@Override
	public Users select(int no) {
		Users user = userDAO.select(no);
		return user;
	}

	@Override
	public Users select(String username) {
		Users user = userDAO.select(username);
		
		return user;
	}

	
	
}
