package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    private ShippingFeeCalculator calculateShippingFee;

    public PlaceOrderController() {
    }

    public PlaceOrderController(ShippingFeeCalculator calculateShippingFee) {
        this.calculateShippingFee = calculateShippingFee;
    }

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * 
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public boolean validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        String address = info.get("address");
        String name = info.get("name");
        String phoneNumber = info.get("phone");
        return validateAddress(address) && validateName(name) && validatePhoneNumber(phoneNumber);
    }

    /**
	 * Method validate phone number
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param phoneNumber
	 * @return true/false
	 */
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.charAt(0) != '0' || phoneNumber.length() != 10) {
            return false;
        }

        boolean isValid = true;
        for (char ch : phoneNumber.toCharArray()) {
            if ( !Character.isDigit(ch) ) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    /**
	 * Method validate name
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param User's name
	 * @return true/false
	 */
    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        boolean isValid = true;
        for (char ch : name.toCharArray()) {
            if ( !Character.isLetter(ch) ) {
                isValid = false;
            }
        }

        return isValid;
    }

    /**
	 * Method validate address
	 * @author Nguyen Ngoc Thai Chau - 20183869
	 * @param User's address
	 * @return true/false
	 */
    public boolean validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }

        boolean isValid = true;
        for (char ch : address.toCharArray()) {
            if ( ch == ' ' ) {
                continue;
            }
            if ( !Character.isLetter(ch) ) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    

    /**
     * This method calculates the shipping fees of order
     * 
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        return calculateShippingFee.calculateShippingFee(order);
    }
}
