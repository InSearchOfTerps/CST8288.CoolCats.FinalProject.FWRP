import java.sql.Date;

public class FoodItem {
private int itemID;
private int ownerID;
private String name;
private int price;
private int quantity;
private Date expDate;
/** Surplus value determines which Category the item belongs to
 * 0 = not surplus
 * 1 = donation surplus
 * 2 = discount surplus
 */
private int surplus;

public int getItemID() {
	return itemID;
}
public void setItemID(int itemID) {
	this.itemID = itemID;
}
public int getOwnerID() {
	return ownerID;
}
public void setOwnerID(int ownerID) {
	this.ownerID = ownerID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public Date getExpDate() {
	return expDate;
}
public void setExpDate(Date expDate) {
	this.expDate = expDate;
}
public int getSurplus() {
	return surplus;
}
public void setSurplus(int surplus) {
	this.surplus = surplus;
}
}
