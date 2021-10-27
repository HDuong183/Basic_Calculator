
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//import calculator.InfixToPostfix;

public class Calculator extends JFrame implements ActionListener {

    private JMenuBar mBar;
    private JMenu mMore;
    private JMenuItem itmExit, itmAbout;
    private String btnName[] ={"(", ")", "←", "÷",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "AC", "0", ".", "="};
    private JPanel plDisplay, plButton, plMain;
    private JButton btn;
    private JTextArea txtAreaDisplay;
    private String infix;


    public Calculator(){
        init();
        itmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        itmAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "This is a calculator.\nVersion 1.1.0", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //Panel container textFielt
    private JPanel createPanelTxtArea(){
        plDisplay = new JPanel(new GridLayout(1, 1));
        txtAreaDisplay = new JTextArea(4, 3);
        txtAreaDisplay.setEditable(false);
        txtAreaDisplay.setFont(new Font("Courier New", Font.BOLD, 22));
        txtAreaDisplay.setLineWrap(true);
        txtAreaDisplay.setWrapStyleWord(true);
        plDisplay.add(txtAreaDisplay);

        return plDisplay;
    }


    // Panel container Button
    private JPanel createPanelBtn(){
        plButton = new JPanel(new GridLayout(5, 4));
        for(int i=0; i<btnName.length; i++){
            btn = new JButton(btnName[i]);
            btn.setBackground(Color.LIGHT_GRAY);
            if(btnName[i].equals("="))
                btn.setBackground(Color.RED);
            if(btnName[i]=="0" || btnName[i]=="1" ||btnName[i]=="2" || btnName[i]=="3" ||
                    btnName[i]=="4" || btnName[i]=="5" || btnName[i]=="6" || btnName[i]=="7"
                    || btnName[i]=="8" || btnName[i]=="9")
                btn.setBackground(Color.GRAY);
            btn.setFont(new Font("Courier New", Font.BOLD, 22));
            btn.addActionListener(this);
            plButton.add(btn);
        }
        return plButton;
    }

    //Panel main container btn and text
    public JPanel createPanelMain(){
        plMain = new JPanel(new BorderLayout(4, 5));
        plMain.add(createPanelBtn(), BorderLayout.CENTER);
        plMain.add(createPanelTxtArea(), BorderLayout.NORTH);
        return plMain;

    }

    public void init(){

        mBar = new JMenuBar();
        mMore = new JMenu("☰");

        ImageIcon iconExit = new ImageIcon("images/exit.jpg");
        ImageIcon iconAbout = new ImageIcon("images/about.jpg");
        Image iconF = Toolkit.getDefaultToolkit().getImage("images/icon.jpg");

        itmAbout = new JMenuItem("About" ,iconAbout);
        itmExit = new JMenuItem("Exit", iconExit);

        mMore.add(itmAbout);
        mMore.add(itmExit);
        mBar.add(mMore);

        setJMenuBar(mBar);
        setIconImage(iconF);
        setTitle("Calculator");
        setSize(300,420);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(createPanelMain());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="1")
            txtAreaDisplay.append("1");
        if(e.getActionCommand()=="2")
            txtAreaDisplay.append("2");
        if(e.getActionCommand()=="3")
            txtAreaDisplay.append("3");
        if(e.getActionCommand()=="4")
            txtAreaDisplay.append("4");
        if(e.getActionCommand()=="5")
            txtAreaDisplay.append("5");
        if(e.getActionCommand()=="6")
            txtAreaDisplay.append("6");
        if(e.getActionCommand()=="7")
            txtAreaDisplay.append("7");
        if(e.getActionCommand()=="8")
            txtAreaDisplay.append("8");
        if(e.getActionCommand()=="9")
            txtAreaDisplay.append("9");
        if(e.getActionCommand()=="0")
            txtAreaDisplay.append("0");
        if(e.getActionCommand()==".")
            txtAreaDisplay.append(".");
        if(e.getActionCommand()=="+")
            txtAreaDisplay.append("+");
        if(e.getActionCommand()=="-")
            txtAreaDisplay.append("-");
        if(e.getActionCommand()=="(")
            txtAreaDisplay.append("(");
        if(e.getActionCommand()==")")
            txtAreaDisplay.append(")");
        if(e.getActionCommand()=="x")
            txtAreaDisplay.append("*");
        if(e.getActionCommand()=="÷")
            txtAreaDisplay.append("/");

        //delete
        if(e.getActionCommand().equals("←")){
            String str = txtAreaDisplay.getText();
            if(str.length() != 0)
                txtAreaDisplay.setText(str.substring(0, str.length()-1));
        }

        //delete all
        if(e.getActionCommand().equals("AC")){
            txtAreaDisplay.setText("");
        }

        // result
        if (e.getActionCommand().equals("=")) {
            infix = txtAreaDisplay.getText();
            InfixToPostfix calculator = new InfixToPostfix();
            double result = calculator.Result(infix);
            txtAreaDisplay.setText(Double.toString(result));

        }

    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }
}
