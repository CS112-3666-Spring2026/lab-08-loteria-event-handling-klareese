package cs112.lab08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    @Override
    public void start(Stage stage) throws IOException {

        Label titleLabel = new Label("EChALE STEM Lotería");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ImageView cardImageView = new ImageView();
        cardImageView.setFitWidth(200);
        cardImageView.setFitHeight(200);
        cardImageView.setPreserveRatio(true);

        Label messageLabel = new Label("Press Draw Card to start!");

        Button drawCardButton = new Button("Draw Card");

        ProgressBar gameProgressBar = new ProgressBar(0);
        gameProgressBar.setPrefWidth(250);

        boolean[] usedCards = new boolean[LOTERIA_CARDS.length];
        final int[] cardsDrawn = {0};

        drawCardButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                if (cardsDrawn[0] == LOTERIA_CARDS.length) {
                    return;
                }

                int index;
                do {
                    index = (int) (Math.random() * LOTERIA_CARDS.length);
                } while (usedCards[index]);

                usedCards[index] = true;
                cardsDrawn[0]++;

                LoteriaCard card = LOTERIA_CARDS[index];
                cardImageView.setImage(card.getImage());
                messageLabel.setText(card.getCardName());
                gameProgressBar.setProgress(
                        (double) cardsDrawn[0] / LOTERIA_CARDS.length
                );
                if (cardsDrawn[0] == LOTERIA_CARDS.length) {

                    messageLabel.setText(
                            "GAME OVER. No more cards! Exit and run program again to reset ^_^"
                    );
                    cardImageView.setImage(new Image("echale_logo.png"));
                    drawCardButton.setDisable(true);
                    gameProgressBar.setStyle("-fx-accent: red;");
                }
            }
        });
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(350, 500);

        root.getChildren().addAll(
                titleLabel,
                cardImageView,
                messageLabel,
                drawCardButton,
                gameProgressBar
        );
        Scene scene = new Scene(root);
        stage.setTitle("EChALE STEM Lotería");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
