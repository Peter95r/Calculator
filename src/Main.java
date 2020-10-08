import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(()->
        {
            JFrame f = new JFrame();
            JPanel p = new CalculatorPanel();
            Container c = f.getContentPane();
            c.add(p);
            f.setTitle("Kalkulator");
            f.setLocationByPlatform(true);
            f.setSize(600,300);
            f.setVisible(true);

        });

    }
}
class CalculatorPanel extends JPanel
{
    private JPanel panel;
    private JPanel gora;
    private JTextField display;
    private JLabel label;
    private double results;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel()
    {
        setLayout(new BorderLayout());

        results = 0;
        lastCommand = "=";
        start = true;

        gora = new JPanel();
        add(gora,BorderLayout.NORTH);
        gora.setLayout(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("Wynik");
        gora.add(label);
        display = new JTextField("0",53);
        display.setEnabled(false);
        gora.add(display);



        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        add(panel);

        addButton("7",insert);
        addButton("8",insert);
        addButton("9",insert);
        addButton("/",command);

        addButton("4",insert);
        addButton("5",insert);
        addButton("6",insert);
        addButton("*",command);

        addButton("1",insert);
        addButton("2",insert);
        addButton("3",insert);
        addButton("-",command);

        addButton("0",insert);
        addButton(".",insert);
        addButton("=",command);
        addButton("+",command);

    }

    public void addButton(String name, ActionListener action)
    {
        JButton button = new JButton(name);
        button.addActionListener(action);
        panel.add(button);
    }

    class InsertAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
          String input = e.getActionCommand();
          if (start)
          {
              display.setText(" ");
              start=false;
          }
          display.setText(display.getText()+ input);
        }
    }
    class CommandAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (start)
            {
                if (command.equals("-"))
                {
                    display.setText(command);
                    start = false;
                }
                else lastCommand = command;
            }
            else
            {
                calculate(Double.parseDouble(display.getText()));
                lastCommand=command;
                start=true;
            }
        }
    }

    public void calculate(double x)
    {
        if (lastCommand.equals("+")) results+=x;
        else if (lastCommand.equals("-")) results-=x;
        else if (lastCommand.equals("*")) results*=x;
        else if (lastCommand.equals("/")) results/=x;
        else if (lastCommand.equals("=")) results=x;
        display.setText(" "+results);
    }
}

