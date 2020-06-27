package mvasem8.com;

import mvasem8.data.EnumD;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JRDialog  extends JDialog {
    private EnumD dialog = EnumD.NONE;

    public EnumD getDialog () {
        return dialog;
    }

    public void setDialog (EnumD dialog) {
        this.dialog = dialog;
    }

    public EnumD showDialog(){
        setLocationRelativeTo(JRDialog.this.getParent());
        setModal(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent e) {
                setDialog(EnumD.Cancel);
            }
        });
        setVisible(true);
        return getDialog();
    }
    public void close(){
        dispose();
    }
}
