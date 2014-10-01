/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author Oksana_Moroz
 */
public enum Position {
    Director("Директор"), 
    Producer("Продюсер"), 
    Secretary("Секретар"), 
    Bookkeeper("Бухгалтер");
    
    private final String name;
    
    Position(String position) {
        this.name = position;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
