package view;

import controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        JButton start=new JButton("Start");
        JButton restart=new JButton("Restart");
        JLabel background=new JLabel(new ImageIcon("./images/backg.jpg"));
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        //新建的
        addStart(start);//开始按键
        addBackGround(background);//开始时的背景设置，可以换图片
        removeBackGround(background,start);//按下开始之后，将背景图片设为不可见
        addReStart(restart);//
        ReStart(background,restart,chessboard);//重新开始，背景图片重新可见

        //Demo中的
        addChessboard(chessboard);
        addLabel();
        addHelloButton();
        addLoadButton();
    }
    // 添加开始按键
    private void addStart(JButton start) {

        start.setLocation(HEIGTH, HEIGTH /40*23);
        start.setSize(200, 60);
        start.setFont(new Font("Start", Font.BOLD, 20));
        add(start);
    }
    //背景添加
    private void addBackGround(JLabel background){
        background.setVisible(true);
        background.setSize(750,700);
        add(background);
    }
    ///按下start 后，棋盘显示
    private void removeBackGround(JLabel background,JButton start){
        start.addActionListener(e -> {
            background.setVisible(false);
        });
    }

    // 添加重新开始按键
    private void addReStart(JButton restart) {
        restart.setLocation(HEIGTH, HEIGTH /4*3);
        restart.setSize(200, 60);
        restart.setFont(new Font("Restart", Font.BOLD, 20));
        add(restart);
    }
    //重新开始
    private void ReStart(JLabel background,JButton restart,Chessboard chessboard){
        restart.addActionListener(e -> {
            background.setVisible(true);
            chessboard.init();
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
    private void addLabel() {
        JLabel statusLabel = new JLabel("Have fun!");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

}
