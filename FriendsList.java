import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FriendsList extends JComponent {
    String friend;
    String user;
    ArrayList<FriendsList> friendsList;
    public FriendsList(String user, String friend) {
        this.friend = friend;
        this.user = user;
    }
    public FriendsList() {
        friendsList = new ArrayList<FriendsList>();
        if (new File("friendsList.txt").exists() ) {
            try {
                FileReader fr = new FileReader("friendsList.txt");
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                while (line != null && !line.equals("")) {
                    user = line.split(",")[0];
                    friend = line.split(",")[1];
                    FriendsList List = new FriendsList(user, friend);
                    friendsList.add(List);
                    line = bfr.readLine();
                }
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File toWrite = new File("friendsList.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(toWrite));
                StringBuilder sb = new StringBuilder();
                sb.append("user friend");
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
            } catch (IOException n) {
                n.printStackTrace();
            }
        }
    }
    public void usersFriendsList(String usersName) {
        JFrame frame = new JFrame("Your Friend List");
        frame.setSize(450, 600);
        frame.setLocation(400,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0, n = 0 ; i < friendsList.size() ; i++) {
            if (usersName.equals(friendsList.get(i).getUser())) {
                GridLayout obj = new GridLayout(++n/2 + 1, ++n/2 + 1);
                frame.setLayout(obj);
                JButton buttonName = new JButton(friendsList.get(i).getFriend());
                frame.add(buttonName);
                buttonName.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame("Profile");
                        frame.setSize(100, 200);
                        frame.setLocation(400, 0);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
            });
            }
        }
        frame.setVisible(true);
        frame.setResizable(true);
    }
    public void friendsList(String user, String friend) {
        try {
            Files.write(Paths.get("friendsList.txt"), (user
                    + "," + friend + "\n").getBytes(), StandardOpenOption.APPEND); //append text
        } catch (IOException io ) {
            io.printStackTrace();
        }
    }

    public String getFriend() {
        return friend;
    }

    public String getUser() {
        return user;
    }
/**
Innitialize one time amd delete the friendsList.friendsList()
**/
    public static void main(String[] args) {
        FriendsList friendsList = new FriendsList();
        friendsList.friendsList("Daniel","Kevin");
        friendsList.friendsList("Daniel","James");
        friendsList.friendsList("Daniel","Chelsea");
        friendsList.friendsList("Daniel","Amy");
        friendsList.friendsList("Daniel","Wilson");
        friendsList.friendsList("Daniel","Josh");
        friendsList.usersFriendsList("Daniel");
    }
}
