package apiBuilders;

import lombok.Data;

@Data
public class createEnquiryPayload {
	private String query;
	private Object variables;

}
