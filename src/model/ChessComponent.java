package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.EventListener;


/**
 * 这个类是一个抽象类，主要表示8*8棋盘上每个格子的棋子情况
 * 在这个设计中，每个棋子的图案是用图片画出来的（由于国际象棋那个棋子手动画太难了）
 * 因此每个棋子占用的形状是一个正方形，大小是50*50
 */

public abstract class ChessComponent extends JComponent {

// Variable

    // private static final Dimension CHESSGRID_SIZE = new Dimension(1080 / 4 * 3 / 8, 1080 / 4 * 3 / 8); // 主要用于确定每个棋子在页面中显示的大小
    private static final Color[] BACKGROUND_COLORS = {Color.WHITE, Color.LIGHT_GRAY};

    private ClickController clickController; // handle click event

    private ChessboardPoint chessboardPoint; // 表示8*8棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0), (0, 7),(7, 7)等等
    protected ChessColor chessColor; //表示这个棋子的颜色，有白色，黑色，无色三种
    private String name;
    private boolean selected; // 表示这个棋子是否被选中
    private boolean Moved;
    private boolean firstAndTwo = false;//这个为第一次行棋且直进两格
    public boolean flag1 = false;

// Constructor

    protected ChessComponent(String name, ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size, boolean moved) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        this.name = name;
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
        this.Moved = moved;
    }

// Getter and Setter

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    @Override
    public Point getLocation(Point rv) {
        return super.getLocation(rv);
    }

    @Override
    public String getName() {
        return name;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public void setMoved(boolean moved) {
        Moved = moved;
    }

    public boolean isMoved() {
        return Moved;
    }

    public void setFirstAndTwo(boolean firstAndTwo) {
        this.firstAndTwo = firstAndTwo;
    }

    public boolean isFirstAndTwo() {
        return firstAndTwo;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ClickController getClickController() {
        return clickController;
    }

// Other

    public abstract boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean Turnboard, Chessboard chessboard);

    public abstract void loadResource() throws IOException; // 加载一些特定资源，如棋子图片等等; @throws IOException 如果一些资源找不到(如棋子图片路径错误)，就会抛出异常

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
//        System.out.printf("repaint chess [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
        Color squareColor = BACKGROUND_COLORS[(chessboardPoint.getX() + chessboardPoint.getY()) % 2];
        g.setColor(squareColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void swapLocation(ChessComponent another) {
        /**
         * @param another 主要用于和另外一个棋子交换位置
         *                <br>
         *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
         */

        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        /**
         * @param e 响应鼠标监听事件
         *          <br>
         *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用所有监听者的onClick方法，处理棋子的选中，移动等等行为。
         */

        super.processMouseEvent(e);

        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            clickController.onClick(this);
        }
    }

}
