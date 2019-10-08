/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import representation.*;

/**
 *
 * @author ordinaute
 */
public class testencoreplusvite {
    public static void main(String[] args){
        
        Variable x0 = Variable.makeBooleanVariable("x0");
        Variable x1 = new Variable("x1", "0", "1");
        Variable x2 = new Variable("x2", "0", "1", "2");
        Variable x3 = new Variable("x3", "0", "1", "2", "3", "6");
        RestrictedDomain rd1 = new RestrictedDomain(x3, "0", "1", "2");
        RestrictedDomain rd2 = new RestrictedDomain(x3, "1");
        
        System.out.println(rd1.equals(rd2));
    }
}
