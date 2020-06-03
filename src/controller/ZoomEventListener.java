package controller;

import View.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Button;


public class ZoomEventListener implements MouseListener, MouseWheelListener {

    private final View.Window window;
    private long zoomCounter = 0;

    public ZoomEventListener(Window window) {
        this.window = window;
        this.window.getPlus().addMouseListener(this);
        this.window.getMines().addMouseListener(this);
        this.window.getWindowForDrow().getChart().addMouseWheelListener(this);
    }

    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
        Button target = (Button) mouseEvent.widget;
        if (target == this.window.getPlus())
            this.window.getWindowForDrow().getChart().getAxisSet().zoomIn();
        else if (target == this.window.getMines())
            this.window.getWindowForDrow().getChart().getAxisSet().zoomOut();
        this.window.getWindowForDrow().redraw();
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseScrolled(MouseEvent mouseEvent) {
        long prev = this.zoomCounter;
        this.zoomCounter += mouseEvent.count;
        if (mouseEvent.stateMask != SWT.CTRL) return;
        if (zoomCounter > prev) {
            this.window.getWindowForDrow().getChart().getAxisSet().zoomOut();
        } else if (zoomCounter < prev) {
            this.window.getWindowForDrow().getChart().getAxisSet().zoomIn();
        }
        this.window.getWindowForDrow().redraw();
        this.zoomCounter = 0;
    }
}
