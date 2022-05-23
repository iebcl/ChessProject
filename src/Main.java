import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame open = new JFrame("Play game !");
            open.setSize(1400, 1000);
            open.setLocationRelativeTo(null); // Center the window.
            open.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
            JLabel background = new JLabel(new ImageIcon("./images/backgg.jpg"));

            background.setLocation(0,50);
            background.setSize(1400, 1000);
            JButton Open = new JButton("Come to play with us !");
            Open.setSize(250, 100);
            Open.setLocation(500,750);
            Open.setVisible(true);
            open.add(Open);
            open.add(background);
            open.setVisible(true);
            background.setVisible(true);
            Open.addActionListener(e -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
                mainFrame.setVisible(true);
                open.setVisible(false);
            });
        });
    }
}
