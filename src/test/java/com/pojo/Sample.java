package com.pojo;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;

import io.restassured.response.Response;

public class Sample extends BaseClass {
	int stateIdNum;

	@Test(priority = 1)
	public void login() {
		// header
		addHeader("accept", "application/json");
		// basic authentication
		addBasicAuth("rishwanabegam96@gmail.com", "137$Ris");
		// method type
		Response response = requestType("POSt", "https://omrbranch.com/api/postmanBasicAuthLogin");
		// status
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String actfirstname = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals(actfirstname, "Riswana", "verify firstname");

	}

	@Test(priority = 2)
	public void getStateList() {
		addHeader("accept", "application/json");
		Response response = requestType("GET", "https://omrbranch.com/api/stateList");
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "verify status code");
		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> liststatelist = stateList_Output_Pojo.getData();

		for (StateList eachstateList : liststatelist) {
			String actstatename = eachstateList.getName();
			if (actstatename.equals("")) {
				stateIdNum = eachstateList.getId();
				
			}

		}

	}

}
