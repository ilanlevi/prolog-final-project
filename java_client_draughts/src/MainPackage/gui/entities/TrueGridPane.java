package MainPackage.gui.entities;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class TrueGridPane extends GridPane {

    //properties
    private DoubleProperty cellWidth = new SimpleDoubleProperty();
    private DoubleProperty cellHeight = new SimpleDoubleProperty();

    /**
     * The constructor for TrueGrid objects
     *
     * @param i The number of columns
     * @param j The number of rows
     */
    public TrueGridPane(int i, int j) {
        initializeColumns(i);
        initializeRows(j);
    }

    /**
     * fill the grid with i columns
     *
     * @param i columns
     */
    private void initializeColumns(int i) {
        for (int c = 0; c < i; c++) {
            getColumnConstraints().add(new ColumnConstraints());
            getColumnConstraints().get(c).setPercentWidth(100.0 / i);
        }
    }

    /**
     * fill the grid with j rows
     *
     * @param j rows
     */
    private void initializeRows(int j) {
        for (int c = 0; c < j; c++) {
            getRowConstraints().add(new RowConstraints());
            getRowConstraints().get(c).setPercentHeight(100.0 / j);
        }
    }


    //getter methods
    public DoubleProperty cellWidthProperty() {
        return cellWidth;
    }

    public double cellWidth() {
        return cellWidth.getValue();
    }

    public DoubleProperty cellHeightProperty() {
        return cellHeight;
    }

    public double cellHeight() {
        return cellHeight.getValue();
    }

}