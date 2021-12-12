package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * 
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController {

	/**
	 * Just for logging purpose
	 */
	private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

	/**
	 * This method checks the avalibility of product when user click PlaceOrder
	 * button
	 * 
	 * @throws SQLException
	 */
	public void placeOrder() throws SQLException {
		Cart.getCart().checkAvailabilityOfProduct();
	}

	/**
	 * This method creates the new Order based on the Cart
	 * 
	 * @return Order
	 * @throws SQLException
	 */
	public Order createOrder() throws SQLException {
		Order order = new Order();
		for (Object object : Cart.getCart().getListMedia()) {
			CartMedia cartMedia = (CartMedia) object;
			OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
			order.getlstOrderMedia().add(orderMedia);
		}
		return order;
	}

	/**
	 * This method creates the new Invoice based on order
	 * 
	 * @param order
	 * @return Invoice
	 */
	public Invoice createInvoice(Order order) {
		return new Invoice(order);
	}

	/**
	 * This method takes responsibility for processing the shipping info from user
	 * 
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException {
		LOGGER.info("Process Delivery Info");
		LOGGER.info(info.toString());
		validateDeliveryInfo(info);
	}

	/**
	 * The method validates the info
	 * 
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException {

	}
	/**
	 * Method validate phone number
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param phoneNumber
	 * @return true/false
	 */
	public boolean validatePhoneNumber(String phoneNumber) {
		// check phone number (10 length, start with '0')
		if (phoneNumber.length() != 10 || phoneNumber == null || phoneNumber.charAt(0) != '0')
			return false;
		// check the phonenumber contain only number
		try {
			Integer.parseInt(phoneNumber);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	/**
	 * Method validate name
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param name
	 * @return true/false
	 */
	public boolean validateName(String name) {
		if (name == null)
			return false;
		Pattern p = Pattern.compile("[^A-Za-z]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name.trim().replaceAll("\\s",""));
		if (m.find()) return false;
		return true;
	}
	/**
	 * Method validate address
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param address
	 * @return true/false
	 */
	public boolean validateAddress(String address) {
		if (address == null)
			return false;
		Pattern p = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(address.trim().replaceAll("\\s",""));
		if (m.find()) return false;
		return true;
	}

	/**
	 * This method calculates the shipping fees of order
	 * 
	 * @param order
	 * @return shippingFee
	 */
	public int calculateShippingFee(Order order) {
		Random rand = new Random();
		int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
		LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
		return fees;
	}
}
