package com.ud.signal.plan.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommonMessage {

	private String code;
	private String severity;
	private String codeSeverity;
	private String message;

}
