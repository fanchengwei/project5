import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * CS 180 spring 2021
 * Project 5
 * @author Pengfei hu
 * @version April 22,2021
 */

public class TestMian extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private List<Person> person = new ArrayList<Person>();
	private String[] columnNames = { "Serial number", "name", "age" };
	private Person seletd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestMian frame = new TestMian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestMian() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Serial number");
		label.setBounds(14, 13, 43, 18);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(56, 10, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("name");
		label_1.setBounds(171, 13, 35, 18);
		contentPane.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(220, 10, 86, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("age");
		label_2.setBounds(14, 47, 43, 18);
		contentPane.add(label_2);

		textField_2 = new JTextField();
		textField_2.setBounds(56, 44, 86, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton button = new JButton("add");
		button.setBounds(320, 9, 86, 27);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				add();
			}
		});
		contentPane.add(button);

		JButton button_1 = new JButton("delete");
		button_1.setBounds(420, 9, 80, 27);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				System.out.println(row);
				int a = table.getSelectedRows().length;
				System.out.println(a);
				if (row != -1) {
					for (int i = 0; i < a; i++) {

						person.remove(row);

					}
				}
				getinfo();
			}
		});
		contentPane.add(button_1);

		JButton button_2 = new JButton("Update");
		button_2.setBounds(171, 44, 70, 27);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});
		contentPane.add(button_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 78, 549, 468);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				seletd = person.get(row);
				if (row != -1) {
					textField.setText(seletd.getFid());
					textField_1.setText(seletd.getFname());
					textField_2.setText(seletd.getFage() + "");
					textField.setEnabled(false);
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setViewportView(table);
		scrollPane.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.setEnabled(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {

				int i = JOptionPane.showConfirmDialog(null, "Do you want to save the data?");
				switch (i) {
				case 0:
					OutputStream os = null;
					ObjectOutputStream oos = null;
					try {
						os = new FileOutputStream("an.data");
						oos = new ObjectOutputStream(os);
						for (Person pp : person) {
							oos.writeObject(pp);
						}
					} catch (Exception e1) {

						e1.printStackTrace();
					} finally {
						try {
							oos.close();
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						try {
							os.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					System.exit(0);
					break;
				case 1:
					System.exit(0);
					break;
				case 2:

					break;

				default:
					break;
				}

			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		InputStream is = null;
		ObjectInputStream ois = null;

		try {
			is = new FileInputStream("an.data");
			ois = new ObjectInputStream(is);
			while (true) {
				Person p = (Person) ois.readObject();
				person.add(p);
			}
		} catch (EOFException e1) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		getinfo();
	}

	public void modify() {
		String aid = textField.getText();
		String naem = textField_1.getText();
		String age = textField_2.getText();
		seletd.setFid(aid);
		seletd.setFname(naem);
		seletd.setFage(Integer.parseInt(age));
		getinfo();
	}

	public void add() {
		String aid = textField.getText();
		String naem = textField_1.getText();
		String age = textField_2.getText();
		Person p = new Person(aid, naem, Integer.parseInt(age));
		person.add(p);
		getinfo();
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
	}

	public void getinfo() {
		System.out.println(person);
		Object[][] tableValues = new Object[person.size()][];
		for (int i = 0; i < person.size(); i++) {
			Person p1 = person.get(i);
			System.out.println(p1);
			Object[] ff = { p1.getFid(), p1.getFname(), p1.getFage() };
			System.out.println(Arrays.toString(ff));
			tableValues[i] = ff;
		}
		table.setModel(new DefaultTableModel(tableValues, columnNames));
	}
}
