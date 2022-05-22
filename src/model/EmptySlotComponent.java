package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size, boolean moved) {
        super("Empty", chessboardPoint, location, ChessColor.NONE, listener, size, moved);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination, ChessColor color,Boolean Turnboard,  Chessboard chessboard) {
        return false;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

}
