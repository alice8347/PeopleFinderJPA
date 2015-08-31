import java.io.IOException;
import java.util.List;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import customTools.DBUtil;

/**
 * Servlet implementation class findCust
 */
@WebServlet("/findCust")
public class findCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findCust() {
        super();
        message = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		message = "";
		response.setContentType("text/html");
		String name = request.getParameter("lastName");
		message = findName(name);
		if (message.equals("No matched names.")) {
			message = "<div class=\"container\"><div class=\"jumbotron\"><h4>" + message + "</h4></div></div>";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);
		} else {
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/Output.jsp").forward(request, response);
		}
	}
	
	public static String findName(String name) {
		String content = "";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT c FROM Customer c WHERE c.lastname LIKE '" + name + "%' OR c.companyBean.name LIKE '" + name + "%'";
		TypedQuery<Customer> q = em.createQuery(query, Customer.class);
		try {
			List<Customer> custList = q.getResultList();
			if (!custList.isEmpty()) {
				content = "<div class=\"container\"><h3>Results</h3><a href=\"Search.jsp\">Back</a><table class=\"table table-striped\"><thead><tr><th>Title</th><th>First Name</th><th>Last Name</th><th>Street Address</th><th>City</th><th>State</th><th>Zip Code</th><th>Email Address</th><th>Position</th><th>Company</th></tr></thead><tbody>";
				for (int i = 0; i < custList.size(); i++) {
					content += "<tr><td>" + custList.get(i).getTitle() + "</td><td>" + custList.get(i).getFirstname() + "</td><td>" + custList.get(i).getLastname() + "</td><td>" + custList.get(i).getStreetaddress() + "</td><td>" + custList.get(i).getCityBean().getName() + "</td><td>" + custList.get(i).getStateBean().getName() + "</td><td>" + custList.get(i).getZipcode().substring(0, 5) + "</td><td>" + custList.get(i).getEmailaddress() + "</td><td>" + custList.get(i).getPosition() + "</td><td>" + custList.get(i).getCompanyBean().getName() + "</td></tr>";
				}
				content += "</tbody></table></div>";
			} else {
				content = "No matched names.";
			}
			return content;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}

}
