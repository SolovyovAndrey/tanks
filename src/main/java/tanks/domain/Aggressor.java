package tanks.domain;

import tanks.ActionField;
import tanks.BattleField;
import tanks.service.BFObject;
import tanks.service.Direction;

import java.awt.*;

public class Aggressor extends AbstractTank {

    private ActionField af;
    private BattleField bf;
    private Boolean Agressor = true;

    public Aggressor(ActionField af, BattleField bf) {
        super(af, bf);
        this.af = af;
        this.bf = bf;
        tankColor = new Color(255, 250, 0);
        towerColor = new Color(255, 0, 0);

    }

    public Aggressor(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        this.af = af;
        this.bf = bf;
        tankColor = new Color(255, 255, 0);
        towerColor = new Color(255, 0, 0);
    }


    public void processDestroyShtab() throws Exception {

        int Yt = this.getY() / 64 + 1;
        int Xt = this.getX() / 64 + 1;

        int YEagle = 0;
        int XEagle = 0;

        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                BFObject bfObject = bf.scanQuadrant(i, k);
                if ((bfObject instanceof Eagle)) {
                    XEagle = k + 1;
                    YEagle = i + 1;
                }
            }
        }
        int numY = positiveNumber(Yt - YEagle);
        int numX = positiveNumber(Xt - XEagle);

        if (numY > numX || numY == numX) {

            this.moveToQuadrant(XEagle, Yt);

            if (Yt > YEagle) {
                this.action("UP");
            } else {
                this.action("DOWN");
            }
            System.out.println("what Y --- " + (XEagle - 1));
            //af.namberShotY(this, XEagle - 1);
            af.namberShotY(this,(XEagle - 1));


        } else {

            this.moveToQuadrant(Xt, YEagle);

            if (Xt > XEagle) {
                this.action("LEFT");
            } else {
                this.action("RIGHT");
            }

            af.namberShotX(this,YEagle - 1);

        }
    }


    public int positiveNumber(int i) {
        if (i < 0) {
            i = i * (-1);
        }
        return i;
    }

    public Boolean getAgressor() {
        return Agressor;
    }
}
