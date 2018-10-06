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

            while ((st = br.readLine()) != null) {
                ChineseCharacter c = new ChineseCharacter();
                c.setCharacter(st);
                st = br.readLine();
                c.setPinyin(st);
                st = br.readLine();
                c.setDescription(st);
                st = br.readLine();
                c.setLevel(st);
                list.add(c);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo " + file.getName());
        }

        return list;
    }

}
