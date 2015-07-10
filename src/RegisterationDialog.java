import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class RegisterationDialog extends JDialog
{
	JFrame owner;
	JPanel mainPane = new JPanel();
	int testId;

	LabeledTextField firstName = new LabeledTextField(" \u0646\u0627\u0645:", 5, 47, 18);
	LabeledTextField lastName = new LabeledTextField(" \u0646\u0627\u0645 \u062e\u0627\u0646\u0648\u0627\u062f\u06af\u06cc:", 5, 0, 18);
	LabeledTextField age = new LabeledTextField(" \u0633\u0646:", 5, 55, 6);
	LabeledTextField graduate = new LabeledTextField(" \u0645\u062f\u0631\u06a9 \u062a\u062d\u0635\u064a\u0644\u06cc:", 5, 0, 6);
	JComboBox genderComboBox = new JComboBox();
	JComboBox handComboBox = new JComboBox();

	class AddPersonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				Connection c = DBServices.getConnection();
				Statement s = c.createStatement();

				ResultSet r = s.executeQuery("SELECT Max(ID) AS MaxID FROM Person");
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

				s.executeUpdate("INSERT INTO Person VALUES(" + id + ", '" +
					firstName.getText() + "', '" +
					lastName.getText() + "', " +
					age.getText() + ", '" +
					genderComboBox.getSelectedItem() + "', '" +
					graduate.getText() + "', '" +
					handComboBox.getSelectedItem() + "')"
					);

				s.close();

				setVisible(false);

				((MainFrame)owner).startTest(id, testId);

			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
					JOptionPane.INFORMATION_MESSAGE);

				//System.out.println(exp.getMessage());
			}
		}
	}

	RegisterationDialog(JFrame theOwner, int id)
	{
		super(theOwner, "\u0641\u0631\u0645 \u062b\u0628\u062a \u0646\u0627\u0645",true);
		owner = theOwner;
		testId = id;

		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

		mainPane.add(firstName);
		mainPane.add(lastName);

		JPanel ageAndSexPane = new JPanel();
        JLabel space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		ageAndSexPane.add(space);


        genderComboBox.addItem("\u0645\u0631\u062f");
		genderComboBox.addItem("\u0632\u0646");
        genderComboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ageAndSexPane.add(genderComboBox);
        JLabel label = new JLabel(" \u062c\u0646\u0633\u064a\u062a:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
        ageAndSexPane.add(label);
        ageAndSexPane.add(age);

		/*space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,7));
		ageAndSexPane.add(space);*/

		mainPane.add(ageAndSexPane);

		JPanel graduateAndHandPane = new JPanel();
        space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		graduateAndHandPane.add(space);

        handComboBox.addItem("\u0631\u0627\u0633\u062a");
		handComboBox.addItem("\u0686\u067e");
		handComboBox.addItem("\u0647\u0631 \u062f\u0648");
        handComboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		graduateAndHandPane.add(handComboBox);
        label = new JLabel(" \u062f\u0633\u062a:");
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
        graduateAndHandPane.add(label);
        graduateAndHandPane.add(graduate);

		/*space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,6));
		graduateAndHandPane.add(space);*/
		mainPane.add(graduateAndHandPane);

		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton OK = new JButton("\u062a\u0627\u064a\u064a\u062f");
        OK.setFont(new Font("Times New Roman", Font.BOLD, 15));
        space = new JLabel();
        space.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        JButton cancel = new JButton("\u0627\u0646\u0635\u0631\u0627\u0641");
        cancel.setFont(new Font("Times New Roman", Font.BOLD, 15));

		OK.addActionListener(new AddPersonActionListener());
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

    public static void main(String[] args)
    {
        (new RegisterationDialog(null, 0)).setVisible(true);
    }
}

class LabeledTextField extends JPanel
{
	JTextField textField = new JTextField(15);

    LabeledTextField(String l)
	{
		JLabel label = new JLabel(l);

		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		add(textField);
        add(label);
	}

	LabeledTextField(String l, int borderSize, int distance, int textFieldSize)
	{
		setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));

		JLabel label = new JLabel(l);
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));

		textField.setColumns(textFieldSize);
		add(textField);
        JLabel space = new JLabel();
        space.setBorder(BorderFactory.createEmptyBorder(0,0,0,distance));
        add(space);
		add(label);
	}

	public String getText()
	{
		return textField.getText();
	}
}
