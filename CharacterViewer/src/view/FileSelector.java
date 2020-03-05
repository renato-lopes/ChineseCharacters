/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;

/**
 *
 * @author renato
 */
public class FileSelector {
    public static File chooseFile(){
        JFileChooser jfc = new JFileChooser();
        try {
            jfc.setCurrentDirectory(new File(FileSelector.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
        } catch (URISyntaxException ex) {
        }
        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile();
        }
        return null;
    }
}
