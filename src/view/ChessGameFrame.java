package view;

import controller.GameController;
import model.ChessColor;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static javax.swing.JFileChooser.CANCEL_OPTION;

public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static String filename;

    // Constructor
    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project "); //设置标题
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

        AtomicInteger SelectPicture = new AtomicInteger();
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, statusLabel, SelectColor, SelectPicture);

        JLabel background = new JLabel(new ImageIcon("./images/backg.jpg"));
        JButton start = new JButton("Start");
        JButton restart = new JButton("Restart");
        JButton load = new JButton("Load");
        JButton store = new JButton("Store");
        addStart(chessboard, statusLabel, start);//开始按键
        addReStart(restart);
        addLoad(load);
        addStore(chessboard, store);
        PressStartButton(chessboard, statusLabel, background, start, SelectColor, SelectPicture);//按下开始之后，将背景图片设为不可见
        PressReStartButton(background, restart, chessboard, statusLabel, SelectColor, SelectPicture);//重新开始，背景图片重新可见
        PressLoadButton(chessboard, background, load, start);
        PressStoreButton(chessboard, store);

        addBackGround(background);//开始时的背景设置，可以换图片

        //Demo中的
        addChessboard(chessboard);
//        addLabel(chessboard, statusLabel);
//        addHelloButton();

    }


    // Getter
    public static String getFilename() {
        return filename;
    }


    // Buttons
    // Start
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

    private void PressStartButton(Chessboard chessboard, JLabel statusLabel, JLabel background, JButton start, AtomicInteger SelectColor, AtomicInteger pickPicture) {
        Object[] color = {"Black", "White"};
        Object[] picture = {"black or white", "color"};
        start.addActionListener(e -> {
            //Black :selectColor==0.White:SelectColor==1
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
            chessboard.setCurrentColor(1);
            pickPicture.set(JOptionPane.showOptionDialog(null, "Select your pictures!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, picture, picture[0]));
            chessboard.init(SelectColor.get() == 0 ? ChessColor.BLACK : ChessColor.WHITE, pickPicture.get());
            background.setVisible(false);
            addLabel(chessboard, statusLabel);
            start.setVisible(false);

            Date date = new Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
            this.filename = simple.format(date);
            String dir = new String("resource\\" + this.filename + ".txt");
            File file = null;
            try {
                file = new File(dir);
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter("resource\\" + getFilename() + ".txt"));
                writer.write(chessboard.getCurrentColor().toString());
                writer.newLine();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        writer.write(chessboard.getChessComponents()[i][j].getChessColor().toString());
                        writer.write(chessboard.getChessComponents()[i][j].getName());
                        writer.write("f");
                        writer.newLine();
                    }
                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            playBgm();
        });
    }

    // Restart
    private void addReStart(JButton restart) {
        restart.setLocation(HEIGTH, HEIGTH * 19 / 60);
        restart.setSize(200, 60);
        restart.setFont(new Font("Restart", Font.BOLD, 20));
        add(restart);
    }

    private void PressReStartButton(JLabel background, JButton restart, Chessboard chessboard, JLabel statusLabel, AtomicInteger SelectColor, AtomicInteger pickPicture) {
        Object[] color = {"Black", "White"};
        Object[] picture = {"black or white", "color"};
        AtomicReference<ChessColor> currentColor1 = new AtomicReference<>();
        restart.addActionListener(e -> {
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
            if (SelectColor.get() == 0) {
                currentColor1.set(ChessColor.BLACK);
            } else {
                currentColor1.set(ChessColor.WHITE);
            }
            pickPicture.set(JOptionPane.showOptionDialog(null, "Select your pictures!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, picture, picture[0]));
            chessboard.setCurrentColor(1);
            chessboard.init(SelectColor.get() == 0 ? ChessColor.BLACK : ChessColor.WHITE, pickPicture.get());
            chessboard.setVisible(false);
            addLabel(chessboard, chessboard.sta);
            chessboard.setVisible(true);

            Date date = new Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
            this.filename = simple.format(date);
            String dir = new String("resource\\" + filename + ".txt");
            try {
                File file = new File(dir);
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter("resource\\" + getFilename() + ".txt"));
                writer.write(chessboard.getCurrentColor().toString());
                writer.newLine();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        writer.write(chessboard.getChessComponents()[i][j].getChessColor().toString());
                        writer.write(chessboard.getChessComponents()[i][j].getName());
                        writer.write("f");
                        writer.newLine();
                    }
                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    // Load
    private void addLoad(JButton load) {
        load.setLocation(HEIGTH, HEIGTH * 8 / 15);
        load.setSize(200, 60);
        load.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(load);
    }

    private void PressLoadButton(Chessboard chessboard, JLabel background, JButton load, JButton start) {
        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("resource\\"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            String saveType[] = {"txt"};
            fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", saveType));
            String path = "";
            int value = fileChooser.showOpenDialog(new ChessGameFrame(1000, 760));
            if (value == JFileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile().getName();
            } else {
            }
//            String path = JOptionPane.showInputDialog(this, "Input filename here");
            this.filename = path;
            background.setVisible(false);
            gameController.loadGameFromFile(path);
            addLabel(chessboard, chessboard.sta);
            removeStartButton(start);
        });
    }

    // Store
    private void addStore(Chessboard chessboard, JButton store) {
        store.setLocation(HEIGTH, HEIGTH * 3 / 4);
        store.setSize(200, 60);
        store.setFont(new Font("Store", Font.BOLD, 20));
        add(store);
    }

    private void PressStoreButton(Chessboard chessboard, JButton store) {
        store.addActionListener(e -> {
            try {
                BufferedWriter writer;
                if (getFilename().substring(getFilename().length() - 4).equals(".txt")) {
                    writer = new BufferedWriter(new FileWriter("resource\\" + getFilename()));
                } else {
                    writer = new BufferedWriter(new FileWriter("resource\\" + getFilename() + ".txt"));
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
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    // Other
    private void addBackGround(JLabel background) {
        background.setVisible(true);
        background.setSize(750, 700);
        add(background);
    }

    private void addChessboard(Chessboard chessboard) {
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
    }

    // 在游戏面板中添加标签
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
        statusLabel.setVisible(true);
        add(statusLabel);
    }

    public static void playBgm() {
        File file = new File("resource//Windows XP 关机.wav");
        Clip clip = null;
        try {
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
