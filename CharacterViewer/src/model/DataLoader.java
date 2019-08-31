/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author renatojuniortmp
 */
public class DataLoader {

    public static List<ChineseCharacter> getData(File file) {
        List<ChineseCharacter> list = new ArrayList<>();

        try {
            
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;

            st = br.readLine(); // Discard first line 
            
            while ((st = br.readLine()) != null) {
                String[] lineData = st.split(",");
                ChineseCharacter c = new ChineseCharacter();
                c.setCharacter(lineData[0]);
                c.setPinyin(lineData[1]);
                c.setDescription(lineData[2]);
                c.setLevel(lineData[3]);
                list.add(c);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo " + file.getName());
        }

        return list;
    }

}
