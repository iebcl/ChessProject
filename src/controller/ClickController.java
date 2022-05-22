package controller;

import model.*;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class ClickController {
    private final Chessboard chessboard;
    public ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        //chesscomponent是第二次被点击的位置--棋子即将去到的地方
        //first是我们选中的棋子
        if (first == null) {//最开始就没选中棋子
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
            }
        } else {//选中棋子
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {//canmoveto chesscomponent
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();//下完这步，换下棋方
                first.setSelected(false);
                first = null;
            }
        }
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
//        if (chessComponent.getName().equals("Pawn")) {
//            boolean a = chessComponent.getChessColor() != chessboard.getCurrentColor() &&
//                    first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(), first.getChessColor(), chessboard.getTurnchessboard(), chessboard);
//            return a;
//        } else {
//            boolean a = chessComponent.getChessColor() != chessboard.getCurrentColor() &&
//                    first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(), first.getChessColor(), chessboard.getTurnchessboard(), chessboard);
//            return a;
//        }
        return chessComponent.getChessColor() != chessboard.getCurrentColor()
                && first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(), first.getChessColor(), chessboard.getTurnchessboard(), chessboard);
    }

    public void turntoOther(ChessboardPoint chesspoint, int size, int select) {
        ChessboardPoint source = chesspoint;
        ChessboardPoint k = new ChessboardPoint(source.getX(), source.getY());
        Point l = first.getLocation();
        ClickController p = first.getClickController();
        int s = size;//为新的空棋子的参数
        chessboard.remove(first);
        if (select == 0)//选后
        {
            first = new QueenChessComponent(k, l, first.getChessColor(), p, s, true);
        } else if (select == 1)//Rook
        {
            first = new RookChessComponent(k, l, first.getChessColor(), p, s, true);
        } else if (select == 2) {
            first = new KnightChessComponent(k, l, first.getChessColor(), p, s, true);
        } else if (select == 3) {
            first = new BishopChessComponent(k, l, first.getChessColor(), p, s, true);
        }
        chessboard.add(first);
    }

}
