package task4.characters;

import task4.weapons.Weapon;

public abstract class GameCharacter {

    protected Weapon weapon;

    public GameCharacter(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    public abstract void attack();
}