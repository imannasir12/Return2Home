package planet.memorymaze;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import planet.Game;
import views.AdventureGameView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class MemoryGame implements Game {
    AdventureGameView adventureGameView;
    GridPane gamePane;
    VBox textEntry = new VBox();
    String currPiece;
    ArrayList<String> pieceCorrect;
    int wrong;
    int click;
    int losses;
    boolean result;
    public boolean isVisited = false;
    Label planetIns = new Label();
    boolean instruct;


    public MemoryGame(AdventureGameView game) {
        this.adventureGameView = game;
        currPiece = "";
        gamePane = new GridPane(4,4);
        click = 0;
        pieceCorrect = new ArrayList<>() {{
            add("13");
            add("2");
            add("3");
            add("14");
            add("5");
            add("6");
            add("1");
            add("12");
            add("16");
            add("10");
            add("11");
            add("8");
            add("7");
            add("4");
            add("15");
            add("9");
        }};
        wrong = 0;
        losses = 0;
    }
    /**
     * getResult()
     * __________________________
     * If the player has won the game, they lose no lives and move on to next planet.
     * If the player lost the game, they retry and lose a life. if they have no life, game over.
     */
    @Override
    public Boolean getResult() {
        ArrayList<Node> cell_1_1 = new ArrayList<>();
        for (javafx.scene.Node node : this.adventureGameView.gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && columnIndex != null && rowIndex == 1 && columnIndex == 1) {
                cell_1_1.add(node);
            }
        }
        this.adventureGameView.gridPane.getChildren().removeAll(cell_1_1);
        return this.result;
    }
    /**
     * updateResult()
     * __________________________
     * If the player has won the game, they lose no lives and move on to next planet.
     * If the player lost the game, they retry and lose a life. if they have no life, game over.
     */
    @Override
    public void updateResult() {
        //check if all pieces are matched
       if(pieceCorrect.size() == 16){
           result = true;
           //update the objects
           updateObject();
           //empty the gridpane
           this.adventureGameView.gridPane.getChildren().remove(gamePane);
           ArrayList<Node> cell_1_1 = new ArrayList<>();
           for (javafx.scene.Node node : this.adventureGameView.gridPane.getChildren()) {
               Integer rowIndex = GridPane.getRowIndex(node);
               Integer columnIndex = GridPane.getColumnIndex(node);

               if (rowIndex != null && columnIndex != null && rowIndex == 1 && columnIndex == 1) {
                   cell_1_1.add(node);
               }
           }

           this.adventureGameView.gridPane.getChildren().removeAll(cell_1_1);
           //display the winning screen
           this.adventureGameView.updateScene("YOU HAVE WON.", "planet/cosmiccode/Images/victory.png");

           PauseTransition pause2 = new PauseTransition(Duration.seconds(2.0));
           pause2.setOnFinished((event2) -> {
               // after 2 seconds of displaying their loss, start round 1
               this.adventureGameView.textEntry.setVisible(true);
               this.adventureGameView.displayChoice();
           });
           pause2.play();
       }
       else{
           //player lost
           this.result = false;
       }

    }
    /**
     * updateObject()
     * __________________________
     * If the player collected any new objects, update them.
     */
    @Override
    public void updateObject() {
        //update tools for me
        this.adventureGameView.updateTools("sofia");
    }
    /**
     * checkCorrect()
     * __________________________
     * this checks if the pieces are correct or not and what to do after
     */
    public void checkCorrect(String n) {
        //this is the first click
        if (click == 0) {
            currPiece = n;
            click++;
        } else {
            //match first click to any other click and add them to pane
            if ((currPiece.equals("1") && n.equals("7"))||(n.equals("1") && currPiece.equals("7"))) {
                pieceCorrect.add("1");
                pieceCorrect.add("7");
                //match first click to any other click and add them to pane
            } else if ((currPiece.equals("2") && n.equals("8"))||(n.equals("8") && currPiece.equals("2"))) {
                pieceCorrect.add("2");
                pieceCorrect.add("8");
                //match first click to any other click and add them to pane
            } else if (currPiece.equals("3") && n.equals("15")) {
                pieceCorrect.add("3");
                pieceCorrect.add("15");
                //match first click to any other click and add them to pane
            } else if (n.equals("15") && currPiece.equals("3")) {
                pieceCorrect.add("15");
                pieceCorrect.add("3");
            }            //match first click to any other click and add them to pane
            else if (currPiece.equals("4") && n.equals("13")) {
                pieceCorrect.add("4");
                pieceCorrect.add("13");            //match first click to any other click and add them to pane

            } else if (n.equals("13") && currPiece.equals("4")) {
                pieceCorrect.add("13");
                pieceCorrect.add("4");
            }            //match first click to any other click and add them to pane

            else if (currPiece.equals("5") && n.equals("10")) {
                pieceCorrect.add("5");
                pieceCorrect.add("10");            //match first click to any other click and add them to pane

            } else if (n.equals("10") && currPiece.equals("5")) {
                pieceCorrect.add("5");
                pieceCorrect.add("10");
            }            //match first click to any other click and add them to pane

            else if (currPiece.equals("6") && n.equals("11")) {
                pieceCorrect.add("6");
                pieceCorrect.add("11");
            } else if (n.equals("11") && currPiece.equals("6")) {
                pieceCorrect.add("11");
                pieceCorrect.add("6");
            }            //match first click to any other click and add them to pane

            else if (currPiece.equals("9") && n.equals("16")) {
                pieceCorrect.add("9");
                pieceCorrect.add("16");
            } else if (n.equals("16") && currPiece.equals("9")) {
                pieceCorrect.add("16");
                pieceCorrect.add("9");
            }            //match first click to any other click and add them to pane

            else if (currPiece.equals("12") && n.equals("14")) {
                pieceCorrect.add("12");
                pieceCorrect.add("14");
            } else if (n.equals("14") && currPiece.equals("12")) {
                pieceCorrect.add("14");
                pieceCorrect.add("12");
            }
            click = 0;
            //make it a loss
            losses += 1;
            if (this.losses % 3 == 0) {
                this.adventureGameView.model.player.updateLives(false);
                this.adventureGameView.updateLives();
                losses = 0;
            }
            currPiece = "";
            updateResult();
            makeGrid();

        }

    }
    /**
     * makeGrid()
     * __________________________
     * this makes this grid for the game
     */
    public void makeGrid() {
        gamePane = new GridPane(4,4);
        int track = 1;
        for (int i = 0; i < 4; i++) {
            //loop thorugh and add all the images to the buttons
            for (int j = 0; j < 4; j++) {
                Image createAlternative = new Image("planet/memorymaze/images/white.png");
                Image create = new Image("planet/memorymaze/images/" + track + ".png");
                ImageView make = new ImageView(createAlternative);
                if (pieceCorrect.contains(String.valueOf(track))) {
                    make = new ImageView(create);
                }
                make.setFitHeight(130);
                make.setFitWidth(130);
                Button a = new Button();
                a.setGraphic(make);
                a.setPadding(new Insets(0,0,0,0));
                a.setId(String.valueOf(track));
                a.setOnAction(event -> {
                    checkCorrect(a.getId());
                });
                gamePane.add(a,j,i);
                track++;
            }
        }
        gamePane.setHgap(0);
        gamePane.setVgap(0);
        gamePane.setAlignment(Pos.CENTER);
        this.adventureGameView.gridPane.add(gamePane,1,1);
    }

    /**
     * play
     * __________________________
     * plays the audio of the instructions for the player.
     */
    public void play() {
        //play the audio
        String musicFile = "planet/memorymaze/MemoryGameAudio.m4a";
        Media sound = new Media(new File(musicFile).toURI().toString());
        this.adventureGameView.mediaPlayer = new MediaPlayer(sound);
        this.adventureGameView.mediaPlayer.play();

    }

    /**
     * showInstructions()
     * __________________________
     * this shows the instructions
     */
    public void showInstructions() throws IOException {
        if (this.instruct) {
            this.instruct = false;
        } else {
            //display instructions
            String text = "";
            String fileName = "planet/memorymaze/MemoryGameInstructions.txt";
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            String line = buff.readLine();
            while (line != null) { // while not EOF
                text += line+"\n";
                line = buff.readLine();
            }
            //add audio
            this.adventureGameView.roomDescLabel.setFont(new Font("Arial", 16));
            this.adventureGameView.roomDescLabel.setText(text);
            Button b = new Button("Hear Audio");
            b.setPrefSize(130,50);
            b.setFont(new Font("Arial", 16));
            b.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
            b.setId("instruct");
            b.setOnAction(event -> {
                play();
            });
            VBox roomPane = new VBox();
            roomPane.getChildren().addAll(this.adventureGameView.roomDescLabel, b);
            roomPane.setPadding(new Insets(20));
            roomPane.setAlignment(Pos.TOP_CENTER);
            roomPane.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
            ScrollPane n = new ScrollPane(roomPane);
            n.setPadding(new Insets(10));
            n.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
            n.setFitToWidth(true);
            this.adventureGameView.gridPane.add(n,1, 1);
            this.instruct = true;

        }

    }
    /**
     * startGame()
     * __________________________
     * this starts the game
     */
    @Override
    public void startGame() {
        //change to visited
        this.isVisited = true;
        this.adventureGameView.textEntry.setVisible(false);
        Image bg = new Image("Images/MemoryMaze/background.jpeg");
        BackgroundImage background = new BackgroundImage(
                bg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        //add stuff to background
        this.adventureGameView.gridPane.setBackground(new Background(background));
        ArrayList<Node> cell_1_1 = new ArrayList<>();
        for (javafx.scene.Node node : this.adventureGameView.gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && columnIndex != null && rowIndex == 1 && columnIndex == 1) {
                cell_1_1.add(node);
            }
        }
        this.adventureGameView.gridPane.getChildren().removeAll(cell_1_1);
        //this.adventureGameView.updateTools("");
        this.adventureGameView.objectsInRoom.getChildren().clear();
        try {
            showInstructions();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //pause transition
        PauseTransition pauseInstruct = new PauseTransition(Duration.seconds(5));
        pauseInstruct.setOnFinished(event -> {
            try {
                showInstructions();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ArrayList<Node> cell = new ArrayList<>();
            for (javafx.scene.Node node : this.adventureGameView.gridPane.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);

                if (rowIndex != null && columnIndex != null && rowIndex == 1 && columnIndex == 1) {
                    cell.add(node);
                }
            }
            this.adventureGameView.gridPane.getChildren().removeAll(cell);
            makeGrid();
            //makeScroll();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event1 -> {
                pieceCorrect = new ArrayList<>();
                makeGrid();
            });
            pause.play();
        });
        pauseInstruct.play();
    }
}
