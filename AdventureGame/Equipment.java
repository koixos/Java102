public abstract class Equipment {}
abstract class Weapon extends Equipment {
    private String weaponName;
    private int weaponId;
    private int weaponDamage;
    private int weaponPrice;

    public String getWeaponName() {
        return weaponName;
    } /* END OF getWeaponName */

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    } /* END OF setWeaponName */

    public int getWeaponId() {
        return weaponId;
    } /* END OF getWeaponId */

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    } /* END OF setWeaponId */

    public int getWeaponDamage() {
        return weaponDamage;
    } /* END OF getWeaponDamage */

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    } /* END OF setWeaponDamage */

    public int getWeaponPrice() {
        return weaponPrice;
    } /* END OF getWeaponPrice */

    public void setWeaponPrice(int weaponPrice) {
        this.weaponPrice = weaponPrice;
    } /* END OF setWeaponPrice */
}

class Revolver extends Weapon {
    public Revolver() {
        this.setWeaponName("Tabanca");
        this.setWeaponId(1);
        this.setWeaponDamage(2);
        this.setWeaponPrice(25);
    } /* END OF Revolver */
}

class Rifle extends Weapon {
    public Rifle() {
        this.setWeaponName("Tüfek");
        this.setWeaponId(2);
        this.setWeaponDamage(3);
        this.setWeaponPrice(35);
    } /* END OF Revolver */
}

class Sword extends Weapon {
    public Sword() {
        this.setWeaponName("Kılıç");
        this.setWeaponId(3);
        this.setWeaponDamage(7);
        this.setWeaponPrice(45);
    } /* END OF Revolver */
}

abstract class Armor extends Equipment {
    private String armorName;
    private int armorId;
    private int armorDefence;
    private int armorPrice;

    public String getArmorName() {
        return armorName;
    } /* END OF getArmorName */

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    } /* END OF setArmorName */

    public int getArmorId() {
        return armorId;
    } /* END OF getArmorId */

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    } /* END OF setArmorId */

    public int getArmorDefence() {
        return armorDefence;
    } /* END OF getArmorDefence */

    public void setArmorDefence(int armorDefence) {
        this.armorDefence = armorDefence;
    } /* END OF setArmorDefence */

    public int getArmorPrice() {
        return armorPrice;
    } /* END OF getArmorPrice */

    public void setArmorPrice(int armorPrice) {
        this.armorPrice = armorPrice;
    } /* END OF setArmorPrice */
}

class LightArmor extends Armor {
    public LightArmor() {
        setArmorName("Hafif Zırh");
        setArmorId(1);
        setArmorDefence(1);
        setArmorPrice(15);
    } /* END OF LightArmor */
}

class MediumArmor extends Armor {
    public MediumArmor() {
        setArmorName("Orta Zırh");
        setArmorId(2);
        setArmorDefence(3);
        setArmorPrice(25);
    } /* END OF MediumArmor */
}

class HeavyArmor extends Armor {
    public HeavyArmor() {
        setArmorName("Ağır Zırh");
        setArmorId(3);
        setArmorDefence(5);
        setArmorPrice(40);
    } /* END OF HeavyArmor */
}