package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entities.*;
import modelo.*;

import javax.swing.JTextField;

public class HermesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<Child> comboChild;
	private JComboBox<Context> comboContext;
	private JComboBox<Category> comboCategory;
	private JComboBox<Pictogram> comboPictogram;
	private JComboBox<String> comboDesde;
	private JComboBox<String> comboHasta;
	private JComboBox<Tag> comboTagList, comboTagRename, comboTagAssign, comboTagRemove;
	private DefaultTableModel tableModel;
	private MonitorInformation viewInfo;
	private SimpleDateFormat tableDateFormat = new SimpleDateFormat("yyyy.MM.dd   HH:mm:ss");
	private Boolean tableIsFiltered = false;
	JLabel lblNewNotifications;
	JButton btnViewAllNotifications;

	public HermesView(MonitorInformation list) {
//----- MODELs Initialization
		viewInfo = list;
		String textForAll = "Todos";
		final String textForNone = "-";
		
		
		//FECHAyHORA
		
				// Desde
				comboDesde = new JComboBox<String>();
				comboDesde.addItem(textForNone);
				for (Date temp : viewInfo.getFechas())
					comboDesde.addItem(tableDateFormat.format(temp));
				// Hasta
				comboHasta = new JComboBox<String>();
				comboHasta.addItem(textForNone);
				for (Date temp : viewInfo.getFechas())
					comboHasta.addItem(tableDateFormat.format(temp));
		
				
				
				
		// Child
		comboChild = new JComboBox<Child>();
		comboChild.addItem(new Child(0, textForAll));
		for (Child temp : viewInfo.getChild())
			comboChild.addItem(temp);
		
		// Context
		comboContext = new JComboBox<Context>();
		comboContext.addItem(new Context(0, textForAll));
		for (Context temp : viewInfo.getContext())
			comboContext.addItem(temp);
		
		// Category
		comboCategory = new JComboBox<Category>();
		comboCategory.addItem(new Category(0, textForAll));
		for (Category temp : viewInfo.getCategory())
			comboCategory.addItem(temp);
		
		// Pictogram
		comboPictogram = new JComboBox<Pictogram>();
		comboPictogram.addItem(new Pictogram(0, textForAll));
		for (Pictogram temp : viewInfo.getPictogram())
			comboPictogram.addItem(temp);
		
		// Tag
		comboTagList = new JComboBox<Tag>();
		comboTagRename = new JComboBox<Tag>();
		comboTagAssign = new JComboBox<Tag>();
		comboTagRemove = new JComboBox<Tag>();

		comboTagList.addItem(new Tag(0, textForAll));
		comboTagRename.addItem(new Tag(0, textForNone));
		comboTagAssign.addItem(new Tag(0, textForNone));
		comboTagRemove.addItem(new Tag(0, textForNone));
		for (Tag temp : viewInfo.getTag()) {
			comboTagList.addItem(temp);
			comboTagRename.addItem(temp);
			comboTagAssign.addItem(temp);
			comboTagRemove.addItem(temp);
		}
		
		// Table



		
//----- WINDOW DRAWING
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 900, 600);
		setTitle("Hermes Monitor");
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// PANEL DE
		// NOTIFICACIONES---------------------------------------------------------------------------
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
		gbl_Notificaciones.columnWidths = new int[] { 0 };
		gbl_Notificaciones.rowHeights = new int[] { 0, 0 };
		gbl_Notificaciones.columnWeights = new double[] { 1.0 };
		gbl_Notificaciones.rowWeights = new double[] { 0.0, 1.0 };
		Notificaciones.setLayout(gbl_Notificaciones);

		// TITULO
		JPanel panelTableTitle = new JPanel();
		panelTableTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTableTitle.setBackground(new Color(51, 102, 153));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 10, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		Notificaciones.add(panelTableTitle, gbc_panel_1);

		JLabel lblNotificaciones = new JLabel("NOTIFICACIONES");
		lblNotificaciones.setForeground(new Color(0, 0, 0));
		lblNotificaciones.setFont(new Font("Cambria", Font.BOLD, 15));
		panelTableTitle.add(lblNotificaciones);
		
		btnViewAllNotifications = new JButton("Ver todas");
		panelTableTitle.add(btnViewAllNotifications);
		btnViewAllNotifications.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				HermesView.this.showFullTable();
			}
		});
		
		lblNewNotifications = new JLabel("Hay nuevas notificaciones");
		panelTableTitle.add(lblNewNotifications);
		lblNewNotifications.setVisible(false);

		// TABLA
		tableModel = new DefaultTableModel();
		String[] column = new String[] { "Fecha/hora env\u00EDo", "Contenido", "Contexto", "Categor\u00EDa",
				"Ni\u00F1@", "Etiquetas", "id_notification" };
		for (int i = 0; i < 7; i++) {
			tableModel.addColumn(column[i]);
		}
		showFullTable();

		final JTable table = new JTable(tableModel);
		table.removeColumn(table.getColumnModel().getColumn(6));

		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		Notificaciones.add(scrollPane, gbc_scrollPane);
		TableRowSorter<TableModel> orden = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(orden);

		table.addMouseListener(new MouseAdapter() {

			private MonitorInformation viewInfo;

			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				if ((fila > -1)) {
					int filaReordenada= table.convertRowIndexToModel(fila);
					this.viewInfo.setSelectNotification(table.getModel().getValueAt(filaReordenada, 6).toString());
				}
			}

			public MouseAdapter init(MonitorInformation viewInfo) {
				this.viewInfo = viewInfo;
				return this;
			}

		}.init(this.viewInfo));

		// PANEL DE
		// FILTROS---------------------------------------------------------------------------
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
		gbl_Filtros.columnWidths = new int[] { 50, 100, 100 };
		gbl_Filtros.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_Filtros.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_Filtros.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		Filtros.setLayout(gbl_Filtros);

		// TITULO
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

		// CONTENIDO
		JLabel contenido = new JLabel("Contenido:");
		contenido.setHorizontalAlignment(SwingConstants.LEFT);
		// contenido.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_contenido = new GridBagConstraints();
		gbc_contenido.fill = GridBagConstraints.HORIZONTAL;
		gbc_contenido.insets = new Insets(0, 5, 5, 5);
		gbc_contenido.gridx = 0;
		gbc_contenido.gridy = 1;
		Filtros.add(contenido, gbc_contenido);

		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		Filtros.add(comboPictogram, gbc_comboBox);
		comboPictogram.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					viewInfo.getFilter().setPictogram(e.getItem().toString());
				}
			}
		});

		// FECHA
		JLabel Fechahora = new JLabel("Fecha/Hora");
		Fechahora.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_Fechahora = new GridBagConstraints();
		gbc_Fechahora.insets = new Insets(0, 10, 0, 10);
		gbc_Fechahora.gridx = 2;
		gbc_Fechahora.gridy = 1;
		Filtros.add(Fechahora, gbc_Fechahora);

		JLabel desde = new JLabel("Desde:");
		GridBagConstraints gbc_desde = new GridBagConstraints();
		gbc_desde.fill = GridBagConstraints.HORIZONTAL;
		gbc_desde.insets = new Insets(0, 10, 0, 10);
		gbc_desde.gridx = 2;
		gbc_desde.gridy = 2;
		Filtros.add(desde, gbc_desde);

		JLabel hasta = new JLabel("Hasta:");
		GridBagConstraints gbc_hasta = new GridBagConstraints();
		gbc_hasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_hasta.insets = new Insets(0, 10, 0, 10);
		gbc_hasta.gridx = 2;
		gbc_hasta.gridy = 4;
		Filtros.add(hasta, gbc_hasta);
		
		
		GridBagConstraints gbc_comboDesde = new GridBagConstraints();
		gbc_comboDesde.insets = new Insets(0, 10, 0, 10);
		gbc_comboDesde.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboDesde.gridx = 2;
		gbc_comboDesde.gridy = 3;
		Filtros.add(comboDesde, gbc_comboDesde);
		comboDesde.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					if(e.getItem().toString().equals("-")){viewInfo.getFilter().setSince(0);}
					else{
						try {viewInfo.getFilter().setSince(tableDateFormat.parse(e.getItem().toString()).getTime());}
						catch (ParseException e1) {e1.printStackTrace();}
					}
				}
			});
		
		
		
		GridBagConstraints gbc_comboHasta = new GridBagConstraints();
		gbc_comboHasta.insets = new Insets(0, 10, 0, 10);
		gbc_comboHasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboHasta.gridx = 2;
		gbc_comboHasta.gridy = 5;
		Filtros.add(comboHasta, gbc_comboHasta);
		comboHasta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					if(e.getItem().toString().equals("-")){viewInfo.getFilter().setUntil(0);}
					else{
						try {viewInfo.getFilter().setUntil(tableDateFormat.parse(e.getItem().toString()).getTime());} 
						catch (ParseException e1) {e1.printStackTrace();}
					}
			}
		});
		
		// CONTEXTO
		JLabel contexto = new JLabel("Contexto:");
		contexto.setHorizontalAlignment(SwingConstants.LEFT);
		// contexto.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_contexto = new GridBagConstraints();
		gbc_contexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_contexto.insets = new Insets(0, 5, 5, 5);
		gbc_contexto.gridx = 0;
		gbc_contexto.gridy = 2;
		Filtros.add(contexto, gbc_contexto);

		
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 2;
		Filtros.add(comboContext, gbc_comboBox_1);
		comboContext.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.getFilter().setContext(e.getItem().toString());
			}
		});

		// CATEGORIA
		JLabel categoria = new JLabel("Categor\u00EDa:");
		categoria.setHorizontalAlignment(SwingConstants.LEFT);
		// categoria.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_categoria = new GridBagConstraints();
		gbc_categoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoria.insets = new Insets(0, 5, 5, 5);
		gbc_categoria.gridx = 0;
		gbc_categoria.gridy = 3;
		Filtros.add(categoria, gbc_categoria);

		
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 3;
		Filtros.add(comboCategory, gbc_comboBox_2);
		comboCategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.getFilter().setCategory(e.getItem().toString());
			}
		});

		// NINO
		JLabel nino = new JLabel("Ni\u00F1@:");
		nino.setHorizontalAlignment(SwingConstants.LEFT);
		// nino.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_nino = new GridBagConstraints();
		gbc_nino.fill = GridBagConstraints.HORIZONTAL;
		gbc_nino.insets = new Insets(0, 5, 5, 5);
		gbc_nino.gridx = 0;
		gbc_nino.gridy = 4;
		Filtros.add(nino, gbc_nino);

		
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 4;
		Filtros.add(comboChild, gbc_comboBox_3);
		comboChild.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.getFilter().setChild(e.getItem().toString());
			}
		});

		// ETIQUETA
		JLabel Etiqueta = new JLabel("Etiqueta:");
		Etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		// Etiqueta.setFont(new Font("Cambria", Font.BOLD, 11));
		GridBagConstraints gbc_Etiqueta = new GridBagConstraints();
		gbc_Etiqueta.fill = GridBagConstraints.HORIZONTAL;
		gbc_Etiqueta.insets = new Insets(0, 0, 5, 5);
		gbc_Etiqueta.gridx = 0;
		gbc_Etiqueta.gridy = 5;
		Filtros.add(Etiqueta, gbc_Etiqueta);

		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 1;
		gbc_comboBox_4.gridy = 5;
		Filtros.add(comboTagList, gbc_comboBox_4);
		comboTagList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.getFilter().setTag(e.getItem().toString());
			}
		});

		// BOTON DEL FILTRO
		JButton buttonFilter = new JButton("Filtrar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		Filtros.add(buttonFilter, gbc_btnNewButton);
		buttonFilter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				HermesView.this.updateTable();
				tableIsFiltered = true;
				btnViewAllNotifications.setVisible(true);
			}
		});

		// PANEL DE
		// ETIQUETAS---------------------------------------------------------------------------
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
		gbl_Etiquetas.columnWidths = new int[] { 50, 100, 50 };
		gbl_Etiquetas.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_Etiquetas.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_Etiquetas.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0 };
		Etiquetas.setLayout(gbl_Etiquetas);

		// TITULO
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

		// ASIGNACION
		JLabel lblNewLabel_2 = new JLabel("Asignacion etiqueta:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		Etiquetas.add(lblNewLabel_2, gbc_lblNewLabel_2);

		GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
		gbc_comboBox_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_6.gridx = 1;
		gbc_comboBox_6.gridy = 5;
		Etiquetas.add(comboTagAssign, gbc_comboBox_6);
		comboTagAssign.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.setSelectAsignar(e.getItem().toString());
			}
		});

		JButton btnAsignardesasignar = new JButton("Asignar/desasignar");
		GridBagConstraints gbc_btnAsignardesasignar = new GridBagConstraints();
		gbc_btnAsignardesasignar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAsignardesasignar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAsignardesasignar.gridx = 2;
		gbc_btnAsignardesasignar.gridy = 5;
		Etiquetas.add(btnAsignardesasignar, gbc_btnAsignardesasignar);
		btnAsignardesasignar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (viewInfo.getSelectNotification() != null && viewInfo.getSelectAsignar() != null && viewInfo.getSelectAsignar() != textForNone ) {
					String n = viewInfo.getSelectNotification();
					Tag t = FactoriaDAO.getTagDAO().getByText(viewInfo.getSelectAsignar());
					FactoriaDAO.getNotificatioTagDAO().save(Integer.parseInt(n), t.getId());
				}
				HermesView.this.updateTable();
			}
		});

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

		// RENOMBRAR
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar etiqueta:");
		GridBagConstraints gbc_lblRenombrarEtiqueta = new GridBagConstraints();
		gbc_lblRenombrarEtiqueta.anchor = GridBagConstraints.WEST;
		gbc_lblRenombrarEtiqueta.insets = new Insets(0, 5, 5, 5);
		gbc_lblRenombrarEtiqueta.gridx = 0;
		gbc_lblRenombrarEtiqueta.gridy = 7;
		Etiquetas.add(lblRenombrarEtiqueta, gbc_lblRenombrarEtiqueta);

		GridBagConstraints gbc_comboBox_7 = new GridBagConstraints();
		gbc_comboBox_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_7.gridwidth = 2;
		gbc_comboBox_7.gridx = 1;
		gbc_comboBox_7.gridy = 7;
		Etiquetas.add(comboTagRename, gbc_comboBox_7);
		comboTagRename.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.setSelectModify(e.getItem().toString());
			}
		});

		JLabel lblNuevoNombre = new JLabel("Nuevo nombre:");
		GridBagConstraints gbc_lblNuevoNombre = new GridBagConstraints();
		gbc_lblNuevoNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNuevoNombre.insets = new Insets(0, 5, 0, 5);
		gbc_lblNuevoNombre.gridx = 0;
		gbc_lblNuevoNombre.gridy = 8;
		Etiquetas.add(lblNuevoNombre, gbc_lblNuevoNombre);

		final JTextField txtFldRenameTag = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 8;
		Etiquetas.add(txtFldRenameTag, gbc_textField_1);
		txtFldRenameTag.setColumns(10);

		JButton btnRenombrar = new JButton("Renombrar");
		GridBagConstraints gbc_btnRenombrar = new GridBagConstraints();
		gbc_btnRenombrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRenombrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRenombrar.gridx = 2;
		gbc_btnRenombrar.gridy = 8;
		Etiquetas.add(btnRenombrar, gbc_btnRenombrar);
		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpIndex = comboTagRename.getSelectedIndex();
				
				if (viewInfo.getSelectModify() != null && viewInfo.getSelectModify() != textForNone && txtFldRenameTag.getText().length() > 0 && FactoriaDAO.getTagDAO().getByText(txtFldRenameTag.getText())==null) {
					FactoriaDAO.getTagDAO().modify(txtFldRenameTag.getText(), comboTagRename.getSelectedItem().toString());
					viewInfo.setTag(FactoriaDAO.getTagDAO().getList());

					comboTagList.removeItemAt(tmpIndex);
					comboTagRename.removeItemAt(tmpIndex);
					comboTagAssign.removeItemAt(tmpIndex);
					comboTagRemove.removeItemAt(tmpIndex);
					
					Tag tmp = FactoriaDAO.getTagDAO().getByText(txtFldRenameTag.getText());
					comboTagList.insertItemAt(tmp, tmpIndex);
					comboTagRename.insertItemAt(tmp, tmpIndex);
					comboTagAssign.insertItemAt(tmp, tmpIndex);
					comboTagRemove.insertItemAt(tmp, tmpIndex);

					HermesView.this.updateTable();
					
					txtFldRenameTag.setText(null);
					viewInfo.setSelectModify(null);
				}
			}
		});

		// ELIMINAR
		JLabel lblNewLabel_1 = new JLabel("Eliminar etiqueta:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		Etiquetas.add(lblNewLabel_1, gbc_lblNewLabel_1);

		GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
		gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_5.gridx = 1;
		gbc_comboBox_5.gridy = 3;
		Etiquetas.add(comboTagRemove, gbc_comboBox_5);
		comboTagRemove.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					viewInfo.setSelectDelete((Tag) e.getItem());
			}
		});

		JButton btnDeleteTag = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 3;
		Etiquetas.add(btnDeleteTag, gbc_btnEliminar);
		btnDeleteTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpIndex = comboTagRemove.getSelectedIndex();
				if (viewInfo.getSelectDelete() != null) {
					FactoriaDAO.getTagDAO().delete(viewInfo.getSelectDelete());
					viewInfo.deleteTag(viewInfo.getSelectDelete());

					viewInfo.getTag().remove(viewInfo.getSelectDelete());
					
					comboTagList.removeItemAt(tmpIndex);
					comboTagRename.removeItemAt(tmpIndex);
					comboTagAssign.removeItemAt(tmpIndex);
					comboTagRemove.removeItemAt(tmpIndex);

					HermesView.this.updateTable();

					viewInfo.setSelectDelete(null);
				}
			}
		});

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

		// CREAR
		JLabel lblNewLabel = new JLabel("Crear etiqueta:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		Etiquetas.add(lblNewLabel, gbc_lblNewLabel);

		final JTextField inputCrear = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		Etiquetas.add(inputCrear, gbc_textField);
		inputCrear.setColumns(10);

		JButton btnCrear = new JButton("Crear");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 2;
		gbc_btnCrear.gridy = 1;
		Etiquetas.add(btnCrear, gbc_btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputCrear.getText().length() > 0) {
					if (FactoriaDAO.getTagDAO().getByText(inputCrear.getText()) == null) {
						FactoriaDAO.getTagDAO().save(new Tag(0, inputCrear.getText()));
						Tag newTag = FactoriaDAO.getTagDAO().getByText(inputCrear.getText());
						comboTagRename.addItem(newTag);
						comboTagAssign.addItem(newTag);
						comboTagRemove.addItem(newTag);
						comboTagList.addItem(newTag);
					}
					inputCrear.setText(null);
				}
			}
		});

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
	}

	public void addChild(Child t) {
		this.comboChild.addItem(t);
	}

	public void addContext(Context t) {
		this.comboContext.addItem(t);
	}

	public void addCategory(Category t) {
		this.comboCategory.addItem(t);
	}

	public void addTag(Tag t) {
		this.comboTagList.addItem(t);
	}

	public void addPictogram(Pictogram t) {
		this.comboPictogram.addItem(t);
	}
	
	public void addFecha(Date d) {
		this.comboHasta.addItem(tableDateFormat.format(d));
		this.comboDesde.addItem(tableDateFormat.format(d));
	}

	private void updateTable() {
		int filas = tableModel.getRowCount();
		for (int i = 0; filas > i; i++)
			tableModel.removeRow(0);

		for (Notification temp : viewInfo.getFilter().filter()) {
			Object[] row = new Object[] { tableDateFormat.format(temp.getSent()),
					temp.getPictogram(), temp.getContext(),	temp.getCategory(), 
					temp.getChild(), temp.getTag(),	temp.getId()};
			tableModel.addRow(row);
		}
	}

	private void showFullTable() {
		btnViewAllNotifications.setVisible(false);
		lblNewNotifications.setVisible(false);
		tableIsFiltered = false;

		int filas = tableModel.getRowCount();
		for (int i = 0; filas > i; i++)
			tableModel.removeRow(0);
		
		for (Notification temp : viewInfo.getNotification()) {
			Object[] row = new Object[] { tableDateFormat.format(temp.getSent()), temp.getPictogram(),
					temp.getContext(), temp.getCategory(),
					temp.getChild(), temp.getTag(), temp.getId()};
			tableModel.addRow(row);
		}
	}

	public void addNotification(Notification notification) {
		if (tableIsFiltered) {
			lblNewNotifications.setVisible(true);
		} else {
			Object[] row = new Object[] { tableDateFormat.format(notification.getSent()), notification.getPictogram(),
					notification.getContext(), notification.getCategory(),
					notification.getChild(), notification.getTag(), notification.getId()};
			tableModel.insertRow(0, row);
		}		
	}
}
