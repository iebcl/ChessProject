package view;

import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;

    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        //来源
        JLabel statusLabel = new JLabel("");
        AtomicInteger SelectColor = new AtomicInteger();
        JButton start = new JButton("Start");
        JButton restart = new JButton("Restart");
        JLabel background = new JLabel(new ImageIcon("./images/backg.jpg"));
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, statusLabel, SelectColor);
        //新建的
        addStart(chessboard, statusLabel, start);//开始按键
        addBackGround(background);//开始时的背景设置，可以换图片
        PressStartButton(chessboard, statusLabel, background, start, SelectColor);//按下开始之后，将背景图片设为不可见
        addReStart(restart);//
        PressReStartButton(background, restart, chessboard, statusLabel, SelectColor);//重新开始，背景图片重新可见

        //Demo中的
        addChessboard(chessboard);
//        addLabel(chessboard, statusLabel);
//        addHelloButton();
        addLoadButton();

    }

    // 添加开始按键
    private void addStart(Chessboard chessboard, JLabel statusLabel, JButton start) {

        start.setLocation(HEIGTH, HEIGTH / 10);
        start.setSize(200, 60);
        start.setFont(new Font("Start", Font.BOLD, 20));
        add(start);
//        addLabel(chessboard,statusLabel);
    }

    private void removeStartButton(JButton start) {
        remove(start);
    }

    //背景添加
    private void addBackGround(JLabel background) {
        background.setVisible(true);
        background.setSize(750, 700);
        add(background);
    }

    ///按下start 后，棋盘显示
    private void PressStartButton(Chessboard chessboard, JLabel statusLabel, JLabel background, JButton start, AtomicInteger SelectColor) {
        Object[] color = {"Black", "White"};
        start.addActionListener(e -> {
            //Black :selectColor==0.White:SelectColor==1
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
//            System.out.println(SelectColor.get());
            chessboard.setCurrentColor(SelectColor.get());
            chessboard.init(chessboard.getCurrentColor());

            background.setVisible(false);
            addLabel(chessboard, statusLabel);
            start.setVisible(false);
        });
    }

    // 添加重新开始按键
    private void addReStart(JButton restart) {
        restart.setLocation(HEIGTH, HEIGTH / 60 * 22);
        restart.setSize(200, 60);
        restart.setFont(new Font("Restart", Font.BOLD, 20));

        add(restart);
    }

    //重新开始
    private void PressReStartButton(JLabel background, JButton restart, Chessboard chessboard, JLabel statusLabel, AtomicInteger SelectColor) {
        Object[] color = {"Black", "White"};
        AtomicReference<ChessColor> currentColor1 = new AtomicReference<>();
        restart.addActionListener(e -> {
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
            System.out.println(SelectColor.get());
            if (SelectColor.get() == 0) {
                currentColor1.set(ChessColor.BLACK);
            } else {
                currentColor1.set(ChessColor.WHITE);
            }
            chessboard.setCurrentColor(SelectColor.get());
            chessboard.setVisible(false);
            chessboard.init(currentColor1.get());
            addLabel(chessboard, chessboard.sta);
            chessboard.setVisible(true);
        });
    }

    //重新开始中，对棋子的位置更新


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard(Chessboard chessboard) {
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
    }


    /**
     * 在游戏面板中添加标签
     */
    public void addLabel(Chessboard chessboard, JLabel statusLabel) {
        statusLabel.setVisible(false);
        remove(statusLabel);
        boolean m = chessboard.getCurrentColor().getName().equals("Black");
        if (m) {
            remove(statusLabel);
            statusLabel.setText("Time for Black");
        } else {
            remove(statusLabel);
            statusLabel.setText("Time for White");
        }
        statusLabel.setLocation(HEIGTH / 3, HEIGTH / 100);

        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
//        System.out.println(statusLabel.getText());
        statusLabel.setVisible(true);
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示选择己方颜色
     */

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 40 * 23);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

}
