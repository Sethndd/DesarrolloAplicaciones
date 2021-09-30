package DataBaseConnection;

import POJO.HistoriaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriaAcademicaDAO {
    public static List<HistoriaAcademica> getHistoriasAcademicasActivas(){
        Connection connection = DBConnector.getConnection();
        String query = "SELECT \"idHistoria_academica\", \"idEstudiante\", colegio_anterior, ano, grado, h.activo, CONCAT(e.primer_nom, ' ', e.segundo_nom, ' ', e.primer_ape, ' ', e.segundo_ape) AS Estudiante " +
                "FROM public.\"Historia_Academica\" h " +
                "LEFT JOIN \"estudiante\" e ON \"idEstudiante\" = e.id " +
                "WHERE h.activo = true;";
        List<HistoriaAcademica> historiaAcademicas = new ArrayList<>();
        try {
            PreparedStatement sentence = connection.prepareStatement(query);
            ResultSet resultSet = sentence.executeQuery();
            HistoriaAcademica historiaAcademica;
            while (resultSet.next()){
                historiaAcademica = new HistoriaAcademica();
                historiaAcademica.setIdHistoriaAcademica(resultSet.getInt("idHistoria_academica"));
                historiaAcademica.setIdEstudiante(resultSet.getInt("idEstudiante"));
                historiaAcademica.setColegioAnterior(resultSet.getString("colegio_anterior"));
                historiaAcademica.setAño(resultSet.getString("ano"));
                historiaAcademica.setGrado(resultSet.getString("grado"));
                historiaAcademica.setNombreEstudiante(resultSet.getString("estudiante"));
                historiaAcademicas.add(historiaAcademica);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return historiaAcademicas;
    }

    public static void guardar(HistoriaAcademica historiaAcademica){
        Connection connection = DBConnector.getConnection();
        try {
            String query = "INSERT INTO public.\"Historia_Academica\"(\"idEstudiante\", colegio_anterior, ano, grado, activo) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, historiaAcademica.getIdEstudiante());
            preparedStatement.setString(2, historiaAcademica.getColegioAnterior());
            preparedStatement.setString(3, historiaAcademica.getAño());
            preparedStatement.setString(4, historiaAcademica.getGrado());
            preparedStatement.setBoolean(5, true);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void borrar(HistoriaAcademica historiaAcademica){
        Connection connection = DBConnector.getConnection();
        try {
            String query = "UPDATE public.\"Historia_Academica\" SET activo=false WHERE idHistoria_academica = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, historiaAcademica.getIdHistoriaAcademica());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
