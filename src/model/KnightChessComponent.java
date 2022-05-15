package model;
// 马

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的马
 */
public class KnightChessComponent extends ChessComponent {
    /**
     * 黑马和白马的图片，static使得其可以被所有马对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Knight_WHITE;
    private static Image Knight_BLACK;

    /**
     * 马棋子对象自身的图片，是上面两种中的一种
     */
    private Image KnightImage;

    /**
     * 读取加载马棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (Knight_WHITE == null) {
            Knight_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (Knight_BLACK == null) {
            Knight_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定KnightImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                KnightImage = Knight_WHITE;
            } else if (color == ChessColor.BLACK) {
                KnightImage = Knight_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super("Knight", chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
    }

    /**
     * 马棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 马棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color,Boolean Turnboard , Chessboard chessboard) {
        ChessboardPoint source = getChessboardPoint();
        //翻转对象没有影响

        //假设子在（0，0）时，有八个角度：
        // (2,1)、（2、-1）、（-2，-1）、（-2，-1）
        //（1，2）、（1，-2）、（-1，2）、（-1，-2）
        if((Math.abs(source.getY()-destination.getY())==0||Math.abs(source.getX()-destination.getX())==0)){//排除/num中num=0的情况
            return false;
        }else {
            if (Math.abs(source.getX() - destination.getX()) / (Math.abs(source.getY() - destination.getY())) == 2) {
                // (2,1)、（2、-1）、（-2，-1）、（-2，-1）
                if ((Math.abs(source.getY() - destination.getY()) != 1)) { //只能走一个日字
                    return false;
                }
            } else if ((Math.abs(source.getY() - destination.getY()) / Math.abs(source.getX() - destination.getX())) == 2) {
                //（1，2）、（1，-2）、（-1，2）、（-1，-2）
                if ((Math.abs(source.getX() - destination.getX()) != 1)) { //只能走一个日字
                    return false;
                }
            } else {
                return false;
            }
        }

        if(color.equals(ChessColor.BLACK)) {
            color.setLastone(Color.BLACK, false,11,11);//设置为11，即不在棋盘上，不影响吃过路兵功能
        }else if(color.equals(ChessColor.WHITE)){
            color.setLastone(Color.WHITE, false,11,11);//设置为11，即不在棋盘上，不影响吃过路兵功能
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
//        g.drawImage(KnightImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(KnightImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
