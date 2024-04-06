import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAO implements DAO {
	
	PreparedStatement query = null;
	
	int surplusValue;
	
	public void createItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("INSERT INTO retailInventory(itemName,quantity,price,surplus,expiryDate,userId) VALUES(?,?,?,?,?,?)");
			query.setString(1, item.getName());
			query.setInt(2, item.getQuantity());
			query.setInt(3,item.getPrice());
			query.setInt(4,item.getSurplus());
			query.setDate(5,item.getExpDate());
			query.setInt(6,item.getOwnerID());
			query.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("UPDATE retailInventory SET itemName = ?,quantity = ?,price = ?,surplus = ?,expiryDate = ?,userId = ? WHERE foodItemId = ?");
			query.setString(1, item.getName());
			query.setInt(2, item.getQuantity());
			query.setInt(3,item.getPrice());
			query.setInt(4,item.getSurplus());
			query.setDate(5,item.getExpDate());
			query.setInt(6,item.getOwnerID());
			query.setInt(7, item.getItemID());
			query.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public FoodItem retrieveItem(int id) {
		FoodItem item = new FoodItem();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT FROM retailInventory WHERE foodItemId = ?");
			query.setInt(1, id);
			results = query.executeQuery();
			while (results.next()) {
				item.setItemID(results.getInt("foodItemId"));
				item.setName(results.getString("itemName"));
				item.setQuantity(results.getInt("quantity"));
				item.setPrice(results.getInt("price"));
				item.setSurplus(results.getInt("surplus"));
				item.setExpDate(results.getDate("expiryDate"));
				item.setOwnerID(results.getInt("userId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public ArrayList<FoodItem> retrieveAllItems() {
		ArrayList<FoodItem> items = new ArrayList<>();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT * retailInventory");
			results = query.executeQuery();
			while (results.next()) {
				FoodItem item = new FoodItem();
				item.setItemID(results.getInt("foodItemId"));
				item.setName(results.getString("itemName"));
				item.setQuantity(results.getInt("quantity"));
				item.setPrice(results.getInt("price"));
				item.setSurplus(results.getInt("surplus"));
				item.setExpDate(results.getDate("expiryDate"));
				item.setOwnerID(results.getInt("userId"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	
	public void deleteItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM retailInventory WHERE foodItemId = ?");
			query.setInt(1, item.getItemID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
