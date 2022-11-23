package com.ud.signal.plan.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@PropertySource(value = {"application.yml"})
public class WebFluxConfig implements WebFluxConfigurer {

	@Value("${ms.configuration.signal.basePath:}")
	private String basePath;

	@Bean
	public WebClient getWebClient(WebClient.Builder webClientBuilder){
		return webClientBuilder
				.clientConnector(new ReactorClientHttpConnector(getHttpClient()))
				.baseUrl(basePath)
				.build();
	}

	private HttpClient getHttpClient(){
		return HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 240_000)
				.doOnConnected(conn -> conn
						.addHandlerLast(new ReadTimeoutHandler(240))
						.addHandlerLast(new WriteTimeoutHandler(240)));
	}
}
