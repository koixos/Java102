public abstract class Obstacle {
    private String obstacleName;
    private int obstacleHealth;
    private int obstacleDamage;
    private int obstacleGold;
    private String spoil;

    public void printObstacleInfo() {
        System.out.print("- Düşman Bilgileri\n-------------------------------\n\tTür: " + this.getObstacleName() +
                "\n\t-> Sağlık: " + this.getObstacleHealth() +
                "\n\t-> Hasar: " + this.getObstacleDamage() +
                "\n\t-> Hazine: " + this.getObstacleGold() +
                "\n\t-> Ödül: " + this.getSpoil() +
                "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    } /* END OF printObstacleInfo */

    public String getObstacleName() {
        return obstacleName;
    } /* END OF getObstacleName */

    public void setObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    } /* END OF setObstacleName */

    public int getObstacleHealth() {
        return obstacleHealth;
    } /* END OF getObstacleHealth */

    public void setObstacleHealth(int obstacleHealth) {
        this.obstacleHealth = obstacleHealth;
    } /* END OF setObstacleHealth */

    public int getObstacleDamage() {
        return obstacleDamage;
    } /* END OF getObstacleDamage */

    public void setObstacleDamage(int obstacleDamage) {
        this.obstacleDamage = obstacleDamage;
    } /* END OF setObstacleDamage */

    public int getObstacleGold() {
        return obstacleGold;
    } /* END OF getObstacleGold */

    public void setObstacleGold(int obstacleGold) {
        this.obstacleGold = obstacleGold;
    } /* END OF setObstacleGold */

    public String getSpoil() {
        return spoil;
    } /* END OF getSpoil */

    public void setSpoil(String spoil) {
        this.spoil = spoil;
    } /* END OF setSpoil */
}

class Vampire extends Obstacle {
    public Vampire() {
        this.setObstacleName("Vampir");
        this.setObstacleHealth(10);
        this.setObstacleDamage(3);
        this.setObstacleGold(4);
        this.setSpoil("Odun");
    } /* END OF Vampire */
}

class Werewolf extends Obstacle {
    public Werewolf() {
        this.setObstacleName("Kurt Adam");
        this.setObstacleHealth(14);
        this.setObstacleDamage(4);
        this.setObstacleGold(7);
        this.setSpoil("Su");
    } /* END OF Werewolf */
}

class Witch extends Obstacle {
    public Witch() {
        this.setObstacleName("Cadı");
        this.setObstacleHealth(20);
        this.setObstacleDamage(7);
        this.setObstacleGold(12);
        this.setSpoil("Yemek");
    } /* END OF Witch */
}

class Zombie extends Obstacle {
    public Zombie(Fighter fighter) {
        this.setObstacleName("Zombi");
        this.setObstacleHealth(12);
        this.setObstacleDamage(randomDamage());
        this.setObstacleGold(0);
        this.setSpoil(randomSpoil(fighter));
    } /* END OF Zombie */

    public int randomDamage() {
        return (int)(Math.random() * 4) + 3;
    } /* END OF randomDamage */

    public String randomSpoil(Fighter fighter) {
        int chance = this.randomChance();

        if (chance <= 15) {
            Weapon spoil;
            chance = this.randomChance();

            if (chance <= 20) spoil = new Rifle();
            else if (chance <= 50) spoil = new Sword();
            else spoil = new Revolver();

            fighter.getChest().setWeapon(spoil);
            return spoil.getWeaponName();
        } else if (chance <= 30) {
            Armor spoil;
            chance = this.randomChance();

            if (chance <= 20) spoil = new HeavyArmor();
            else if (chance <= 50) spoil = new MediumArmor();
            else spoil = new LightArmor();

            fighter.getChest().setArmor(spoil);
            return spoil.getArmorName();
        } else if (chance <= 55) {
            int spoil;
            chance = this.randomChance();

            if (chance <= 20) spoil = 10;
            else if (chance <= 50) spoil = 5;
            else spoil = 1;

            fighter.getChest().setGold(fighter.getChest().getGold() + spoil);
            return spoil + " Altın";
        } else return "-";
    } /* END OF randomSpoil */

    public int randomChance() {
        return (int)(Math.random() * 100) + 1;
    } /* END OF randomChance */
}