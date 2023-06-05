package myMiniProject;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class FileManager {
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader input;
    private BufferedWriter output;
    public static final String UserLists = "src/FilesTxt/Users.txt";
    public static final String WordLists = "src/FilesTxt/TwoHundredWords.txt";
    private ArrayList<String> users,words;
    private ArrayList<String> NombreYNivel;
    private DefaultListModel usuariosModel;
    public void escribirTexto(String linea) {
        try
        {
            fileWriter = new FileWriter(UserLists, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                output.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> leerPalabras(){
        words = new ArrayList<>();
        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(WordLists, StandardCharsets.UTF_8);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                words.add(line);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }
    public  ArrayList<String> leerUsuarios(){
        String ArchivoLeido = UserLists;
        users = new ArrayList<>();
        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(ArchivoLeido, StandardCharsets.UTF_8);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                users.add(line);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
    public DefaultListModel NombreYNivel(){
        usuariosModel = new DefaultListModel<>();
        NombreYNivel = leerUsuarios();
        for (int i = 0; i < NombreYNivel.size() && !Objects.equals(NombreYNivel.get(i), " "); i++)
        {
            String eachUser = NombreYNivel.get(i);
            String[] datosUsuario = eachUser.split(":");
            String name = datosUsuario[0];
            String level = datosUsuario[1];
            String Player = name + " nivel "+ level;
            usuariosModel.addElement(Player);
        }
        return usuariosModel;
    }
    public void actualizarNivel(int posicion, int nivelNuevo)
    {
        try {
            ArrayList<String> usuariosActualizados = leerUsuarios();
            String usuarioAntiguo = usuariosActualizados.get(posicion);
            String usuarioActualizado = usuarioAntiguo.substring(0, usuarioAntiguo.lastIndexOf("=") + 1) + nivelNuevo;
            usuariosActualizados.remove(posicion);
            usuariosActualizados.add(posicion, usuarioActualizado);
            fileWriter = new FileWriter(UserLists, false);
            output = new BufferedWriter(fileWriter);
            for (String usuariosActualizado : usuariosActualizados) {
                output.write(usuariosActualizado);
                output.newLine();

            }
            output.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}