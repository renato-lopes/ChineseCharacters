/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.List;
import model.ChineseCharacter;
import model.DataLoader;
import view.Display;
import view.FileSelector;

/**
 *
 * @author renato
 */
public class Controller {
    public void startViewer(){
        Display display = new Display(this);
        File file = FileSelector.chooseFile();
        if(file != null) {
            List<ChineseCharacter> characterList = DataLoader.getData(file);
            display.initializeDisplay(characterList);
            display.setVisible(true);
        }
    }
    
    public boolean checkAnswer(ChineseCharacter c, String answer) {
        return c.checkCorrectPinyin(answer);
    }
}
