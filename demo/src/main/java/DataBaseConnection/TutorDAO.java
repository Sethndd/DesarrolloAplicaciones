package DataBaseConnection;

import POJO.Tutor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TutorDAO {
    public TutorDAO() {
    }

    public static List<Tutor> getTutores() {
        Connection connection = DBConnector.getConnection();
        List<Tutor> tutores = new ArrayList();
        String query = "SELECT public.\"Tutor\".\"id\", \"id_estudiante\", \"primernom\", \"segundonom\", \"apellidopat\", \"apellidomat\", \"correo_electronico\", \"tel_casa\", \"tel_movil\", public.\"Tutor\".\"activo\", CONCAT(e.primer_nom, ' ', e.segundo_nom, ' ', e.primer_ape, ' ', e.segundo_ape)  AS estudiante FROM public.\"Tutor\" LEFT JOIN public.\"Estudiante\" e ON public .\"Tutor\".id_estudiante = e.id WHERE \"Tutor\".activo = true ORDER BY id ASC ;";

        try {
            PreparedStatement sentece = connection.prepareStatement(query);
            ResultSet resultSet = sentece.executeQuery();

            while(resultSet.next()) {
                Tutor tutor = new Tutor();
                tutor.setIdTutor(resultSet.getInt("id"));
                tutor.setIdEstudiante(resultSet.getInt("id_estudiante"));
                tutor.setPrimerNombreTutor(resultSet.getString("primernom"));
                tutor.setSegundoNombreTutor(resultSet.getString("segundonom"));
                tutor.setApellidoPatTutor(resultSet.getString("apellidopat"));
                tutor.setApellidoMatTutor(resultSet.getString("apellidomat"));
                tutor.setCorreoElectronico(resultSet.getString("correo_electronico"));
                tutor.setTelefonoFijo(resultSet.getString("tel_casa"));
                tutor.setTelefonoCelular(resultSet.getString("tel_movil"));
                tutor.setNombreEstudiante(resultSet.getString("estudiante"));
                tutor.setActivo(resultSet.getBoolean("activo"));
                tutores.add(tutor);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return tutores;
    }

    public static void guardar(Tutor tutor) {
        Connection connection = DBConnector.getConnection();

        try {
            String query = "INSERT INTO public. \"Tutor\" (id_estudiante, primernom, segundonom, apellidopat, apellidomat, correo_electronico, tel_casa, tel_movil, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tutor.getIdEstudiante());
            preparedStatement.setString(2, tutor.getPrimerNombreTutor());
            preparedStatement.setString(3, tutor.getSegundoNombreTutor());
            preparedStatement.setString(4, tutor.getApellidoPatTutor());
            preparedStatement.setString(5, tutor.getApellidoMatTutor());
            preparedStatement.setString(6, tutor.getCorreoElectronico());
            preparedStatement.setString(7, tutor.getTelefonoFijo());
            preparedStatement.setString(8, tutor.getTelefonoCelular());
            preparedStatement.setBoolean(9, tutor.isActivo());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public static void borrar(Tutor tutor) {
        Connection connection = DBConnector.getConnection();

        try {
            String query = "UPDATE public.\"Tutor\" SET activo=false WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tutor.getIdTutor());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }
}
