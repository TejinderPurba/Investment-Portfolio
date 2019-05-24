/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

/**
 *
 * @author Tejinder
 */
public class PriceFieldException extends Exception {

    public PriceFieldException() {
        super("Price must be greater than 0!");
    }

    public PriceFieldException(String message) {
        super(message);
    }
}
