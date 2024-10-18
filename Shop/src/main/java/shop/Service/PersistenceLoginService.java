package shop.Service;

import shop.DTO.Persistence_logins;

public interface PersistenceLoginService {
	
	// 토큰 등록
	public Persistence_logins insert(String username);
	
	// 토큰 조회 (아이디)
	public Persistence_logins select(String username);
	
	// 토큰 조회 (토큰)
	public Persistence_logins selectByToken(String token);
	
	
	// 토큰 수정
	public Persistence_logins update(String username);
	
	//토큰 갱신(없으면 등록, 있으면 수정)
	public Persistence_logins refresh(String username);
	
	// 토큰 유효성 체크(만료 여부 확인)
	public boolean isValid(String token);
	
	// 토큰 삭제
	public boolean delete(String username);
		
	}

