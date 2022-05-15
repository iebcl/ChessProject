package model;
// 象

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;



/////
/**
 * 这个类表示国际象棋里面的象
 */
public class BishopChessComponent extends ChessComponent {
    /**
     * 黑象和白象的图片，static使得其可以被所有象对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Bishop_WHITE;
    private static Image Bishop_BLACK;

    /**
     * 象棋子对象自身的图片，是上面两种中的一种
     */
    private Image BishopImage;

    /**
     * 读取加载象棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (Bishop_WHITE == null) {
            Bishop_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (Bishop_BLACK == null) {
            Bishop_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定BishopImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                BishopImage = Bishop_WHITE;
            } else if (color == ChessColor.BLACK) {
                BishopImage = Bishop_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super("Bishop", chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
    }

    /**
     * 象棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 象棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean Turnboard , Chessboard chessboard) {
        ChessboardPoint source = getChessboardPoint();//象原本位置
        //翻转对象没有影响
            if (source.getX() + source.getY() == destination.getX() + destination.getY()) {
                //走斜线45度斜角
                if (source.getY() > destination.getY()) {
                    for (int i = 1; i < source.getY() - destination.getY(); i++) {//不能越子
                        if (!(chessComponents[destination.getX() - i][destination.getY() + i] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else if (source.getY() < destination.getY()) {
                    for (int i = 1; i < -source.getY() + destination.getY(); i++) {//不能越子
                        if (!(chessComponents[source.getX() - i][source.getY() + i] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else {
                    //source==destination
                    return false;
                }
            } else if (source.getX() - source.getY() == destination.getX() - destination.getY()) {
                //走斜线135度斜角
                if (source.getY() > destination.getY()) {
                    for (int i = 1; i < source.getY() - destination.getY(); i++) {//不能越子
                        if (!(chessComponents[destination.getX() + i][destination.getY() + i] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else if (source.getY() < destination.getY()) {
                    for (int i = 1; i < -source.getY() + destination.getY(); i++) {//不能越子
                        if (!(chessComponents[source.getX() + i][source.getY() + i] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else {
                    //source==destination
                    return false;
                }

            } else {
                return false;
            }
            if (color.equals(ChessColor.BLACK)) {
                color.setLastone(Color.BLACK, false, 11, 11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            } else if (color.equals(ChessColor.WHITE)) {
                color.setLastone(Color.WHITE, false, 11, 11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            }
        return true;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(BishopImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(BishopImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
