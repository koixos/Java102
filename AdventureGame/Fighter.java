import java.util.Scanner;

public class Fighter {
    private final Scanner inp = new Scanner(System.in);
    private final Chest chest = new Chest();
    private GameChar gameChar;
    private final String fighterName;

    public Fighter(String fighterName) {
        this.fighterName = fighterName;
    } /* END OF Fighter */

    public int makeAChoice(int minValue, int maxValue) {
        int selection;
        while (true) {
            System.out.print("Seçim: ");
            try {
                selection = inp.nextInt();
                if (selection >= minValue && selection <= maxValue)
                    break;
                else System.out.print("Lütfen geçerli bir seçim yap!\n");
            } catch (Exception e) {
                inp.nextLine();
                System.out.print("Lütfen geçerli bir seçim yap!\n");
            } finally {
                System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
            }
        }
        return selection;
    } /* END OF makeAChoice */

    public Chest getChest() {
        return chest;
    } /* END OF getChest */

    public GameChar getGameChar() {
        return gameChar;
    } /* END OF getGameChar */

    public void setGameChar(GameChar gameChar) {
        this.gameChar = gameChar;
    } /* END OF setGameChar */

    public String getFighterName() {
        return fighterName;
    } /* END OF getFighterName */
}
