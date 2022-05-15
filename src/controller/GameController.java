package controller;

import view.Chessboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<String> loadGameFromFile(String filename) {
        if (!filename.substring(filename.length() - 4, filename.length() - 1).equals(".txt")) {
            System.out.println("Wrong file format.");
            return null;
        } else {
            try {
                FileReader fileReader = new FileReader(new String("resource\\" + filename));
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                List<String> readLines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    readLines.add(line);
                }
                chessboard.loadGame(readLines);
                reader.close();
                fileReader.close();
                return readLines;

//            List<String> chessData = Files.readAllLines(Path.of(path));
//            return chessData;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
