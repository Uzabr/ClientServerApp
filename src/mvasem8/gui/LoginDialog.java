package mvasem8.gui;

import mvasem8.com.DbConnection;
import mvasem8.com.JRDialog;
import mvasem8.data.EnumD;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoginDialog extends JRDialog {

    private JTextField logintext;
    private JPasswordField passwordField;
    private JButton buttonOk;
    private JButton buttonCancel;
    private Connection con;
    private DbConnection manager;
    private String  url;

    public LoginDialog (DbConnection manager) {
        this.manager = manager;
        createGui();
        setTitle("Соединение с БД");
        FileInputStream frop;
        Properties prop = new Properties();
        try{
            frop = new FileInputStream("/Users/user/IdeaProjects/MVA/src/mvasem8/data/urlProp");
            prop.load(frop);
            url = prop.getProperty("URL_DB");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error file not found");
        }

    }

    private void createGui () {
        MigLayout layout =  new MigLayout("insets 12, gapy 5",
                "[]12[grow, fill][]" , "");
        JPanel panel = new JPanel(layout);
        panel.add(new JLabel("Логин"));
        logintext = new JTextField(15);
        panel.add(logintext, "span");
        panel.add(new JLabel("Пароль"));
        passwordField = new JPasswordField(15);
        panel.add(passwordField, "span");
        buttonOk = new JButton("Войти");
        buttonCancel = new JButton("Отмена");
        panel.add(buttonOk, "gaptop 8, span, split 2, right");
        panel.add(buttonCancel);
        getContentPane().add(panel);
        getRootPane().setDefaultButton(buttonOk);
        pack();
        setResizable(false);
        bindListener();

    }

    private void bindListener () {
        buttonCancel.addActionListener(ActionEvent -> {
            setDialog(EnumD.Cancel);
            close();
        });
        buttonOk.addActionListener(ActionEvent -> {
            if(authenticate()){
                setDialog(EnumD.OK);
                close();
            }
        });
    }

    private boolean authenticate () {
        String login = null;
        char[] pss = null;
        try {
            login = logintext.getText().trim();
            pss = passwordField.getPassword();
            if (login.length() == 0 || pss.length == 0) {
                throw new Exception("Логин и/или пароль не указаны.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Ошибка ввода данных", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(null);
            return false;

        }
        try {
            String pwd = new String(pss);
            con = connect(login, pwd);
            if (con == null)
                throw new Exception("Логин и/или пароль указаны не верно");
            manager.setConn(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Ошибка входа в систему", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(null);
            return false;

        }
        return true;
    }

    private Connection connect (String login, String pwd) {
        try{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

            }catch (Exception e){
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, login, pwd);
            con.setAutoCommit(false);
            return con;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    }
