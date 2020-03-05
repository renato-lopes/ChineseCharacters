/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterviewer;

import controller.Controller;

/**
 *
 * @author renatojuniortmp
 */
public class CharacterViewer {
    
    public static String VERSION = "0.1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Controller().startViewer();
    }
    
}
