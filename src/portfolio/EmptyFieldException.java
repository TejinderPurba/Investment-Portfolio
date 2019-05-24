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
public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super("Field can't be empty");
    }

    public EmptyFieldException(String message) {
        super(message);
    }
}
