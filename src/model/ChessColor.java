package model;

import java.awt.*;

/**
 * 这个类主要用于包装Color对象，用于Chess游戏使用。
 */
public enum ChessColor {
    BLACK("Black", Color.BLACK), WHITE("White", Color.WHITE), NONE("No Player", Color.WHITE);

    private final String name;
    private final Color color;
    public  boolean lastone;
    public int lastX;
    public int lastY;

    ChessColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void setLastone(Color color,boolean lastone,int x,int y) {//用以判断吃过路兵中是否在对方行两步之后立马吃过路兵
        if(color==Color.BLACK){//注意：这里的set lastone是设置的对方兵是否刚刚直行两步
            BLACK.lastone=lastone;//即这里是WHITE刚刚执行完直行两步
            BLACK.lastX=x;//x，y用以判断被吃的子是否是刚刚执行完直行的棋子
            BLACK.lastY=y;
        }else if(color==Color.WHITE){
            WHITE.lastone=lastone;
            WHITE.lastX=x;
            WHITE.lastY=y;
        }
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
