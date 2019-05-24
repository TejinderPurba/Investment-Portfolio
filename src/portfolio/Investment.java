
package portfolio;

/**
 * @author Tejinder Purba
 */
abstract public class Investment {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    
    /**
     * Constructor of the stock class, initializing all data
     * @param symbol constructs and allocates memory for the symbol variable for the investment
     * @param name constructs and allocates memory for the name variable for the investment
     * @param quantity constructs and allocates memory for the quantity variable for the investment
     * @param price constructs and allocates memory for the price variable for the investment
     * @param bookValue constructs and allocates memory for the book value variable for the investment
     */
    public Investment(String symbol, String name, int quantity, double price, double bookValue) throws EmptyFieldException, 
            QuantityFieldException, PriceFieldException, BookvalueFieldException{
        
        if (symbol == null || symbol.equals("")){
            throw new EmptyFieldException();
        } else {
            this.symbol = symbol;
        }
        if (name == null || name.equals("")) {
           throw new EmptyFieldException();
        } else{
            this.name = name;
        }
        if (this.quantity < 0) {
            throw new QuantityFieldException();
        } else {
            this.quantity = quantity;
        }
        if (this.price < 0.0) {
            throw new PriceFieldException();
        } else{
            this.price = price;
        }
        if (this.bookValue < 0.0) {
           throw new BookvalueFieldException();
        } else{
            this.bookValue = bookValue;
        }
    }
    
    /**
     * Copy constructor for privacy protection
     * @param original original constructor parameter
     */
    public Investment(Investment original){
        if (original == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        symbol = original.symbol;
        name = original.name;
        quantity = original.quantity;
        price = original.price;
        bookValue = original.bookValue;
    }
        
    /**
    * Default constructor to provide empty class object
    */
    public Investment(){
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.bookValue = 0.0;
    }
    
    /**
     * Formats data from the class to return a string value
     * @return returns a string formatted to include all variables truncated to length
     */
    @Override
    public String toString(){
        return String.format("-%s, %s, %d, %.2f, %.2f", this.symbol, this.name, this.quantity, this.price, this.bookValue);
    }
    
    /**
     * Converts object to class type and checks for equality
     * @param o object to convert to Investment class and compare values with
     * @return boolean value indicating if object values equal for not
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Investment)) return false;
        else{
            Investment d = (Investment) o;
            if (!(this.getSymbol().equalsIgnoreCase(d.getSymbol()))) return false;
            return true;
        }
    }
    
    /**
     * Gets symbol instance from class
     * @return string indicating symbol value
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Gets name instance from class
     * @return string indicating name value
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets quantity instance from class 
     * @return integer type indicating quantity value
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Gets price instance from class
     * @return double type indicating price value
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets book value instance from class
     * @return double type indicating book value
     */
    public double getBookValue() {
        return bookValue;
    }
    
    /**
     * Sets symbol instance for class
     * @param symbol sets a new value for symbol instance in class stock
     */
    public void setSymbol(String symbol)throws EmptyFieldException {
        if (symbol == null || symbol.equals("")) throw new EmptyFieldException();
        this.symbol = symbol;
    } 
    
    /**
     * Sets name instance for class
     * @param name sets a new value for name instance in class stock
     */
    public void setName(String name)throws EmptyFieldException {
        if (name == null || name.equals("")) throw new EmptyFieldException();
        this.name = name;
    }
    
    /**
     * Sets quantity instance for class
     * @param quantity sets a new value for quantity instance in class stock
     */
    public void setQuantity(int quantity)throws QuantityFieldException {
        if (quantity < 0) throw new QuantityFieldException();
        this.quantity = quantity;
    }
    
    /**
     * 
     * @param price sets a new value for price instance in class stock
     */
    public void setPrice(double price)throws PriceFieldException {
        if (price < 0.0) throw new PriceFieldException();
        this.price = price;
    }
    
    /**
     * Sets book value instance for class
     * @param bookValue sets a new value for book value instance in class stock
     */
    public void setBookValue(double bookValue)throws BookvalueFieldException {
        if (bookValue < 0) throw new BookvalueFieldException();
        this.bookValue = bookValue;
    }
    
    /**
     * Abstract Method to calculate book value for a mutual fund
     * @param originalVal original book value of investment
     * @param quantity amount of investments total
     * @param price current price of investment
     * @return book value amount as a double
     */
    abstract public double calcBookValue(double originalVal, int quantity, double price);
    
    /**
     * Abstract Method to calculate gain for a mutual fund
     * @param quantity amount of investments total
     * @param price current price of investment
     * @param bookVal current book value of investment
     * @return gain amount as a double
     */
    abstract public double calcGain(int quantity, double price, double bookVal);
    
    /**
     * Abstract Method to calculate payment for a mutual fund
     * @param quantity amount of investments being sold
     * @param price current price of investment
     * @return payment amount as a double
     */
    abstract public double calcPayment(int quantity, double price);
}
