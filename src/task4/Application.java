package task4;


import task4.characters.*;
import task4.weapons.*;


public class Application {
    public static void main(String[] args) {
        System.out.println("\nThe FIGHT began!\n");

        System.out.println("------");
        Weapon sword = new Hammer();
        Weapon Bow = new Bow();
        Weapon megaFireball = new Fireball();
        Weapon pineApple = new Pineapple();

        GameCharacter Mario = new Warrior(sword);
        Mario.attack();

        System.out.println("------");

        GameCharacter Dumbledore = new Mag(megaFireball);
        Dumbledore.attack();
        System.out.println("------");
        Mario.equipWeapon(Bow);
        Mario.attack();
        System.out.println("------");

        Dumbledore.equipWeapon(pineApple);
        Dumbledore.attack();
        System.out.println("------");

        System.out.println("\nThe FIGHT is over!\n");
    }
}