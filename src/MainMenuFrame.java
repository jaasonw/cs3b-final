import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MainMenuFrame extends JFrame {
    JPanel panel;
    JPanel bottomPanel;
    JTextField text;
    RectanglesComponent rectangles;

    class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int n = Integer.parseInt(text.getText());
            if (n <= 0)
                JOptionPane.showMessageDialog(new JFrame(), "need at least 1", "title",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                rectangles.append(n);

        }
    }

    public MainMenuFrame() {
        super();
        this.panel = new JPanel(new BorderLayout());
        this.bottomPanel = new JPanel(new GridLayout(0, 1));
        this.text = new JTextField();

        // link bottom panel to main panel
        this.panel.add(this.bottomPanel, BorderLayout.SOUTH);

        // add text field
        this.bottomPanel.add(this.text);

        // add button
        JButton button = new JButton("draw");
        button.addActionListener(new ClickListener());
        this.bottomPanel.add(button);

        // add rectangle drawing thing
        this.rectangles = new RectanglesComponent();
        this.rectangles.repaint();
        this.panel.add(this.rectangles);
        // add
        add(this.panel);
    }
}