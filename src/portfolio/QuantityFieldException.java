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
public class QuantityFieldException extends Exception {

    public QuantityFieldException() {
        super("Quantity must be greater than 0!");
    }

    public QuantityFieldException(String message) {
        super(message);
    }
}
