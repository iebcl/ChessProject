package controller;

import model.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static view.ChessGameFrame.*;

public class ClickController {
    private final Chessboard chessboard;
    public ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        //chesscomponent是第二次被点击的位置--棋子即将去到的地方
        //first是我们选中的棋子

        // play click music
        playClick();

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

                // 王被吃
                if (chessComponent.getName().equals("King")) {
                    Object[] over = {"OK"};
                    AtomicInteger Over = new AtomicInteger();
                    Over.set(JOptionPane.showOptionDialog(null, "Game over! Winner is " + chessboard.getCurrentColor().getName(), "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, over, over[0]));
                }

                // 判断将军
//                ChessboardPoint kingPoint = null;
//                flag1:
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        if (chessboard.getChessComponents()[i][j].getName().equals("King")) {
//                            if (!chessboard.getChessComponents()[i][j].getChessColor().equals(chessboard.getCurrentColor())) {
//                                kingPoint = chessboard.getChessComponents()[i][j].getChessboardPoint();
//                                break flag1;
//                            }
//                        }
//                    }
//                }
//                flag2:
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        if (chessboard.getChessComponents()[i][j].canMoveTo(chessboard.getChessComponents(), kingPoint, chessboard.getChessComponents()[i][j].getChessColor(), chessboard.getTurnchessboard(), chessboard)) {
//                            if (chessboard.getCurrentColor().equals(ChessColor.BLACK)) {
//                                chessboard.swapColor();//下完这步，换下棋方
//                                first.setSelected(false);
//                                first = null;
//                                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "White is dying!", "Warning", 2);
//                                return;
//                            } else {
//                                chessboard.swapColor();//下完这步，换下棋方
//                                first.setSelected(false);
//                                first = null;
//                                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Black is dying!", "Warning", 2);
//                                return;
//                            }
//                        }
//                    }
//                }

                chessboard.swapColor();//下完这步，换下棋方
                first.setSelected(false);
                first = null;

                // store this step
                StringBuilder newFileName = new StringBuilder(getFilename());
                if (newFileName.substring(newFileName.length() - 4).equals(".txt")) {
                    newFileName.delete(newFileName.length() - 4, newFileName.length() - 1);
                }
                int newCounter = getCounter() + 1;
                setCounter(newCounter);
                newFileName.append(String.valueOf(getCounter()));
                try {
                    BufferedWriter writer;
                    if (newFileName.substring(newFileName.length() - 4).equals(".txt")) {
                        writer = new BufferedWriter(new FileWriter("resource\\" + newFileName));
                    } else {
                        writer = new BufferedWriter(new FileWriter("resource\\" + newFileName + ".txt"));
                    }
                    writer.write(chessboard.getCurrentColor().toString());
                    writer.newLine();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            writer.write(chessboard.getChessComponents()[i][j].getChessColor().toString());
                            writer.write(chessboard.getChessComponents()[i][j].getName());
                            writer.write(chessboard.getChessComponents()[i][j].isMoved() ? "t" : "f");
                            writer.newLine();
                        }
                    }
                    writer.write(String.valueOf(getCounter()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    public void onMovedin(ChessComponent chessComponent) {
        chessComponent.setBackground(Color.red);
//        System.out.println(chessComponent.getChessboardPoint().getX());
        chessboard.moveIn(chessComponent.getChessboardPoint().getX(), chessComponent.getChessboardPoint().getY());
    }

    public void onMovedout(ChessComponent chessComponent) {
        chessComponent.setBackground(Color.red);
    }

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor()
                && first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint(), first.getChessColor(), chessboard.getTurnchessboard(), chessboard);
    }

    public void turntoOther(ChessboardPoint chesspoint, int size, int select, int picture) {
        ChessboardPoint source = chesspoint;
        ChessboardPoint k = new ChessboardPoint(source.getX(), source.getY());
        Point l = first.getLocation();
        ClickController p = first.getClickController();
        int s = size;//为新的空棋子的参数
        chessboard.remove(first);
        if (select == 0)//选后
        {
            first = new QueenChessComponent(k, l, first.getChessColor(), p, s, true, picture);
        } else if (select == 1)//Rook
        {
            first = new RookChessComponent(k, l, first.getChessColor(), p, s, true, picture);
        } else if (select == 2) {
            first = new KnightChessComponent(k, l, first.getChessColor(), p, s, true, picture);
        } else if (select == 3) {
            first = new BishopChessComponent(k, l, first.getChessColor(), p, s, true, picture);
        }
        chessboard.add(first);
    }

    public static void playClick() {
        File file = new File("resource//Windows XP 叮当声.wav");
        InputStream inputStream = null;
        AudioStream audioStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            audioStream = new AudioStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioPlayer.player.start(audioStream);
    }

}
