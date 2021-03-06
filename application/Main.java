package application;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

  HashMap<String, ArrayList<Question>> questionList = new HashMap<String, ArrayList<Question>>();

  @Override
  public void start(Stage primaryStage) {
    try {
      // Creation of buttons and boxes
      primaryStage.setTitle("Quiz Generator");
      Scene scene = home();
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
  
  public Scene home () {
	  // Creation of buttons and boxes
      VBox vungryBox = new VBox();
      HBox hungryBox1 = new HBox();
      HBox hungryBox2 = new HBox();
      HBox hungryBox3 = new HBox();
      Text topicLabel = new Text("Topics ");
      Button takeQuiz = new Button("Take Quiz");
      Button addNewQuestion = new Button("Add New Question");
      Button importBooks = new Button("Import JSON");
      Button export = new Button("Export");
      TextField numQuestions = new TextField();
      numQuestions.setMaxWidth(50);
      Text placeHolderNum = new Text("Number of Questions: ");

      // Create comboBox with possible topics
      ObservableList<String> topicList = FXCollections.observableArrayList(questionList.keySet());
      Collections.sort(topicList);
      ComboBox<String> topics = new ComboBox<String>();
      topics.setItems(topicList);
      topics.getSelectionModel().selectFirst();

      // Display questions for default topic
      ArrayList<Question> topicQuestions = questionList.get(topics.getValue());
      ObservableList<String> questionTitles = FXCollections.observableArrayList();
      for (Question q : topicQuestions) {
        questionTitles.add(q.getQuestionTitle());
      }
      ListView<String> list = new ListView<String>(questionTitles);

      // CSS styling for objects
      /*hungryBox1.setStyle("-fx-border-color:black;");
      hungryBox1.setStyle("-fx-border-radius:5;");
      takeQuiz.setStyle("-fx-font-size:20px;");
      placeHolderNum.setStyle("-fx-font-size:21px;");*/
      
      takeQuiz.getStyleClass().addAll("custom-button", "basic-text");
      addNewQuestion.getStyleClass().addAll("custom-button", "basic-text");
      importBooks.getStyleClass().addAll("custom-button", "basic-text");
      export.getStyleClass().addAll("custom-button", "basic-text");
      topics.getStyleClass().addAll("custom-combo", "basic-text");
      placeHolderNum.getStyleClass().addAll("basic-text");
      placeHolderNum.setFont(Font.font("Letter Gothic"));
      placeHolderNum.setFill(Color.rgb(13,61,137));
      numQuestions.getStyleClass().addAll("custom-textfield");
      list.getStyleClass().addAll("custom-list", "basic-text");     
      topicLabel.getStyleClass().addAll("basic-text", "text-padding-top");
      topicLabel.setFill(Color.rgb(13,61,137));
      HBox.setMargin(topicLabel, new Insets(8,0,0,0));
      HBox.setMargin(importBooks, new Insets(0,10,0,0));
      
      hungryBox1.setPadding(new Insets(10, 10, 0, 10));
      hungryBox2.setPadding(new Insets(10, 10, 0, 10));
      hungryBox3.setPadding(new Insets(10, 10, 10, 10));

      topics.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
      final Pane spacer = new Pane();
      HBox.setHgrow(spacer, Priority.ALWAYS);
      final Pane spacer2 = new Pane();
      HBox.setHgrow(spacer2, Priority.ALWAYS);
      spacer.setMinSize(10, 1);
      takeQuiz.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

      // Add all objects to scene and display
      hungryBox1.getChildren().addAll(topicLabel, topics, spacer, takeQuiz);
      hungryBox2.getChildren().addAll(addNewQuestion, spacer2, importBooks, export);
      hungryBox3.getChildren().addAll(placeHolderNum, numQuestions);
      vungryBox.getChildren().addAll(hungryBox1, hungryBox2, hungryBox3, list);
      Scene scene = new Scene(vungryBox, 800, 600);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return scene;
  }
}
