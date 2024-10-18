package shop.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data  //getter, setter 기본생성자 toString 자동 생성
@AllArgsConstructor // 모든 매개변수가 있는 생성자
@Builder // 빌더 패턴 적용
@NoArgsConstructor
public class Users {
	private int no;
	private String username;
	private String password;
	private String name;
	private String email;
	private Boolean enabled = true;
	private Date regDate;
	private Date updDate;

	
	
}
