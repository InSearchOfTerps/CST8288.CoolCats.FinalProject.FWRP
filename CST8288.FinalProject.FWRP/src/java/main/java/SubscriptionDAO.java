import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionDAO implements DAO{
	PreparedStatement query = null;
	
	public void createSubscription(FoodItem item, User user) {
		try {
			query = DBC.useConnection().prepareStatement("INSERT INTO subscriptions(userId,foodItemId,subscribed) VALUES(?,?,?)");
			query.setInt(1, user.getUserID());
			query.setInt(2, item.getItemID());
			query.setBoolean(3,true);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSubscription(FoodItem item, User user) {
		boolean subOrNot = true;
		ResultSet results; 
		try {
			query = DBC.useConnection().prepareStatement("Select subscribed From subscriptions WHERE userId = ?,foodItemId = ?");
			results = query.executeQuery();
			while (results.next()) {
				if (results.getBoolean("subscribed")) {
					subOrNot = false;
				}
				else {
					subOrNot = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			query = DBC.useConnection().prepareStatement("Update subscriptions SET subscribed = ? WHERE userId = ?,foodItemId = ?");
			query.setBoolean(3,subOrNot);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean retrieveSubscription(FoodItem item, User user){
		boolean subOrNot = true;
		ResultSet results;
		try {
			query = DBC.useConnection().prepareStatement("Select subscribed From subscriptions WHERE userId = ?,foodItemId = ?");
			results = query.executeQuery();
			while (results.next()) {
				if (results.getBoolean("subscribed")) {
					subOrNot = true;
				}
				else {
					subOrNot = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subOrNot;
	}
	public void deleteSubscription(FoodItem item, User user) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM subscriptions WHERE userId = ?,foodItemId = ?");
			query.setInt(1, user.getUserID());
			query.setInt(2, item.getItemID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
