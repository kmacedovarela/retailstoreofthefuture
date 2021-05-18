package com.demo.retailstoreofthefuture.service;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.demo.retailstoreofthefuture.model.Customer;
import com.demo.retailstoreofthefuture.model.CustomerMovementEvent;
import com.demo.retailstoreofthefuture.model.Department;
import com.demo.retailstoreofthefuture.model.TrackedCustomer;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RulesExecutor {

	private static Logger logger = LoggerFactory.getLogger(RulesExecutor.class);
	private static KieSession kSession;
	
	private static ReleaseId kjarReleaseId = new ReleaseIdImpl("com.demo.retailstoreofthefuture", "customer-tracker-kjar", "1.0-SNAPSHOT");

	public TrackedCustomer fireRulesForNewEvent(CustomerMovementEvent customerMovementEvent) throws Exception{
		// Create a new fact to be evaluated based on the incoming movement event
		Customer customer = new Customer
									.CustomerBuilder(customerMovementEvent.getCustomerId())
									.atDepartment(locateCustomer(customerMovementEvent).getDepartmentName())
									.atLocation(customerMovementEvent.getLocation())
									.withMoveTimestamp(customerMovementEvent.getTimestamp())
									.build();

		
		List<Command> commands = new ArrayList<Command>();
		commands.add(CommandFactory.newSetGlobal("customerListResult", new ArrayList<TrackedCustomer>(),true));
		commands.add(CommandFactory.newInsert(customer));
		commands.add(CommandFactory.newFireAllRules());

		logger.debug("Firing rules for tracked Customer "+customer.getCustomerId()+" Location "+customer.getDepartmentName());
		ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(commands));

		// Gets result from execution via global variable
		List<TrackedCustomer> customerListResult = (ArrayList<TrackedCustomer>) results.getValue("customerListResult");

		return (customerListResult.isEmpty()) ? null : customerListResult.get(0);
	}	
	
	public static void setupKIEContainer() {
		KieServices ks = KieServices.Factory.get(); 
		KieContainer kContainer = ks.newKieContainer(kjarReleaseId);
				
		kSession = kContainer.newKieSession();
	}

	private static Department locateCustomer(CustomerMovementEvent customerMovementEvent) throws Exception {
		List<Department> departments = getDepartments().stream()
				.filter(department -> department.getDeptLocation().contains(customerMovementEvent.getLocation()))
				.collect(Collectors.toList()); 
		
		if(!departments.isEmpty()){
			Department department = departments.get(0);
			logger.debug("CustomerId [{}] located at [{}] ", customerMovementEvent.getCustomerId(),  department.getDepartmentName());
			return department;
		} else {
			logger.error("Couldn't map customer with id {} to a department!", customerMovementEvent.getCustomerId());
			throw new Exception("Couldn't map customer with id "+ customerMovementEvent.getCustomerId()+" to a department!");
		}
	}
	
	private static List<Department> getDepartments() {
		List<Department> departments = new ArrayList<>();
		departments.add(new Department("Women", new Rectangle(445, 443,  221, 436))); // X,Y, Width, Height
		departments.add(new Department("Boys",  new Rectangle(672, 443,  320, 215)));  
		departments.add(new Department("Girls", new Rectangle(998, 443, 319, 215)));
		departments.add(new Department("Men",   new Rectangle(672, 664, 1317, 879)));
		departments.add(new Department("Sports",new Rectangle(614, 984, 572, 308)));

		return departments;
	}

	// public static final void main(String[] args) {
	// 	try {
	// 		rulesSimulation();
	// 	} catch (Exception e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
	// }

	// private static void rulesSimulation() throws Exception{
	// 	if (kSession == null){
	// 		// Load the KIE base:
	// 		KieServices ks = KieServices.Factory.get();
	// 		KieContainer kContainer = ks.newKieContainer(kjarReleaseId);
			
	// 		KieSessionConfiguration conf = KieServices.Factory.get().newKieSessionConfiguration();
	// 		conf.setOption(ClockTypeOption.get("pseudo"));
			
	// 		kSession = kContainer.newKieSession(conf);
	// 	}

	// 	List<CustomerMovementEvent> events = simulateEvents();

	// 	for (CustomerMovementEvent customerMovementEvent : events) {
	// 		//fireRulesForNewEvent(customerMovementEvent);
	// 	}
	// }

	// private static List<CustomerMovementEvent> simulateEvents() {
	// 	List<CustomerMovementEvent> events = new ArrayList<CustomerMovementEvent>();

	// 	events.add(new CustomerMovementEvent("1", new Point(500,700), 1621273200l ));  // Customer 1 - Women
	// 	events.add(new CustomerMovementEvent("2", new Point(675,1500),1621273320l)); // Customer 2 - Men - Monday, May 17, 2021 5:42:00 PM
	// 	events.add(new CustomerMovementEvent("2", new Point(675,1485),1621273440l)); // Customer 2 - Men - Monday, May 17, 2021 5:44:00 PM
	// 	events.add(new CustomerMovementEvent("1", new Point(615,1000)));  // Customer 1 - Sports    
	// 	events.add(new CustomerMovementEvent("2", new Point(675,1490), 1621273445l)); // Customer 2 - Men - Monday, May 17, 2021 5:44:05 PM
	// 	events.add(new CustomerMovementEvent("1", new Point(675,1398)));  // Customer 1 - Men
	// 	events.add(new CustomerMovementEvent("1", new Point(500,750)));  // Customer 1 - Women    
	// 	events.add(new CustomerMovementEvent("1", new Point(500,750)));  // Customer 1 - Women    
	// 	events.add(new CustomerMovementEvent("1", new Point(700,443)));  // Customer 1 - Boys  
	// 	events.add(new CustomerMovementEvent("1", new Point(1000,445)));  // Customer 1 - Girls
	// 	events.add(new CustomerMovementEvent("1", new Point(615,1000)));  // Customer 1 - Sports
		
	// 	return events;
	// }


}
