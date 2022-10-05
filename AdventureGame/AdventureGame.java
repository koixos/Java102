import java.util.Scanner;

public class AdventureGame {
    private final Scanner inp = new Scanner(System.in);

    public void start() {
        int isAlive = 1;

        System.out.print("""
                        
                        ============================================================================================
                        \t\t\t\t\t\t\t\tMACERA ADASINA HOŞGELDIN!
                        ============================================================================================
                        Isminiz:\s""");

        Fighter fighter = new Fighter(inp.nextLine());
        this.selectChar(fighter);
        fighter.getChest().setGold(fighter.getGameChar().getCharGold());

        System.out.print("Hadi gitmek istediğin yeri seç!\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
        while (isAlive == 1) {
            isAlive = this.selectLocation(fighter);

            if (isAlive == 0)
                System.out.print("Maceran ne yazık ki burada sonlandı " + fighter.getFighterName() + "...\n" +
                        "En kısa zamanda cesaretini toplayıp geri dönmeni umuyoruz. Burada sana ihtiyacımız var!\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

            if (isAlive == 999)
                System.out.print("Inanılmaz! Bütün canavarları yendin ve oyunu KAZANDIN! Tebrikler!!\n" +
                        "Başka maceralarda görüşmek üzere " + fighter.getFighterName() + ". Kendine iyi bak!\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
        }
    } /* END OF start */

    public void selectChar(Fighter fighter) {
        int selection;

        GameChar[] gameChars = { new Samurai(), new Archer(), new Knight() };

        System.out.print("\nMerhaba " + fighter.getFighterName() +"! Uzun bir zamandır seni bekliyorduk!\nBizi buradan yalnızca sen kurtarabilirsin...\n" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
                "Önce savaşmak için bir savaşçı seçmelisin:\n\n");

        for (GameChar value : gameChars)
            System.out.print("\t" + value.getCharId() + ".\t" + value.getCharName() + "\t\t\t");
        System.out.print("\n");
        for (GameChar value : gameChars)
            System.out.print("\t-> Sağlık:\t" + value.getCharHealth() + "\t\t");
        System.out.print("\n");
        for (GameChar value : gameChars)
            System.out.print("\t-> Hasar:\t" + value.getCharDamage() + "\t\t");
        System.out.print("\n");
        for (GameChar value : gameChars)
            System.out.print("\t-> Altın:\t" + value.getCharGold() + "\t\t");
        System.out.print("\n\n");

        selection = fighter.makeAChoice(1,3);

        switch (selection) {
            case 1 -> fighter.setGameChar(new Samurai());
            case 2 -> fighter.setGameChar(new Archer());
            case 3 -> fighter.setGameChar(new Knight());
        }
        System.out.print("Güzel seçim " + fighter.getFighterName() + "! " + fighter.getGameChar().getCharName() + " ile düşmanlarının canına okuyacaksın!\n\n");
    } /* END OF selectChar */

    public int selectLocation(Fighter fighter) {
        int selection;

        Location[]  safeLocations = {new SafeHouse(), new Store()},
                    battleLocations = {new Forest(), new River(), new Cave(), new Mine(fighter)};

            System.out.print("\t\t\t\t\t\t\t\tADA HARITASI\t\t\t(Sağlık: " + fighter.getGameChar().getCharHealth() + "/" + fighter.getGameChar().getTempHealth() + " - Altın: " + fighter.getChest().getGold() +"/" + fighter.getGameChar().getCharGold() + ")\n" + "------------------------------------------------------------------------------------------------\n");
            for (Location location : safeLocations)
                System.out.print("\n\t" + location.getLocationId() +". " + location.getLocationName());
            System.out.print("\n\t3. Savaş!\n\t4. Sandık\n\t0. Çıkış\n\n");

            selection = fighter.makeAChoice(0,4);

            if (selection == 1) {
                if (this.didWin(fighter) == 999)
                    return 999;
                SafeHouse tempLocation = new SafeHouse();
                return tempLocation.refillHealth(fighter);
            } else if (selection == 2) {
                Store tempLocation = new Store();
                return tempLocation.menu(fighter);
            } else if (selection == 3) {
                System.out.print("\n\tSavaş Mekanları\t\t(Sağlık: " + fighter.getGameChar().getCharHealth() + "/" + fighter.getGameChar().getTempHealth() + ")\n\t--------------------------------------------------------");
                for (Location location : battleLocations)
                    System.out.print("\n\t " + location.getLocationId() +". " + location.getLocationName());
                System.out.print("\n\t 0. Çıkış\n\n");

                selection = fighter.makeAChoice(0,4);

                if (selection == 1) {
                    if (fighter.getChest().isFirewood()) {
                        System.out.print("Bu bölgeyi zaten fethettin ve savaşacak hiçbir şey bırakmadın " + fighter.getFighterName() + "...\n" +
                                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                        return 1;
                    }
                    Forest tempLocation = new Forest();
                    return tempLocation.fight(fighter);
                } else if (selection == 2) {
                    if (fighter.getChest().isWater()) {
                        System.out.print("Bu bölgeyi zaten fethettin ve savaşacak hiçbir şey bırakmadın " + fighter.getFighterName() + "...\n" +
                                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                        return 1;
                    }
                    River tempLocation = new River();
                    return tempLocation.fight(fighter);
                } else if (selection == 3) {
                    if (fighter.getChest().isFood()) {
                        System.out.print("Bu bölgeyi zaten fethettin ve savaşacak hiçbir şey bırakmadın " + fighter.getFighterName() + "...\n" +
                                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                        return 1;
                    }
                    Cave tempLocation = new Cave();
                    return tempLocation.fight(fighter);
                } else if (selection == 4) {
                    Mine tempLocation = new Mine(fighter);
                    return tempLocation.fight(fighter);
                } else return 1;
            } else if (selection == 4) {
                fighter.getChest().printChestInfo();
                return 1;
            } else return 0;
    } /* END OF selectLocation */

    public int didWin(Fighter fighter) {
        if (fighter.getChest().isFood() && fighter.getChest().isFirewood() && fighter.getChest().isWater())
            return 999;
        return -999;
    } /* END OF didWin */
}
