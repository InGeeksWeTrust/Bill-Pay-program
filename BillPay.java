/*
*Name: Peter Valdez
*Date: 12-11-16
*Purpose: This program will allow the user to enter in the account number, payment amount, name,
* and address of the person making a payment.
*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class BillPay extends JFrame implements ActionListener
{
	//Declaration for the output stream
	DataOutputStream output;

	//This will construct a panel for each of the rows
	JPanel rowOne = new JPanel();
	JPanel rowTwo = new JPanel();
	JPanel rowThree = new JPanel();
	JPanel rowFour = new JPanel();
	JPanel rowFive = new JPanel();
	JPanel rowSix = new JPanel();
	JPanel rowSeven = new JPanel();
	JPanel rowEight = new JPanel();

	//This constructs a panel for the fields and the buttons
	JPanel fieldPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	//This constructs the labels and the text boxes
	JLabel accountLabel = new JLabel("Account Number                         ");
		JTextField account = new JTextField(15);
	JLabel paymentLabel = new JLabel("Payment Amount:");
		JTextField payment = new JTextField(10);
	JLabel firstNameLabel = new JLabel("First Name:                 ");
		JTextField firstName = new JTextField(10);
	JLabel lastNameLabel = new JLabel("Last Name:");
		JTextField lastName = new JTextField(20);
	JLabel addressLabel = new JLabel("Address:");
		JTextField address = new JTextField(35);
	JLabel cityLabel = new JLabel("City:                            ");
		JTextField city = new JTextField(10);
	JLabel stateLabel = new JLabel("State:");
		JTextField state = new JTextField(2);
	JLabel zipLabel = new JLabel("Zip:");
		JTextField zip = new JTextField(9);

	//This will construct the submit button
	JButton submitButton = new JButton("Submit");

	public static void main(String[] args)
	{
		//This will set the look and feel of the interface
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "The UIManager was unable to set the Look and Feel for this applocation.","Error",
			JOptionPane.INFORMATION_MESSAGE);
		}
		BillPay f = new BillPay();
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(450,300);
		f.setTitle("inGeeksWeTrust Inc. Customer Payments");
		f.setResizable(false);
		f.setLocation(200,200);
		f.setVisible(true);
	}

	public BillPay()
	{
		Container c = getContentPane();
		c.setLayout((new BorderLayout()));
		fieldPanel.setLayout(new GridLayout(8,1));
		FlowLayout rowSetup = new FlowLayout(FlowLayout.LEFT,5,3);
			rowOne.setLayout(rowSetup);
			rowTwo.setLayout(rowSetup);
			rowThree.setLayout(rowSetup);
			rowFour.setLayout(rowSetup);
			rowFive.setLayout(rowSetup);
			rowSix.setLayout(rowSetup);
			rowSeven.setLayout(rowSetup);
			rowEight.setLayout(rowSetup);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//Add fields to the rows
		rowOne.add(accountLabel);
		rowOne.add(paymentLabel);

		rowTwo.add(account);
		rowTwo.add(payment);

		rowThree.add(firstNameLabel);
		rowThree.add(lastNameLabel);


		rowFour.add(firstName);
		rowFour.add(lastName);

		rowFive.add(addressLabel);

		rowSix.add(address);

		rowSeven.add(cityLabel);
		rowSeven.add(stateLabel);
		rowSeven.add(zipLabel);

		rowEight.add(city);
		rowEight.add(state);
		rowEight.add(zip);

		//This will add rows to the panel
		fieldPanel.add(rowOne);
		fieldPanel.add(rowTwo);
		fieldPanel.add(rowThree);
		fieldPanel.add(rowFour);
		fieldPanel.add(rowFive);
		fieldPanel.add(rowSix);
		fieldPanel.add(rowSeven);
		fieldPanel.add(rowEight);


		//This will add a button to the panel
		buttonPanel.add(submitButton);

		//This adds all the panels to the frame
		c.add(fieldPanel, BorderLayout.CENTER);
		c.add(buttonPanel, BorderLayout.SOUTH);

		//This will add functionality to the button
		submitButton.addActionListener(this);

		//Get the current date and open the file
		Date todayDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		String fileName = "payments" + dateFormat.format(todayDate);

		try
		{
			output = new DataOutputStream(new FileOutputStream(fileName));
		}
		catch(IOException io)
		{
			JOptionPane.showMessageDialog(null,"The application was unable to create a storage location. Please check the disk drive and try running the program again.","Error",JOptionPane.INFORMATION_MESSAGE);

			System.exit(1);
		}

		addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					int answer = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the application? The file will be automatically saved.", "File Submission", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION)
						System.exit(0);
				}
			}
		);
	}

	public void actionPerformed(ActionEvent e)
	{
		String arg = e.getActionCommand();

		if (checkFields())
		{
			try
			{
				output.writeUTF(account.getText());
				output.writeUTF(payment.getText());
				output.writeUTF(firstName.getText());
				output.writeUTF(lastName.getText());
				output.writeUTF(address.getText());
				output.writeUTF(city.getText());
				output.writeUTF(state.getText());
				output.writeUTF(zip.getText());

				JOptionPane.showMessageDialog(null,"The payment information has been saved.", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException c)
			{
				System.exit(1);
			}
			clearFields();
		}
	}

	public boolean checkFields()
	{
		if ((account.getText().compareTo("")< 1)	||
			(payment.getText().compareTo("")< 1)		||
			(firstName.getText().compareTo("")< 1)	||
			(lastName.getText().compareTo("")< 1)	||
			(address.getText().compareTo("")< 1)	||
			(city.getText().compareTo("")< 1)		||
			(state.getText().compareTo("")< 1)		||
			(zip.getText().compareTo("")< 1))
		{
			JOptionPane.showMessageDialog(null,"Cannot leave any fields empty. Please complete all fields.","Data Entry Error",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else
		{
			return true;
		}
	}

	public void clearFields()
	{
		//This will clear fields and reset the focus
		account.setText("");
		payment.setText("");
		firstName.setText("");
		lastName.setText("");
		address.setText("");
		city.setText("");
		state.setText("");
		zip.setText("");
		account.requestFocus();
	}
}
