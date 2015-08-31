package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUSTOMERS database table.
 * 
 */
@Entity
@Table(name="CUSTOMERS", schema="TESTDB")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="CUST_ID_GENERATOR", sequenceName="CUST_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUST_ID_GENERATOR")
	private long customerid;

	private String company;

	private String emailaddress;

	private String firstname;

	private String fullname;

	private String lastname;

	@Column(name="\"POSITION\"")
	private String position;

	private String streetaddress;

	private String title;

	private String zipcode;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY")
	private City cityBean;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANYID")
	private Company companyBean;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="STATE")
	private State stateBean;

	public Customer() {
	}

	public long getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStreetaddress() {
		return this.streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public City getCityBean() {
		return this.cityBean;
	}

	public void setCityBean(City cityBean) {
		this.cityBean = cityBean;
	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

	public State getStateBean() {
		return this.stateBean;
	}

	public void setStateBean(State stateBean) {
		this.stateBean = stateBean;
	}

}