package model;
// 王

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的王
 */
public class KingChessComponent extends ChessComponent {
    /**
     * 黑王和白王的图片，static使得其可以被所有王对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image King_WHITE;
    private static Image King_BLACK;

    /**
     * 王棋子对象自身的图片，是上面两种中的一种
     */
    private Image KingImage;

    /**
     * 读取加载王棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (King_WHITE == null) {
            King_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }

        if (King_BLACK == null) {
            King_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定KingImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                KingImage = King_WHITE;
            } else if (color == ChessColor.BLACK) {
                KingImage = King_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super("King", chessboardPoint, location, color, listener, size);
        initiateKingImage(color);
    }

    /**
     * 王棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 王棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color,Boolean Turnboard,  Chessboard chessboard) {
        ChessboardPoint source = getChessboardPoint();
        //翻转对象没有影响

        if ((destination.getX() - source.getX() <= 1 && destination.getX() - source.getX() >= -1)
                && destination.getY() - source.getY() <= 1 && destination.getY() - source.getY() >= -1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!chessComponents[i][j].getChessColor().equals(color) && chessComponents[i][j].
                            canMoveTo(chessComponents, destination, color,Turnboard, chessboard)) {
                        return false;
                    }
                }
            }
            if(color.equals(ChessColor.BLACK)) {
                color.setLastone(Color.BLACK, false,11,11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            }else if(color.equals(ChessColor.WHITE)){
                color.setLastone(Color.WHITE, false,11,11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            }
            return true;
        }

        return false;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(KingImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(KingImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
