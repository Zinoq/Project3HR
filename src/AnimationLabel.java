import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andre on 19-4-2016.
 */

public class AnimationLabel extends Label {
    Timeline timeline;
    List<String> list = Arrays.asList("Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag");
    int i = 0;

    public AnimationLabel() {
    }

    public void StartAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (i == 7) {
                                    i = 0;
                                }
                                setText(list.get(i));
                                i++;
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void StopAnimation() {
        timeline.stop();
    }
}

