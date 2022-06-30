package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JEditorPane;

public class ReportT extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static private String fld;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportT frame = new ReportT();
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
	public ReportT() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(369, 180, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.setForeground(new Color(255, 255, 255));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lblX.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(653, 6, 47, 42);
		contentPane.add(lblX);
		
		int blah=50;
		
		String stud[][]=new String[blah][3];
		for(int i=0;i<blah;i++)
		{
			for(int j=0;j<3;j++)
			{
				stud[i][j]=" ";
			}
		}
		int i=0;
		int j=0;
		String head[]= {"UID","Name","Field"};
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "bajar143");
			PreparedStatement p=(PreparedStatement)c.prepareStatement("select * from teacher");
			ResultSet rs=p.executeQuery();
			while(rs.next()) 
			{
				j=0;
				stud[i][j]=rs.getString(1);
				j=1;
				stud[i][j]=rs.getString(2);
				j=2;
				stud[i][j]=rs.getString(3);
				i++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		table = new JTable(stud,head);
		table.setRowSelectionAllowed(false);
		table.setBounds(33, 21, 550, 419);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(59, 64, 520, 292);
		contentPane.add(scrollPane);
		
		JLabel lblList = new JLabel("Teacher Report :");
		lblList.setBackground(new Color(0, 0, 0));
		lblList.setBounds(371, 11, 291, 42);
		contentPane.add(lblList);
		lblList.setForeground(Color.WHITE);
		lblList.setFont(new Font("Sitka Small", Font.BOLD, 22));
		lblList.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file="Teacher Report.xls";
				Export.toExcel(table,new File(file));
				JOptionPane.showMessageDialog(null,"File Exported To Base Directory Of Project 5.0 \nFile Name: Teacher Report.xls");
			}
		});
		btnExport.setFont(new Font("Sitka Small", Font.BOLD, 12));
		btnExport.setBounds(540, 371, 122, 33);
		contentPane.add(btnExport);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon(ReportT.class.getResource("/images/TEACHER BACKGROUND.png")));
		label.setBounds(0, 0, 700, 450);
		contentPane.add(label);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	}
}
