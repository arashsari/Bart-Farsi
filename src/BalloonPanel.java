import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.applet.*;
import java.net.*;


class BalloonPanel extends JPanel
{
	BalloonPanel balloonPanel = this;

	int personId = 0;
	int testId = 0;

	//int[] test30BalloonsIds = new int[3];
	int balloonsId = 0;

	int[] balloons;
	int currentBalloon = -1;
	int pumpCount = 0;
	int totalPumpCount = 0;

	int money = 0;

	int width;
	int height;

	int baseX = width / 2;
	int baseY = 7 * height / 10 + height / 33;

	int balloonStartWidth = 100;
	int balloonStartHeight = 100;

	int balloonWidth = balloonStartWidth;
	int balloonHeight = balloonStartHeight;

	int balloonIncrement = 3;

	int rectWidth = 25;
	int rectHeight = 15;

    String totalEarnedString = "<html><font face='Times New Roman' size='5'><center>&#1605;&#1580;&#1605;&#1608;&#1593;&nbsp;&#1662;&#1608;&#1604;&nbsp;&#1589;&#1606;&#1583;&#1608;&#1602;&nbsp;&#1575;&#1589;&#1604;&#1740;<br>";
    String lastBalloonEarnedString = "<html><font face='Times New Roman' size='5'><center>&#1589;&#1606;&#1583;&#1608;&#1602;&nbsp;&#1605;&#1608;&#1602;&#1578;&nbsp;&#1576;&#1575;&#1583;&#1705;&#1606;&#1705;&nbsp;&#1602;&#1576;&#1604;&#1740;<br>";

    JButton pump = new JButton("<html><font face='Times New Roman' size='5'><center>&#1575;&#1610;&#1606;&nbsp;&#1583;&#1705;&#1605;&#1607;&nbsp;&#1585;&#1575;&nbsp;&#1601;&#1588;&#1575;&#1585;&nbsp;&#1583;&#1607;&#1610;&#1583;<br>&#1578;&#1575;&nbsp;&#1576;&#1575;&#1583;&#1705;&#1606;&#1705;&nbsp;&#1576;&#1575;&#1583;&nbsp;&#1588;&#1608;&#1583;");
	JButton collect = new JButton("<html><font face='Times New Roman' size='5'><center>&#1601;&#1588;&#1575;&#1585;&nbsp;&#1583;&#1607;&#1610;&#1583;&nbsp&#1578;&#1575;&#1662;&#1608;&#1604;&nbsp;&#1580;&#1605;&#1593;&nbsp;&#1588;&#1583;&#1607;&nbsp;&#1583;&#1585;&nbsp;&#1589;&#1606;&#1583;&#1608;&#1602;&nbsp;&#1605;&#1608;&#1602;&#1578;&nbsp;&#1585;&#1575;&nbsp;&#1608;&#1575;&#1585;&#1583;<br>&#1589;&#1606;&#1583;&#1608;&#1602;&nbsp;&#1575;&#1589;&#1604;&#1610;&nbsp;&#1606;&#1605;&#1575;&#1610;&#1610;&#1583;");
	JLabel totalEarned = new JLabel(totalEarnedString + "&#1776;&nbsp;&#1578;&#1608;&#1605;&#1575;&#1606;");
	JLabel lastBalloon = new JLabel(lastBalloonEarnedString + "&#1776;&nbsp;&#1578;&#1608;&#1605;&#1575;&#1606;");

	class PumpActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (currentBalloon < 0) return;

			pumpCount++;
            if (pumpCount > 118) balloonIncrement = 2;

			if ( balloons[currentBalloon] > pumpCount)
			{
				balloonWidth += balloonIncrement;
				balloonHeight += balloonIncrement;
			}
			else
			{
				//Explode sound
                try
                {
                    AudioClip clip = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/sounds/explosion.wav"));
                    clip.play();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                insertCurrentBalloonIntoDB("No");

                lastBalloon.setText(lastBalloonEarnedString + "&#1776;&nbsp;&#1578;&#1608;&#1605;&#1575;&#1606;");
				totalPumpCount += pumpCount;
				currentBalloon++;
				if (currentBalloon >= balloons.length)
				{
					insertPersonTestIntoDB();
					currentBalloon = -1;
                    try
                    {
                        AudioClip clip = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/sounds/finale.wav"));
                        clip.play();
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
				}
				else
				{
					pumpCount = 0;
					balloonWidth = balloonStartWidth;
					balloonHeight = balloonStartHeight;
				}
			}

			balloonPanel.revalidate();
			balloonPanel.repaint();
		}
	}

	class CollectActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (currentBalloon < 0) return;

			lastBalloon.setText(lastBalloonEarnedString + FarsiTools.numberToHtmlUnicode(pumpCount * 50) + "&nbsp;&#1578;&#1608;&#1605;&#1575;&#1606;");

            money += pumpCount * 50;
			if (pumpCount > 0)
            {
                try
                {
                    AudioClip clip = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/sounds/casino.wav"));
                    clip.play();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

			totalEarned.setText(totalEarnedString + FarsiTools.numberToHtmlUnicode(money) + "&nbsp;&#1578;&#1608;&#1605;&#1575;&#1606;");
			insertCurrentBalloonIntoDB("Yes");

			totalPumpCount += pumpCount;
			currentBalloon++;
			if (currentBalloon >= balloons.length)
			{
				insertPersonTestIntoDB();
				currentBalloon = -1;
                try
                {
                    AudioClip clip = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/sounds/clap.wav"));
                    clip.play();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
			}
			else
			{
				pumpCount = 0;
				balloonWidth = balloonStartWidth;
				balloonHeight = balloonStartHeight;
			}

			balloonPanel.revalidate();
			balloonPanel.repaint();

		}
	}

	void init()
	{
		setPreferredSize(new Dimension(width, height));
		setLayout(null);

		add(pump);
		add(collect);

		pump.setSize(170,70);
		pump.setLocation(baseX - pump.getWidth() / 2, baseY + rectHeight + 1);
		//pump.setRolloverEnabled(true);

		collect.setSize(190,85);
		collect.setLocation(baseX - pump.getWidth() / 2 - collect.getWidth() - 25, baseY + rectHeight);

		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		add(labels);

		labels.setSize(200, 140);
		labels.setLocation(baseX + pump.getWidth() / 2 + 50,
			baseY + rectHeight + pump.getHeight() / 2 - 70 + 1);

		totalEarned.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(8,18,8,10)));
        lastBalloon.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(8,18,8,10)));

		labels.add(totalEarned);
		JLabel space = new JLabel();
		space.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
		labels.add(space);
		labels.add(lastBalloon);

		pump.addActionListener(new PumpActionListener());
		collect.addActionListener(new CollectActionListener());
	}

	BalloonPanel(int[] b, int w, int h)
	{
		width = w;
		height = h;

		baseX = width / 2;
		baseY = 7 * height / 10 + height / 33;
		if (baseY > height - 110)
			baseY = height - 110;

		//balloonIncrement = 3;

		init();

		if (b != null && b.length > 1) currentBalloon = 0;
		balloons = b;
	}

	BalloonPanel(int pId, int tId, int w, int h)
	{
		this(null, w, h);

		personId = pId;
		testId = tId;

		// for test
		// insertPersonTestIntoDB();

		try
		{
			AudioClip clip = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/sounds/tada.wav"));
			clip.play();

			Connection c = DBServices.getConnection();
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT [30BlueBalloons]" +
				"FROM BARTest WHERE ID = " + testId);
			if (!r.next()) throw new Exception("Invalid test ID!");

			int blueBalloonsId = r.getInt("30BlueBalloons");

			int[] balloons = new int[30];

			r = s.executeQuery("SELECT * FROM [30Balloons] WHERE ID = " + blueBalloonsId);
			if (!r.next()) throw new Exception("Invalid blue balloons ID!");

			for (int i = 0; i < 30; i++)
    			balloons[i] = r.getInt("B" + (i + 1));

			currentBalloon = 0;
			this.balloons = balloons;

			s.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void insertCurrentBalloonIntoDB(String success)
	{
		if (personId <= 0 || testId <= 0) return;

		try
		{
			Connection c = DBServices.getConnection();
			Statement s = c.createStatement();

			if (currentBalloon == 0)
			{
				ResultSet r = s.executeQuery("SELECT MAX(ID) AS MaxID FROM 30Balloons");
				int id = 1;
				if (r.next()) id = r.getInt("MaxID") + 1;

				s.executeUpdate("INSERT INTO 30Balloons(ID) VALUES(" + id + ")");
				balloonsId = id;
			}

			s.executeUpdate("UPDATE [30Balloons] SET B" + (currentBalloon + 1) +
				"Success = " + success + ", B" + (currentBalloon + 1) + " = " + pumpCount +
				" WHERE ID = " + balloonsId);
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void insertPersonTestIntoDB()
	{
		if (personId <= 0 || testId <= 0) return;
		try
		{
			Connection c = DBServices.getConnection();
			Statement s = c.createStatement();

			Calendar calendar = Calendar.getInstance();

			s.executeUpdate("INSERT INTO PersonBARTest VALUES(" + personId + ", " + testId + ", '" +
				 calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" +
				 calendar.get(Calendar.DAY_OF_MONTH) +"', '" + calendar.get(Calendar.HOUR) + "-" +
				 calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + "', " +
				 balloonsId + ", " + money / 50 + ", " + (totalPumpCount / 30) + ")");

			throw new Exception("Thank you.");
        }
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp.getMessage(), "Information",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Rectangle balloon = new Rectangle( baseX - balloonWidth / 2, baseY - balloonHeight, balloonWidth, balloonHeight);
		Rectangle rect = new Rectangle( baseX - rectWidth / 2, baseY, rectWidth, rectHeight);

		g.drawRect(rect.x, rect.y, rect.width, rect.height);

		if (currentBalloon < 0 )
			g.setColor(Color.black);
        else
            g.setColor(Color.blue);

		g.fillOval(balloon.x, balloon.y, balloon.width, balloon.height);
	}

	public static void main(String[] args)
	{
		JFrame testFrame = new JFrame("Test Balloon Panel");

		int[] exampleBalloons = {128, (int)(128 * Math.random() + 1), (int)(128 * Math.random() + 1)};

		testFrame.setContentPane(new BalloonPanel(exampleBalloons, 800, 600));
		//testFrame.setContentPane(new BalloonPanel(1, 1, 1016, 734));
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		testFrame.pack();
		testFrame.show();
	}
}