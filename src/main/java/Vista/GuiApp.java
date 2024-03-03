package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.math.BigDecimal;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import CodNegocio.Vertice;
import Controlador.Controlador;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class GuiApp {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel pnlCalculoClique,pnlCargarDatos,pnlBotonesCalcularClique;
	private PanelGrafo pnlGrafo;
	private JButton btnCalculoClique,btnMostrarClique,btnSelectVert1,btnSelectVert2,btnCrearArista,btnLimpiarCarga;
	private JScrollPane scrollPnl_Aristas,scrollPnl_Vertices;
	private JList<String> listAristas;
	private JList<Vertice<BigDecimal>> listVertices;
	private DefaultListModel<String> listModelArist;
	private DefaultListModel<Vertice<BigDecimal>> listModelVertice;
	private JLabel lblaux3,lblaux4,lblaux5,lblaux6,lblaux7,lblaux8,lblaux9,lblPesoV1,lblEtiquetaV1,lblPesoV2,lblEtiquetaV2;
	private Controlador controlador;
	
	
	
	public GuiApp() {
		try
		{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) { 
			JOptionPane.showMessageDialog(null,e.getMessage());
		} 
	} 
		
	public void creaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI();
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void GUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 395);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CargarPanels();
		ComponentesPestañaCargarDatos();
		InicializarBotones();
		
		this.getPnlGrafo().addMouseListener(controlador);
		this.getBtnCalculoClique().addActionListener(controlador);
		this.getBtnCrearArista().addActionListener(controlador);
		this.getBtnLimpiarCarga().addActionListener(controlador);
		this.getBtnMostrarClique().addActionListener(controlador);				
		this.getBtnSelectVert1().addActionListener(controlador);
		this.getBtnSelectVert2().addActionListener(controlador);
		
		frame.setVisible(true);
	}
	
//----------------------gettes y setter-------------------------------------------
	public JLabel getLblPesoV1() {
		return lblPesoV1;
	}

	public void setLblPesoV1(JLabel lblPesoV1) {
		this.lblPesoV1 = lblPesoV1;
	}

	public JLabel getLblEtiquetaV1() {
		return lblEtiquetaV1;
	}

	public void setLblEtiquetaV1(JLabel lblEtiquetaV1) {
		this.lblEtiquetaV1 = lblEtiquetaV1;
	}

	public JLabel getLblPesoV2() {
		return lblPesoV2;
	}

	public void setLblPesoV2(JLabel lblPesoV2) {
		this.lblPesoV2 = lblPesoV2;
	}

	public JLabel getLblEtiquetaV2() {
		return lblEtiquetaV2;
	}

	public void setLblEtiquetaV2(JLabel lblEtiquetaV2) {
		this.lblEtiquetaV2 = lblEtiquetaV2;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public PanelGrafo getPnlGrafo() {
		return pnlGrafo;
	}

	public JButton getBtnCalculoClique() {
		return btnCalculoClique;
	}

	public JButton getBtnMostrarClique() {
		return btnMostrarClique;
	}
	
	public JButton getBtnSelectVert1() {
		return this.btnSelectVert1;
	}

	public JButton getBtnSelectVert2() {
		return this.btnSelectVert2;
	}

	public JButton getBtnCrearArista() {
		return btnCrearArista;
	}

	public JButton getBtnLimpiarCarga() {
		return btnLimpiarCarga;
	}
	
	public JList<String> getListAristas() {
		return listAristas;
	}

	public JList<Vertice<BigDecimal>> getListVertices() {
		return listVertices;
	}

	public void setControlador(Controlador cont) {
		this.controlador=cont;
		
	}	 
	
	public DefaultListModel<Vertice<BigDecimal>> getListModelVertice() {
		return listModelVertice;
	}
	
	public DefaultListModel<String> getListModelArista() {
		
		return listModelArist;
	}
	
//--------------------------------------------------------------------------------------------------	


	public void MostrarVentanaEmergente(String info) {
		JOptionPane.showMessageDialog(this.getFrame(), info);
	}

	public void borrarLblPestañaCargaDatos()
	{
		this.getLblEtiquetaV1().setText("...");
		this.getLblPesoV1().setText("...");
		this.getLblEtiquetaV2().setText("...");
		this.getLblPesoV2().setText("...");
	}

	public void agregarVertice2DALista(Vertice<BigDecimal> vertice)
	{
		listModelVertice.addElement(vertice);
		listVertices.setModel(listModelVertice);
	}
	
	public void agregarAristaLista(String arista)
	{
		listModelArist.addElement(arista);
		this.getListAristas().setModel(listModelArist);
	}

	public void limpiarListas()	{
		this.getListAristas().clearSelection();
		this.getListVertices().clearSelection();
	}
	
	public String VentanaIngresoPeso() {
		String aux= JOptionPane.showInputDialog("Ingrese peso");
		if(aux.isEmpty())
			throw new NullPointerException("no se ingreso peso");
		return aux;
	}
	
	
 //------------------------auxliares---------------------------------------------------------------------------------
	private void CargarPanels() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 700, 365);
		frame.getContentPane().add(tabbedPane);
		
		pnlCalculoClique = new JPanel();
		tabbedPane.addTab("Carga y calculo", null, pnlCalculoClique, null);
		pnlCalculoClique.setLayout(new BorderLayout(0, 0));
		
		pnlBotonesCalcularClique = new JPanel();
		pnlCalculoClique.add(pnlBotonesCalcularClique, BorderLayout.SOUTH);
		pnlBotonesCalcularClique.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
								
		pnlCargarDatos = new JPanel();
		pnlCargarDatos.setFocusable(false);
		tabbedPane.addTab("Establecer conexiones", null, pnlCargarDatos, null);
		pnlCargarDatos.setLayout(null);
		
		pnlGrafo= new PanelGrafo();
		pnlCalculoClique.add(pnlGrafo, BorderLayout.CENTER);
		
	}

	private void InicializarBotones() {
		btnCalculoClique = new JButton("Calcular Clique");
		btnCalculoClique.setFocusable(false);
		pnlBotonesCalcularClique.add(btnCalculoClique);
	
		btnMostrarClique = new JButton("VIsualizar Clique");
		btnMostrarClique.setFocusable(false);
		pnlBotonesCalcularClique.add(btnMostrarClique);
			
		btnSelectVert1 = new JButton("Seleccionar Vertice 1");
	 	btnSelectVert1.setFocusable(false);
		btnSelectVert1.setBounds(206, 32, 192, 20);
		pnlCargarDatos.add(btnSelectVert1);
		
		btnSelectVert2 = new JButton("Seleccion Vertice 2");
		btnSelectVert2.setFocusable(false);
		btnSelectVert2.setBounds(206, 127, 192, 20);
		pnlCargarDatos.add(btnSelectVert2);
		
		btnCrearArista = new JButton("Agregar Arista");
		btnCrearArista.setFocusable(false);
		btnCrearArista.setBounds(12, 301, 155, 25);
		pnlCargarDatos.add(btnCrearArista);
			
		btnLimpiarCarga = new JButton("Limpiar carga");
		btnLimpiarCarga.setFocusable(false);
		btnLimpiarCarga.setBounds(410, 301, 260, 25);
		pnlCargarDatos.add(btnLimpiarCarga);
	}

	private void ComponentesPestañaCargarDatos() {
		scrollPnl_Aristas = new JScrollPane();
		scrollPnl_Aristas.setViewportBorder(new TitledBorder(null, "Aristas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 153)));
		scrollPnl_Aristas.setBounds(580, 5, 90, 289);
		pnlCargarDatos.add(scrollPnl_Aristas);
		
		listAristas = new JList<String>();
		listAristas.setFocusable(false);
		scrollPnl_Aristas.setViewportView(listAristas);
		
		listModelArist=new DefaultListModel<String>();
		
		scrollPnl_Vertices = new JScrollPane();
		scrollPnl_Vertices.setViewportBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Vertices", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 153)));
		scrollPnl_Vertices.setBounds(410, 5, 158, 289);
		pnlCargarDatos.add(scrollPnl_Vertices);
		
		listVertices = new JList<Vertice<BigDecimal>>();
		listVertices.setFocusable(false);
		scrollPnl_Vertices.setViewportView(listVertices);
		
		//listModelVert=new VerticeListModel();
		listModelVertice=new DefaultListModel<Vertice<BigDecimal>>();
		
		
		lblaux3 = new JLabel("Ingreso Arista");
		lblaux3.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblaux3.setForeground(UIManager.getColor("ColorChooser.foreground"));
		lblaux3.setBounds(10, 10, 107, 15);
		pnlCargarDatos.add(lblaux3);
		
		lblaux4 = new JLabel("Vertice 1:");
		lblaux4.setBounds(25, 35, 70, 15);
		pnlCargarDatos.add(lblaux4);
		
		lblaux5 = new JLabel("Etiqueta:");
		lblaux5.setBounds(45, 65, 70, 15);
		pnlCargarDatos.add(lblaux5);
		
		lblaux6 = new JLabel("Peso:");
		lblaux6.setBounds(45, 95, 70, 15);
		pnlCargarDatos.add(lblaux6);
			
		lblaux7 = new JLabel("Vertice 2:");
		lblaux7.setBounds(25, 130, 70, 15);
		pnlCargarDatos.add(lblaux7);
				
		lblaux8 = new JLabel("Etiqueta:");
		lblaux8.setBounds(45, 160, 70, 15);
		pnlCargarDatos.add(lblaux8);
		
		lblaux9 = new JLabel("Peso:");
		lblaux9.setBounds(45, 190, 70, 15);
		pnlCargarDatos.add(lblaux9);
			
		lblEtiquetaV2 = new JLabel("...");
		lblEtiquetaV2.setBounds(130, 160, 70, 15);
		pnlCargarDatos.add(lblEtiquetaV2);
		
		lblPesoV2 = new JLabel("...");
		lblPesoV2.setBounds(130, 190, 70, 15);
		pnlCargarDatos.add(lblPesoV2);

		lblEtiquetaV1 = new JLabel("...");
		lblEtiquetaV1.setBounds(130, 65, 70, 15);
		pnlCargarDatos.add(lblEtiquetaV1);
		
		lblPesoV1 = new JLabel("...");
		lblPesoV1.setBounds(130, 95, 70, 15);
		pnlCargarDatos.add(lblPesoV1);
	
		
	}


}
