import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class NewTestDialog extends JDialog
{
	JFrame owner;
	JPanel mainPane = new JPanel();

	LabeledTextField testName = new LabeledTextField(" \u0646\u0627\u0645 \u0622\u0632\u0645\u0648\u0646:", 7, 7, 8);
	JComboBox versionComboBox = new JComboBox();

	class AddTestActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				Connection c = DBServices.getConnection();
				Statement s = c.createStatement();

				ResultSet r = s.executeQuery("SELECT Max(ID) AS MaxID FROM BARTest");
				int id = 1;
				if (r.next()) id = r.getInt("MaxID") + 1;


				/*System.out.println("INSERT INTO Person VALUES(" + id + ", '" +
					firstName.getText() + "', '" +
					lastName.getText() + "', " +
					age.getText() + ", '" +
					genderComboBox.getSelectedItem() + "', '" +
					graduate.getText() + "', '" +
					handComboBox.getSelectedItem() + "')"
					);
				*/

				s.executeUpdate("INSERT INTO BARTest VALUES(" + id + ", '" +
					testName.getText() + "', '" +
					versionComboBox.getSelectedItem() + "', " +
					generateBalloons(128) + ")"
					);

				s.close();

				setVisible(false);

				(new RegisterationDialog(owner, id)).show();
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
					JOptionPane.INFORMATION_MESSAGE);

				//System.out.println(exp.getMessage());
			}
		}
	}

	NewTestDialog(JFrame theOwner)
	{
		super(theOwner, "\u0622\u0632\u0645\u0648\u0646 \u062c\u062f\u064a\u062f",true);
		owner = theOwner;

		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

		mainPane.add(testName);

		JPanel versionPane = new JPanel();

		versionComboBox.addItem("\u0627\u0633\u062a\u0627\u0646\u062f\u0627\u0631\u062f");
        versionComboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));

        JLabel space = new JLabel();
        space.setBorder(BorderFactory.createEmptyBorder(0,0,0,24));
        versionPane.add(space);

		versionPane.add(versionComboBox);
        JLabel label = new JLabel("\u0646\u0633\u062e\u0647:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label.setBorder(BorderFactory.createEmptyBorder(0,5,5,24));

		space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,32));
		versionPane.add(space);
        versionPane.add(label);

		mainPane.add(versionPane);

		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(12,35,12,35));

		JButton OK = new JButton("\u062a\u0627\u064a\u064a\u062f");
        OK.setFont(new Font("Times New Roman", Font.BOLD, 15));
		space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		JButton cancel = new JButton("\u0627\u0646\u0635\u0631\u0627\u0641");
        cancel.setFont(new Font("Times New Roman", Font.BOLD, 15));

		OK.addActionListener(new AddTestActionListener());
        cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		});

		buttonsContainer.add(cancel);
		buttonsContainer.add(space);
		buttonsContainer.add(OK);

        mainPane.add(buttonsContainer);

		setContentPane(mainPane);
		pack();
		setLocationRelativeTo(owner);
	}

	private int generateBalloons(int weight) throws Exception
	{
		int[] balloons = new int[30];
		int sum = 0;

		for (int b = 0; b < 3; b++)
		{
			sum = 0;
			while ( sum != weight * 5)
			{
				sum = 0;
				for (int i = 0; i < 10; i++)
				{
					balloons[b * 10 + i] = (int)(weight * Math.random()) + 1;
					sum += balloons[b * 10 + i];
				}
			}
		}

		/*for (int i = 0; i < 30; i++)
			System.out.print(balloons[i] + " ");

		System.out.println("Success.");*/

		Connection c = DBServices.getConnection();
		Statement s = c.createStatement();

		ResultSet r = s.executeQuery("SELECT Max(ID) AS MaxID FROM 30Balloons");
		int id = 1;
		if (r.next()) id = r.getInt("MaxID") + 1;

		String query = "INSERT INTO 30Balloons VALUES(" + id;
		for (int i = 0; i < 30; i++)
			query += ", No, " + balloons[i];
		query += ")";


		//String query = "INSERT INTO Test VALUES(No)";
		s.executeUpdate(query);

		return id;
	}
}
