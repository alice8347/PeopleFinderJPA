package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STATES database table.
 * 
 */
@Entity
@Table(name="STATES", schema="TESTDB")
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="stateBean")
	private List<Customer> customers;

	public State() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setStateBean(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setStateBean(null);

		return customer;
	}

}