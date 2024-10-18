package shop.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persistence_logins {

	private int no;
	private String username;
	private String token;
	private Date expiryDate;
	private Date regDate;
	private Date updDate;
	
}
