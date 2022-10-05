public abstract class GameChar {
    private String charName;
    private int charId;
    private int charHealth;
    private int tempHealth;
    private int charDamage;
    private int charGold;

    public String getCharName() {
        return charName;
    } /* END OF getCharName */

    public void setCharName(String charName) {
        this.charName = charName;
    } /* END OF setCharName */

    public int getCharId() {
        return charId;
    } /* END OF getCharId */

    public void setCharId(int charId) {
        this.charId = charId;
    } /* END OF setCharId */

    public int getCharHealth() {
        return charHealth;
    } /* END OF getCharHealth */

    public void setCharHealth(int charHealth) {
        this.charHealth = charHealth;
    } /* END OF setCharHealth */

    public int getTempHealth() {
        return tempHealth;
    } /* END OF getTempHealth */

    public void setTempHealth(int tempHealth) {
        this.tempHealth = tempHealth;
    } /* END OF setTempHealth */

    public int getCharDamage() {
        return charDamage;
    } /* END OF getCharDamage */

    public void setCharDamage(int charDamage) {
        this.charDamage = charDamage;
    } /* END OF setCharDamage */

    public int getCharGold() {
        return charGold;
    } /* END OF getCharGold */

    public void setCharGold(int charGold) {
        this.charGold = charGold;
    } /* END OF setCharGold */
}

class Samurai extends GameChar {
    public Samurai() {
        setCharName("Samuray");
        setCharId(1);
        setCharHealth(21);
        setTempHealth(this.getCharHealth());
        setCharDamage(5);
        setCharGold(15);
    } /* END OF Samurai */
}

class Archer extends GameChar {
    public Archer() {
        setCharName("Okçu");
        setCharId(2);
        setCharHealth(18);
        setTempHealth(this.getCharHealth());
        setCharDamage(7);
        setCharGold(20);
    } /* END OF Archer */
}

class Knight extends GameChar {
    public Knight() {
        setCharName("Şövalye");
        setCharId(3);
        setCharHealth(24);
        setTempHealth(this.getCharHealth());
        setCharDamage(8);
        setCharGold(5);
    } /* END OF Knight */
}