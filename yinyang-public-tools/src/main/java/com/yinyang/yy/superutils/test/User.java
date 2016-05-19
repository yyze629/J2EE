package com.yinyang.yy.superutils.test;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "u")
@NamespaceList({ @Namespace(reference = "urn:loc.gov:books"), @Namespace(reference = "urn:ISBN:0-395-36341-6", prefix = "isbn") })
public class User {

	@Element(name = "name", required = false)
	private String name;

	@Element(name = "age")
	private int age;

	@Attribute(name = "address")
	private String address;

	@ElementList(entry = "order", inline = false, name = "orders", type = Order.class)
	private List<Order> orders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
