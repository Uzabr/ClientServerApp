package mvasem8.com;

import mvasem8.data.EnumD;
import mvasem8.gui.LoginDialog;
import mvasem8.gui.MainWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RunnerClass {
    private DbConnection manager = null;

    public RunnerClass () {

    }
    public void run(){
        if (showLoginDialog()){
            showMainWindow();
        }
        else{
            closeWindow();
        }

    }

    private void closeWindow () {
        System.exit(0);
    }

    private void showMainWindow () {
        LoadDialog loadDialog = new LoadDialog("Загружается программа...");
        loadDialog.setVisible(true);
        MainWindow mw = null;
        try{
            mw = createMainWindow(manager);
            mw.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed (WindowEvent e) {
                    closeWindow();
                }
            });
            mw.initialize();
            loadDialog.setVisible(false);
            loadDialog.dispose();
            mw.setVisible(true);
        }catch (Exception e){
            String s = e.getMessage();
            JOptionPane.showMessageDialog(null, s,
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            loadDialog.setVisible(false);
            loadDialog.dispose();
        }
    }

    private MainWindow createMainWindow (DbConnection manager) {
        return new MainWindow(manager);
    }

    private boolean showLoginDialog () {
        try{
            manager = new DbConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        LoginDialog ld = new LoginDialog(manager);
        EnumD res = ld.showDialog();
        return (res == EnumD.OK);
    }

    public static void main (String[] args) {
        RunnerClass run = new RunnerClass();
        run.run();
    }
}
