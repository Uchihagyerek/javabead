package dip;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Battle extends Canvas {



    static JFrame thisFrame;
    Map map;
    Monster enemy;
    JProgressBar enemyHealth;
    JProgressBar playerHealth;
    JButton attack;
    JButton spell;
    JButton potion;
    JButton escape;
    Player player;

    private boolean boss;

    public Battle(Map map, Player player, boolean boss) {
        setSize(900, 900);

        this.boss = boss;
        if (!boss)
            enemy = new Monster("patrik", 6, player);
        else
            enemy = new Monster("Boss", 10, player);

        this.map = map;
        this.player = player;





        if (player.monsterWeakened) {
            enemy.health /= 2;
        }
        enemy.maxHealth=enemy.health;

        int playerInitialRoll=throwDice();
        int enemyInitialRoll=throwDice();

        if (enemyInitialRoll>playerInitialRoll){
            System.out.println("Enemy rolled bigger, it can start the fight");
            enemyAttack();
        }else{
            System.out.println("You can start the fight");
        }


    }


    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics ig) {
        BufferedImage image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 900, 900);
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(new File("src/sprites/" + enemy.name + ".jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            sprite = null;
        }
        ig.drawImage(image, 0, 0, this);
        ig.drawImage(sprite, sprite.getWidth() / 2, 20, null);

    }

    public void ui(JFrame frame) {
        thisFrame = frame;
        attack = new JButton("Attack");
        attack.setBounds(50, 750, 200, 50);
        attack.setBackground(Color.WHITE);
        attack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attackBtn(player.damage);
            }
        });

        spell = new JButton("Spell");
        spell.setBounds(250, 750, 200, 50);
        spell.setBackground(Color.WHITE);
        spell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spell();
            }
        });

        potion = new JButton("Potion");
        potion.setBounds(50, 800, 200, 50);
        potion.setBackground(Color.WHITE);
        potion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                potion();
            }
        });

        escape = new JButton("Escape");
        escape.setBounds(250, 800, 200, 50);
        escape.setBackground(Color.WHITE);
        escape.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escape();
            }
        });

        enemyHealth = new JProgressBar();
        int maxhealth = enemy.health;
        enemyHealth.setMaximum(maxhealth);
        enemyHealth.setValue(maxhealth);
        enemyHealth.setBounds(0, 0, 900, 30);
        enemyHealth.setString(enemy.name + ": " + enemy.health + "/" + maxhealth);
        enemyHealth.setStringPainted(true);
        enemyHealth.setForeground(Color.RED);

        playerHealth = new JProgressBar();
        playerHealth.setMaximum(player.maxHealth);
        playerHealth.setValue(player.health);
        playerHealth.setBounds(500, 750, 350, 50);
        playerHealth.setString("Health: " + player.health + "/" + player.maxHealth);
        playerHealth.setStringPainted(true);
        playerHealth.setForeground(Color.GREEN);


        frame.add(attack);
        frame.add(spell);
        frame.add(potion);
        frame.add(escape);
        frame.add(enemyHealth);
        frame.add(playerHealth);


    }


    private void attackBtn(int damage) {
        attack();
        manageButtons();
        enemyAttack();
        manageButtons();
    }

    private int throwDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void attack() {
        int playerRoll = throwDice();
        int enemyRoll = throwDice();


        if(boss){
           enemyRoll++;
        }
        if (playerRoll == 6 || playerRoll + player.boost > enemyRoll) {
            enemy.health--;
            System.out.println("player attacked");
        }
        System.out.println("Player rolled: " + playerRoll);
        System.out.println("Enemy rolled: " + enemyRoll);
        if (player.familiar) {
            int familiarRoll = throwDice();
            enemyRoll = throwDice();
            if (familiarRoll == 6 || familiarRoll > enemyRoll) {

                enemy.health--;
            }
            System.out.println("Familiar rolled: " + familiarRoll);
            System.out.println("Enemy rolled: " + enemyRoll);
        }



        setStats();
        if (enemy.health<1){
            map.defeated=true;
            endBattle();
        }
    }

    private void enemyAttack() {

        int playerRoll = throwDice();
        int enemyRoll = throwDice();

        if(boss){
            enemyRoll+=2;
        }

        if (player.armor) {
            enemyRoll--;
        }
        if (enemyRoll == 6 || enemyRoll > playerRoll+player.boost) {
            player.health--;
            System.out.println("enemy attacked");
            setStats();
        }
        System.out.println("Player rolled: " + playerRoll);
        System.out.println("Enemy rolled: " + enemyRoll);
    }

    private void setStats() {
        try {
            playerHealth.setValue(player.health);
            playerHealth.setString("Health: " + player.health + "/" + player.maxHealth);
            enemyHealth.setValue(enemy.health);
            enemyHealth.setString("Health: " + enemy.health + "/" + enemy.maxHealth);
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void spell() {

    }

    private void potion() {
        if (player.potionsCount > 0) {

            player.health = player.maxHealth;
            setStats();
            player.potionsCount--;
        } else {
            System.out.println("You don't have any potions left");
        }
    }

    private void escape() {

        map.defeated = false;
        endBattle();
    }

    private void manageButtons() {
        if (attack.isEnabled()) {
            attack.setEnabled(false);
            spell.setEnabled(false);
            potion.setEnabled(false);
            escape.setEnabled(false);
        } else {
            attack.setEnabled(true);
            potion.setEnabled(true);
            escape.setEnabled(true);
        }

    }

    private void endBattle() {
        map.setFocusable(true);
        map.requestFocus();

        if(player.sword){
            player.boost=2;
        }else{
            player.boost=1;
        }

        if(player.monsterWeakened){
            player.monsterWeakened=false;
        }



        player.familiarTime--;
        if (player.familiarTime <= 0) {
            player.familiar = false;
        }
        thisFrame.dispose();
        map.started = false;
        if (map.defeated) {
            if(boss){
                player.money+=500;
                Treasure.treasure(player, false);
                player.health=player.maxHealth;
            }else{
                player.money+=100;
            }
            map.repaint();

        }

    }


}
