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
public class BookvalueFieldException extends Exception {

    public BookvalueFieldException() {
        super("Bookvalue must be greater than 0!");
    }

    public BookvalueFieldException(String message) {
        super(message);
    }
}
