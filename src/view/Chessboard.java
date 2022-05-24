package view;

import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Chessboard extends JComponent {

// Variable

    private static final int CHESSBOARD_SIZE = 8;
    private final int CHESS_SIZE;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor; // 当前行棋方
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    Boolean turnchessboard; // 选择不同颜色后棋盘翻倒
    JLabel sta;

// Constructor

    public Chessboard(int width, int height, JLabel statusLabel, AtomicInteger SelectColor, AtomicInteger SelectPicture) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        if (SelectColor.get() == 0) {
            currentColor = ChessColor.WHITE;
        } else {
            currentColor = ChessColor.WHITE;
        }
//        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);
        init(currentColor,SelectPicture.get());
        sta = statusLabel;
    }

// Getter and Setter

    public int getCHESS_SIZE() {
        return CHESS_SIZE;
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int num) {
        if (num == 0) {
            currentColor = ChessColor.BLACK;
        } else {
            currentColor = ChessColor.WHITE;
        }
    }

    public Boolean getTurnchessboard() {
        return turnchessboard;
    }

    public ClickController getClickController() {
        return clickController;
    }

// Init

    public void moveIn(int x,int y){
        chessComponents[x][y].setBackground(Color.blue);
    }

    public void init(ChessColor currentColor1, int pictureNum) {
        initiateEmptyChessboard(pictureNum);
        currentColor = ChessColor.WHITE;
        if (currentColor1 == ChessColor.WHITE) {
            turnchessboard = true;
            initRookOnBoard(0, 0, ChessColor.BLACK, pictureNum);
            initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK, pictureNum);
            initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE, pictureNum);
            initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE, pictureNum);
            initKnightOnBoard(0, 1, ChessColor.BLACK, pictureNum);
            initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK, pictureNum);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE, pictureNum);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE, pictureNum);
            initBishopOnBoard(0, 2, ChessColor.BLACK,pictureNum);
            initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK,pictureNum);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE,pictureNum);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE,pictureNum);
            initQueenOnBoard(0, 3, ChessColor.BLACK,pictureNum);
            initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE,pictureNum);
            initKingOnBoard(0, 4, ChessColor.BLACK,pictureNum);
            initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 0, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 1, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 2, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 3, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 4, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 5, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 6, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 7, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.WHITE,pictureNum);
        } else {
            turnchessboard = false;
            initRookOnBoard(0, 0, ChessColor.WHITE,pictureNum);
            initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.WHITE,pictureNum);
            initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.BLACK,pictureNum);
            initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.BLACK,pictureNum);
            initKnightOnBoard(0, 1, ChessColor.WHITE,pictureNum);
            initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.WHITE,pictureNum);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.BLACK,pictureNum);
            initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.BLACK,pictureNum);
            initBishopOnBoard(0, 2, ChessColor.WHITE,pictureNum);
            initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.WHITE,pictureNum);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.BLACK,pictureNum);
            initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.BLACK,pictureNum);
            initQueenOnBoard(0, 3, ChessColor.WHITE,pictureNum);
            initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.BLACK,pictureNum);
            initKingOnBoard(0, 4, ChessColor.WHITE,pictureNum);
            initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(1, 0, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 1, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 2, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 3, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 4, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 5, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 6, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(1, 7, ChessColor.WHITE,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.BLACK,pictureNum);
            initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.BLACK,pictureNum);
        }
    }

    public void initiateEmptyChessboard(int picture) {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE, false,picture));
            }
        }
    }

    private void initRookOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initPawnOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKnightOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initQueenOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initBishopOnBoard(int row, int col, ChessColor color,int picture) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE, false,picture);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

// Game

    public void loadGame(List<String> chessData, ChessGameFrame chessGameFrame) {
        if (chessData.size() == 0) {
            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No content!", "warning", 0);
            return;
        } else {
            // Color
            ChessColor tempcolor;
            if (chessData.get(0).equals("WHITE")) {
                tempcolor = ChessColor.WHITE;
            } else if (chessData.get(0).equals("BLACK")) {
                tempcolor = ChessColor.BLACK;
            } else {
                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No current player message!", "Warning", 0);
                return;
            }

            // Chessboard
            if (chessData.size() != 66) {
                JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chessboard!", "Warning", 0);
                return;
            }

            // Chess
            for (int i = 1; i < 65; i++) {
                int x = (i - 1) / 8;
                int y = i - 8 * x - 1;
                char c = chessData.get(i).charAt(0);
                ChessColor tempColori;
                if (c == 'N') {
                    if (chessData.get(i).length() >= 4) {
                        if (chessData.get(i).substring(0, 4).equals("NONE")) {
//                            this.remove(chessComponents[x][y]);
//                            chessComponents[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), calculatePoint(x, y), clickController, CHESS_SIZE, false);
//                            this.add(chessComponents[x][y]);
                            continue;
                        }
                    }
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                } else if (c == 'W') {
                    if (chessData.get(i).length() >= 5) {
                        if (chessData.get(i).substring(0, 5).equals("WHITE")) {
//                            tempColori = ChessColor.WHITE;
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
                        if (chessData.get(i).substring(0, 5).equals("BLACK")) {
//                            tempColori = ChessColor.BLACK;
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

                if (chessData.get(i).length() == 12
                        && chessData.get(i).substring(5, 11).equals("Bishop")
                        && (chessData.get(i).charAt(11) == 't' || chessData.get(i).charAt(11) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(11) == 't';
//                    chessComponents[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("King")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(9) == 't';
//                    chessComponents[x][y] = new KingChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else if (chessData.get(i).length() == 12
                        && chessData.get(i).substring(5, 11).equals("Knight")
                        && (chessData.get(i).charAt(11) == 't' || chessData.get(i).charAt(11) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(11) == 't';
//                    chessComponents[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("Pawn")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(9) == 't';
//                    chessComponents[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else if (chessData.get(i).length() == 11
                        && chessData.get(i).substring(5, 10).equals("Queen")
                        && (chessData.get(i).charAt(10) == 't' || chessData.get(i).charAt(10) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(10) == 't';
//                    chessComponents[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("Rook")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
//                    this.remove(chessComponents[x][y]);
//                    boolean moved = chessData.get(i).charAt(9) == 't';
//                    chessComponents[x][y] = new RookChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                } else {
//                    init(currentColor);
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                }
                chessGameFrame.setCounter(Integer.parseInt(chessData.get(65)));
//                this.add(chessComponents[x][y]);
//                repaint();
            }

            // Init
            AtomicInteger SelectPicture = new AtomicInteger();
            Object[] picture = {"black or white", "color"};
            SelectPicture.set(JOptionPane.showOptionDialog(null, "Select your pictures!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, picture, picture[0]));
//
            this.currentColor = tempcolor;
            this.init(this.currentColor, 0);
            for (int i = 1; i < 65; i++) {
                int x = (i - 1) / 8;
                int y = i - 8 * x - 1;
                char c = chessData.get(i).charAt(0);
                ChessColor tempColori;
                if (c == 'N') {
                    if (chessData.get(i).length() >= 4) {
                        if (chessData.get(i).substring(0, 4).equals("NONE")) {
                            this.remove(chessComponents[x][y]);
                            chessComponents[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), calculatePoint(x, y), clickController, CHESS_SIZE, false,SelectPicture.get());
                            this.add(chessComponents[x][y]);
                            continue;
                        }
                    }
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                } else if (c == 'W') {
                    if (chessData.get(i).length() >= 5) {
                        if (chessData.get(i).substring(0, 5).equals("WHITE")) {
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
                        if (chessData.get(i).substring(0, 5).equals("BLACK")) {
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

                if (chessData.get(i).length() == 12
                        && chessData.get(i).substring(5, 11).equals("Bishop")
                        && (chessData.get(i).charAt(11) == 't' || chessData.get(i).charAt(11) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(11) == 't';
                    chessComponents[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved,SelectPicture.get());
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("King")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(9) == 't';
                    chessComponents[x][y] = new KingChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved,SelectPicture.get());
                } else if (chessData.get(i).length() == 12
                        && chessData.get(i).substring(5, 11).equals("Knight")
                        && (chessData.get(i).charAt(11) == 't' || chessData.get(i).charAt(11) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(11) == 't';
                    chessComponents[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE,moved,SelectPicture.get());
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("Pawn")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(9) == 't';
                    chessComponents[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved,SelectPicture.get());
                } else if (chessData.get(i).length() == 11
                        && chessData.get(i).substring(5, 10).equals("Queen")
                        && (chessData.get(i).charAt(10) == 't' || chessData.get(i).charAt(10) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(10) == 't';
                    chessComponents[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved,SelectPicture.get());
                } else if (chessData.get(i).length() == 10
                        && chessData.get(i).substring(5, 9).equals("Rook")
                        && (chessData.get(i).charAt(9) == 't' || chessData.get(i).charAt(9) == 'f')) {
                    this.remove(chessComponents[x][y]);
                    boolean moved = chessData.get(i).charAt(9) == 't';
                    chessComponents[x][y] = new RookChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved,SelectPicture.get());
                } else {
                    init(currentColor,SelectPicture.get());
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                    return;
                }
                this.add(chessComponents[x][y]);
                repaint();
            }

            sta.setText("Time for " + currentColor.getName());
            this.setVisible(true);
        }
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
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE, false,chess1.picture));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        sta.setText("Time for " + currentColor.getName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

}
