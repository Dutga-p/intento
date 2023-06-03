package myMiniProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is used for ...
 * @autor Esteban Camilo Martinez Urbano - esteban.urbano@correounivalle.edu.co
 * @version v.1.0.0 date:27/05/2023
 */
public class GUI extends JFrame {

    private static final String Information = "Se te van a presentar una serie de palabras de una en una"
            +"\n y tendras que memorizarlas, en seguida se te presentaran"
            +"\n el doble de palabras presentadas anteriormente, pero con"
            +"\n ciertas palabras que no se encontraban en la primera lista"
            +"\n tu funcion sera determinar que palabras se encontraban en"
            +"\n la primera lista y cuales no, por cada nivel el numero de"
            +"\n palabras se ira duplicando consecutivamente. !SUERTE¡";

    private JButton Instructions,Exit,ContinueButton,NewGame,botonOK,Back,volverButton,seleccionarButton;
    private JPanel OpeningPanel,GamePanel,panelSeleccionUsuario;
    private JLabel labelUsername,Aviso;
    private JTextField entradaUsuario;

    private Escucha escucha;
    private Header header;
    private Player player;
    private GridBagConstraints constraints,layoutPanelGame,layoutLoadGame,layout;
    private FileManager fileManager;
    private DefaultListModel<String> usuariosModel;
    private JScrollPane scrollPane;
    private GUI gui;


    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Configuración por defecto del JFrame
        this.setTitle("I know that word");
        this.setSize(200,100);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        /**
         * Create Listener Object and Control Object
         */
        escucha = new Escucha();

        /**
         * tittle
         */
        header = new Header("I KNOW THAT WORD", new Color(135, 7, 122));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.CENTER;
        this.add(header, constraints);

        /**
         * help button
         */
        Instructions = new JButton("Como jugar");
        Instructions.addActionListener(escucha);
        Instructions.setPreferredSize(new Dimension(110, 30));
        Instructions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Instructions.setBorder(new EmptyBorder(100, 0, 100, 0));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(Instructions, constraints);
        revalidate();
        repaint();

        /**
         * exit button
         */
        Exit = new JButton("Salir");
        Exit.addActionListener(escucha);
        Exit.setPreferredSize(new Dimension(110, 30));
        Exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Exit.setBorder(new EmptyBorder(100, 0, 100, 0));
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        add(Exit, constraints);
        NewGameAndContinue();
        revalidate();
        repaint();
    }

    public void NewGameAndContinue(){
        constraints = new GridBagConstraints();
        layout = new GridBagConstraints();
        OpeningPanel = new JPanel();
        OpeningPanel.setPreferredSize(new Dimension(600, 400));
        OpeningPanel.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(OpeningPanel, constraints);
        /**
         * new game button
         */
        NewGame = new JButton("Nuevo juego");
        NewGame.addActionListener(escucha);
        NewGame.setPreferredSize(new Dimension(110, 30));
        NewGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        NewGame.setBorder(new EmptyBorder(100, 0, 100, 0));
        layout.gridx = 1;
        layout.gridy = 1;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.NONE;
        layout.anchor = GridBagConstraints.CENTER;
        OpeningPanel.add(NewGame, layout);

        /**
         * continue game button
         */
        ContinueButton = new JButton("Continuar");
        ContinueButton.addActionListener(escucha);
        ContinueButton.setPreferredSize(new Dimension(110, 30));
        ContinueButton.setBorder(new EmptyBorder(100, 0, 100, 0));
        layout.gridx = 1;
        layout.gridy = 2;
        layout.gridwidth = 1;
        layout.fill = GridBagConstraints.NONE;
        layout.anchor = GridBagConstraints.CENTER;
        OpeningPanel.add(ContinueButton, layout);
    }
    public void NewGamePanel(){
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridBagLayout());
        layoutPanelGame = new GridBagConstraints(); // panelGame layout component
        GamePanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(GamePanel, constraints);

        labelUsername = new JLabel("Nombre de Usuario");
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 2;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        GamePanel.add(labelUsername, layoutPanelGame);

        // TextField input box
        entradaUsuario = new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250, 40));
        entradaUsuario.setFont(new Font("Arial ", Font.PLAIN, 30));
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_END;
        GamePanel.add(entradaUsuario, layoutPanelGame);

        // Confirmation button
        botonOK = new JButton("Aceptar");
        botonOK.addActionListener(escucha);
        botonOK.setPreferredSize(new Dimension(100, 100));
        botonOK.setBorderPainted(false);
        botonOK.setContentAreaFilled(false);
        layoutPanelGame.gridx = 1;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_START;
        GamePanel.add(botonOK, layoutPanelGame);


        Back = new JButton("Atras");
        Back.addActionListener(escucha);
        Back.setPreferredSize(new Dimension(100, 100));
        Back.setBorderPainted(false);
        Back.setContentAreaFilled(false);
        layoutPanelGame.gridx = 1;
        layoutPanelGame.gridy = 2;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_START;
        GamePanel.add(Back, layoutPanelGame);
        revalidate();
        repaint();
    }
    public void LoadGame(){
        panelSeleccionUsuario = new JPanel();
        panelSeleccionUsuario.setLayout(new GridBagLayout());
        layoutLoadGame = new GridBagConstraints(); // panelGame layout component
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.insets = new Insets(10, 10, 10, 10);
        panelSeleccionUsuario.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelSeleccionUsuario, constraints);




        fileManager = new FileManager();
        JList<String> usuariosList = new JList<>(fileManager.NombreYNivel());
        usuariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        layoutLoadGame = new GridBagConstraints();
        Aviso = new JLabel("Seleciona tu Usuario");
        Aviso.setPreferredSize(new Dimension(100, 200));
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        panelSeleccionUsuario.add(Aviso, layoutLoadGame);

        volverButton = new JButton("Volver");
        volverButton.addActionListener(escucha);
        volverButton.setPreferredSize(new Dimension(100, 100));
        volverButton.setBorderPainted(false);
        volverButton.setContentAreaFilled(false);
        layoutLoadGame.gridx = 1;
        layoutLoadGame.gridy = 3;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        panelSeleccionUsuario.add(volverButton, layoutLoadGame);

        seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(escucha);
        seleccionarButton.setPreferredSize(new Dimension(100, 100));
        seleccionarButton.setBorderPainted(false);
        seleccionarButton.setContentAreaFilled(false);
        layoutLoadGame.gridx = 2;
        layoutLoadGame.gridy = 3;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        panelSeleccionUsuario.add(seleccionarButton, layoutLoadGame);

        scrollPane = new JScrollPane(usuariosList);
        layoutLoadGame.gridx = 1;
        layoutLoadGame.gridy = 2;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.BOTH;
        layoutLoadGame.weightx = 1.0;
        layoutLoadGame.weighty = 1.0;
        panelSeleccionUsuario.add(scrollPane, layoutLoadGame);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha  implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Exit) System.exit(0);
            if (e.getSource() == Instructions)
                JOptionPane.showMessageDialog(null, Information, null, JOptionPane.INFORMATION_MESSAGE);
            if (e.getSource()==NewGame){
                remove(OpeningPanel);
                NewGamePanel();
                add(GamePanel);

            }
            if(e.getSource()==ContinueButton){
                fileManager = new FileManager();
                if(fileManager.leerUsuarios().isEmpty()){
                    JOptionPane.showMessageDialog(OpeningPanel, "No hay ningun usuario registrado aún", "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                    remove(OpeningPanel);
                    LoadGame();
                    revalidate();
                    repaint();
                }
            }
            if(e.getSource()==Back){
                gui.main(null);
            }
            if(e.getSource() == botonOK){
                String name = entradaUsuario.getText();
                player = new Player(name);
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(GamePanel, "Necesitas un nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(player.ExisteJugador()){
                        JOptionPane.showMessageDialog(GamePanel, "El usuario ya existe, ingresa un usurio diferente", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        player.registrarJugador();
                    }
                }
            }
        }
    }
}