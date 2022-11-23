package com.ud.signal.plan.service;

import com.ud.signal.plan.config.SignalPlanParameters;
import com.ud.signal.plan.response.ServiceResponse;
import com.ud.socket.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
@RequiredArgsConstructor
@Slf4j
public class TotalPlanService {


	public static final String ERROR = "Error";
	@Autowired
	private SignalPlanParameters signalPlanParameters;
	@Autowired
	private WebClient webClient;

	private static final String CTE_AUTHORIZATION = "Authorization";



	public ResponseEntity<ServiceResponse<Object>> getPlanTotalOrch(String intersection) {


		log.info("Plan Total Interseccion: "+ intersection );
		ResponseEntity<ServiceResponse<Object>> responseEntity;

		try{
				JSONObject jsonInterseccion = this.getPlanTotal(intersection).block();
				responseEntity = new ResponseEntity<>(new ServiceResponse<>(jsonInterseccion), HttpStatus.OK);
		}catch (Exception e){
			responseEntity = new ResponseEntity<>(new ServiceResponse<>("error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Fin Plan Total Interseccion "+intersection);
		return responseEntity;
	}

	public Mono<JSONObject> getPlanTotal(String intersection){


		//consumir el api de token de latam devolviendo
		return webClient.get()
				.uri(signalPlanParameters.getPathPlanTotal()+"/"+intersection)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(JSONObject.class)
				.timeout( Duration.ofMillis( 15_000 ));
	}

	public ResponseEntity<ServiceResponse<Object>> setSignalPlanToSocket(String jsonObject, Client client, ResponseEntity<ServiceResponse<Object>> responseEntity){

		client.setJsonObject(jsonObject);
		return responseEntity;

	}

}
