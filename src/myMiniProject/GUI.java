package myMiniProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

    private JButton Instructions,Exit,ContinueButton,NewGame,OK,Back,BackButton,SelectButton,StartGame,Continue,Yes,No;
    private JPanel OpeningPanel,GamePanel,SelectUser,Words,PanelGame,OptionPanel,Componente2;
    private JLabel labelUsername,Ad,Level,Time,WordLabel;
    private JTextField Box;

    private Escucha escucha;
    private Header header;
    private Player player;
    private GridBagConstraints constraints,layoutPanelGame,layoutLoadGame,layout;
    private FileManager fileManager;
    private JScrollPane scrollPane;
    private Controller controller = new Controller();
    private GUI gui;
    public String name;
    private Timer timer;
    private int counter,fase;
    private JTextArea Message = new JTextArea();

    /** Constructor of GUI class */
    public GUI(){
        initGUI();
        this.setTitle("I KNOW THAT WORD");
        this.setUndecorated(true);
        //this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        /** Create Listener Object and Control Object */
        escucha = new Escucha();

        /** tittle */
        header = new Header("I KNOW THAT WORD", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, constraints);

        /** help button */
        Instructions = new JButton("Como jugar");
        Instructions.addActionListener(escucha);
        Instructions.setPreferredSize(new Dimension(110, 30));
        Instructions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Instructions.setBorder(new EmptyBorder(100, 0, 100, 0));
        constraints.gridx = 0;
        constraints.gridy = 1;
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
        constraints.gridx = 1;
        constraints.gridy = 1;
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
        /** new game button */
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

        /** continue game button */
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
        layoutPanelGame = new GridBagConstraints(); // PanelGame layout component
        GamePanel.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 3;
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
        Box = new JTextField();
        Box.setPreferredSize(new Dimension(250, 40));
        Box.setFont(new Font("Arial ", Font.PLAIN, 30));
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_END;
        GamePanel.add(Box, layoutPanelGame);

        // Confirmation button
        OK = new JButton("Aceptar");
        OK.addActionListener(escucha);
        OK.setPreferredSize(new Dimension(100, 100));
        OK.setBorderPainted(false);
        OK.setContentAreaFilled(false);
        layoutPanelGame.gridx = 1;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_START;
        GamePanel.add(OK, layoutPanelGame);


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
        SelectUser = new JPanel();
        SelectUser.setLayout(new GridBagLayout());
        layoutLoadGame = new GridBagConstraints(); // PanelGame layout component
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.insets = new Insets(10, 10, 10, 10);
        SelectUser.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(SelectUser, constraints);

        fileManager = new FileManager();
        JList<String> usuariosList = new JList<>(fileManager.NombreYNivel());
        usuariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        layoutLoadGame = new GridBagConstraints();
        Ad = new JLabel("Seleciona tu Usuario");
        Ad.setPreferredSize(new Dimension(100, 200));
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        SelectUser.add(Ad, layoutLoadGame);

        BackButton = new JButton("Volver");
        BackButton.addActionListener(escucha);
        BackButton.setPreferredSize(new Dimension(100, 100));
        BackButton.setBorderPainted(false);
        BackButton.setContentAreaFilled(false);
        layoutLoadGame.gridx = 1;
        layoutLoadGame.gridy = 3;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        SelectUser.add(BackButton, layoutLoadGame);

        SelectButton = new JButton("Seleccionar");
        SelectButton.addActionListener(escucha);
        SelectButton.setPreferredSize(new Dimension(100, 100));
        SelectButton.setBorderPainted(false);
        SelectButton.setContentAreaFilled(false);
        layoutLoadGame.gridx = 2;
        layoutLoadGame.gridy = 3;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        SelectUser.add(SelectButton, layoutLoadGame);

        scrollPane = new JScrollPane(usuariosList);
        layoutLoadGame.gridx = 1;
        layoutLoadGame.gridy = 2;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.BOTH;
        layoutLoadGame.weightx = 1.0;
        layoutLoadGame.weighty = 1.0;
        SelectUser.add(scrollPane, layoutLoadGame);
    }

    public void Welcome(){
        SelectUser = new JPanel();
        SelectUser.setLayout(new GridBagLayout());
        layoutLoadGame = new GridBagConstraints(); // PanelGame layout component
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.insets = new Insets(10, 10, 10, 10);
        SelectUser.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(SelectUser, constraints);

        layoutLoadGame = new GridBagConstraints();
        Ad = new JLabel("Bienvenido "+name+" es hora de memorizar");
        Ad.setPreferredSize(new Dimension(500, 200));
        layoutLoadGame.gridx = 1;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 3;
        layoutLoadGame.fill = GridBagConstraints.BOTH;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        SelectUser.add(Ad, layoutLoadGame);

        StartGame = new JButton("Comenzar");
        StartGame.addActionListener(escucha);
        StartGame.setPreferredSize(new Dimension(100, 100));
        StartGame.setBorderPainted(false);
        StartGame.setContentAreaFilled(false);
        layoutLoadGame.gridx = 3;
        layoutLoadGame.gridy = 3;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        SelectUser.add(StartGame, layoutLoadGame);
    }

    public void Components() {

        PanelGame = new JPanel();
        PanelGame.setLayout(new GridBagLayout());
        layoutLoadGame = new GridBagConstraints(); // PanelGame layout component
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.insets = new Insets(10, 10, 10, 10);
        PanelGame.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(PanelGame, constraints);

        Level = new JLabel("NIVEL: " + Integer.toString(controller.getNivelActual()));
        Level.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.LINE_START;
        PanelGame.add(Level, layoutLoadGame);

        Time = new JLabel("00:00");
        Time.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutLoadGame.gridx = 4;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.LINE_END;
        PanelGame.add(Time, layoutLoadGame);

        Words = new JPanel(new GridBagLayout());
        GridBagConstraints layoutPanelPalabras = new GridBagConstraints();
        Words.setPreferredSize(new Dimension(500, 350));
        Words.setOpaque(false);
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        PanelGame.add(Words, layoutLoadGame);

        /*Cambiar nombre variable*/ WordLabel = new JLabel();
        WordLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        layoutPanelPalabras.gridx = 0;
        layoutPanelPalabras.gridy = 0;
        layoutPanelPalabras.gridwidth = 1;
        layoutPanelPalabras.fill = GridBagConstraints.NONE;
        layoutPanelPalabras.anchor = GridBagConstraints.CENTER;
        Words.add(WordLabel, layoutPanelPalabras);

        timer = new Timer(1000, escucha);
        revalidate();
        repaint();
    }
    public void Instructions() {
        
        Message.setText("\n               A continuación se mostraran en panatalla unas palabras \n   Tendras que escoger Si (si estaban en las palabras anteriores) \n   " +
                "Y No (si no estaban en las palabras anteriores)");
        Message.setEditable(false);
        Message.setLineWrap(true);
        Message.setWrapStyleWord(true);
        Message.setOpaque(true);
        Message.setPreferredSize(new Dimension(405, 155));
        Message.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        PanelGame.add(Message, layoutLoadGame);

        Continue = new JButton("Continuar");
        Continue.addActionListener(escucha);
        Continue.setPreferredSize(new Dimension(200, 65));
        Continue.setBorderPainted(false);
        Continue.setContentAreaFilled(false);
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.LINE_END;
        PanelGame.add(Continue, layoutLoadGame);
        revalidate();
        repaint();
    }

    public void Components2() {
        Componente2 = new JPanel();
        Componente2.setLayout(new GridBagLayout());
        layoutLoadGame = new GridBagConstraints(); // PanelGame layout component
        Componente2.setPreferredSize(new Dimension(600, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(Componente2, constraints);

        Level = new JLabel("NIVEL: " + Integer.toString(controller.getNivelActual()));
        Level.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.LINE_START;
        Componente2.add(Level, layoutLoadGame);

        Time = new JLabel("00:00");
        Time.setFont(new Font("Impact", Font.PLAIN, 27));
        layoutLoadGame.gridx = 4;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.LINE_END;
        Componente2.add(Time, layoutLoadGame);

        Words = new JPanel(new GridBagLayout());
        GridBagConstraints layoutPanelPalabras = new GridBagConstraints();
        Words.setPreferredSize(new Dimension(500, 300));
        Words.setOpaque(false);
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 1;
        layoutLoadGame.gridwidth = 2;
        layoutLoadGame.fill = GridBagConstraints.BOTH;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        Componente2.add(Words, layoutLoadGame);

        /*Cambiar nombre variable*/ WordLabel = new JLabel();
        WordLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        layoutPanelPalabras.gridx = 0;
        layoutPanelPalabras.gridy = 0;
        layoutPanelPalabras.gridwidth = 1;
        layoutPanelPalabras.fill = GridBagConstraints.NONE;
        layoutPanelPalabras.anchor = GridBagConstraints.CENTER;
        Words.add(WordLabel, layoutPanelPalabras);

        OptionPanel = new JPanel();
        GridBagConstraints layoutOptionPanel = new GridBagConstraints();
        OptionPanel.setPreferredSize(new Dimension(500, 100));
        OptionPanel.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        Componente2.add(OptionPanel, constraints);

        Yes = new JButton("Si");
        Yes.addActionListener(escucha);
        Yes.setBorderPainted(false);
        Yes.setContentAreaFilled(false);
        layoutOptionPanel.gridx = 0;
        layoutOptionPanel.gridy = 2;
        layoutOptionPanel.gridwidth = 1;
        layoutOptionPanel.fill = GridBagConstraints.NONE;
        layoutOptionPanel.anchor = GridBagConstraints.LINE_START;
        OptionPanel.add(Yes,layoutOptionPanel);

        No = new JButton("No");
        No.addActionListener(escucha);
        No.setBorderPainted(false);
        No.setContentAreaFilled(false);
        layoutOptionPanel.gridx = 1;
        layoutOptionPanel.gridy = 2;
        layoutOptionPanel.gridwidth = 1;
        layoutOptionPanel.fill = GridBagConstraints.NONE;
        layoutOptionPanel.anchor = GridBagConstraints.LINE_END;
        OptionPanel.add(No,layoutOptionPanel);
        timer = new Timer(1000, escucha);
        revalidate();
        repaint();
    }

    public void ContinueLevel() {
        String textoFinal = "";
        int aciertos = controller.getAciertos();
        int porcentaje = controller.HitPercentage();
        player.setNivelesAprobados();
        if (controller.getApruebaNivel()) {
            textoFinal = "\n               Has superado el nivel. \n   Número de aciertos: " + aciertos +
                    "\n   porcentaje: " + porcentaje + "%";
        } else {
            textoFinal = "\n               No has superado el nivel. \n   Número de aciertos: " + aciertos +
                    "\n   porcentaje: " + porcentaje + "%";
        }
        Message.setText(textoFinal);
        layoutLoadGame.gridx = 0;
        layoutLoadGame.gridy = 0;
        layoutLoadGame.gridwidth = 1;
        layoutLoadGame.fill = GridBagConstraints.NONE;
        layoutLoadGame.anchor = GridBagConstraints.CENTER;
        PanelGame.add(Message, layoutLoadGame);
        StartGame.setVisible(true);
        System.out.println(textoFinal);
        revalidate();
        repaint();
    }

    /** Main */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /** inner class that extends an Adapter Class or implements Listeners used by GUI class */
    private class Escucha  implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Exit) System.exit(0);
            if (e.getSource() == Instructions)
                JOptionPane.showMessageDialog(null, Information, null, JOptionPane.INFORMATION_MESSAGE);
            if (e.getSource()==NewGame){
                remove(OpeningPanel);
                NewGamePanel();
                revalidate();
                repaint();
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
            if(e.getSource()==BackButton){gui.main(null);}
            if(e.getSource()==SelectButton){}
            if(e.getSource()==Back){gui.main(null);}
            if(e.getSource() == OK){
                name = Box.getText();
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
                remove(GamePanel);
                Welcome();
                revalidate();
                repaint();
            }
            if(e.getSource() == StartGame){
                remove(SelectUser);
                Components();
                WordLabel.setText(controller.getPalabrasMemorizar());
                StartGame.setVisible(false);
                fase = 1;
                counter = 1;
                timer.start();
                revalidate();
                repaint();
            }
            if (e.getSource() == timer) {
                Time.setText("00:0" + counter);
                counter++;

                if (fase == 1) {
                    if (counter > 1) {
                        WordLabel.setText(controller.getPalabrasMemorizar());
                        counter = 0;
                    }
                    if (WordLabel.getText() == "") {
                        timer.stop();
                        PanelGame.removeAll();
                        revalidate();
                        repaint();
                        Instructions();
                    }
                }
                if (fase == 2) {
                    if (counter > 7) {
                        WordLabel.setText(controller.getPalabrasAleatorias());
                        counter = 0;
                    }
                    if (Objects.equals(WordLabel.getText(), "")) {
                        timer.stop();
                        PanelGame.removeAll();
                        revalidate();
                        repaint();
                        ContinueLevel();
                    }
                }
            }
            if (e.getSource() == Yes) {
                controller.validarPalabraCorrecta(WordLabel.getText());
                WordLabel.setText(controller.getPalabrasAleatorias());
                counter = 1;
                revalidate();
                repaint();
            }
            if (e.getSource() == No) {
                controller.validarPalabraIncorrecta(WordLabel.getText());
                WordLabel.setText(controller.getPalabrasAleatorias());
                counter = 1;
                revalidate();
                repaint();
            }
            if (e.getSource() == Continue) {
                remove(PanelGame);
                Components2();
                revalidate();
                repaint();
                WordLabel.setText(controller.getPalabrasAleatorias());
                fase = 2;
                timer.start();
            }
        }
    }
}