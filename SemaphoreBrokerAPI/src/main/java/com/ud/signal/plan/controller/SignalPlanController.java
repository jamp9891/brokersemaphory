package com.ud.signal.plan.controller;

import com.ud.signal.plan.response.ServiceResponse;
import com.ud.signal.plan.service.ConexionPlanService;
import com.ud.signal.plan.service.DesconexionPlanService;
import com.ud.signal.plan.service.TotalPlanService;
import com.ud.socket.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/signal")
@Api(tags = {"signal"})
public class SignalPlanController {

	@Autowired
	private TotalPlanService totalPlanService;
	@Autowired
	private ConexionPlanService conexionPlanService;
	@Autowired
	private DesconexionPlanService desconexionPlanService;

	private static final String REG_EX = "\"[^a-zA-Z0-9]\"";
	private Client client;

	@ApiOperation(nickname = "get Total signal plan", value = "get total signal plan", notes = "get total signal plan ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation successful.", response = ServiceResponse.class)})
	@GetMapping(value = "/enviarPlanAutomatico/{intersection}/{puerto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Object>> enviarPlanAutomatico(@PathVariable(name ="intersection") final String intersection,
	@PathVariable(name ="puerto") final int puerto) throws IOException {
		String intersect =intersection == null ? "" :intersection.replaceAll(REG_EX,"");
		ResponseEntity<ServiceResponse<Object>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		if(!intersect.equals("") ) {
			responseEntity = totalPlanService.getPlanTotalOrch(intersect);
			client = new Client(puerto);
			responseEntity = totalPlanService.setSignalPlanToSocket(responseEntity.getBody().getSignalPlan().toString(),client,responseEntity);
			client.run();
		}
		else responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ServiceResponse<>("Error"));
		return responseEntity;
	}

	@ApiOperation(nickname = "get Total signal plan", value = "get total signal plan", notes = "get total signal plan ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation successful.", response = ServiceResponse.class)})
	@GetMapping(value = "/enviarPlanConexion/{intersection}/{puerto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Object>> enviarPlanConexion(@PathVariable(name ="intersection") final String intersection,
																		@PathVariable(name ="puerto") final int puerto) throws IOException {
		String intersect =intersection == null ? "" :intersection.replaceAll(REG_EX,"");
		ResponseEntity<ServiceResponse<Object>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		if(!intersect.equals("") ) {
			responseEntity = conexionPlanService.getPlanConexionOrch(intersect);
			client = new Client(puerto);
			responseEntity = totalPlanService.setSignalPlanToSocket(responseEntity.getBody().getSignalPlan().toString(),client,responseEntity);
			client.run();
		}
		else responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ServiceResponse<>("Error"));
		return responseEntity;
	}

	@ApiOperation(nickname = "get Total signal plan", value = "get total signal plan", notes = "get total signal plan ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation successful.", response = ServiceResponse.class)})
	@GetMapping(value = "/enviarPlanDesconexion/{intersection}/{puerto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Object>> enviarPlanDesconexion(@PathVariable(name ="intersection") final String intersection,
																	  @PathVariable(name ="puerto") final int puerto) throws IOException {
		String intersect =intersection == null ? "" :intersection.replaceAll(REG_EX,"");
		ResponseEntity<ServiceResponse<Object>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		if(!intersect.equals("") ) {
			responseEntity = desconexionPlanService.getPlanDesconexionOrch(intersect);
			client = new Client(puerto);
			responseEntity = totalPlanService.setSignalPlanToSocket(responseEntity.getBody().getSignalPlan().toString(),client,responseEntity);
			client.run();
		}
		else responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ServiceResponse<>("Error"));
		return responseEntity;
	}
}
