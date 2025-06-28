package buddha.swinglet.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 这是一个可以安装到JPanel容器中的选择框
 * @author BRYAN
 * @since 0.0.1
 */
public class JSelectionBox extends JDialog {

    private JSelectionBox(JPanel target, Color background, float opacity, Border border) {
        setOpacity(opacity);
        setUndecorated(true);
        setModal(false);
        setVisible(false);
        setupTargetContainer(target);
        target.putClientProperty("JSelectionBox.instance", this);
        JPanel pnl = new JPanel();
        pnl.setBackground(background);
        pnl.setBorder(border);
        setContentPane(pnl);
    }

    void setupTargetContainer(JPanel target) {
        JSelectionBoxMouseAdapter adapter = new JSelectionBoxMouseAdapter(this);
        target.addMouseListener(adapter);
        target.addMouseMotionListener(adapter);
        target.putClientProperty("JSelectionBox.adapter", adapter);
    }

    public static void install(JPanel target) {
        install(target, Color.GRAY, 0.2f, BorderFactory.createDashedBorder(Color.BLACK));
    }

    public static void install(JPanel target, Color background, float opacity, Border border) {
        new JSelectionBox(target, background, opacity, border);
    }

    public static void uninstall(JPanel target) {
        Object adapter = target.getClientProperty("JSelectionBox.adapter");
        if (adapter != null && adapter.getClass() == JSelectionBoxMouseAdapter.class) {
            target.removeMouseListener((MouseListener) adapter);
            target.removeMouseMotionListener((MouseMotionListener) adapter);
        }
        target.putClientProperty("JSelectionBox.instance", null);
        target.putClientProperty("JSelectionBox.adapter", null);
    }
}
