package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANIES database table.
 * 
 */
@Entity
@Table(name="COMPANIES", schema="TESTDB")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long companyid;

	private String name;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="companyBean")
	private List<Customer> customers;

	public Company() {
	}

	public long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(long companyid) {
		this.companyid = companyid;
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
		customer.setCompanyBean(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setCompanyBean(null);

		return customer;
	}

}