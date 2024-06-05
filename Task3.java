import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task3 extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2;
    JButton b1, b2;
    JCheckBox partyA, partyB, partyC;

    Task3() {
        f = new JFrame("Election voting");

        l1 = new JLabel("Online Voting System");
        l1.setBounds(150, 20, 200, 30);
        l2 = new JLabel("ENTER NAME:");
        l2.setBounds(100, 70, 100, 30);
        t1 = new JTextField();
        t1.setBounds(200, 70, 200, 30);
        l3 = new JLabel("ENTER NUMBER:");
        l3.setBounds(100, 120, 100, 30);
        t2 = new JTextField();
        t2.setBounds(200, 120, 200, 30);

        l4 = new JLabel("CAST YOUR VOTE HERE:");
        l4.setBounds(100, 170, 200, 30);

        partyA = new JCheckBox("Party A");
        partyA.setBounds(100, 210, 100, 30);
        partyB = new JCheckBox("Party B");
        partyB.setBounds(100, 250, 100, 30);
        partyC = new JCheckBox("Party C");
        partyC.setBounds(100, 290, 100, 30);

        b1 = new JButton("SUBMIT YOUR VOTE");
        b1.setBounds(200, 200, 200, 30);
        b1.addActionListener(this);
        b2 = new JButton("CHECK RESULTS");
        b2.setBounds(200, 280, 200, 30);
        b2.addActionListener(this);

        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(l3);
        f.add(t2);
        f.add(l4);
        f.add(partyA);
        f.add(partyB);
        f.add(partyC);
        f.add(b1);
        f.add(b2);

        f.setSize(600, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String name = t1.getText();
            String number = t2.getText();
            String vote = "";

            if (partyA.isSelected()) {
                vote = "Party A";
            } else if (partyB.isSelected()) {
                vote = "Party B";
            } else if (partyC.isSelected()) {
                vote = "Party C";
            }

            saveVote(name, number, vote);

            JOptionPane.showMessageDialog(f, "Name: " + name + "\nNumber: " + number + "\nVote: " + vote);
        } else if (e.getSource() == b2) {
            // Placeholder for checking results
            JOptionPane.showMessageDialog(f, "Results functionality not implemented yet.");
        }
    }

    private void saveVote(String name, String number, String vote) {
    

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votes","root","root")) {
            if (conn != null) {
                String sql = "INSERT INTO votes(name, number, vote) VALUES(?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, number);
                pstmt.setString(3, vote);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Task3();
    }
}

