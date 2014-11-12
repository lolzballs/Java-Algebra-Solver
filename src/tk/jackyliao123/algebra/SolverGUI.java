package tk.jackyliao123.algebra;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverGUI {

    private SolverGUI() {
    }

    private static char[] unks = "xyzabcdefghijklmnopqrstuvwXYZABCDEFGHIJKLMNOPQRSTUVWαβγδεζηθικλμ".toCharArray();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("Algebra Solver");

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel linear = new JPanel();
        tabbedPane.addTab("Linear System", null, linear, null);
        linear.setLayout(new BorderLayout(0, 0));

        JPanel unk = new JPanel();
        linear.add(unk, BorderLayout.NORTH);
        unk.setLayout(new BorderLayout(0, 0));

        final JTextArea equationNumber = new JTextArea();
        final JTextField unknownChars = new JTextField();
        unknownChars.setText("x");

        JPanel unknown = new JPanel();
        unk.add(unknown, BorderLayout.WEST);
        unknown.setLayout(new BorderLayout(0, 0));

        JLabel label = new JLabel("Number of unknowns: ");
        unknown.add(label);

        final JSpinner spinner = new JSpinner();
        unknown.add(spinner, BorderLayout.EAST);
        spinner.setModel(new SpinnerNumberModel(1, 1, 64, 1));

        unk.add(unknownChars, BorderLayout.CENTER);
        unknownChars.setToolTipText("Characters for unknown separated by ','");
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int n = (Integer) spinner.getValue();
                if (unknownChars.getText().split(",").length != n) {
                    String s = "";
                    for (int i = 1; i <= n; i++) {
                        s += Integer.toString(i) + "\n";
                    }
                    equationNumber.setText(s);
                    s = "";
                    for (int i = 0; i < n; i++) {
                        s += unks[i] + (i == n - 1 ? "" : ", ");
                    }
                    unknownChars.setText(s);
                }
            }
        });

        final JTextArea equations = new JTextArea();
        equations.setToolTipText("All the equations in the system separated by returns");

        JButton solve = new JButton("Solve");
        linear.add(solve, BorderLayout.SOUTH);
        solve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int unknowns = (Integer) spinner.getValue();
                    String[] ss = unknownChars.getText().split(",");
                    char[] uks = new char[ss.length];
                    for (int i = 0; i < ss.length; i++) {
                        uks[i] = ss[i].trim().charAt(0);
                    }
                    Rational[] r = Algebra.solve(unknowns, uks, equations.getText().split("\n"));
                    String s = "";
                    for (int i = 0; i < r.length; i++) {
                        s += uks[i] + " = " + r[i] + (i != r.length - 1 ? "\n" : "");
                    }
                    JTextArea area = new JTextArea();
                    area.setText(s);
                    area.setEditable(false);
                    JOptionPane.showMessageDialog(null, area, "Solution Found", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex instanceof ArithmeticException ? ex.getLocalizedMessage() : ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JScrollPane scrollPane = new JScrollPane();

        linear.add(scrollPane, BorderLayout.CENTER);

        JPanel equationPanel = new JPanel();
        scrollPane.setViewportView(equationPanel);
        equationPanel.setLayout(new BorderLayout(0, 0));

        equationPanel.add(equationNumber, BorderLayout.WEST);
        equationNumber.setText("1\n");

        equationNumber.setEditable(false);
        equationNumber.setEnabled(false);

        equationPanel.add(equations, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
