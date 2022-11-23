package com.ud.signal.plan.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@NoArgsConstructor
@ApiModel(
		value = "service-response",
		description = "Generic object response "
)
public class ServiceResponse<T> {

	@JsonProperty("SignalPlan")
	private T signalPlan;

	public ServiceResponse(T signalPlan) {
		this.signalPlan = signalPlan;
	}
}
