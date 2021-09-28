package DataBaseConnection;

import POJO.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public static List<Estudiante> getEstudiantesActivos(){
        Connection connection = DBConnector.getConnection();
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM public.\"estudiante\" WHERE activo = true ORDER BY id ASC ;";

        try{
            PreparedStatement sentence = connection.prepareStatement(query);
            ResultSet resultSet = sentence.executeQuery();
            Estudiante estudiante;
            while (resultSet.next()){
                estudiante = new Estudiante(
                        resultSet.getInt("id"),
                        resultSet.getString("primer_ape"),
                        resultSet.getString("segundo_ape"),
                        resultSet.getString("primer_nom"),
                        resultSet.getString("segundo_nom"),
                        resultSet.getBoolean("activo"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }

    public static void guardar(Estudiante estudiante){
        Connection connection = DBConnector.getConnection();
        try {
            String query = "INSERT INTO public.\"estudiante\" (primer_ape, segundo_ape, primer_nom, segundo_nom, activo) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estudiante.getPrimerApe());
            preparedStatement.setString(2, estudiante.getSegundoApe());
            preparedStatement.setString(3, estudiante.getPrimerNom());
            preparedStatement.setString(4, estudiante.getSegundoNom());
            preparedStatement.setBoolean(5, estudiante.isActivo());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void borrar(Estudiante estudiante){
        Connection connection = DBConnector.getConnection();

        try {
            String query = "UPDATE public.\"estudiante\" SET activo=false WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, estudiante.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
