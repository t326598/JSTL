package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User_files {
	int no;
	String file_name;
	String file_path;
	int parent_no;
	Date reg_date;
	Date upd_date;
}