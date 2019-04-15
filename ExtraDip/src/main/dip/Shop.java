package dip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shop extends Canvas {
    static JFrame thisFrame;
    Map map;
    Player player;
    static boolean isOpen=false;




    public Shop(Map map, Player player){
        this.map=map;
        this.player=player;

        setSize (300,320);


    }


    @Override
    public void update(Graphics g) {
        paint(g);

    }

    @Override
    public void paint(Graphics ig) {

    }

    public void ui(JFrame frame){
        thisFrame=frame;


        JButton potion =new JButton ("Potion (50g)");
        JButton boost=new JButton ("Boost Potion (150g)");
        JButton familiar = new JButton ("Summon familiar (300g)");
        JButton map=new JButton ("Map (350g)");
        JButton sword=new JButton ("Sword (500g)");
        JButton armor=new JButton ("Armor (500g)");
        JButton exit=new JButton ("Exit");

        potion.setBounds (10,10,135,75);
        potion.setBackground (Color.WHITE);
       potion.addActionListener(new ActionListener () {
           public void actionPerformed(ActionEvent e) {
               buyPotion ();
               System.out.println ("You have "+player.money+" gold");
           }
       });

        boost.setBounds (155,10,135,75);
        boost.setBackground (Color.WHITE);
        boost.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buyBoost ();
                System.out.println ("You have "+player.money+" gold");
            }
        });


        familiar.setBounds (10,95,135,75);
        familiar.setBackground (Color.WHITE);
        familiar.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buyFamiliar ();
                System.out.println ("You have "+player.money+" gold");
            }
        });
        if(player.familiar){
            familiar.setEnabled (false);
        }

        map.setBounds (155,95,135,75);
        map.setBackground (Color.WHITE);
        map.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buyMap ();
                System.out.println ("You have "+player.money+" gold");
            }
        });

        sword.setBounds (10,180,135,75);
        sword.setBackground (Color.WHITE);
        sword.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buySword ();
                System.out.println ("You have "+player.money+" gold");
            }
        });
        if(player.sword){
            sword.setEnabled (false);
        }

        armor.setBounds (155,180,135,75);
        armor.setBackground (Color.WHITE);
        armor.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                buyArmor ();
                System.out.println ("You have "+player.money+" gold");
            }
        });
        if(player.armor){
            armor.setEnabled (false);
        }

        exit.setBounds (215,275,75,35);
        exit.setBackground (Color.WHITE);
        exit.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                exit ();
            }
        });



        thisFrame.add(potion);
        thisFrame.add(boost);
        thisFrame.add(familiar);
        thisFrame.add(map);
        thisFrame.add(sword);
        thisFrame.add(armor);
        thisFrame.add(exit);



    }

    private void buyPotion() {
        if(player.money>=50){
            player.money-=50;
            player.potionsCount++;
        }else{
            System.out.println ("You don't have enough money");
        }
    }

    private void buyBoost(){
        if(player.money>=150){
            player.money-=150;
            player.boost++;
        }else{
            System.out.println ("You don't have enough money");
        }
    }

    private void buyFamiliar(){
        if(player.money>=300){
            player.money-=300;
            player.familiar=true;
            player.familiarTime=5;
        }else{
            System.out.println ("You don't have enough money");
        }
    }

    private void buyMap(){
        if(player.money>=350){
            player.money-=350;
            player.map.showMap ();
        }else{
            System.out.println ("You don't have enough money");
        }
    }

    private void buySword(){
        if(player.money>=500){
            player.money-=500;
            player.sword=true;
            player.boost=2;
        }else{
            System.out.println ("You don't have enough money");
        }

    }

    private void buyArmor(){
        if(player.money>=500){
            player.money-=500;
            player.armor=true;
        }else{
            System.out.println ("You don't have enough money");
        }
    }

    private void exit(){
        thisFrame.dispose ();
    }


}
