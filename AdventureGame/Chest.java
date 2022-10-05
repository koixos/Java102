public class Chest {
    private Weapon weapon;
    private Armor armor;
    private boolean food;
    private boolean water;
    private boolean firewood;
    private int gold;

    public Chest() {
        this.weapon = null;
        this.armor = null;
        this.food = false;
        this.water = false;
        this.firewood = false;
        this.gold = 0;
    } /* END OF Chest */

    public Weapon getWeapon() {
        return weapon;
    } /* END OF getWeapon */

    public void printChestInfo() {
        System.out.print("""
                \t\t\tSandık
                ------------------------------------------------------------------------------------------------
                \tSilah\t:\s""");
        if (this.getWeapon() == null)
            System.out.print("-");
        else System.out.print(this.getWeapon().getWeaponName() + " (Hasar: +" + this.getWeapon().getWeaponDamage() + ")");
        System.out.print("\n\tZırh\t: ");
        if (this.getArmor() == null)
            System.out.print("-");
        else System.out.print(this.getArmor().getArmorName() + " (Savunma: +" + this.getArmor().getArmorDefence() + ")");
        System.out.print("\n\tAltın\t: " + this.getGold() +
                "\n\tOdun\t: ");
        if (this.isFirewood()) System.out.print("VAR");
        else System.out.print("YOK");
        System.out.print("\n\tYemek\t: ");
        if (this.isFood()) System.out.print("VAR");
        else System.out.print("YOK");
        System.out.print("\n\tSu\t\t: ");
        if (this.isWater()) System.out.print("VAR");
        else System.out.print("YOK");
        System.out.print("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
    } /* END OF printChestInfo */

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    } /* END OF setWeapon */

    public Armor getArmor() {
        return armor;
    } /* END OF getArmor */

    public void setArmor(Armor armor) {
        this.armor = armor;
    } /* END OF setArmor */

    public boolean isFood() {
        return food;
    } /* END OF isFood */

    public void setFood(boolean food) {
        this.food = food;
    } /* END OF setFood */

    public boolean isWater() {
        return water;
    } /* END OF isWater */

    public void setWater(boolean water) {
        this.water = water;
    } /* END OF setWater */

    public boolean isFirewood() {
        return firewood;
    } /* END OF isFirewood */

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    } /* END OF setFirewood */

    public int getGold() {
        return gold;
    } /* END OF getGold */

    public void setGold(int gold) {
        this.gold = gold;
    } /* END OF setGold */
}
