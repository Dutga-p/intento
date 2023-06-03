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
    public static final String usuariosListados = "src/FilesTxt/Users.txt";
    public static final String bancoDePalabras = "src/myProject/files/TwoHuundredWords.txt";
    private ArrayList users,words;
    private ArrayList<String> NombreYNivel;
    private DefaultListModel usuariosModel;
    public void escribirTexto(String linea) {
        try
        {
            fileWriter = new FileWriter(usuariosListados, true);
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
        String PalabrasLeidas = bancoDePalabras;
        users = new ArrayList<>();
        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(PalabrasLeidas, StandardCharsets.UTF_8);
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
        String ArchivoLeido = usuariosListados;
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
            String Jugador = NombreYNivel.get(i).substring(0, NombreYNivel.get(i).lastIndexOf(":"))+" Nivel "+ NombreYNivel.get(i).substring(0, NombreYNivel.get(i).indexOf(":"));
            usuariosModel.addElement(Jugador);
        }
        return usuariosModel;
    }
}