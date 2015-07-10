import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class BART
{
	static JFrame mainFrame = new MainFrame();

	public static void main(String [] args)
	{
	    try {
	        UIManager.setLookAndFeel(
				UIManager.getCrossPlatformLookAndFeelClassName()); // Java Look & Feel
				//UIManager.getSystemLookAndFeelClassName()); // System Look & Fill
				//"com.sun.java.swing.plaf.motif.MotifLookAndFeel"); // CDE/Motif Look & Feel
    	} catch (Exception e)
        {
            e.printStackTrace();
        }

		mainFrame.show();
	}
}


class MainFrame extends JFrame
{
	MainFrame()
	{
		super("\u0622\u0632\u0645\u0648\u0646 \u062e\u0637\u0631\u067e\u0630\u064a\u0631\u06cc \u0628\u0627\u062f\u06a9\u0646\u06a9\u064a");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponents();
		setLocation(100, 80);
		pack();
	}

	void addComponents()
	{
		final JFrame mainFrame = this;
		JPanel mainPane = new JPanel();

		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		mainPane.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

		/*JLabel label = new JLabel("In The Name of The Lord");
		label.setAlignmentY((float)0.5);
		mainPane.add(label);*/

		JPanel imageAndLabelContainer = new JPanel();
		imageAndLabelContainer.setLayout(new BoxLayout(imageAndLabelContainer, BoxLayout.X_AXIS));
		//imageAndLabelContainer.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        //imageAndLabelContainer.setBorder(BorderFactory.createLineBorder(Color.gray));
        imageAndLabelContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.white, 2),
                        BorderFactory.createLineBorder(Color.gray, 1)),
                BorderFactory.createEmptyBorder(8,0,5,0)));

        JLabel imageLabel = new JLabel(new ImageIcon("images/logo.jpg"));
        imageLabel.setBorder(BorderFactory.createEmptyBorder( 0, 15, 0, 20));
        imageAndLabelContainer.add(imageLabel);

		//imageAndLabelContainer.add(new JLabel(new ImageIcon("images/logo.jpg")));

		JPanel labelsContainer = new JPanel();
		labelsContainer.setLayout(new BoxLayout(labelsContainer, BoxLayout.Y_AXIS));
		labelsContainer.setBorder(BorderFactory.createEmptyBorder(0,15,10,10));

        String fontName = "Times New Roman";

		JLabel label = new JLabel("  \u0628\u0647 \u0646\u0627\u0645 \u062e\u062f\u0627        ");
		label.setFont(new Font(fontName, Font.BOLD, 22));
		labelsContainer.add(label);

		labelsContainer.add(new JLabel(" "));

        // Cognitive Science Studies Institute
        //label = new JLabel("  \u0645\u0648\u0633\u0633\u0647 \u0645\u0637\u0627\u0644\u0639\u0627\u062a \u0639\u0644\u0648\u0645 \u0634\u0646\u0627\u062e\u062a\u06cc                                 ");

        label = new JLabel("  \u0622\u0632\u0645\u0648\u0646 \u062e\u0637\u0631\u067e\u0630\u064a\u0631\u06cc \u0628\u0627\u062f\u06a9\u0646\u06a9\u06cc                          ");
        label.setFont(new Font(fontName, Font.BOLD, 20));
        labelsContainer.add(label);

        label = new JLabel("Balloon Analogue Risk Task (BART)");
        label.setFont(new Font(fontName, Font.BOLD, 17));
        labelsContainer.add(label);

        labelsContainer.add(new JLabel(" "));

        label = new JLabel("Designed by");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelsContainer.add(label);
		//labelsContainer.add(new JLabel("Original Version Designed by"));

        label = new JLabel("             C. W. Lejuez");
        label.setFont(new Font(fontName, Font.BOLD, 14));
        labelsContainer.add(label);

        label = new JLabel("             Department of Psychology");
        label.setFont(new Font(fontName, Font.BOLD, 14));
        labelsContainer.add(label);

        label = new JLabel("             University of Maryland");
        label.setFont(new Font(fontName, Font.BOLD, 14));
        labelsContainer.add(label);

        labelsContainer.add(new JLabel(" "));

        label = new JLabel(" \u0637\u0631\u0627\u062d\u0627\u0646 \u0646\u0633\u062e\u0647 \u0641\u0627\u0631\u0633\u06cc                              ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelsContainer.add(label);
        //labelsContainer.add(new JLabel("\u0637\u0631\u0627\u062d\u0627\u0646 \u0646\u0633\u062e\u0647 \u0641\u0627\u0631\u0633\u06cc \u062d\u0627\u0645\u062f \u0627\u062e\u062a\u064a\u0627\u0631\u06cc \u0648 \u062f\u064a\u06af\u0631\u0627\u0646"));

        label = new JLabel(" \u062d\u0627\u0645\u062f \u0627\u062e\u062a\u064a\u0627\u0631\u06cc \u0648 \u0639\u0644\u06cc \u062c\u0646\u062a\u06cc           ");
        label.setFont(new Font(fontName, Font.BOLD, 16));
        labelsContainer.add(label);
        //labelsContainer.add(new JLabel("\u0637\u0631\u0627\u062d\u0627\u0646 \u0646\u0633\u062e\u0647 \u0641\u0627\u0631\u0633\u06cc \u062d\u0627\u0645\u062f \u0627\u062e\u062a\u064a\u0627\u0631\u06cc \u0648 \u062f\u064a\u06af\u0631\u0627\u0646"));

        label = new JLabel(" \u062f\u0627\u0646\u0634\u06af\u0627\u0647 \u0639\u0644\u0648\u0645 \u067e\u0632\u0634\u0643\u064a \u062a\u0647\u0631\u0627\u0646         ");
        label.setFont(new Font(fontName, Font.BOLD, 16));
        labelsContainer.add(label);

        labelsContainer.add(new JLabel(" "));

        label = new JLabel("\u0628\u0631\u0646\u0627\u0645\u0647 \u0633\u0627\u0632\u0627\u0646 \u06a9\u0627\u0645\u067e\u064a\u0648\u062a\u0631\u06cc                             ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelsContainer.add(label);
        //labelsContainer.add(new JLabel("\u0628\u0631\u0646\u0627\u0645\u0647 \u0633\u0627\u0632\u0627\u0646 \u06a9\u0627\u0645\u067e\u064a\u0648\u062a\u0631\u06cc \u0627\u0645\u064a\u0631 \u0645\u0642\u064a\u0645\u06cc \u0648 \u0645\u062d\u0633\u0646 \u0644\u0633\u0627\u0646\u06cc"));

        label = new JLabel("\u0627\u0645\u064a\u0631 \u0645\u0642\u064a\u0645\u06cc \u0648 \u0645\u062d\u0633\u0646 \u0644\u0633\u0627\u0646\u06cc          ");
        label.setFont(new Font(fontName, Font.BOLD, 16));
        labelsContainer.add(label);
        //labelsContainer.add(new JLabel("\u0628\u0631\u0646\u0627\u0645\u0647 \u0633\u0627\u0632\u0627\u0646 \u06a9\u0627\u0645\u067e\u064a\u0648\u062a\u0631\u06cc \u0627\u0645\u064a\u0631 \u0645\u0642\u064a\u0645\u06cc \u0648 \u0645\u062d\u0633\u0646 \u0644\u0633\u0627\u0646\u06cc"));

        label = new JLabel("  \u062f\u0627\u0646\u0634\u0643\u062f\u0647 \u0641\u0646\u06cc \u062f\u0627\u0646\u0634\u06af\u0627\u0647 \u062a\u0647\u0631\u0627\u0646          ");
        label.setFont(new Font(fontName, Font.BOLD, 16));
        labelsContainer.add(label);

        imageAndLabelContainer.add(labelsContainer);

		mainPane.add(imageAndLabelContainer);
        mainPane.add(new JLabel(" "));

		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton continueTest = new JButton("\u0627\u062f\u0627\u0645\u0647 \u0622\u0632\u0645\u0648\u0646 \u0642\u062f\u064a\u0645\u06cc");
		//JButton continueTest = new JButton("\u0627\u062f\u0627\u0645\u0647 \u0622\u0632\u0645\u0648\u0646 \u0642\u062f\u064a\u0645\u06cc");
        continueTest.setFont(new Font(fontName, Font.BOLD, 15));
		JLabel space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		JButton startNewTest = new JButton("\u0634\u0631\u0648\u0639 \u0622\u0632\u0645\u0648\u0646 \u062c\u062f\u064a\u062f");
        //JButton startNewTest = new JButton("\u0634\u0631\u0648\u0639 \u0622\u0632\u0645\u0648\u0646 \u062c\u062f\u064a\u062f");
        startNewTest.setFont(new Font(fontName, Font.BOLD, 15));

		startNewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				(new NewTestDialog(mainFrame)).show();
			}
		});

		continueTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Connection c = DBServices.getConnection();
					Statement s = c.createStatement();

					//Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					//	ResultSet.CONCUR_READ_ONLY);


					ResultSet r = s.executeQuery("SELECT COUNT(ID) AS Count FROM BARTest");

					int size = 0;
					if (r.next()) size = r.getInt("Count");

					/*int size = 0;
					while (r.next())
						size++;
					r.beforeFirst();*/

					if (size == 0) throw new Exception("\u0647\u064a\u0686 \u0622\u0632\u0645\u0648\u0646 \u0642\u062f\u064a\u0645\u06cc \u0648\u062c\u0648\u062f \u0646\u062f\u0627\u0631\u062f.");

					r = s.executeQuery("SELECT Name FROM BARTest ORDER BY Name");

					String [] options = new String[size];
					int i = 0;
					while (r.next())
						options[i++] = r.getString("Name");

					String chosenTest = (String)JOptionPane.showInputDialog(mainFrame,
                        "\u064a\u06a9\u06cc \u0627\u0632 \u0622\u0632\u0645\u0648\u0646\u0647\u0627\u06cc \u0642\u062f\u064a\u0645\u06cc \u0631\u0627 \u0627\u0646\u062a\u062e\u0627\u0628 \u06a9\u0646\u064a\u062f",
                        "\u0627\u062f\u0627\u0645\u0647 \u0622\u0632\u0645\u0648\u0646 \u0642\u062f\u064a\u0645\u06cc",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (chosenTest != null)
					{
						r = s.executeQuery("SELECT ID FROM BARTest WHERE Name = '" + chosenTest + "'");
						if (r.next())
							(new RegisterationDialog(mainFrame, r.getInt("ID"))).show();
					}

					s.close();

				}
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		buttonsContainer.add(continueTest);
		buttonsContainer.add(space);
		buttonsContainer.add(startNewTest);

		mainPane.add(buttonsContainer);

		setContentPane(mainPane);
	}

	public void startTest(final int personId, final int testId)
	{
		JPanel mainPane = new JPanel();
		//JPanel topPanel = new JPanel();
		//JPanel bottomPanel = new JPanel();
		//topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

		/*JPanel instructionsPanel = new JPanel();
		//instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
		instructionsPanel.setBorder(BorderFactory.createEmptyBorder(30,60,30,40));

        String introductionString = "";
        try
        {
            BufferedReader introductionFile
               = new BufferedReader(new FileReader("texts/introduction.txt"));
            introductionString = introductionFile.readLine();
            //introductionString = FarsiTools.readUnicodeFarsiStringFromFile("introduction.txt");
            //System.out.println(introductionString);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("FileNotFound!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("IO!");
            e.printStackTrace();
        }

        /*JTextArea instructions = new JTextArea("Throughout the task, you will be presented with 90 balloons, one at a " +
			"time. For each balloon you can click on a button labeled \"Press This Button to Pump Up the Balloon\" " +
			"to increase the size of the balloon. You will accumulate 5 cents in a temporary bank for each pump. " +
			"You will not be shown the amount you have accumulated in your temporary bank. At any point, you can stop " +
			"pumping up the balloon and click on the button labeled \"Collect $$$.\" Clicking this button will start you " +
			"on the next balloon and will transfer the accumulated money from your temporary bank to your permanent bank " +
			"labeled \"Total Earned.\" The amount you earned on the previous is shown in the box labeled \"Last Balloon.\" " +
			"It is your choice to determine how much to pump up the balloon, but be aware that at some point the balloon " +
			"will explode. The explosion point varies across balloons, ranging from the first pump to enough pumps to make " +
			"the balloon fill the entire computer screen. If the balloon explodes before you click on \"Collect $$$,\" " +
			"the you move on to the next balloon and all money in your temporary bank is lost. Exploded balloons do not " +
			"affect the money accumulated in your permanent bank. At the end of the task, you will receive gift certificates " +
			"in the amount earned in your permanent bank.",
			25, 27);*
        JTextArea instructions = new JTextArea(introductionString,35, 27);
		instructions.setFont(new Font("Times New Roman", Font.BOLD, 15));
        instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setEditable(false);
		instructions.setBorder(BorderFactory.createLoweredBevelBorder());
		//instructions.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, new ImageIcon("images/Bubbles.JPG")));

		instructionsPanel.add(instructions);
		//instructionsPanel.setAlignmentX(TOP_ALIGNMENT);

		topPanel.add(instructionsPanel);*/

		int[] exampleBalloons = {(int)(128 * Math.random() + 1),
            (int)(128 * Math.random() + 1),
            (int)(128 * Math.random() + 1)};

		JPanel examplePanel = new JPanel();
		examplePanel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

		BalloonPanel exampleBalloonPanel = new BalloonPanel(exampleBalloons /*personId, testId*/, 740, 470);
        //exampleBalloonPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		exampleBalloonPanel.setBorder(BorderFactory.createTitledBorder(" \u0645\u062b\u0627\u0644 "));
		//exampleBalloonPanel.setAlignmentX(TOP_ALIGNMENT);
		examplePanel.add(exampleBalloonPanel);
        examplePanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPane.add(examplePanel);

		//JLabel space = new JLabel();
		//space.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		//topPanel.add(space);

		JButton startTest = new JButton("\u0634\u0631\u0648\u0639 \u0622\u0632\u0645\u0648\u0646");
        startTest.setFont(new Font("Times New Roman", Font.BOLD, 20));
        startTest.setAlignmentX(CENTER_ALIGNMENT);

		startTest.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				BalloonPanel balloonPanel = new BalloonPanel(personId, testId, 800, 600);
				setContentPane(balloonPanel);
				setLocation(0, 0);
				pack();
			}
		});

		//mainPane.add(topPanel);
        JLabel space = new JLabel();
        space.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        mainPane.add(space);
		mainPane.add(startTest);

		space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
		mainPane.add(space);

		setContentPane(mainPane);
		setLocation(0, 0);
		pack();
	}
}
