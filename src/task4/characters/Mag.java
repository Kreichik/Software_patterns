package task4.characters;

import task4.weapons.Weapon;

public class Mag extends GameCharacter {

    public Mag(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void attack() {
        System.out.println("ADABRA KEDABRA!");
        weapon.applyDamage();
    }
}