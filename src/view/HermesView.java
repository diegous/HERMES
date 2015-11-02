package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.*;

import javax.swing.JTextField;


public class HermesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private MonitorInformation viewInfo;

	
	public HermesView(MonitorInformation list) {
		viewInfo=list;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 900, 600);
		setTitle("Hermes Monitor");
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		
		
		
		//PANEL DE FILTROS---------------------------------------------------------------------------
		JPanel Filtros = new JPanel();
		Filtros.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Filtros.setBackground(new Color(153, 204, 51));
		GridBagConstraints gbc_Filtros = new GridBagConstraints();
		gbc_Filtros.insets = new Insets(5, 5, 5, 5);
		gbc_Filtros.fill = GridBagConstraints.BOTH;
		gbc_Filtros.gridx = 0;
		gbc_Filtros.gridy = 0;
		contentPane.add(Filtros, gbc_Filtros);
		GridBagLayout gbl_Filtros = new GridBagLayout();
		gbl_Filtros.columnWidths = new int[]{50, 100, 100};
		gbl_Filtros.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_Filtros.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_Filtros.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		Filtros.setLayout(gbl_Filtros);
		
		
		//TITULO
		JPanel Titulo = new JPanel();
		Titulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Titulo.setBackground(new Color(51, 102, 153));
		GridBagConstraints gbc_Titulo = new GridBagConstraints();
		gbc_Titulo.anchor = GridBagConstraints.NORTH;
		gbc_Titulo.gridwidth = 4;
		gbc_Titulo.insets = new Insets(0, 0, 10, 0);
		gbc_Titulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Titulo.gridx = 0;
		gbc_Titulo.gridy = 0;
		Filtros.add(Titulo, gbc_Titulo);
		
		JLabel filtros = new JLabel("FILTROS");
		Titulo.add(filtros);
		filtros.setForeground(new Color(0, 0, 0));
		filtros.setFont(new Font("Cambria", Font.BOLD, 15));
		
		//CONTENIDO
		JLabel contenido = new JLabel("Contenido:");
		contenido.setHorizontalAlignment(SwingConstants.LEFT);
		//contenido.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_contenido = new GridBagConstraints();
		gbc_contenido.fill = GridBagConstraints.HORIZONTAL;
		gbc_contenido.insets = new Insets(0, 5, 5, 5);
		gbc_contenido.gridx = 0;
		gbc_contenido.gridy = 1;
		Filtros.add(contenido, gbc_contenido);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Entusiasmado", "Alegre", "Molesto"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		Filtros.add(comboBox, gbc_comboBox);
		
		//FECHA
		JLabel Fechahora = new JLabel("Fecha/Hora");
		Fechahora.setHorizontalAlignment(SwingConstants.LEFT);
		//Fechahora.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_Fechahora = new GridBagConstraints();
		gbc_Fechahora.insets = new Insets(0, 10, 0, 10);
		gbc_Fechahora.gridx = 2;
		gbc_Fechahora.gridy = 1;
		Filtros.add(Fechahora, gbc_Fechahora);
		
				
		JLabel desde = new JLabel("Desde:");
		//desde.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_desde = new GridBagConstraints();
		gbc_desde.fill = GridBagConstraints.HORIZONTAL;
		gbc_desde.insets = new Insets(0, 10, 0, 10);
		gbc_desde.gridx = 2;
		gbc_desde.gridy = 2;
		Filtros.add(desde, gbc_desde);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1445914800000L), new Date(1445914800000L), new Date(1476932400000L), Calendar.DAY_OF_YEAR));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 10, 0, 10);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 3;
		Filtros.add(spinner, gbc_spinner);
		
		JLabel hasta = new JLabel("Hasta:");
		//hasta.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_hasta = new GridBagConstraints();
		gbc_hasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_hasta.insets = new Insets(0,10,0, 10);
		gbc_hasta.gridx = 2;
		gbc_hasta.gridy = 4;
		Filtros.add(hasta, gbc_hasta);
		
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(1445914800000L), new Date(1445914800000L), new Date(1445914800000L), Calendar.DAY_OF_YEAR));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 10, 0, 10);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 5;
		Filtros.add(spinner_1, gbc_spinner_1);
		
		
		//CONTEXTO
		JLabel contexto = new JLabel("Contexto:");
		contexto.setHorizontalAlignment(SwingConstants.LEFT);
		//contexto.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_contexto = new GridBagConstraints();
		gbc_contexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_contexto.insets = new Insets(0, 5, 5, 5);
		gbc_contexto.gridx = 0;
		gbc_contexto.gridy = 2;
		Filtros.add(contexto, gbc_contexto);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Pista", "Establo-Terapia", "Hogar"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 2;
		Filtros.add(comboBox_1, gbc_comboBox_1);
		
		//CATEGORIA
		JLabel categoria = new JLabel("Categor\u00EDa:");
		categoria.setHorizontalAlignment(SwingConstants.LEFT);
		//categoria.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_categoria = new GridBagConstraints();
		gbc_categoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoria.insets = new Insets(0, 5, 5, 5);
		gbc_categoria.gridx = 0;
		gbc_categoria.gridy = 3;
		Filtros.add(categoria, gbc_categoria);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Emociones", "Alimentos", "Actividades y paseo", "<Predeterminada>"}));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 3;
		Filtros.add(comboBox_2, gbc_comboBox_2);
		
		
		
		//NINO
		JLabel nino = new JLabel("Ni\u00F1@:");
		nino.setHorizontalAlignment(SwingConstants.LEFT);
		//nino.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_nino = new GridBagConstraints();
		gbc_nino.fill = GridBagConstraints.HORIZONTAL;
		gbc_nino.insets = new Insets(0, 5, 5, 5);
		gbc_nino.gridx = 0;
		gbc_nino.gridy = 4;
		Filtros.add(nino, gbc_nino);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		
		comboBox_3.addItem(" ");
		for (Child temp : viewInfo.getChild()) {
			comboBox_3.addItem(temp.getName());
		}
		
		
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 4;
		Filtros.add(comboBox_3, gbc_comboBox_3);
		
		
		
		
		//ETIQUETA
		JLabel Etiqueta = new JLabel("Etiqueta:");
		Etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		//Etiqueta.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_Etiqueta = new GridBagConstraints();
		gbc_Etiqueta.fill = GridBagConstraints.HORIZONTAL;
		gbc_Etiqueta.insets = new Insets(0, 0, 5, 5);
		gbc_Etiqueta.gridx = 0;
		gbc_Etiqueta.gridy = 5;
		Filtros.add(Etiqueta, gbc_Etiqueta);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Importante", "CharlarConPadres"}));
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 1;
		gbc_comboBox_4.gridy = 5;
		Filtros.add(comboBox_4, gbc_comboBox_4);
		
		
		
		JButton btnNewButton = new JButton("Filtrar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		Filtros.add(btnNewButton, gbc_btnNewButton);
		
		
		
		
		//PANEL DE ETIQUETAS---------------------------------------------------------------------------
		JPanel Etiquetas = new JPanel();
		Etiquetas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Etiquetas.setBackground(new Color(153, 204, 51));
		GridBagConstraints gbc_Etiquetas = new GridBagConstraints();
		gbc_Etiquetas.insets = new Insets(5, 5, 5, 5);
		gbc_Etiquetas.fill = GridBagConstraints.BOTH;
		gbc_Etiquetas.gridx = 1;
		gbc_Etiquetas.gridy = 0;
		contentPane.add(Etiquetas, gbc_Etiquetas);
		GridBagLayout gbl_Etiquetas = new GridBagLayout();
		gbl_Etiquetas.columnWidths = new int[]{50, 100, 50};
		gbl_Etiquetas.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_Etiquetas.columnWeights = new double[]{1.0, 1.0, 1.0,Double.MIN_VALUE};
		gbl_Etiquetas.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0};
		Etiquetas.setLayout(gbl_Etiquetas);
		
		
		
		
		
		//TITULO
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(51, 102, 153));
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		Etiquetas.add(panel, gbc_panel);
		
		JLabel lblEtiquetas = new JLabel("ETIQUETAS");
		lblEtiquetas.setForeground(new Color(0, 0, 0));
		lblEtiquetas.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(lblEtiquetas);
		
		//CREAR
		JLabel lblNewLabel = new JLabel("Crear etiqueta:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		Etiquetas.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		Etiquetas.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 2;
		gbc_btnCrear.gridy = 1;
		Etiquetas.add(btnCrear, gbc_btnCrear);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		Etiquetas.add(separator, gbc_separator);
		
		
		//ELIMINAR
		JLabel lblNewLabel_1 = new JLabel("Eliminar etiqueta:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		Etiquetas.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
		gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_5.gridx = 1;
		gbc_comboBox_5.gridy = 3;
		Etiquetas.add(comboBox_5, gbc_comboBox_5);
		
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 3;
		Etiquetas.add(btnEliminar, gbc_btnEliminar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		Etiquetas.add(separator_1, gbc_separator_1);
		
		
		//ASIGNACION
		JLabel lblNewLabel_2 = new JLabel("Asignacion etiqueta:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		Etiquetas.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JComboBox<String> comboBox_6 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
		gbc_comboBox_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_6.gridx = 1;
		gbc_comboBox_6.gridy = 5;
		Etiquetas.add(comboBox_6, gbc_comboBox_6);
		
		JButton btnAsignardesasignar = new JButton("Asignar/desasignar");
		GridBagConstraints gbc_btnAsignardesasignar = new GridBagConstraints();
		gbc_btnAsignardesasignar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAsignardesasignar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAsignardesasignar.gridx = 2;
		gbc_btnAsignardesasignar.gridy = 5;
		Etiquetas.add(btnAsignardesasignar, gbc_btnAsignardesasignar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 6;
		Etiquetas.add(separator_2, gbc_separator_2);
		
		
		//RENOMBRAR
		
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar etiqueta:");
		GridBagConstraints gbc_lblRenombrarEtiqueta = new GridBagConstraints();
		gbc_lblRenombrarEtiqueta.anchor = GridBagConstraints.WEST;
		gbc_lblRenombrarEtiqueta.insets = new Insets(0, 5, 5, 5);
		gbc_lblRenombrarEtiqueta.gridx = 0;
		gbc_lblRenombrarEtiqueta.gridy = 7;
		Etiquetas.add(lblRenombrarEtiqueta, gbc_lblRenombrarEtiqueta);
		
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_7 = new GridBagConstraints();
		gbc_comboBox_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_7.gridwidth = 2;
		gbc_comboBox_7.gridx = 1;
		gbc_comboBox_7.gridy = 7;
		Etiquetas.add(comboBox_7, gbc_comboBox_7);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre:");
		GridBagConstraints gbc_lblNuevoNombre = new GridBagConstraints();
		gbc_lblNuevoNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNuevoNombre.insets = new Insets(0, 5, 0, 5);
		gbc_lblNuevoNombre.gridx = 0;
		gbc_lblNuevoNombre.gridy = 8;
		Etiquetas.add(lblNuevoNombre, gbc_lblNuevoNombre);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 8;
		Etiquetas.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnRenombrar = new JButton("Renombrar");
		GridBagConstraints gbc_btnRenombrar = new GridBagConstraints();
		gbc_btnRenombrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRenombrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRenombrar.gridx = 2;
		gbc_btnRenombrar.gridy = 8;
		Etiquetas.add(btnRenombrar, gbc_btnRenombrar);
		
		
		
		
		
		
		//PANEL DE NOTIFICACIONES---------------------------------------------------------------------------
		JPanel Notificaciones = new JPanel();
		Notificaciones.setToolTipText("");
		Notificaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Notificaciones.setBackground(new Color(153, 204, 51));
		GridBagConstraints gbc_Notificaciones = new GridBagConstraints();
		gbc_Notificaciones.gridwidth = 2;
		gbc_Notificaciones.insets = new Insets(5, 5, 5, 5);
		gbc_Notificaciones.fill = GridBagConstraints.BOTH;
		gbc_Notificaciones.gridx = 0;
		gbc_Notificaciones.gridy = 1;
		contentPane.add(Notificaciones, gbc_Notificaciones);
		GridBagLayout gbl_Notificaciones = new GridBagLayout();
		gbl_Notificaciones.columnWidths = new int[]{0};
		gbl_Notificaciones.rowHeights = new int[]{0, 0};
		gbl_Notificaciones.columnWeights = new double[]{1.0};
		gbl_Notificaciones.rowWeights = new double[]{0.0, 1.0};
		Notificaciones.setLayout(gbl_Notificaciones);
		
		
		//TITULO
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(new Color(51, 102, 153));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 10, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		Notificaciones.add(panel_1, gbc_panel_1);
		
		JLabel lblNotificaciones = new JLabel("NOTIFICACIONES");
		lblNotificaciones.setForeground(new Color(0, 0, 0));
		lblNotificaciones.setFont(new Font("Cambria", Font.BOLD, 15));
		panel_1.add(lblNotificaciones);
	
		
		//TABLA
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
					{"10", "Entusiasmado", null, null, null, null},
					{"9", "Entusiasmado", null, null, null, null},
					{"7", "Alegre", null, null, null, null},
					{"2", "Molesto", null, null, null, null},
					{"5", "Entusiasmado", null, null, null, null},
					{"6", "Alegre", null, null, null, null},
					{"8", "Molesto", null, null, null, null},
					{"7", "Alegre", null, null, null, null},
					{"4", "Entusiasmado", null, null, null, null},
					{"10", "Molesto", null, null, null, null},
				},
				new String[] {
					"Fecha/hora env\u00EDo", "Contenido", "Contexto", "Categor\u00EDa", "Ni\u00F1@", "Etiquetas"
				}
			) {
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				@SuppressWarnings("unchecked")
				public Class<String> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			
			
			table = new JTable(modelo);
			
			
			JScrollPane scrollPane = new JScrollPane(table);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			Notificaciones.add(scrollPane, gbc_scrollPane);
		
					
			
			TableRowSorter<TableModel> orden = new TableRowSorter<TableModel>(modelo);
			table.setRowSorter(orden);
			
			//orden.setRowFilter(RowFilter.regexFilter("Entusiasmado", 1));
			//orden.setRowFilter(RowFilter.regexFilter("10", 0));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

	
	
	
}




















