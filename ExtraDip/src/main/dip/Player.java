package dip;

import javax.swing.*;

public class Player extends Entity {

    public int potionsCount;
    public int damage;
    public int money = 50;
    public int boost = 1;
    public int familiarTime;
    public boolean monsterWeakened = false;
    public boolean familiar = false;
    public boolean armor = false;
    public boolean sword = false;


    Map map;

    public Player(String name, Map map) {
        this.name = name;
        maxHealth = 12;

        health = maxHealth;
        this.map = map;
        potionsCount = 1;
    }


    public void getStats() {
        System.out.println("Health: " + maxHealth);

    }

    int attack() {
        return 0;
    }

    void die() {
        int restart = JOptionPane.YES_NO_OPTION;
        restart = JOptionPane.showConfirmDialog(null, "Would you like to restart?", "You died!", restart);
        if (restart == JOptionPane.YES_OPTION) {
            map.playerDead = true;
            map.generateMap();
        } else if (restart == JOptionPane.NO_OPTION) {
            System.exit(1);

        }
    }
}
