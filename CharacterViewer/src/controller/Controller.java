/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.ChineseCharacter;
import model.DataLoader;
import view.Display;
import view.FileSelector;

/**
 *
 * @author renato
 */
public class Controller {

    public static final String ALL_LEVELS = "ALL";
    
    private List<ChineseCharacter> characters;
    private List<ChineseCharacter> currentCharacterList;
    private boolean[] correctState;
    private ChineseCharacter currentCharacter;
    private int currentPosition;
    private boolean showPinyinAndDescription;
    private boolean checkTones;
    private String currentLevel;
    
    public Controller() {
        File file = FileSelector.chooseFile();
        if (file != null) {
            this.characters = DataLoader.getData(file);
        } else {
            System.exit(0);
        }
        this.showPinyinAndDescription = false;
        this.checkTones = true;
    }

    public void startViewer() {
        Display display = new Display(this);
        shuffleList(this.characters);

        setCurrentLevel(ALL_LEVELS);
        resetCharactersControlVariables();

        display.initializeDisplay();
        display.setVisible(true);
    }
    
    private void resetCharactersControlVariables() {
        this.currentPosition = 0;
        this.currentCharacter = this.currentCharacterList.get(0);
        this.correctState = new boolean[this.currentCharacterList.size()];
        for (int i = 0; i < this.correctState.length; i++) {
            this.correctState[i] = false;
        }
    }
    
    public void reset() {
        this.resetCharactersControlVariables();
    }
    
    public void setCurrentLevel(String level) throws IllegalArgumentException {
        if (!level.equals(ALL_LEVELS) && !getAvailableLevels().contains(level)) {
            throw new IllegalArgumentException("Invalid Level");
        }
        this.currentLevel = level;
        updateCharacterList();
        resetCharactersControlVariables();
    }
    
    private void updateCharacterList() {
        if (this.currentLevel.equals(ALL_LEVELS)) {
            this.currentCharacterList = this.characters;
        } else {
            List<ChineseCharacter> charList = new ArrayList<>();
            for (ChineseCharacter c : this.characters) {
                if(c.getLevel().equals(this.currentLevel)) {
                    charList.add(c);
                }
            }
            this.currentCharacterList = charList;
        }
    }
    
    public Set<String> getAvailableLevels() {
        Set<String> levels = new HashSet<>();
        for (ChineseCharacter c : this.characters) {
            levels.add(c.getLevel());
        }
        return levels;
    }

    private void shuffleList(List list) {
        Collections.shuffle(list);
    }

    public void checkAnswer(String answer) {
        answer = answer.trim().toLowerCase().replaceAll("\\s+", "");
        answer = Normalizer.normalize(answer, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        String char_pinyin = this.currentCharacter.getPinyin().trim().toLowerCase().replaceAll("\\s+", "");
        char_pinyin = Normalizer.normalize(char_pinyin, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        if (!checkTones) {
            answer = answer.replaceAll("[0-9]","");
            char_pinyin = char_pinyin.replaceAll("[0-9]","");
        }
        boolean correct = char_pinyin.equals(answer);
        this.correctState[this.currentPosition] = correct;
    }

    public void forward() {
        int newPosition = (this.currentPosition + 1) % this.currentCharacterList.size();
        this.currentCharacter = this.currentCharacterList.get(newPosition);
        this.currentPosition = newPosition;
    }

    public void backward() {
        int newPosition = (this.currentPosition - 1) % this.currentCharacterList.size();
        if (newPosition < 0) {
            newPosition += this.currentCharacterList.size();
        }
        this.currentCharacter = this.currentCharacterList.get(newPosition);
        this.currentPosition = newPosition;
    }
    
    public void setIndex(int i) throws IllegalArgumentException {
        if (i < 0 || i >= this.currentCharacterList.size()) {
            throw new IllegalArgumentException("Invalid Index!");
        }
        this.currentCharacter = this.currentCharacterList.get(i);
        this.currentPosition = i;
    }

    public void changeShowPinyinAndDescription() {
        this.showPinyinAndDescription = !this.showPinyinAndDescription;
    }
    
    public void changeCheckTones() {
        this.checkTones = !this.checkTones;
    }

    public boolean isCurrentPositionCorrect() {
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

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public int getCharacterCount() {
        return this.currentCharacterList.size();
    }

    public boolean isCheckTones() {
        return checkTones;
    }
}
