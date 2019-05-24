
package portfolio;

/**
 * @author Tejinder Purba 
 */
public class Stock extends Investment{
  
     /**
     * Constructor of the stock class, initializing all data
     * @param symbol constructs and allocates memory for the symbol variable for the investment
     * @param name constructs and allocates memory for the name variable for the investment
     * @param quantity constructs and allocates memory for the quantity variable for the investment
     * @param price constructs and allocates memory for the price variable for the investment
     * @param bookValue constructs and allocates memory for the book value variable for the investment
     */
    public Stock(String symbol, String name, int quantity, double price, double bookValue) throws EmptyFieldException, 
            QuantityFieldException, PriceFieldException, BookvalueFieldException{
        super(symbol, name, quantity, price, bookValue);
    }
    
    /**
     * Copy constructor for privacy protection
     * @param original constructor that is to be copied
     */
    public Stock(Stock original){
        super(original);
        if (original == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }
    
    /**
    * Default constructor to provide empty class object
    */
    public Stock() throws EmptyFieldException, QuantityFieldException, PriceFieldException, 
                          BookvalueFieldException{
        super("","",0,0.0,0.0);
    }
    
    
    /**
     * Formats data from the class to return a string value
     * @return returns a string formatted to include all variables truncated to length
     */
    @Override
    public String toString(){
        return super.toString();
    }
   
    /**
     * Converts object to class type and checks for equality
     * @param o object to convert to stock class and compare values with
     * @return boolean value indicating if object values equal for not
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Stock cd = (Stock) o;
        if (super.equals(o)) { //Same base info, then similar
            return true;
        }
        return false; 
    }
    
    /**
     * Method to calculate book value for a stock
     * @param originalVal original book value of investment
     * @param quantity amount of investments total
     * @param price current price of investment
     * @return book value amount as a double
     */
    public double calcBookValue(double originalVal, int quantity, double price) {
        double bookVal = originalVal + (quantity * price + 9.99);
        return bookVal;
    }
    
    /**
     * Method to calculate gain for a stock
     * @param quantity amount of investments total
     * @param price current price of investment
     * @param bookVal current book value of investment
     * @return gain amount as a double
     */
    public double calcGain(int quantity, double price, double bookVal) {
        double gain = (quantity * price - 9.99) - bookVal;
        return gain;
    }
    
    /**
     * Method to calculate payment for a stock
     * @param quantity amount of investments being sold
     * @param price current price of investment
     * @return payment amount as a double
     */
    public double calcPayment(int quantity, double price) {
        double pay = (quantity * price - 9.99);
        return pay;
    }
    
}
