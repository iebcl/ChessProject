package view;


import jdk.nashorn.internal.scripts.JO;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    Boolean turnchessboard;//选择不同颜色后棋盘翻倒
    JLabel sta;


    public Chessboard(int width, int height, JLabel statusLabel, AtomicInteger SelectColor) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        if (SelectColor.get() == 0) {
            currentColor = ChessColor.BLACK;
        } else {
            currentColor = ChessColor.WHITE;
        }
//        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);
        init(currentColor);
        sta = statusLabel;
    }

    public void init(ChessColor currentColor1) {

        initiateEmptyChessboard();
        currentColor = ChessColor.WHITE;
        if (currentColor1 == ChessColor.WHITE) {
            turnchessboard = true;
            initRookOnBoard(0, 0, ChessColor.BLACK);
            initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
            initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
            initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
            initKnightOnBoard(0, 1, ChessColor.BLACK);
            initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
            initBishopOnBoard(0, 2, ChessColor.BLACK);
            initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
            initQueenOnBoard(0, 3, ChessColor.BLACK);
            initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
            initKingOnBoard(0, 4, ChessColor.BLACK);
            initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
            initPawnOnBoard(1, 0, ChessColor.BLACK);
            initPawnOnBoard(1, 1, ChessColor.BLACK);
            initPawnOnBoard(1, 2, ChessColor.BLACK);
            initPawnOnBoard(1, 3, ChessColor.BLACK);
            initPawnOnBoard(1, 4, ChessColor.BLACK);
            initPawnOnBoard(1, 5, ChessColor.BLACK);
            initPawnOnBoard(1, 6, ChessColor.BLACK);
            initPawnOnBoard(1, 7, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.WHITE);
        } else {
            turnchessboard = false;
            initRookOnBoard(0, 0, ChessColor.WHITE);
            initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
            initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.BLACK);
            initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
            initKnightOnBoard(0, 1, ChessColor.WHITE);
            initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.BLACK);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
            initBishopOnBoard(0, 2, ChessColor.WHITE);
            initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.BLACK);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
            initQueenOnBoard(0, 3, ChessColor.WHITE);
            initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.BLACK);
            initKingOnBoard(0, 4, ChessColor.WHITE);
            initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.BLACK);
            initPawnOnBoard(1, 0, ChessColor.WHITE);
            initPawnOnBoard(1, 1, ChessColor.WHITE);
            initPawnOnBoard(1, 2, ChessColor.WHITE);
            initPawnOnBoard(1, 3, ChessColor.WHITE);
            initPawnOnBoard(1, 4, ChessColor.WHITE);
            initPawnOnBoard(1, 5, ChessColor.WHITE);
            initPawnOnBoard(1, 6, ChessColor.WHITE);
            initPawnOnBoard(1, 7, ChessColor.WHITE);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.BLACK);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.BLACK);
        }
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public Boolean getTurnchessboard() {
        return turnchessboard;
    }

    public void setCurrentColor(int num) {
        if (num == 0) {
            currentColor = ChessColor.WHITE;
        } else {
            currentColor = ChessColor.BLACK;
        }
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        // 判断能否被吃掉
        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        sta.setText("Time for " + currentColor.getName());
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
//        chessData.forEach(System.out::println);
        if (chessData.size() == 0) {
            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No content!", "warning", 0);
            return;
        } else {
            if (chessData.get(0).equals("WHITE")) {
                this.currentColor = ChessColor.WHITE;
            } else if (chessData.get(0).equals("BLACK")) {
                this.currentColor = ChessColor.BLACK;
            } else {
                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No current player message!", "Warning", 0);
                return;
            }
            if (chessData.size() != 65) {
                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chessboard!", "Warning", 0);
                return;
            }
            this.init(this.currentColor);
            for (int i = 1; i < 65; i++) {
                int x = (i - 1) / 8;
                int y = i - 8 * x - 1;
                this.remove(chessComponents[x][y]);
                char c = chessData.get(i).charAt(0);
                ChessColor tempColori;
                if (c == 'N') {
                    if (chessData.get(i).length() >= 4) {
                        if (chessData.get(i).substring(0, 5).equals("NONE")) {
                            chessComponents[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), calculatePoint(x, y), clickController, CHESS_SIZE);
                            this.add(chessComponents[x][y]);
                            continue;
                        }
                    }
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                } else if (c == 'W') {
                    if (chessData.get(i).length() >= 5) {
                        if (chessData.get(i).substring(0, 6).equals("WHITE")) {
                            tempColori = ChessColor.WHITE;
                        } else {
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                        return;
                    }
                } else if (c == 'B') {
                    if (chessData.get(i).length() >= 5) {
                        if (chessData.get(i).substring(0, 6).equals("BLACK")) {
                            tempColori = ChessColor.BLACK;
                        } else {
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                }

                if (chessData.get(i).length() == 11 && chessData.get(i).substring(5, 11).equals("Bishop")) {
                    chessComponents[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else if (chessData.get(i).length() == 9 && chessData.get(i).substring(5, 9).equals("King")) {
                    chessComponents[x][y] = new KingChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else if (chessData.get(i).length() == 11 && chessData.get(i).substring(5, 11).equals("Knight")) {
                    chessComponents[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else if (chessData.get(i).length() == 9 && chessData.get(i).substring(5, 9).equals("Pawn")) {
                    chessComponents[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else if (chessData.get(i).length() == 10 && chessData.get(i).substring(5, 10).equals("Queen")) {
                    chessComponents[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else if (chessData.get(i).length() == 9 && chessData.get(i).substring(5, 9).equals("Rook")) {
                    chessComponents[x][y] = new RookChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE);
                } else {
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                }
                this.add(chessComponents[x][y]);
            }
            sta.setText("Time for " + currentColor.getName());
            this.setVisible(true);
        }
    }
}
