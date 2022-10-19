package com.app.person;

import com.jk.services.client.JKMatureServiceClient;

public class ServiceClient extends JKMatureServiceClient<Model>{

	@Override
	public String getServiceUrlPropertyName() {
		return "app.services.persons.url";
	}

}
