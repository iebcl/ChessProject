package model;
// 兵

import controller.ClickController;
import view.Chessboard;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

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
    private int size;
    public int  picture;

    AtomicInteger ButtonSelect = new AtomicInteger();

    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource(int picture) throws IOException {
        if(picture==0){  Pawn_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
            Pawn_BLACK = ImageIO.read(new File("./images/pawn-black.png"));

        }else  if(picture==1){ Pawn_WHITE = ImageIO.read(new File("./images/pawn-white1.png"));
               Pawn_BLACK = ImageIO.read(new File("./images/pawn-black1.png"));

        }
    }

    @Override
    public boolean isFirstAndTwo() {
        return super.isFirstAndTwo();
    }

    @Override
    public void setFirstAndTwo(boolean firstAndTwo) {
        super.setFirstAndTwo(firstAndTwo);
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定PawnImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color,int picture) {
        try {
            loadResource(picture);
            if (color == ChessColor.WHITE) {
                PawnImage = Pawn_WHITE;
            } else if (color == ChessColor.BLACK) {
                PawnImage = Pawn_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, boolean moved,int picture) {
        super("Pawn", chessboardPoint, location, color, listener, size, moved,picture);
        initiatePawnImage(color,picture);
        this.size = size;
    }

    /**
     * 兵棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 兵棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean turnchessboard, Chessboard chessboard) {
        if (turnchessboard) {//没有倒转，白色在下
            ChessboardPoint source = getChessboardPoint();
            if (source.getY() == destination.getY()) //只能直走
            {
                if (color.getName().equals("Black")) //此为黑棋，上方
                {
                    if (!(chessComponents[source.getX() + 1][source.getY()] instanceof EmptySlotComponent)) {//前面不为空
                        return false;
                    } else //前面为空
                    {
                        if (destination.getX() - source.getX() == 1)//只走一步
                        {
                            StepOne = false;
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);
                            return true;
                        } else {
                            if (StepOne) {//
                                if (destination.getX() - source.getX() == 2) {//走两步
                                    if (!(chessComponents[source.getX() + 2][source.getY()] instanceof EmptySlotComponent)) {
                                        return false;
                                    } else {
                                        StepOne = false;
                                        if (color.equals(ChessColor.BLACK)) {
                                            color.setLastone(Color.WHITE, true, destination.getX(), destination.getY());
                                        } else if (color.equals(ChessColor.WHITE)) {
                                            color.setLastone(Color.BLACK, true, destination.getX(), destination.getY());
                                        }
                                        ifarrive(chessComponents, destination, color, turnchessboard, chessboard);
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
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        }
                        if (StepOne) {//
                            if (source.getX() - destination.getX() == 2) {//走两步
                                if (!(chessComponents[source.getX() - 2][source.getY()] instanceof EmptySlotComponent)) {
                                    return false;
                                } else {
                                    StepOne = false;
                                    if (color.equals(ChessColor.BLACK)) {
                                        color.setLastone(Color.WHITE, true, destination.getX(), destination.getY());
                                    } else if (color.equals(ChessColor.WHITE)) {
                                        color.setLastone(Color.BLACK, true, destination.getX(), destination.getY());
                                    }
                                    ifarrive(chessComponents, destination, color, turnchessboard, chessboard);
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
            } else if ((destination.getY() - source.getY() == 1 || destination.getY() - source.getY() == -1)) //走斜线
            {
                if (color.getName().equals("Black")) {//此为黑棋，上方
                    if (destination.getX() - source.getX() == 1) {//往下
                        if ((!chessComponents[destination.getX()][destination.getY()].getName().equals("Empty")) && chessComponents[destination.getX()][destination.getY()].getChessColor() != color) {
                            StepOne = false;
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else if (chessComponents[destination.getX()][destination.getY()].getName().equals("Empty") &&
                                chessComponents[destination.getX() - 1][destination.getY()].getChessColor().equals(ChessColor.WHITE) &&
                                color.lastone && color.lastX == destination.getX() - 1 && color.lastY == destination.getY()
                        ) {
                            ChessboardPoint k = new ChessboardPoint(destination.getX() - 1, destination.getY());
                            Point l = chessComponents[destination.getX() - 1][destination.getY()].getLocation();
                            ClickController p = chessComponents[destination.getX() - 1][destination.getY()].getClickController();
                            int s = size;//为新的空棋子的参数
                            chessboard.remove(chessComponents[destination.getX() - 1][destination.getY()]);
                            chessComponents[destination.getX() - 1][destination.getY()] = new EmptySlotComponent(k, l, p, s, false,picture);//新建空棋子
                            chessboard.add(chessComponents[destination.getX() - 1][destination.getY()]);
                            super.flag1 = true;
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

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
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else if (chessComponents[destination.getX()][destination.getY()].getName().equals("Empty") &&
                                chessComponents[destination.getX() + 1][destination.getY()].getChessColor().equals(ChessColor.BLACK) &&
                                color.lastone && color.lastX == destination.getX() + 1 && color.lastY == destination.getY()
                        ) {
                            int pointx = destination.getX() + 1;
                            ChessboardPoint k = new ChessboardPoint(pointx, destination.getY());
                            Point l = chessComponents[pointx][destination.getY()].getLocation();
                            ClickController p = chessComponents[pointx][destination.getY()].getClickController();
                            int s = size;//为新的空棋子的参数
                            chessboard.remove(chessComponents[pointx][destination.getY()]);
                            chessComponents[pointx][destination.getY()] = new EmptySlotComponent(k, l, p, s, false,picture);//新建空棋子
                            chessboard.add(chessComponents[pointx][destination.getY()]);
                            super.flag1 = true;
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

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


        }//没有倒转，白色在下
//        倒转棋盘，黑色在下
        else {
            ChessboardPoint source = getChessboardPoint();
            if (source.getY() == destination.getY()) {//只能直走
                if (color.getName().equals("Black")) {//黑棋，上方
                    if (!(chessComponents[source.getX() - 1][source.getY()] instanceof EmptySlotComponent)) {//前面不为空
                        return false;
                    } else {//前面为空
                        if (destination.getX() - source.getX() == -1) {//只走一步
                            chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(false);
                            chessComponents[source.getX()][source.getY()].setFirstAndTwo(false);
                            StepOne = false;
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else {
                            if (StepOne) {//
                                if (destination.getX() - source.getX() == -2) {//走两步
                                    if (!(chessComponents[source.getX() - 2][source.getY()] instanceof EmptySlotComponent)) {
                                        return false;
                                    } else {
                                        StepOne = false;
                                        chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(true);
                                        if (color.equals(ChessColor.BLACK)) {
                                            color.setLastone(Color.WHITE, true, destination.getX(), destination.getY());
                                        } else if (color.equals(ChessColor.WHITE)) {
                                            color.setLastone(Color.BLACK, true, destination.getX(), destination.getY());
                                        }
                                        ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

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
                } else if (color.getName().equals("White")) {//黑色在下
                    if (!(chessComponents[source.getX() + 1][source.getY()] instanceof EmptySlotComponent)) {//后面不为空
                        return false;
                    } else {
                        if (source.getX() - destination.getX() == -1) {//只走一步
                            chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(false);
                            chessComponents[source.getX()][source.getY()].setFirstAndTwo(false);
                            StepOne = false;
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        }
                        if (StepOne) {//
                            if (source.getX() - destination.getX() == -2) {//走两步
                                if (!(chessComponents[source.getX() + 2][source.getY()] instanceof EmptySlotComponent)) {
                                    return false;
                                } else {
                                    StepOne = false;
                                    chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(true);
                                    if (color.equals(ChessColor.BLACK)) {
                                        color.setLastone(Color.WHITE, true, destination.getX(), destination.getY());
                                    } else if (color.equals(ChessColor.WHITE)) {
                                        color.setLastone(Color.BLACK, true, destination.getX(), destination.getY());
                                    }
                                    ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

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
            } else if ((destination.getY() - source.getY() == -1 || destination.getY() - source.getY() == 1)) {//斜角
                if (color.getName().equals("Black")) {
                    if (destination.getX() - source.getX() == -1) {
                        if ((!chessComponents[destination.getX()][destination.getY()].getName().equals("Empty")) && chessComponents[destination.getX()][destination.getY()].getChessColor() != color) {
                            StepOne = false;
                            chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(false);
                            chessComponents[source.getX()][source.getY()].setFirstAndTwo(false);
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, false, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, false, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else if (chessComponents[destination.getX()][destination.getY()].getName().equals("Empty") &&
                                chessComponents[destination.getX() + 1][destination.getY()].getChessColor().equals(ChessColor.WHITE) &&
                                color.lastone && color.lastX == destination.getX() + 1 && color.lastY == destination.getY()
                        ) {
                            int pointx = destination.getX() + 1;
                            ChessboardPoint k = new ChessboardPoint(pointx, destination.getY());
                            Point l = chessComponents[pointx][destination.getY()].getLocation();
                            ClickController p = chessComponents[pointx][destination.getY()].getClickController();
                            int s = size;//为新的空棋子的参数
                            chessboard.remove(chessComponents[pointx][destination.getY()]);
                            chessComponents[pointx][destination.getY()] = new EmptySlotComponent(k, l, p, s, false,picture);//新建空棋子
                            chessboard.add(chessComponents[pointx][destination.getY()]);
                            super.flag1 = true;
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else if (color.getName().equals("White")) {
                    if (destination.getX() - source.getX() == +1) {
                        if ((!chessComponents[destination.getX()][destination.getY()].getName().equals("Empty")) && chessComponents[destination.getX()][destination.getY()].getChessColor() != color) {
                            StepOne = false;
                            chessComponents[destination.getX()][destination.getY()].setFirstAndTwo(false);
                            chessComponents[source.getX()][source.getY()].setFirstAndTwo(false);
                            if (color.equals(ChessColor.BLACK)) {
                                color.setLastone(Color.WHITE, true, destination.getX(), destination.getY());
                            } else if (color.equals(ChessColor.WHITE)) {
                                color.setLastone(Color.BLACK, true, destination.getX(), destination.getY());
                            }
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

                            return true;
                        } else if (chessComponents[destination.getX()][destination.getY()].getName().equals("Empty") &&
                                chessComponents[destination.getX() - 1][destination.getY()].getChessColor().equals(ChessColor.BLACK) &&
                                color.lastone && color.lastX == destination.getX() - 1 && color.lastY == destination.getY()
                        ) {
                            int pointx = destination.getX() - 1;
                            ChessboardPoint k = new ChessboardPoint(pointx, destination.getY());
                            Point l = chessComponents[pointx][destination.getY()].getLocation();
                            ClickController p = chessComponents[pointx][destination.getY()].getClickController();
                            int s = size;//为新的空棋子的参数
                            chessboard.remove(chessComponents[pointx][destination.getY()]);
                            chessComponents[pointx][destination.getY()] = new EmptySlotComponent(k, l, p, s, false,picture);//新建空棋子
                            chessboard.add(chessComponents[pointx][destination.getY()]);
                            super.flag1 = true;
                            ifarrive(chessComponents, destination, color, turnchessboard, chessboard);

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

    public void receiveSideLine(ChessComponent[][] chessComponents, ChessColor color, Chessboard chessboard) {
        Object[] turn = {"Queen", "Rook", "Knight", "Bishop"};
        ButtonSelect.set(JOptionPane.showOptionDialog(null, "\n" +
                "Soldiers line up changes", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, turn, turn[0]));
        this.getClickController().turntoOther(this.getChessboardPoint(), this.size, ButtonSelect.get(),this.picture);
//        ChessboardPoint source = getChessboardPoint();
//        ChessboardPoint k = new ChessboardPoint(source.getX(), source.getY());
//        Point l = chessComponents[source.getX()][source.getY()].getLocation();
//        ClickController p = chessComponents[source.getX()][source.getY()].getClickController();
//        int s = size;//为新的空棋子的参数
//        chessboard.remove(chessComponents[source.getX()][source.getY()]);
//        if (ButtonSelect.get() == 0)//选后
//        {
//            chessComponents[source.getX()][source.getY()] = new QueenChessComponent(k, l, color, p, s);
//        } else if (ButtonSelect.get() == 1)//Rook
//        {
//            chessComponents[source.getX()][source.getY()] = new RookChessComponent(k, l, color, p, s);
//        } else if (ButtonSelect.get() == 2) {
//            chessComponents[source.getX()][source.getY()] = new KnightChessComponent(k, l, color, p, s);
//        } else if (ButtonSelect.get() == 3) {
//            chessComponents[source.getX()][source.getY()] = new BishopChessComponent(k, l, color, p, s);
//        }
//        System.out.println("name:"+chessComponents[source.getX()][source.getY()].getName()+"color"+chessComponents[source.getX()][source.getY()].getChessColor()+"point"+chessComponents[source.getX()][source.getY()].getChessboardPoint());
////        chessComponents[source.getX()][source.getY()] = new EmptySlotComponent(k, l, p, s);//新建空棋子
//        chessboard.add(chessComponents[source.getX()][source.getY()]);
    }

    public void ifarrive(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color, Boolean turnchessboard, Chessboard chessboard) {
        //是否到达底线
        if (turnchessboard) {//没有倒转，白色在下
            if (color.getName().equals("White")) {
                if (destination.getX() == 0) {
                    receiveSideLine(chessComponents, color, chessboard);
                }
            } else {//black
                if (destination.getX() == 7) {
                    receiveSideLine(chessComponents, color, chessboard);
                }
            }

        } else {
            if (color.getName().equals("White")) {
                if (destination.getX() == 7) {
                    receiveSideLine(chessComponents, color, chessboard);
                }
            } else {//black
                if (destination.getX() == 0) {
                    receiveSideLine(chessComponents, color, chessboard);
                }
            }
        }
    }
}
