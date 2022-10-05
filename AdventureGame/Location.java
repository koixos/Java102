import java.util.Scanner;

public abstract class Location {
    private String locationName;
    private int locationId;

    public abstract int isAlive(Fighter fighter);

    public String getLocationName() {
        return locationName;
    } /* END OF getLocationName */

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    } /* END OF setLocationName */

    public int getLocationId() {
        return locationId;
    } /* END OF getLocationId */

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    } /* END OF setLocationId */
}

abstract class SafeLocation extends Location {
    @Override
    public int isAlive(Fighter fighter) {
        return 1;
    } /* END OF isAlive */
}

class SafeHouse extends SafeLocation {
    public SafeHouse() {
        this.setLocationName("Güvenli Ev");
        this.setLocationId(1);
    } /* END OF SafeHouse */

    public int refillHealth(Fighter fighter) {
        System.out.print(
                "Biraz soluklan " + fighter.getFighterName() + ", " + this.getLocationName() + "desin...\n" +
                "Gücünü topladıktan sonra savaşmaya kaldığın yerden devam edebilirsin!\n\nSağlık yenileniyor..." + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
        fighter.getGameChar().setCharHealth(fighter.getGameChar().getTempHealth());
        return this.isAlive(fighter);
    } /* END OF refillHealth */
}

class Store extends SafeLocation {
    public Store() {
        this.setLocationName("Mağaza");
        this.setLocationId(2);
    } /* END OF Store */

    public int menu(Fighter fighter) {
        int exit = 0, selection;

        System.out.print(this.getLocationName() + "ya hoş geldin " + fighter.getFighterName() + ".\nBurada düşmanlarınla savaşırken işine yarayacak ekipmanlar bulabilirsin.\n\n");

        while (exit == 0) {
            System.out.print("\n\t\tMENÜ\t\t(" + fighter.getChest().getGold() + " Altın)\n\t---------------------------\n\t 1. Silah Listesi\n\t 2. Zırh Listesi\n\t 0. Çıkış\n\n");

            selection = fighter.makeAChoice(0,2);

            switch (selection) {
                case 1 -> this.buyWeapon(fighter);
                case 2 -> this.buyArmor(fighter);
                case 0 -> exit = 1;
            }
        }
        return this.isAlive(fighter);
    } /* END OF menu */

    public void buyWeapon(Fighter fighter) {
        int selection, exit = 0;

        Weapon[] weapons = {new Revolver(), new Rifle(), new Sword()};

        System.out.print("\n\tSILAH LISTESI\t (" + fighter.getChest().getGold() + " Altın)\n\t---------------------------");
        for (Weapon weapon : weapons)
            System.out.print("\n\t " + weapon.getWeaponId() + ". " + weapon.getWeaponName() +
                            "\n\t -> Hasar: " + weapon.getWeaponDamage() +
                            "\n\t -> Fiyat: " + weapon.getWeaponPrice() + "\n");
        System.out.print("\n\t 0. Çıkış\n\n");

        while (exit == 0) {
            selection = fighter.makeAChoice(0,3);

            if (selection == 0)
                exit = 1;
            else if (fighter.getChest().getGold() >= weapons[selection-1].getWeaponPrice()) {
                fighter.getChest().setWeapon(weapons[selection-1]);
                fighter.getChest().setGold(fighter.getChest().getGold() - fighter.getChest().getWeapon().getWeaponPrice());
                System.out.print("Yeni ekipman kilidi açıldı " + fighter.getFighterName() + "! Artık düşmanlarına karşı çok daha güçlüsün!\n" + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                exit = 1;
            } else System.out.print("""
                    Ne yazık ki yeteri kadar altının yok... Daha fazla düşmanla savaşarak hazineni genişletebilirsin!
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                    """);
        }
    } /* END OF buyWeapon */

    public void buyArmor(Fighter fighter) {
        int selection, exit = 0;

        Armor[] armors = {new LightArmor(), new MediumArmor(), new HeavyArmor()};

        System.out.print("\n\tZIRH LISTESI \t(" + fighter.getChest().getGold() + " Altın)\n\t---------------------------");
        for (Armor armor : armors)
            System.out.print("\n\t " + armor.getArmorId() + ". " + armor.getArmorName() +
                    "\n\t -> Savunma: " + armor.getArmorDefence() +
                    "\n\t -> Fiyat: " + armor.getArmorPrice() + "\n");
        System.out.print("\n\t 0. Çıkış\n\n");

        while (exit == 0) {
            selection = fighter.makeAChoice(0,3);

            if (selection == 0)
                exit = 1;
            else if (fighter.getChest().getGold() >= armors[selection-1].getArmorPrice()) {
                fighter.getChest().setArmor(armors[selection-1]);
                fighter.getChest().setGold(fighter.getChest().getGold() - fighter.getChest().getArmor().getArmorPrice());
                System.out.print("Yeni ekipman kilidi açıldı " + fighter.getFighterName() + "! Artık düşmanlarına karşı çok daha güçlüsün!\n" + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                exit = 1;
            } else System.out.print("""
                    Ne yazık ki yeteri kadar altının yok... Daha fazla düşmanla savaşarak hazineni genişletebilirsin!
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                    """);
        }
    } /* END OF buyArmor */
}

abstract class BattleLocation extends Location {
    private final Scanner inp = new Scanner(System.in);
    private Obstacle obstacle;

    public abstract void printLocationInfo(Fighter fighter);

    @Override
    public int isAlive(Fighter fighter) {
        if (fighter.getGameChar().getCharHealth() > 0)
            return 1;
        return 0;
    } /* END OF isAlive */

    public int obstacleNumber() {
        return (int)(Math.random() * 3) + 3;
    } /* END OF obstacleNumber */

    public int fight(Fighter fighter) {
        int     obstacleNumber = this.obstacleNumber(),
                tempNumber = obstacleNumber,
                tempObstacleHealth = this.getObstacle().getObstacleHealth(),
                round = 0;

        this.printLocationInfo(fighter);
        this.getObstacle().printObstacleInfo();

        if (this.fightOrRetreat()) return this.isAlive(fighter);

        while (isAlive(fighter) == 1) {
            int firstMove = 0;

            if (obstacleNumber <= 0) {
                fighter.getChest().setGold(fighter.getChest().getGold() + (tempNumber * this.getObstacle().getObstacleGold()));
                switch (this.getLocationId()) {
                    case 1 -> fighter.getChest().setFirewood(true);
                    case 2 -> fighter.getChest().setWater(true);
                    case 3 -> fighter.getChest().setFood(true);
                }

                System.out.print("\nTebrikler " + fighter.getFighterName() + ", bütün düşmanlarının üstesinden geldin ve bölgeyi fethettin"  + "!\n" +
                        "Işte ödülün...\n\n" +
                        "-> Toplanılan ganimet: " + this.getObstacle().getSpoil() +
                        "\n-> Toplanılan altın: " + (tempNumber * this.getObstacle().getObstacleGold()));
                System.out.print("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                return this.isAlive(fighter);
            }

            if (this.getObstacle().getObstacleHealth() > 0) {
                System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\t\t\t\t\t\t\t\tRAUNT: " + ++round + "\n\n\t\t\t\tSAVAŞÇI: " + fighter.getGameChar().getCharHealth() + "\t\t\s\sVS\t\tDÜŞMAN: " + this.getObstacle().getObstacleHealth() + "\n\nDüşman Sayısı: " + obstacleNumber + "\n");
                if (fighter.getChest().getWeapon() != null)
                    System.out.print("Silah: " + fighter.getChest().getWeapon().getWeaponName() + " (+" + fighter.getChest().getWeapon().getWeaponDamage() + " hasar)\t\t");
                else System.out.print("Silah: -\t\t");
                if (fighter.getChest().getArmor() != null)
                    System.out.print("Zırh: " + fighter.getChest().getArmor().getArmorName() + " (+" + fighter.getChest().getArmor().getArmorDefence() + " savunma)\n");
                else System.out.print("Zırh: -\n");
                firstMove = this.firstMove();
            }

            if (firstMove == 2)
                if (this.attack(fighter, this.getObstacle()) == -999)
                    return this.isAlive(fighter);
            if (this.getObstacle().getObstacleHealth() <= 0) {
                System.out.print("\n................................................................................................\nVaov! Düşmanlarından biri daha kaybetti!\n");
                --obstacleNumber;
                this.getObstacle().setObstacleHealth(tempObstacleHealth);
                firstMove = 0;
            } else this.defence(fighter, this.getObstacle());
            if (firstMove == 1)
                if (this.attack(fighter, this.getObstacle()) == -999)
                    return this.isAlive(fighter);
        }
        System.out.print("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nAh! Çok fazla can kaybettin!\n");
        return this.isAlive(fighter);
    } /* END OF fight */

    public int attack(Fighter fighter, Obstacle tempObstacle) {
        int totalDamage;

        if (this.fightOrRetreat()) return -999;

        if (fighter.getChest().getWeapon() != null)
            totalDamage = fighter.getGameChar().getCharDamage() + fighter.getChest().getWeapon().getWeaponDamage();
        else totalDamage = fighter.getGameChar().getCharDamage();

        System.out.print("\n................................................................................................\nSüper! Düşmana " + totalDamage + " puan hasar verdin." +
                "\n\n\t\t\t\tSAVAŞÇI: " + fighter.getGameChar().getCharHealth() + "\t\tVS\t\tDÜŞMAN: " + tempObstacle.getObstacleHealth() + " (-" + totalDamage + ")\n");

        tempObstacle.setObstacleHealth(tempObstacle.getObstacleHealth() - totalDamage);

        return 999;
    } /* END OF attack */

    public void defence(Fighter fighter, Obstacle tempObstacle) {
        int totalDamage;
        if (fighter.getChest().getArmor() != null)
            totalDamage = tempObstacle.getObstacleDamage() - fighter.getChest().getArmor().getArmorDefence();
        else totalDamage = tempObstacle.getObstacleDamage();

        System.out.print("\n................................................................................................\nUps! " + totalDamage + " puan hasar aldın." +
                "\n\n\t\t\t\tSAVAŞÇI: " + fighter.getGameChar().getCharHealth() + " (-" + totalDamage + ")\t\tVS\t\tDÜŞMAN: " + tempObstacle.getObstacleHealth() + "\n");

        fighter.getGameChar().setCharHealth(fighter.getGameChar().getCharHealth() - totalDamage);
    } /* END OF defence */


    public boolean fightOrRetreat() {
        String choice;
        while (true) {
            System.out.print("\n................................................................................................\n# (S)aldır & (G)eri Çekil : ");
            choice = inp.nextLine();
            if (choice.equals("S") || choice.equals("s"))
                return false;
            else if (choice.equals("G") || choice.equals("g")) {
                System.out.print("\nAcele et! Çok geç olmadan buradan uzaklaşalım!\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                return true;
            }
            System.out.print("Lütfen geçerli bir seçim yapınız.\n................................................................................................");
        }
    } /* END OF fightOrRetreat */

    public int firstMove() {
        return (int)(Math.random() * 2) + 1;
    } /* END OF firstMove */

    public Obstacle getObstacle() {
        return obstacle;
    } /* END OF getObstacle */

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    } /* END OF setObstacle */
}

class Forest extends BattleLocation {
    public Forest() {
        this.setLocationName("Orman");
        this.setLocationId(1);
        this.setObstacle(new Vampire());
    } /* END OF Forest */

    @Override
    public void printLocationInfo(Fighter fighter) {
        System.out.print("Cesur seçim " + fighter.getFighterName() + ". " +
                this.getLocationName() + " yüz yıllardır " + this.getObstacle().getObstacleName() + "lerin istilası altında...\n" +
                "Şimdiye kadar bu karanlık ve korkunç yere girmeye cesaret edebilen hiç kimse geri dönemedi...\n" +
                "Umuyoruz ki sen ilk olursun... Sana güveniyoruz!\n\n");
    } /* END OF printLocationInfo */
}

class River extends BattleLocation {
    public River() {
        this.setLocationName("Nehir");
        this.setLocationId(2);
        this.setObstacle(new Werewolf());
    } /* END OF River */

    @Override
    public void printLocationInfo(Fighter fighter) {
        System.out.print("Buraya kadar gelebildin demek! Gerçekten de beklediğimizden çok daha cesur ve güçlü bir savaşçıymışsın " + fighter.getFighterName() + "!\n" +
                this.getLocationName() + " " + this.getObstacle().getObstacleName() + "ların bir numaralı çöplüğü... Devasa vücutları ve olağanüstü kas güçleriyle onlarla baş edebilen çok az savaşçı oldu...\nAma biz senin başarabileceğine inanıyoruz " + fighter.getFighterName() + "! Hadi durma! Bir an önce yola koyul!\n\n");
    } /* END OF printLocationInfo */
}

class Cave extends BattleLocation {
    public Cave() {
        this.setLocationName("Mağara");
        this.setLocationId(3);
        this.setObstacle(new Witch());
    } /* END OF Cave */

    @Override
    public void printLocationInfo(Fighter fighter) {
        System.out.print("Demek mağaraya geldin... Şimdiden seni uyarmalıyım " + fighter.getFighterName() + "...\n" +
                this.getObstacle().getObstacleName() + "lar bütün canavarlar içerisinden en güçlü ve neredeyse yenilmez olanıdır. Eğer gerekli ekipmanın ve en önemlisi cesaretin yoksa başa çıkman çok zor!\n" +
                "Umuyoruz ki buraya gelmekle akıllıca bir seçim yapmışsındır... Bol şans! Çok ihtiyacın olacak...\n\n");
    } /* END OF printLocationInfo */
}

class Mine extends BattleLocation {
    public Mine(Fighter fighter) {
        this.setLocationName("Maden");
        this.setLocationId(4);
        this.setObstacle(new Zombie(fighter));
    } /* END OF Mine */

    @Override
    public void printLocationInfo(Fighter fighter) {

    } /* END OF printLocationInfo */
}

