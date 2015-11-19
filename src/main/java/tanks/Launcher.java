package tanks;

public class Launcher {

    public static void main(String[] args) throws Exception {

        simpleStart();
    }

    public static void simpleStart() throws Exception {

        ChooseTank ct = new ChooseTank();
        ct.createTankPanel();
        Thread.sleep(1000);

        ActionField af = new ActionField();
        af.getTank(ct.youChoise());

        System.out.println("Launcher ,- Tank = " + ct.youChoise());
        af.runTheGame();
    }
}
