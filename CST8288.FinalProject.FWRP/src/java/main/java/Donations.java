

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class Servlet
 */
public class Donations extends HttpServlet implements WebPageServlet{
	private static final long serialVersionUID = 1L;
	SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
	User currentUser;
	ArrayList<FoodItem> foodList = new ArrayList<>();
	ArrayList<FoodItem> checkout = new ArrayList<>();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

	try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Donations Page for Charities</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Donations at " + request.getContextPath() + "</h1>");
        this.displayFood();
        // code to get OwnerID address
        out.println("<table border=\"1\">");
        out.println("<tr><td colspan=\"2\">Display All Food</td><td colspan=\"2\">Display Donations></tr>"); //make into buttons
        out.println("<tr>");
        out.println("<td>Food Name</td>");
        out.println("<td>Quantity</td>");
        out.println("<td>ExpDate</td>");
        // out.println("<td>Location</td>"); for OwnerID address
        out.println("<td>Action</td>");
        out.println("</tr>");
        for(FoodItem food : foodList){
            out.printf("<tr><td>%s</td><td>%d</td><td>%s</td>"
            		//+ "<td>%s</td>" for OwnerID address
            		+ "<td>Claim Button,Subscribe Button</td></tr>", //make into buttons
                food.getName(), food.getQuantity(), food.getExpDate().toString());
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void displayFood() {
		foodList = inventoryDAO.retrieveAllItems();
	}
	public void displayDonations() {
		foodList.removeIf(f -> (f.getSurplus() != 1));
	}
	public void claimDonation(FoodItem item) {
		checkout.add(item);
	}
	public void checkoutDonations(ArrayList<FoodItem> list) {
		for (FoodItem item : list) {
		bookKeepingDAO.add(item,currentUser); //add when code is written.
		inventoryDAO.deleteItem(item);
		}
		checkout = new ArrayList<FoodItem>();
	}
	public void subscribeToFoodItem(FoodItem item) {
		subscriptionDAO.createSubscription(item, currentUser);
	}
}
