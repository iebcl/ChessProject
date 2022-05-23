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
<<<<<<< HEAD
    public int picture;
=======
    public int  picture;
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263

    /**
     * 王棋子对象自身的图片，是上面两种中的一种
     */
    private Image KingImage;

    /**
     * 读取加载王棋子的图片
     *
     * @throws IOException
     */
    public void loadResource(int picture) throws IOException {
<<<<<<< HEAD
        if (picture == 0) {
            King_WHITE = ImageIO.read(new File("./images/king-white.png"));

            King_BLACK = ImageIO.read(new File("./images/king-black.png"));

        } else if (picture == 1) {
            King_WHITE = ImageIO.read(new File("./images/king-white1.png"));
            King_BLACK = ImageIO.read(new File("./images/king-black1.png"));
=======
        if(picture==0){
                King_WHITE = ImageIO.read(new File("./images/king-white.png"));

                King_BLACK = ImageIO.read(new File("./images/king-black.png"));

        }else if(picture==1) {
                King_WHITE = ImageIO.read(new File("./images/king-white1.png"));
              King_BLACK = ImageIO.read(new File("./images/king-black1.png"));
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定KingImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

<<<<<<< HEAD
    private void initiateKingImage(ChessColor color, int picture) {
=======
    private void initiateKingImage(ChessColor color,int picture) {
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
        try {
            loadResource(picture);
            if (color == ChessColor.WHITE) {
                KingImage = King_WHITE;
            } else if (color == ChessColor.BLACK) {
                KingImage = King_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, boolean moved, int picture) {
        super("King", chessboardPoint, location, color, listener, size, moved, picture);
        initiateKingImage(color, picture);
=======
    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, boolean moved ,int picture) {
        super("King", chessboardPoint, location, color, listener, size, moved,picture);
        initiateKingImage(color,picture);
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
    }

    /**
     * 王棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 王棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean Turnboard, Chessboard chessboard) {
        ChessboardPoint source = getChessboardPoint();
        //翻转对象没有影响
        if ((destination.getX() - source.getX() <= 1 && destination.getX() - source.getX() >= -1)
                && (destination.getY() - source.getY() <= 1 && destination.getY() - source.getY() >= -1)) {
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (!chessComponents[i][j].getChessColor().equals(color)
//                            && chessComponents[i][j].canMoveTo(chessComponents, destination, color, Turnboard, chessboard)) {
//                        return false;
//                    }
//                }
//            }
            if (color.equals(ChessColor.BLACK)) {
                color.setLastone(Color.BLACK, false, 11, 11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            } else if (color.equals(ChessColor.WHITE)) {
                color.setLastone(Color.WHITE, false, 11, 11);//设置为11，即不在棋盘上，不影响吃过路兵功能
            }
            return true;
        } else if (destination.getX() == source.getX() && !super.isMoved()) {
            if (destination.getY() - source.getY() == 2) {
                if (!chessComponents[source.getX()][7].isMoved()) {
                    for (int i = 5; i < 7; i++) {
                        if (!(chessComponents[source.getX()][i] instanceof EmptySlotComponent)) {
                            return false;
                        }
<<<<<<< HEAD
//                        chessComponents[source.getX()][i] = chessComponents[source.getX()][source.getY()];
//                        chessComponents[source.getX()][i].setChessboardPoint(new ChessboardPoint(source.getX(), i));
//                        for (int j = 0; j < 8; j++) {
//                            for (int k = 0; k < 8; k++) {
//                                if (j != source.getX() && k != source.getY()) {
//                                    if (chessComponents[j][k].canMoveTo(chessComponents, new ChessboardPoint(source.getX(), i), chessComponents[j][k].getChessColor(), Turnboard, chessboard)) {
//                                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false, picture);
//                                        repaint();
//                                        return false;
//                                    }
//                                }
//                            }
//                        }
//                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false, picture);
=======
//                        chessboard.remove(chessComponents[source.getX()][i]);
                        chessComponents[source.getX()][i] = chessComponents[source.getX()][source.getY()];
                        chessComponents[source.getX()][i].setChessboardPoint(new ChessboardPoint(source.getX(), i));
//                        chessboard.add(chessComponents[source.getX()][i]);
                        for (int j = 0; j < 8; j++) {
                            for (int k = 0; k < 8; k++) {
                                if (j != source.getX() && k != source.getY()) {
                                    if (chessComponents[j][k].canMoveTo(chessComponents, new ChessboardPoint(source.getX(), i), chessComponents[j][k].getChessColor(), Turnboard, chessboard)) {
//                                        chessboard.remove(chessComponents[source.getX()][i]);
                                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false,picture);
//                                        chessComponents[source.getX()][i].setVisible(true);
//                                        chessboard.add(chessComponents[source.getX()][i]);
                                        repaint();
                                        return false;
                                    }
                                }
                            }
                        }
//                        chessboard.remove(chessComponents[source.getX()][i]);
                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false,picture);
//                        chessComponents[source.getX()][i].setVisible(true);
//                        chessboard.add(chessComponents[source.getX()][i]);
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
                    }
                    chessboard.swapChessComponents(chessComponents[source.getX()][7],chessComponents[source.getX()][5]);
                    chessComponents[source.getX()][5].setMoved(true);
<<<<<<< HEAD
=======
                    chessComponents[source.getX()][7] = new EmptySlotComponent(new ChessboardPoint(source.getX(), 7), new Point(source.getX(), 7), getClickController(), chessboard.getCHESS_SIZE(), false,picture);
                    chessboard.add(chessComponents[source.getX()][7]);
                    chessboard.add(chessComponents[source.getX()][5]);
                    repaint();
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
                    return true;
                }
            } else if (destination.getY() - source.getY() == -2) {
                if (!chessComponents[source.getX()][0].isMoved()) {
                    for (int i = 3; i > 0; i--) {
                        if (!(chessComponents[source.getX()][i] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
<<<<<<< HEAD
//                    for (int i = 3; i > 1; i--) {
//                        chessComponents[source.getX()][i] = chessComponents[source.getX()][source.getY()];
//                        chessComponents[source.getX()][i].setChessboardPoint(new ChessboardPoint(source.getX(), i));
//                        for (int j = 0; j < 8; j++) {
//                            for (int k = 0; k < 8; k++) {
//                                if (j != source.getX() && k != source.getY()) {
//                                    if (chessComponents[j][k].canMoveTo(chessComponents, new ChessboardPoint(source.getX(), i), chessComponents[j][k].getChessColor(), Turnboard, chessboard)) {
//                                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false, picture);
//                                        return false;
//                                    }
//                                }
//                            }
//                        }
//                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false, picture);
//                    }
                    chessboard.swapChessComponents(chessComponents[source.getX()][0],chessComponents[source.getX()][3]);
                    chessComponents[source.getX()][3].setMoved(true);
=======
                    for (int i = 3; i > 1; i--) {
//                        chessboard.remove(chessComponents[source.getX()][i]);
//                        chessboard.remove(chessComponents[source.getX()][i]);
                        chessComponents[source.getX()][i] = chessComponents[source.getX()][source.getY()];
                        chessComponents[source.getX()][i].setChessboardPoint(new ChessboardPoint(source.getX(), i));
//                        chessboard.add(chessComponents[source.getX()][i]);
                        for (int j = 0; j < 8; j++) {
                            for (int k = 0; k < 8; k++) {
                                if (j != source.getX() && k != source.getY()) {
                                    if (chessComponents[j][k].canMoveTo(chessComponents, new ChessboardPoint(source.getX(), i), chessComponents[j][k].getChessColor(), Turnboard, chessboard)) {
//                                        chessboard.remove(chessComponents[source.getX()][i]);
                                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false,picture);
//                                        chessComponents[source.getX()][i].setVisible(true);
//                                        chessboard.add(chessComponents[source.getX()][i]);
                                        return false;
                                    }
                                }
                            }
                        }
//                        chessboard.remove(chessComponents[source.getX()][i]);
                        chessComponents[source.getX()][i] = new EmptySlotComponent(new ChessboardPoint(source.getX(), i), new Point(source.getX(), i), super.getClickController(), chessboard.getCHESS_SIZE(), false,picture);
//                        chessboard.add(chessComponents[source.getX()][i]);
                    }
                    chessboard.remove(chessComponents[source.getX()][0]);
                    chessboard.remove(chessComponents[source.getX()][3]);
                    chessComponents[source.getX()][3] = chessComponents[source.getX()][0];
                    chessComponents[source.getX()][3].setChessboardPoint(new ChessboardPoint(source.getX(), 3));
//                    chessboard.remove(chessComponents[source.getX()][0]);
                    chessComponents[source.getX()][3].setMoved(true);
                    chessComponents[source.getX()][0] = new EmptySlotComponent(new ChessboardPoint(source.getX(), 0), new Point(source.getX(), 0), getClickController(), chessboard.getCHESS_SIZE(), false,picture);
//                    chessboard.add(chessComponents[source.getX()][0]);
                    chessboard.add(chessComponents[source.getX()][0]);
                    chessboard.add(chessComponents[source.getX()][3]);
                    repaint();
>>>>>>> 01c7290042c4efec37bcc8c8156e6a31b6fc2263
                    return true;
                }
            }
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
