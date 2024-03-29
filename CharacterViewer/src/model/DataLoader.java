/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String st;

            st = br.readLine(); // Discard first line 

            while ((st = br.readLine()) != null) {
                String[] lineData = st.split(",");
                ChineseCharacter c = new ChineseCharacter();
                c.setCharacter(lineData[0].trim());
                c.setPinyin(lineData[1].trim());
                c.setDescription(lineData[2].trim());
                c.setLevel(lineData.length == 4 ? lineData[3].trim() : null);
                list.add(c);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo " + file.getName());
            System.exit(-1);
        }

        return list;
    }

    public static InputStream readFont(String filename) throws FileNotFoundException {
        InputStream input = DataLoader.class.getResourceAsStream("/res/fonts/" + filename);
        if (input == null) {
            // this is how we load file within editor (eg eclipse)
            input = new FileInputStream(new File("res/fonts/" + filename));
        }
        return input;
    }

}
