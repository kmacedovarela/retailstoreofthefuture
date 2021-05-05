package com.demo.retailstoreofthefuture;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.retailstoreofthefuture.model.*;


public class RulesExecutor {

	private static Logger logger = LoggerFactory.getLogger(RulesExecutor.class);
	private static KieSession kSession;
	
	public static final void main(String[] args) {
		try {

			setupKIEContainer();
			
			List<CustomerMovementEvent> events = simulateEvents();


			for (CustomerMovementEvent customerMovementEvent : events) {
				TrackedCustomer trackedCustomer = locateCustomer(customerMovementEvent);
				kSession.insert(trackedCustomer);
				kSession.fireAllRules();
				System.out.println("Fired for trackedId "+trackedCustomer.getCustomerId()+" Location "+trackedCustomer.getDepartment().getDepartmentName());
			}

		}

		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static List<CustomerMovementEvent> simulateEvents() {
		List<CustomerMovementEvent> events = new ArrayList<CustomerMovementEvent>();

		events.add(new CustomerMovementEvent("1", new Point(500,700)));  // Customer 1 - Women
		events.add(new CustomerMovementEvent("2", new Point(675,1500))); // Customer 2 - Men
		events.add(new CustomerMovementEvent("2", new Point(675,1485))); // Customer 2 - Men
		events.add(new CustomerMovementEvent("1", new Point(500,750)));  // Customer 1 - Women    
		events.add(new CustomerMovementEvent("2", new Point(675,1440))); // Customer 2 - Men
		events.add(new CustomerMovementEvent("1", new Point(675,1398)));  // Customer 1 - Men
		events.add(new CustomerMovementEvent("1", new Point(500,750)));  // Customer 1 - Women    
		events.add(new CustomerMovementEvent("1", new Point(500,750)));  // Customer 1 - Women    

		
		return events;
	}

	private static void setupKIEContainer() {
		ReleaseId rid = new ReleaseIdImpl("com.demo.retailstoreofthefuture", "customer-tracker-kjar", "1.0-SNAPSHOT");

		// Load the KIE base:
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(rid);
		kSession = kContainer.newKieSession();
	}

	private static TrackedCustomer locateCustomer(CustomerMovementEvent customerMovementEvent) throws Exception {
		List<Department> departments = getDepartments().stream()
				.filter(department -> department.getDeptLocation().contains(customerMovementEvent.getLocation()))
				.collect(Collectors.toList()); 

		try {
			TrackedCustomer trackedCustomer = new TrackedCustomer(customerMovementEvent.getCustomerId(), departments.get(0));
			logger.debug("New tracked customerId {} at {} ", trackedCustomer.getCustomerId(), trackedCustomer.getDepartment().getDepartmentName());
			return trackedCustomer;
		} catch (Exception e) {
			logger.error("Couldn't map customer with id ? to a department!"+ customerMovementEvent.getCustomerId());
			throw new Exception("Couldn't map customer with id ? to a department!"+ customerMovementEvent.getCustomerId());
		}
	}

	private static List<Department> getDepartments() {
		List<Department> departments = new ArrayList<>();
		departments.add(new Department("Women", new Rectangle(444, 443,  666, 879)));
		departments.add(new Department("Boys",  new Rectangle(672, 443,  992, 658)));
		departments.add(new Department("Girls", new Rectangle(998, 443, 1317, 658)));
		departments.add(new Department("Men",   new Rectangle(672, 664, 1317, 879)));
		departments.add(new Department("Sports",new Rectangle(614, 984, 1186, 1292)));

		return departments;
	}

}
