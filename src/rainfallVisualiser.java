package Alpha;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

/**
 * This file can be used to draw a chart that effectively represents rainfall data.  Just fill in
 * the definition of drawPicture with the code that draws your picture.
 */
public class rainfallVisualiser extends Application {

    /**
     * Draws a picture.  The parameters width and height give the size
     * of the drawing area, in pixels.
     */

    public void drawPicture(GraphicsContext g, int width, int height) {
        // draw the x-axis and y-axis
        int margin = 20;
        Point2D origin = new Point2D(margin, height - margin);

        g.setStroke(BLACK);
        g.setLineWidth(1);
        g.setFont(Font.font(24));

        g.strokeLine(origin.getX(), origin.getY(), origin.getX(), margin);
        g.strokeLine(origin.getX(), origin.getY(), width - margin, origin.getY());

        TextIO.getln(); // ignore the header line

        double xAxisLength = width - 2.0 * margin;
        double yAxisLength = height - 2.0 * margin;
        double columnWidth = xAxisLength / 218.0;
        double maxMonthlyTotal = 1500.0;
        double currentX = origin.getX();

        // draw the monthly totals as a bar chart
        g.setLineWidth(1);
        g.setFill(RED);
        while (!TextIO.eof()) {
            String[] record = TextIO.getln().trim().split(",");
            double monthlyTotal = Double.parseDouble(record[2]);

            double columnHeight = monthlyTotal / maxMonthlyTotal * yAxisLength;

            double x = currentX;
            double y = origin.getY() - columnHeight;
            g.fillRect(x, y, columnWidth, columnHeight);

            currentX += columnWidth;
        }
    } // end drawPicture()

    //------ Implementation details: DO NOT EDIT THIS ------
    public void start(Stage stage) {
        int width = 218 * 6 + 40;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser");
        stage.show();
        stage.setResizable(false);
    }
    //------ End of Implementation details ------


    public static void main(String[] args) {
//        System.out.print("Enter path: ");
//        String path = TextIO.getln();

        String path = "analysedCSV.csv";
        TextIO.readFile(path);
        launch();
    }

} // end SimpleGraphicsStarter

