package View;

import controller.DragEventListener;
import controller.ZoomEventListener;
import model.FirstChart;
import model.SecondChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Window {
    private int time = 500;
    private Runnable timerForSetPoint;
    private Runnable timerForDrawChart;
    private boolean secondSelection = false;
    private boolean checkRunnable = false;
    private Button plus;
    private Button mines;
    private WindowForDrow windowForDrow;

    public WindowForDrow getWindowForDrow() {
        return windowForDrow;
    }



    public Button getPlus() {
        return plus;
    }

    public Button getMines() {
        return mines;
    }





    public void start() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Graphik");
        shell.setSize(1000, 1000);

        windowForDrow = new WindowForDrow(shell);
        FirstChart first = new FirstChart(windowForDrow);
        SecondChart secondChart = new SecondChart(windowForDrow);
        TableWidget tableWidget = new TableWidget(shell);

        Button start = new Button(shell, SWT.PUSH);
        start.setBounds(950, 50, 100, 50);
        start.setText("Старт");

        Button stop = new Button(shell, SWT.PUSH);
        stop.setBounds(1050, 50, 100, 50);
        stop.setText("Стоп");

        plus = new Button(shell, SWT.PUSH);
        plus.setBounds(1150, 50, 50, 50);
        plus.setText("+");

        mines = new Button(shell, SWT.PUSH);
        mines.setBounds(1200, 50, 50, 50);
        mines.setText("-");

        DragEventListener dragEventListener = new DragEventListener(this);
        ZoomEventListener zoomEventListener = new ZoomEventListener(this);

        Text shag = new Text(shell, SWT.NONE);
        shag.setBounds(950, 100, 200, 25);

        Text ranfeOfX = new Text(shell, SWT.NONE);
        ranfeOfX.setBounds(950, 130, 200, 25);
        start.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                if (!checkRunnable) {
                    checkRunnable = true;
                    secondSelection = false;
                    String tempText = shag.getText();
                    float firstPoint = Float.parseFloat(tempText);
                    String tempTextSecond = ranfeOfX.getText();
                    float secondPoint = Float.parseFloat(tempTextSecond);
                    if (first.getFirstPoint() == 0.0 && secondChart.getFirstPoint() == 0.0) {
                        first.setFirstPoint(firstPoint);
                        secondChart.setFirstPoint(secondPoint);
                    }

                    timerForSetPoint = new Runnable() {
                        @Override
                        public void run() {

                            first.setPoint();
                            secondChart.setPoint();





                            display.timerExec(time, this);
                        }
                    };

                    display.timerExec(time, timerForSetPoint);

                    timerForDrawChart = new Runnable() {
                        @Override
                        public void run() {
                            first.upWindowForDrow();
                            secondChart.upWindowForDrow();
                           // first.update(tableWidget);
                            secondChart.update(tableWidget);
                            display.timerExec(time, this);

                        }
                    };
                    display.timerExec(time, timerForDrawChart);

                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        stop.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(secondSelection){
                    first.getPoints().clear();
                    secondChart.getPoints().clear();
                    secondChart.setFirstPoint(0.0);
                    first.setFirstPoint(0.0);
                    first.upWindowForDrow();
                    secondChart.upWindowForDrow();
                    first.update(tableWidget);
                }
                if (checkRunnable){
                    checkRunnable = false;
                    secondSelection = true;
                    display.timerExec(-1, timerForSetPoint);
                    display.timerExec(-1, timerForDrawChart);
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        plus.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
               // windowForDrow.zoomWindow();

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        mines.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
               // windowForDrow.rZoomWindow();

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
