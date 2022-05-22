package model;
// 后

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的后
 */
public class QueenChessComponent extends ChessComponent {
    /**
     * 黑后和白后的图片，static使得其可以被所有后对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Queen_WHITE;
    private static Image Queen_BLACK;
    public int  picture;

    /**
     * 后棋子对象自身的图片，是上面两种中的一种
     */
    private Image QueenImage;

    /**
     * 读取加载后棋子的图片
     *
     * @throws IOException
     */
    public void loadResource(int picture) throws IOException {
        if(picture==0) { Queen_WHITE = ImageIO.read(new File("./images/queen-white.png"));
          Queen_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }else if(picture==1){
  Queen_WHITE = ImageIO.read(new File("./images/queen-white1.png"));
 Queen_BLACK = ImageIO.read(new File("./images/queen-black1.png"));

        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定QueenImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateQueenImage(ChessColor color,int picture) {
        try {
            loadResource(picture);
            if (color == ChessColor.WHITE) {
                QueenImage = Queen_WHITE;
            } else if (color == ChessColor.BLACK) {
                QueenImage = Queen_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, boolean moved,int picture) {
        super("Queen", chessboardPoint, location, color, listener, size, moved,picture);
        initiateQueenImage(color,picture);
    }

    /**
     * 后棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 后棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean Turnboard, Chessboard chessboard) {
        ChessboardPoint source = getChessboardPoint();
        //翻转对象没有影响
        if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getX() + source.getY() == destination.getX() + destination.getY()) {
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
//        g.drawImage(QueenImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(QueenImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
