# Create Java WebApp with Mature Microservice
_Last updated: 2022-11-26_


This repository contains a _JKFramework Web_ project simplifies building Java web applications based on JSF (Jakarta Faces 4) and 
PrimeFaces and a Mature Microservices backend.

## Pre-Requisites:
- [Create Mature Microservice](https://framework.smart-api.com/tutorials/java-maven-tutorial.html)
- Run the Mature Microservice

## Create the Microservice 

Steps: 
1. Create Maven project with the following pom.xml:
````xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
      <groupId>com.jalalkiswani</groupId>
      <artifactId>jk-webapp</artifactId>
      <version>7.0.0-M1</version>
    </parent>
    <artifactId>jk-framework-web-microservice-mature-client-example</artifactId>
    <packaging>war</packaging>
  </project>
````
---
Note: If you are using Eclipse, be sure to refresh your project (select your project→ right click→ Maven→ Update Project)
---

Create config file at src/main/resources/config.properties with the following contents:

app.services.persons.url=http://localhost:8080/app/persons

hibernate.connection.driver_class = org.h2.Driver
hibernate.connection.url = jdbc:h2:file:./h2db.data
hibernate.dialect = org.hibernate.dialect.H2Dialect

hibernate.connection.username = sa
hibernate.connection.password = sa
hibernate.c3p0.min_size=1
hibernate.c3p0.max_size=4
hibernate.c3p0.timeout=3
hibernate.c3p0.max_statements=50

hibernate.hbm2ddl.auto=update
db-entities-packages=com.app
Create Model with the following contents:

package com.app.person;

import java.io.Serializable;

public class Model implements Serializable {
	private Integer id;
	private String nationalId;
	private String name;
	private String email;
	private String address;

	public void setId(Integer id){
	  this.id=id;
	}

	public Integer getId(){
	 return this.id;
	}

	public void setNationalId(String nationalId){
	  this.nationalId=nationalId;
	}

	public String getNationalId(){
	 return this.nationalId;
	}

	public void setName(String name){
	  this.name=name;
	}

	public String getName(){
	 return this.name;
	}

	public void setEmail(String email){
	  this.email=email;
	}

	public String getEmail(){
	 return this.email;
	}

	public void setAddress(String address){
	  this.address=address;
	}

	public String getAddress(){
	 return this.address;
	}


	@Override
	public boolean equals(Object obj) {
	  if (obj == null) {
		return false;
	  }
	  return this.getId().equals(((Model) obj).getId());
	}

	@Override
	public int hashCode() {
	  if(this.id==null) {
	    return toString().hashCode();
	   }
	  return this.id.hashCode();
	}

	@Override
	public String toString(){
	  StringBuffer buf=new StringBuffer();
	  buf.append(this.id).append(",");
	  buf.append(this.nationalId).append(",");
	  buf.append(this.name).append(",");
	  buf.append(this.email).append(",");
	  buf.append(this.address).append(",");
	  return buf.toString();
	}
}
Create ServiceClient class with the following contents:

package com.app.person;

import com.jk.services.client.JKMatureServiceClient;

public class ServiceClient extends JKMatureServiceClient<Model>{

	@Override
	public String getServiceUrlPropertyName() {
		return "app.services.persons.url";
	}

}
Create controller (JSF Managed Bean) at src/main/java/com/app/person/Controller.java with the following contents:

package com.app.person;

import java.util.List;
import java.util.Vector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jk.web.faces.mb.JKManagedBean;

@ManagedBean(name = "controller")
@ViewScoped
public class Controller extends JKManagedBean {
	ServiceClient client=new ServiceClient();
	Model model;
	List<Model> modelList;
	List<Model> filterList;

	public boolean isAllowAdd() {
		return getModel().getId() == null;
	}

	public String add() {
		client.insert(model);
		refresh();
		success("Added Successfully");
		return null;
	}

	public boolean isAllowUpdate() {
		return getModel().getId() != null;
	}

	public String update() {
		client.update(model);
		int id=getModel().getId();
		success("Updated Successfully");
		refresh();
		//to ensure getting updated version from DB
		this.model=client.find(id+"");
		return null;
	}

	public boolean isAllowDelete() {
		return getModel().getId() != null;
	}

	public String delete() {
		client.delete(getModel().getId());
		success("Deleted Successfully");
		refresh();
		return null;
	}

	public String reset() {
		setModel(null);
		return null;
	}

	public Model getModel() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Model> getModelList() {
		if (modelList == null) {
			modelList = client.getAll();
		}
		return modelList;
	}

	public List<Model> getFilterList() {
		return filterList;
	}

	public void setFilterList(Vector<Model> filterList) {
		this.filterList = filterList;
	}

	protected void refresh() {
		this.modelList = null;
		setModel(null);
	}
}
Create JSF XHTML Page at src/webapp/index.xhtml class with the following contents:

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://xmlns.jcp.org/jsf/facelets" xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:f="http://xmlns.jcp.org/jsf/core" xmlns:p="http://primefaces.org/ui">
<h:head>
	<title>JK-Framework Demo</title>
</h:head>
<h:body>
	<h:form id="frmPerson">
		<p:autoUpdate />
		<p:growl />
		<p:panelGrid columns="4" style="margin:auto;" styleClass="shaddow">
			<f:facet name="header">Person Form</f:facet>

			<p:outputLabel value="National Id" for="nationalId" />
			<p:inputText type="text" value="#{controller.model.nationalId}" id="nationalId" placeholder="NationalId" required="true" />

			<p:outputLabel value="Name" for="name" />
			<p:inputText type="text" value="#{controller.model.name}" id="name" placeholder="Name" required="true" />

			<p:outputLabel value="Email" for="email" />
			<p:inputText type="email" value="#{controller.model.email}" id="email" placeholder="Email" required="true" />

			<p:outputLabel value="Address" for="address" />
			<p:inputTextarea id="address" value="#{controller.model.address}" readOnly="false" required="false" placeholder="Address" />
			<f:facet name="footer">
				<div align="center">
					<p:commandButton value="Add" action="#{controller.add}" rendered="#{controller.allowAdd}" process="@form" />
					<p:commandButton value="Update" action="#{controller.update}" rendered="#{controller.allowUpdate}" process="@form" />
					<p:commandButton value="Delete" action="#{controller.delete}" rendered="#{controller.allowDelete}" process="@this" />
					<p:commandButton value="Reset" action="#{controller.reset}" process="@this" />
				</div>
			</f:facet>

		</p:panelGrid>
	</h:form>
	<hr />
	<h:form>
		<p:dataTable value="#{controller.modelList}" var="model" rowKey="#{model.id}" paginator="true" paginatorAlwaysVisible="false"
			paginatorPosition="bottom" selectionMode="single" filteredValue="#{controller.filterList}" selection="#{controller.model}" emptyMessage=""
			rowIndexVar="row" styleClass="shaddow">
			<p:ajax event="rowSelect" />
			<p:autoUpdate />
			<f:facet name="header">Person Data Table</f:facet>
			<p:column headerText="#">#{row+1}</p:column>
			<p:column headerText="national id" sortBy="#{model.nationalId}" filterBy="#{model.nationalId}" filterMatchMode="contains">
				<h:outputText value="#{model.nationalId==null?'-':model.nationalId}" />
			</p:column>
			<p:column headerText="name" sortBy="#{model.name}" filterBy="#{model.name}" filterMatchMode="contains">
				<h:outputText value="#{model.name==null?'-':model.name}" />
			</p:column>
			<p:column headerText="email" sortBy="#{model.email}" filterBy="#{model.email}" filterMatchMode="contains">
				<h:outputText value="#{model.email==null?'-':model.email}" />
			</p:column>
			<p:column headerText="address" sortBy="#{model.address}" filterBy="#{model.address}" filterMatchMode="contains">
				<h:outputText value="#{model.address==null?'-':model.address}" />
			</p:column>
		</p:dataTable>
	</h:form>
</h:body>

</html>
Create Main class with the following contents:

package com.app;

import com.jk.web.embedded.JKWebApplication;

public class App {
	public static void main(String[] args) {
		JKWebApplication.run(8181);
	}
}
Thats it, now run your App class, in few seconds your browser will open and you should see something like this:

Output
Full example source-code can be found at https://github.com/smartapi-com/jk-framework-web-microservice-mature-client
