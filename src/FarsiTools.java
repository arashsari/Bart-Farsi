
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class htmlToJavaUnicodeFrame extends JFrame
{
    JTextField in = new JTextField();
    JTextField out = new JTextField();
    JButton convert = new JButton("Convert");

    public htmlToJavaUnicodeFrame() //throws HeadlessException
    {
        super("HTML Unicode Representation to Java String Reperesentation Converter");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.add(in, BorderLayout.NORTH);
        cp.add(out, BorderLayout.SOUTH);
        cp.add(convert, BorderLayout.CENTER);

        convert.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String inString = in.getText();
                String outString = "";
                for (int i = 0; i < inString.length(); i++) {
                    switch(inString.charAt(i))
                    {
                        case '&':
                            outString += "\\u";
                            break;
                        case '#':
                            String num = "";
                            while (inString.charAt(++i) != ';')
                                num += inString.charAt(i);
                            int n = Integer.parseInt(num);
                            num = Integer.toHexString(n);
                            if (num.length() == 2)
                                num = "00" + num;
                            else if (num.length() == 3)
                                num = "0" + num;
                            else if (num.length() > 4)
                                num = num.substring(num.length() - 4, num.length());
                            outString += num;
                            break;
                         default:
                            outString += inString.charAt(i);
                            break;
                    }
                }
                out.setText(outString);
            }
        });
        pack();
    }
}

public class FarsiTools
{
    /**static String readUnicodeFarsiStringFromFile(String fileName)
            throws FileNotFoundException, IOException
    {
        InputStream inputFile = new FileInputStream(fileName);

        String returnValue = null;
        byte [] byteBuffer = new byte[5000];

        inputFile.read(byteBuffer);
        returnValue = new String(byteBuffer,"UTF-16");

        return returnValue;
    }*/

    public static String numberToHtmlUnicode(int i)
    {
        String htmlUnicode = "";
        String num = Integer.toString(i);

        for (int j = 0; j < num.length(); j++)
        {
            int code = 1776 + Integer.parseInt(Character.toString(num.charAt(j)));
            htmlUnicode += "&#" + code + ";";
        }
        return htmlUnicode;
    }

    public static void main(String[] args) {
        if (args[0].equals("-html"))
            (new htmlToJavaUnicodeFrame()).setVisible(true);
        else if (args[0].equals("-num"))
            System.out.println(FarsiTools.numberToHtmlUnicode(1234567890));
    }
}
