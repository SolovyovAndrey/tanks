package tanks.service;

import tanks.ActionField;
import tanks.BattleField;
import tanks.domain.AbstractTank;
import tanks.domain.Tiger;

import java.util.List;

public class Replay {

    public List <String> stepTank;
    public List <TankAction> stepDefaunder;
    private AbstractTank tank;
    private AbstractTank tankA;
    private BattleField battleField;

    public Replay(List<String> stepTank) {

        this.stepTank = stepTank;
    }

    public void start() throws Exception {

        ActionField af = new ActionField();
        System.out.println(af.getTank(Integer.valueOf(stepTank.get(0))).getClass().getSimpleName());
        tank = new Tiger(af, battleField);
        af.savedReplay = false;
        tankA = new Tiger(af, battleField, 256, 512, Direction.DOWN);
        ThreadVision tv = new ThreadVision(af);
        new Thread(tv).start();

 //       ThreadAgressor ta = new ThreadDefaunder(af.getTank(Integer.valueOf(stepTank.get(0))), af.savedReplay, stepTank, stepDefaunder);
 //       new Thread(td).start();

        
        
    }


    private String reader() {

        String str = null;

        stepTank.get(0);

        return str;
    }
}
