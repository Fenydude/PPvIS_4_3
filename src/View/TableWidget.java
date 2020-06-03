package View;

import model.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.ArrayList;

public class TableWidget {
    private Table table;

    public TableWidget(Shell shell) {
        this.table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setRedraw(true);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        String[] names = {"x", "y"};
        for (int i = 0; i < names.length; i++) {
            TableColumn column = new TableColumn(this.table, SWT.NONE);
            column.setText(names[i]);
        }
        for (int i = 0; i < names.length; i++) {
            table.getColumn(i).pack();
        }
        table.setBounds(900, 250, 100, 600);
    }
    public void addWidget( ArrayList<Point> points){
        table.clearAll();
        String[] names = {"x", "y"};
        int j = 0;
        for(Point p: points)
        {
            TableItem item = new TableItem(table, SWT.NONE);

            this.table.getItem(j).setText(0, Double.toString(p.getX()));
            this.table.getItem(j).setText(1, Double.toString(p.getY()));
            j++;
        }
        for (int i = 0; i < names.length; i++) {
            table.getColumn(i).pack();
        }
    }
    public Table getTable(){
        return table;
    }

}