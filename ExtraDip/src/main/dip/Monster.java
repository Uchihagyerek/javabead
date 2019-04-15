package dip;

import java.util.Random;

public class Monster extends Entity {
    int damage;
    Player player;

    public Monster(String name, int health, Player player){
    this.name=name;
    this.health=health;
    this.player=player;
    this.maxHealth=6;
    }

    synchronized int attack() {

        Random rand=new Random();
        int minDmg= (int) Math.floor(damage-damage*0.3);
        int maxDmg=(int) Math.ceil(damage+damage*0.3);
        int dmgDealt=rand.nextInt(maxDmg-minDmg)+minDmg;
        System.out.println(dmgDealt);
        return dmgDealt;
    }

}
