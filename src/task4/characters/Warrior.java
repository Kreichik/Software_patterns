package task4.characters;

import task4.weapons.Weapon;

public class Warrior extends GameCharacter {

    public Warrior(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void attack() {
        System.out.println("It's me! Mario!");
        weapon.applyDamage();
    }
}