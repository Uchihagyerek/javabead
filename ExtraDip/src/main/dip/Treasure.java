package dip;

import java.util.Random;

public class Treasure {
    private static int type;     //1:MONEY, 2:HPPOTION, 3:PPOTION, 4:WEAKEN, 5:FAMILIAR, 6:MAP, 7:ARMOR, 8: SWORD



    public static void treasure(Player player, boolean room){
        Random rand = new Random();

        System.out.println(type);
        System.out.println("TREASURE FOUND!");
        int count=rand.nextInt(4+2);
        if(!room){
            count=2;
        }
        for (int i = 1; i < count; i++) {

            type = rand.nextInt(6) + 1;


            switch (type) {
                case 1:
                    int money =rand.nextInt(100) + 100;
                    player.money += money;
                    System.out.println(i+": You found "+money+ " gold");
                    break;
                case 2:
                    player.potionsCount++;
                    System.out.println(i+": You found a potion");
                    break;
                case 3:
                    player.boost += 1;
                    System.out.println(i+": You found a Boost potion");
                    break;
                case 4:
                    player.monsterWeakened = true;
                    System.out.println(i+": You weakened the next monster");
                    break;
                case 5:
                    player.familiar = true;
                    player.familiarTime=5;
                    System.out.println(i+": You found a familiar");
                    break;
                case 6:
                    player.map.showMap();
                    System.out.println(i+": You found a map");
                    break;

            }
        }

    }




    public int getType() {
        return type;
    }


}
