package controller;


import model.ChessComponent;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;


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
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                text="Time for "+chessboard.getCurrentColor().getName();
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
        if (chessComponent.getName().equals("Pawn")) {
            return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(),first.getChessColor());
        } else {
            return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(),first.getChessColor());
        }
    }
}
