package tanks;

import tanks.domain.*;

import java.awt.*;
import java.io.IOException;

public class BattleField {

    public static final String BRICK = "B";
    public static final String EAGLE = "E";
    public static final String ROCK = "R";
    public static final String WATER = "W";
    public static final String BLANK = "";

    private int bfWidth = 576;
    private int bfHeight = 576;
    private Bullet bullet;


    private String[][] battleFieldTemplate = {
            { "", "", "", "", "R", "B", "B", "", "", },
            { "B", "B", "R", "", "", "", "R", "B", "", },
            { "", "W", "W", "W", "", "", "", "", "", },
            { "", "", "B", "B", "R", "B", "", "", "", },
            { "", "", "", "", "", "", "", "", "", },
            { "", "B", "B", "", "", "B", "B", "", "R", },
            { "", "", "", "", "R", "", "", "", "", },
            { "", "", "", "R", "R", "R", "", "B", "R", },
            { "B", "", "", "", "E", "", "B", "", "", } };



    private SimpleBFObject[][] battleField = new SimpleBFObject[9][9];


    public BattleField() {
        drawBattleField();
    }


    private String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }


    private void drawBattleField() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String coordinates = getQuadrantXY(i + 1, j + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));

                String obj = battleFieldTemplate[i][j];
                SimpleBFObject bfObject = null;
                if (obj.equals(BRICK)) {
                    bfObject = new Brick(x, y);
                } else if (obj.equals(ROCK)) {
                    bfObject = new Rock(x, y);
                } else if (obj.equals(EAGLE)) {
                    bfObject = new Eagle(x, y);
                } else if (obj.equals(WATER)) {
                    bfObject = new Water(x, y);
                } else if(obj.equals(BLANK)) {
                    bfObject = new Blank(x, y);
                }
                battleField[i][j] = bfObject;
            }
        }
    }


    public void updateQuadrante(int v, int h, String o){

        battleFieldTemplate[v][h] = o;
    }


    public SimpleBFObject scanQuadrant(int v, int h){
        return battleField[v][h];
    }

    public int getDimentionX(){
        return battleField.length;
    }

    public int getDimentionY(){
        return battleField.length;
    }

    public int getBfWidth(){
        return bfWidth;
    }

    public int getBfHeight(){
        return bfHeight;
    }

    public void destroyObject(int v, int h, SimpleBFObject bfObject) {
        battleField[v][h].destroy(bfObject);
    }


    public void draw(Graphics g) throws IOException {
        Batle batle = new Batle(0,0);
        batle.draw(g);

        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                    battleField[j][k].draw(g);
            }
        }
    }
}