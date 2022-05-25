package view;

import controller.GameController;
import model.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static javax.swing.JFileChooser.CANCEL_OPTION;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static String filename;
    private static int counter;
    private static int Picture;

    // Constructor
    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project "); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        this.getContentPane().setBackground(Color.WHITE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        //来源
        JLabel statusLabel = new JLabel("");
        statusLabel.setForeground(Color.BLUE);
        AtomicInteger SelectColor = new AtomicInteger();
        AtomicInteger SelectPicture = new AtomicInteger();

        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE, statusLabel, SelectColor, SelectPicture);
        JLabel background = new JLabel(new ImageIcon("./images/backg.jpg"));
        Picture = SelectPicture.get();

        JButton start = newStart();
        JButton restart = newReStart();
        JButton load = newload();
        JButton store = newStore();
        JButton previousStep = previousStep();
        JButton nextStep = nextStep();
        JButton change = changeColor();
        addStart(chessboard, statusLabel, start);//开始按键
        addReStart(restart);
        addLoad(load);
        addStore(chessboard, store);
        addPreviousStep(previousStep);
        addNextStep(nextStep);
        addChange(change);
        PressChange(change);
        PressStartButton(chessboard, statusLabel, background, start, SelectColor, SelectPicture);//按下开始之后，将背景图片设为不可见
        PressReStartButton(background, restart, chessboard, statusLabel, SelectColor, SelectPicture);//重新开始，背景图片重新可见
        PressLoadButton(chessboard, background, load, start);
        PressStoreButton(chessboard, store);
        PressPreviousStep(chessboard, previousStep);
        PressNextStep(chessboard, nextStep);
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

    public static void setCounter(int newcounter) {
        counter = newcounter;
    }

    public static int getCounter() {
        return counter;
    }

    private JButton newStart() {
        JButton start = new JButton("Start");
        start.setBackground(new Color(176, 196, 222));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                start.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                start.setBackground(new Color(176, 196, 222));
            }
        });
        return start;
    }

    private JButton newReStart() {
        JButton Restart = new JButton("Restart");
        Restart.setBackground(new Color(176, 196, 222));
        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Restart.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Restart.setBackground(new Color(176, 196, 222));
            }
        });
        return Restart;
    }

    private JButton newload() {
        JButton load = new JButton("Load");
        load.setBackground(new Color(176, 196, 222));
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                load.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                load.setBackground(new Color(176, 196, 222));
            }
        });
        return load;
    }

    private JButton newStore() {
        JButton Restart = new JButton("Save");
        Restart.setBackground(new Color(176, 196, 222));
        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Restart.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Restart.setBackground(new Color(176, 196, 222));
            }
        });
        return Restart;
    }

    private JButton previousStep() {
        JButton previousStep = new JButton("Previous Step");
        previousStep.setBackground(new Color(176, 196, 222));
        previousStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                previousStep.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                previousStep.setBackground(new Color(176, 196, 222));
            }
        });
        return previousStep;
    }

    private JButton nextStep() {
        JButton nextStep = new JButton("Next Step");
        nextStep.setBackground(new Color(176, 196, 222));
        nextStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                nextStep.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextStep.setBackground(new Color(176, 196, 222));
            }
        });
        return nextStep;
    }

    private JButton changeColor() {
        JButton start = new JButton("Change");
        start.setBackground(new Color(176, 196, 222));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                start.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                start.setBackground(new Color(176, 196, 222));
            }
        });
        return start;
    }

// Buttons

    // Start
    private void addStart(Chessboard chessboard, JLabel statusLabel, JButton start) {
        start.setLocation(HEIGTH, HEIGTH / 15);
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
        counter = 0;
        start.addActionListener(e -> {
            //Black :selectColor==0.White:SelectColor==1
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
            chessboard.setCurrentColor(1);
            pickPicture.set(JOptionPane.showOptionDialog(null, "Select your pictures!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, picture, picture[0]));
            Picture = pickPicture.get();
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
                writer.write(String.valueOf(counter));
                writer.flush();
                writer.close();

                StringBuilder filename2 = new StringBuilder(getFilename());
                if (filename2.substring(filename2.length() - 4).equals(".txt")) {
                    filename2.delete(filename2.length() - 4, filename2.length() - 1);
                }
                filename2.append(String.valueOf(counter));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter("resource\\" + filename2 + ".txt"));
                writer2.write(chessboard.getCurrentColor().toString());
                writer2.newLine();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        writer2.write(chessboard.getChessComponents()[i][j].getChessColor().toString());
                        writer2.write(chessboard.getChessComponents()[i][j].getName());
                        writer2.write("f");
                        writer2.newLine();
                    }
                }
                writer2.write(String.valueOf(counter));
                writer2.flush();
                writer2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            playBgm();
        });
    }

    // Restart
    private void addReStart(JButton restart) {
        restart.setLocation(HEIGTH, HEIGTH * 3 / 15);
        restart.setSize(200, 60);
        restart.setFont(new Font("Restart", Font.BOLD, 20));
        add(restart);
    }

    private void PressReStartButton(JLabel background, JButton restart, Chessboard chessboard, JLabel statusLabel, AtomicInteger SelectColor, AtomicInteger pickPicture) {
        Object[] color = {"Black", "White"};
        Object[] picture = {"black or white", "color"};
        setCounter(0);
        AtomicReference<ChessColor> currentColor1 = new AtomicReference<>();
        restart.addActionListener(e -> {
            SelectColor.set(JOptionPane.showOptionDialog(null, "Select your color!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, color, color[0]));
            if (SelectColor.get() == 0) {
                currentColor1.set(ChessColor.BLACK);
            } else {
                currentColor1.set(ChessColor.WHITE);
            }
            pickPicture.set(JOptionPane.showOptionDialog(null, "Select your pictures!", "Select", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, picture, picture[0]));
            Picture = pickPicture.get();
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
                writer.write(String.valueOf(counter));
                writer.flush();
                writer.close();

                StringBuilder filename2 = new StringBuilder(getFilename());
                if (filename2.substring(filename2.length() - 4).equals(".txt")) {
                    filename2.delete(filename2.length() - 4, filename2.length() - 1);
                }
                filename2.append(String.valueOf(counter));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter("resource\\" + filename2 + ".txt"));
                writer2.write(chessboard.getCurrentColor().toString());
                writer2.newLine();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        writer2.write(chessboard.getChessComponents()[i][j].getChessColor().toString());
                        writer2.write(chessboard.getChessComponents()[i][j].getName());
                        writer2.write("f");
                        writer2.newLine();
                    }
                }
                writer2.write(String.valueOf(counter));
                writer2.flush();
                writer2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    // Load
    private void addLoad(JButton load) {
        load.setLocation(HEIGTH, HEIGTH * 5 / 15);
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
            this.filename = path.substring(0, path.length() - 4);
            background.setVisible(false);
            gameController.loadGameFromFile(path, this);
            addLabel(chessboard, chessboard.sta);
            removeStartButton(start);
        });
    }

    // Store
    private void addStore(Chessboard chessboard, JButton store) {
        store.setLocation(HEIGTH, HEIGTH * 7 / 15);
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
                writer.write(String.valueOf(counter));
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    // Previous Step
    private void addPreviousStep(JButton previousStep) {
        previousStep.setLocation(HEIGTH, HEIGTH * 9 / 15);
        previousStep.setSize(200, 60);
        previousStep.setFont(new Font("Previous Step", Font.BOLD, 20));
        add(previousStep);
    }

    private void PressPreviousStep(Chessboard chessboard, JButton previousStep) {
        previousStep.addActionListener(e -> {
            StringBuilder newFileName = new StringBuilder(this.filename);
            if (filename.substring(filename.length() - 4).equals(".txt")) {
                newFileName.delete(newFileName.length() - 4, newFileName.length() - 1);
            }
            int newCounter = counter - 1;
            setCounter(newCounter);
            newFileName.append(String.valueOf(counter));
            List<String> readLines = new ArrayList<>();

            try {
                FileReader fileReader = new FileReader(new String("resource\\" + newFileName + ".txt"));
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    readLines.add(line);
                }
                if (readLines.size() == 0) {
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No content!", "warning", 0);
                    return;
                } else {
                    // Color
                    ChessColor tempcolor;
                    if (readLines.get(0).equals("WHITE")) {
                        tempcolor = ChessColor.WHITE;
                    } else if (readLines.get(0).equals("BLACK")) {
                        tempcolor = ChessColor.BLACK;
                    } else {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No current player message!", "Warning", 0);
                        return;
                    }

                    // Chessboard
                    if (readLines.size() != 66) {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chessboard!", "Warning", 0);
                        return;
                    }

                    // Chess
                    for (int i = 1; i < 65; i++) {
                        int x = (i - 1) / 8;
                        int y = i - 8 * x - 1;
                        char c = readLines.get(i).charAt(0);
                        ChessColor tempColori;
                        if (c == 'N') {
                            if (readLines.get(i).length() >= 4) {
                                if (readLines.get(i).substring(0, 4).equals("NONE")) {
//                            chessboard.remove(chessboard.getChessComponents()[x][y]);
//                            chessboard.getChessComponents()[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), calculatePoint(x, y), clickController, CHESS_SIZE, false);
//                            chessboard.add(chessboard.getChessComponents()[x][y]);
                                    continue;
                                }
                            }
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        } else if (c == 'W') {
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("WHITE")) {
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
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("BLACK")) {
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

                        if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Bishop") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(11) == 't';
//                    chessboard.getChessComponents()[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("King") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new KingChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Knight") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(11) == 't';
//                    chessboard.getChessComponents()[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Pawn") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 11 && readLines.get(i).substring(5, 10).equals("Queen") && (readLines.get(i).charAt(10) == 't' || readLines.get(i).charAt(10) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(10) == 't';
//                    chessboard.getChessComponents()[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Rook") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new RookChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else {
//                    init(currentColor);
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                        this.setCounter(Integer.parseInt(readLines.get(65)));
//                chessboard.add(chessboard.getChessComponents()[x][y]);
//                repaint();
                    }

                    if (tempcolor.equals(ChessColor.BLACK)) {
                        chessboard.setCurrentColor(0);
                    } else {
                        chessboard.setCurrentColor(1);
                    }
                    chessboard.init(chessboard.getCurrentColor(), 0);
                    for (int i = 1; i < 65; i++) {
                        int x = (i - 1) / 8;
                        int y = i - 8 * x - 1;
                        char c = readLines.get(i).charAt(0);
                        ChessColor tempColori;
                        if (c == 'N') {
                            if (readLines.get(i).length() >= 4) {
                                if (readLines.get(i).substring(0, 4).equals("NONE")) {
                                    chessboard.remove(chessboard.getChessComponents()[x][y]);
                                    chessboard.getChessComponents()[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), chessboard.getClickController(), chessboard.getCHESS_SIZE(), false, Picture);
                                    chessboard.add(chessboard.getChessComponents()[x][y]);
                                    continue;
                                }
                            }
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        } else if (c == 'W') {
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("WHITE")) {
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
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("BLACK")) {
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

                        if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Bishop") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(11) == 't';
                            chessboard.getChessComponents()[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("King") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new KingChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Knight") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(11) == 't';
                            chessboard.getChessComponents()[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Pawn") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 11 && readLines.get(i).substring(5, 10).equals("Queen") && (readLines.get(i).charAt(10) == 't' || readLines.get(i).charAt(10) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(10) == 't';
                            chessboard.getChessComponents()[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Rook") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new RookChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else {
                            chessboard.init(chessboard.getCurrentColor(), Picture);
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                        chessboard.add(chessboard.getChessComponents()[x][y]);
                        repaint();
                    }

                    chessboard.sta.setText("Time for " + chessboard.getCurrentColor().getName());
                    chessboard.setVisible(true);
                }
                reader.close();
                fileReader.close();
            } catch (IOException f) {
                f.printStackTrace();
            }
            addLabel(chessboard, chessboard.sta);
        });
    }

    // Next Step
    private void addNextStep(JButton nextStep) {
        nextStep.setLocation(HEIGTH, HEIGTH * 11 / 15);
        nextStep.setSize(200, 60);
        nextStep.setFont(new Font("Next Step", Font.BOLD, 20));
        add(nextStep);
    }

    private void PressNextStep(Chessboard chessboard, JButton nextStep) {
        nextStep.addActionListener(e -> {
            StringBuilder newFileName = new StringBuilder(this.filename);
            if (filename.substring(filename.length() - 4).equals(".txt")) {
                newFileName.delete(newFileName.length() - 4, newFileName.length() - 1);
            }
            int newCounter = counter + 1;
            setCounter(newCounter);
            newFileName.append(String.valueOf(counter));
            try {
                FileReader fileReader = new FileReader(new String("resource\\" + newFileName + ".txt"));
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                List<String> readLines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    readLines.add(line);
                }
                if (readLines.size() == 0) {
                    JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No content!", "warning", 0);
                    return;
                } else {
                    // Color
                    ChessColor tempcolor;
                    if (readLines.get(0).equals("WHITE")) {
                        tempcolor = ChessColor.WHITE;
                    } else if (readLines.get(0).equals("BLACK")) {
                        tempcolor = ChessColor.BLACK;
                    } else {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "No current player message!", "Warning", 0);
                        return;
                    }

                    // Chessboard
                    if (readLines.size() != 66) {
                        JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chessboard!", "Warning", 0);
                        return;
                    }

                    // Chess
                    for (int i = 1; i < 65; i++) {
                        int x = (i - 1) / 8;
                        int y = i - 8 * x - 1;
                        char c = readLines.get(i).charAt(0);
                        ChessColor tempColori;
                        if (c == 'N') {
                            if (readLines.get(i).length() >= 4) {
                                if (readLines.get(i).substring(0, 4).equals("NONE")) {
//                            chessboard.remove(chessboard.getChessComponents()[x][y]);
//                            chessboard.getChessComponents()[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), calculatePoint(x, y), clickController, CHESS_SIZE, false);
//                            chessboard.add(chessboard.getChessComponents()[x][y]);
                                    continue;
                                }
                            }
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        } else if (c == 'W') {
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("WHITE")) {
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
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("BLACK")) {
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

                        if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Bishop") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(11) == 't';
//                    chessboard.getChessComponents()[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("King") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new KingChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Knight") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(11) == 't';
//                    chessboard.getChessComponents()[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Pawn") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 11 && readLines.get(i).substring(5, 10).equals("Queen") && (readLines.get(i).charAt(10) == 't' || readLines.get(i).charAt(10) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(10) == 't';
//                    chessboard.getChessComponents()[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Rook") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
//                    chessboard.remove(chessboard.getChessComponents()[x][y]);
//                    boolean moved = readLines.get(i).charAt(9) == 't';
//                    chessboard.getChessComponents()[x][y] = new RookChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), tempColori, clickController, CHESS_SIZE, moved);
                        } else {
//                    init(currentColor);
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                        this.setCounter(Integer.parseInt(readLines.get(65)));
//                chessboard.add(chessboard.getChessComponents()[x][y]);
//                repaint();
                    }

                    if (tempcolor.equals(ChessColor.BLACK)) {
                        chessboard.setCurrentColor(0);
                    } else {
                        chessboard.setCurrentColor(1);
                    }
                    chessboard.init(chessboard.getCurrentColor(), 0);
                    for (int i = 1; i < 65; i++) {
                        int x = (i - 1) / 8;
                        int y = i - 8 * x - 1;
                        char c = readLines.get(i).charAt(0);
                        ChessColor tempColori;
                        if (c == 'N') {
                            if (readLines.get(i).length() >= 4) {
                                if (readLines.get(i).substring(0, 4).equals("NONE")) {
                                    chessboard.remove(chessboard.getChessComponents()[x][y]);
                                    chessboard.getChessComponents()[x][y] = new EmptySlotComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), chessboard.getClickController(), chessboard.getCHESS_SIZE(), false, Picture);
                                    chessboard.add(chessboard.getChessComponents()[x][y]);
                                    continue;
                                }
                            }
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        } else if (c == 'W') {
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("WHITE")) {
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
                            if (readLines.get(i).length() >= 5) {
                                if (readLines.get(i).substring(0, 5).equals("BLACK")) {
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

                        if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Bishop") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(11) == 't';
                            chessboard.getChessComponents()[x][y] = new BishopChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("King") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new KingChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 12 && readLines.get(i).substring(5, 11).equals("Knight") && (readLines.get(i).charAt(11) == 't' || readLines.get(i).charAt(11) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(11) == 't';
                            chessboard.getChessComponents()[x][y] = new KnightChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Pawn") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new PawnChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 11 && readLines.get(i).substring(5, 10).equals("Queen") && (readLines.get(i).charAt(10) == 't' || readLines.get(i).charAt(10) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(10) == 't';
                            chessboard.getChessComponents()[x][y] = new QueenChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else if (readLines.get(i).length() == 10 && readLines.get(i).substring(5, 9).equals("Rook") && (readLines.get(i).charAt(9) == 't' || readLines.get(i).charAt(9) == 'f')) {
                            chessboard.remove(chessboard.getChessComponents()[x][y]);
                            boolean moved = readLines.get(i).charAt(9) == 't';
                            chessboard.getChessComponents()[x][y] = new RookChessComponent(new ChessboardPoint(x, y), chessboard.calculatePoint(x, y), tempColori, chessboard.getClickController(), chessboard.getCHESS_SIZE(), moved, Picture);
                        } else {
                            chessboard.init(chessboard.getCurrentColor(), Picture);
                            JOptionPane.showMessageDialog(new ChessGameFrame(1000, 760), "Wrong chess!", "Warning", 0);
                            return;
                        }
                        chessboard.add(chessboard.getChessComponents()[x][y]);
                        repaint();
                    }

                    chessboard.sta.setText("Time for " + chessboard.getCurrentColor().getName());
                    chessboard.setVisible(true);
                }
                reader.close();
                fileReader.close();
            } catch (IOException f) {
                f.printStackTrace();
            }
            addLabel(chessboard, chessboard.sta);
        });
    }

    // Change Color
    private void addChange(JButton change) {
        change.setSize(80, 25);
        change.setLocation(WIDTH / 10 * 9, HEIGTH / 10 * 9);
        change.setVisible(true);
        add(change);
    }

    private void PressChange(JButton change) {
        change.addActionListener(e -> {
            if (this.getContentPane().getBackground() == Color.WHITE) {
                this.getContentPane().setBackground(Color.BLACK);
            } else {
                this.getContentPane().setBackground(Color.WHITE);
            }
            ;
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
