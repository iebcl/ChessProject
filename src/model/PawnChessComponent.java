package model;
// 兵

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的兵
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑兵和白兵的图片，static使得其可以被所有兵对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Pawn_WHITE;
    private static Image Pawn_BLACK;

    /**
     * 兵棋子对象自身的图片，是上面两种中的一种
     */
    private Image PawnImage;
    private boolean StepOne = true;

    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (Pawn_WHITE == null) {
            Pawn_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (Pawn_BLACK == null) {
            Pawn_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定PawnImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                PawnImage = Pawn_WHITE;
            } else if (color == ChessColor.BLACK) {
                PawnImage = Pawn_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super("Pawn", chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    /**
     * 兵棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 兵棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color) {
        //
        ChessboardPoint source = getChessboardPoint();
        if (source.getY() == destination.getY()) {//只能直走
            if (color.getName().equals("Black")) {//黑棋，上方
                if (!(chessComponents[source.getX() + 1][source.getY()] instanceof EmptySlotComponent)) {//前面不为空
                    return false;
                } else {//前面为空
                    if (destination.getX() - source.getX() == 1) {//只走一步
                        StepOne = false;
                        return true;
                    } else {
                        if (StepOne) {//
                            if (destination.getX() - source.getX() == 2) {//走两步
                                if (!(chessComponents[source.getX() + 2][source.getY()] instanceof EmptySlotComponent)) {
                                    return false;
                                } else {
                                    StepOne = false;
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            } else if (color.getName().equals("White")) {
                if (!(chessComponents[source.getX() - 1][source.getY()] instanceof EmptySlotComponent)) {//后面不为空
                    return false;
                } else {
                    if (source.getX() - destination.getX() == 1) {//只走一步
                        StepOne = false;
                        return true;
                    }
                    if (StepOne) {//
                        if (source.getX() - destination.getX() == 2) {//走两步
                            if (!(chessComponents[source.getX() - 2][source.getY()] instanceof EmptySlotComponent)) {
                                return false;
                            } else {
                                StepOne = false;
                                return true;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if ((destination.getY() - source.getY() == 1 || destination.getY() - source.getY() == -1)) {//斜角
            if (color.getName().equals("Black")) {
                if (destination.getX() - source.getX() == 1) {
                    if ((!chessComponents[destination.getX()][destination.getY()].getName().equals("Empty")) && chessComponents[destination.getX()][destination.getY()].getChessColor() != color) {
                        StepOne = false;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (color.getName().equals("White")) {
                if (destination.getX() - source.getX() == -1) {
                    if ((!chessComponents[destination.getX()][destination.getY()].getName().equals("Empty")) && chessComponents[destination.getX()][destination.getY()].getChessColor() != color) {
                        StepOne = false;
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }


    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(PawnImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(PawnImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
