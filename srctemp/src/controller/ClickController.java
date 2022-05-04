package controller;


import model.ChessComponent;
import model.EmptySlotComponent;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;


public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
    String text;
    JLabel statusLabel;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
//        statusLabel=new JLabel("Time for "+chessboard.getCurrentColor().getName());
    }


    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {

                //repaint in swap chess method.

                if (first.getName().equals("Pawn")
                        && chessComponent.getLocation().getY() != first.getLocation().getY()
                        && chessComponent.getName().equals("Empty")) {
                    if (chessboard.getChessComponents()[first.getX()][chessComponent.getY()].getName().equals("Pawn")
                            && !chessboard.getChessComponents()[first.getX()][chessComponent.getY()].getChessColor().equals(chessComponent.getChessColor())
                    ) {
                        ChessComponent rm = new EmptySlotComponent(new ChessboardPoint(chessComponent.getX(), first.getY()), new Point(chessComponent.getX(), first.getY()), this, chessboard.getCHESS_SIZE());
                        chessboard.putChessOnBoard(rm);
                    }
                }

                if (first.getName().equals("King") && first.isWangCheYiWei()) {
                    ChessboardPoint MiddlePoint = new ChessboardPoint(chessComponent.getX(), (chessComponent.getY()) + first.getY() / 2);
                    Point middlepoint = new Point(chessComponent.getX(), chessComponent.getY());
                    ChessComponent Middle = new EmptySlotComponent(MiddlePoint, middlepoint, this, chessboard.getCHESS_SIZE());
                    if (chessComponent.getY() < first.getY()) {
                        chessboard.swapChessComponents(chessboard.getChessComponents()[first.getX()][0], Middle);
                    } else {
                        chessboard.swapChessComponents(chessboard.getChessComponents()[first.getX()][7], Middle);
                    }
                }

                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                text = "Time for " + chessboard.getCurrentColor().getName();
                statusLabel = new JLabel(text);
                first.setSelected(false);
                first = null;
            }
        }
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        if (chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(), first.getChessColor())) {
            chessComponent.setMoved(true);
            return true;
        } else {
            return false;
        }
    }
}
