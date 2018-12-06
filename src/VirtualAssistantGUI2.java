import javax.swing.JFrame;

public class VirtualAssistantGUI2 {
    public static void main(String[] args) {
        JFrame selectStaff = new SelectStaffFrame();
        JFrame mainMenu = new MainMenuFrame();
        // JFrame frame = new SelectAppointmentFrame();
        // JFrame frame = new EnterInfoForm();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}