package mvasem8.gui;

import mvasem8.com.ClientTable;
import mvasem8.com.DbConnection;
import mvasem8.com.ShowInTextArea;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class MainWindow extends JFrame {
    private DbConnection manager;
    private JMenuBar mainMenu;
    private JMenuItem exitMenu;
    private JMenu spravMenu;
    private JMenuItem clientMenu;
    private JMenuItem clientTableMenu;


    public MainWindow (DbConnection manager) throws HeadlessException {
        this.manager = manager;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testCon();
        createGui();
        bindListener();
        pack();
        setTitle("Хаитбобоев А.М, МВА-16");
        setLocationRelativeTo(null);

    }


    private void bindListener () {
        exitMenu.addActionListener(ActionEvent -> System.exit(0));
        clientMenu.addActionListener(ActionEvent -> showSprav());
        clientTableMenu.addActionListener(ActionEvent -> showClTable());
    }

    protected void showSprav () {
        ShowInTextArea area = new ShowInTextArea(manager);
        area.setVisible(true);
    }

    private void createGui () {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(new ImageIcon("src/mvasem8/images/iconBackground.png"));
        setJMenuBar(createMenu());
        panel.add(label);
        getContentPane().setLayout(new MigLayout("insets 0 2 0 2, gapy 0",
                "[grow, fill]", "[grow, fill]"));
        getContentPane().add(panel, "grow");
    }


    private void testCon () {
        String ver = manager.getVersion();
        System.out.println(ver);
    }
    public void initialize(){
        Locale.setDefault(new Locale("ru"));
    }

    private JMenuBar createMenu(){
        mainMenu = new JMenuBar();
        clientMenu = new JMenuItem("Список Клиентов в JTextArea ");
        clientTableMenu = new JMenuItem("Список Клиентов в JTable");
        spravMenu = new JMenu("Справочники");
        exitMenu = new JMenuItem("Выход");
        spravMenu.add(clientMenu);
        spravMenu.add(clientTableMenu);
        mainMenu.add(spravMenu);
        mainMenu.add(exitMenu);
        return mainMenu;
    }
    protected void showClTable(){
        ClientTable cl = new ClientTable(manager);
        cl.setVisible(true);
    }
}
