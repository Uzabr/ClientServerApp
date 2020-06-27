package mvasem8.com;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoadDialog extends JDialog {
    private static final String DEFAULT_MSG =
            "Ожидание загрузки...";

    public LoadDialog (JDialog parent, String Message) {
        super(parent);
        setUndecorated(true);
        setFocusableWindowState(false);
        JPanel panel = new JPanel(new MigLayout("insets 7", "", ""));
        JLabel lbl = new JLabel();
        lbl.setText( Message == null ? DEFAULT_MSG : Message );
        panel.add(lbl);
        panel.setBorder(BorderFactory.createLineBorder
                (Color.BLACK));
        getContentPane().add(panel);
        pack();
        setModal(true);
        setLocationRelativeTo(this.getParent());
    }

    public LoadDialog (String Message) {
        this(null, Message);

    }

    public LoadDialog () {
        this(null);
    }

    public void setVisible(boolean b) {
        if (b)
            new Thread(() -> LoadDialog.super.setVisible(true)).start();
        else {
            if (SwingUtilities.isEventDispatchThread()) {
                super.setVisible(false);
                dispose();
            } else
                try {
                    SwingUtilities.invokeAndWait(() -> LoadDialog.super.setVisible(false));
                } catch (Exception e) {
                }
        }
    }
}
