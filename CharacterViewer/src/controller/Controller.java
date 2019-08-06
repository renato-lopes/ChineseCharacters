/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.Collections;
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
    
    private List<ChineseCharacter> characters;
    private boolean[] correctState;
    private ChineseCharacter currentCharacter;
    private int currentPosition;
    private boolean showPinyinAndDescription;
    
    
    public void startViewer(){
        Display display = new Display(this);
        File file = FileSelector.chooseFile();
        if(file != null) {
            this.characters = DataLoader.getData(file);
            shuffleList(this.characters);
            
            this.currentPosition = 0;
            this.showPinyinAndDescription = false;
            this.currentCharacter = this.characters.get(0);
            this.correctState = new boolean[this.characters.size()];
            for (int i = 0; i < this.characters.size(); i++) {
                this.correctState[i] = false;
            }
            
            display.initializeDisplay();
            display.setVisible(true);
        } else {
            System.exit(0);
        }
    }
    
    private void shuffleList(List list) {
        Collections.shuffle(list);
    }
    
    public void checkAnswer(String answer) {
        boolean correct = this.currentCharacter.checkCorrectPinyin(answer.trim().toLowerCase());
        this.correctState[this.currentPosition] = correct;
    }
    
    public void resetCorrectState() {
        for (int i = 0; i < this.correctState.length; i++) {
            this.correctState[i] = false;
        }
    }
    
    public void forward() {
        int newPosition = (this.currentPosition + 1) % this.characters.size();
        this.currentCharacter = this.characters.get(newPosition);
        this.currentPosition = newPosition;
    }

    public void backward() {
        int newPosition = (this.currentPosition - 1) % this.characters.size();
        if (newPosition < 0) {
            newPosition += this.characters.size();
        }
        this.currentCharacter = this.characters.get(newPosition);
        this.currentPosition = newPosition;
    }
    
    public void changeShowPinyinAndDescription(){
        this.showPinyinAndDescription = !this.showPinyinAndDescription;
    }
    
    public boolean isCurrentPositionCorrect(){
        return this.correctState[this.currentPosition];
    }

    public boolean[] getCorrectState() {
        return correctState;
    }

    public ChineseCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public boolean isShowPinyinAndDescription() {
        return showPinyinAndDescription;
    }
}
