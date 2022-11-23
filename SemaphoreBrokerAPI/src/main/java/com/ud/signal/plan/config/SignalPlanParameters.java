package com.ud.signal.plan.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms.configuration.signal")
public class SignalPlanParameters {

	private String pathPlanTotal;
	private String pathPlanConexion;
	private String pathPlanDesConexion;



}
