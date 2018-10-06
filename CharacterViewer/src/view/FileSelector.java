/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author renato
 */
public class FileSelector {
    public static File chooseFile(){
        JFileChooser jfc = new JFileChooser(".");
        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile();
        }
        return null;
    }
}
