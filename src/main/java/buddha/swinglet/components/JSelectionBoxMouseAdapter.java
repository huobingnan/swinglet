package buddha.swinglet.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class JSelectionBoxMouseAdapter extends MouseAdapter {
    final JSelectionBox box;
    int sx, sy;
    JSelectionBoxMouseAdapter(JSelectionBox box) {
        this.box = box;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point locationOnScreen = e.getLocationOnScreen();
        this.sx = locationOnScreen.x;
        this.sy = locationOnScreen.y;
        box.setBounds(locationOnScreen.x, locationOnScreen.y, 0, 0);
        box.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        box.setVisible(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point locationOnScreen = e.getLocationOnScreen();
        int ex = locationOnScreen.x;
        int ey = locationOnScreen.y;
        int cx = Math.min(this.sx, ex);
        int cy = Math.min(this.sy, ey);
        int w = Math.abs(this.sx - ex);
        int h = Math.abs(this.sy - ey);
        box.setBounds(cx, cy, w, h);
        if (!box.isVisible()) {
            box.setVisible(true);
        }
    }
}
