package DataBaseConnection;

import POJO.InformacionSalud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
;

public class InformacionSaludDAO {

    public static void guardar(InformacionSalud informacionSalud){
        Connection connection = DBConnector.getConnection();
        try {
            String query = "INSERT INTO public.\"Info_Salud\" (numero_seguro_social, nombre_entidad, grupo_sanguineo, id_estudiante) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, informacionSalud.getNumeroSeguroSocial());
            preparedStatement.setString(2, informacionSalud.getNombreEntidad());
            preparedStatement.setString(3, informacionSalud.getGrupoSanguineo());
            preparedStatement.setInt(4, informacionSalud.getIdEstudiante());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static InformacionSalud getInformacionSaludeByIdEstudiante(int idEstudiante){
        Connection connection = DBConnector.getConnection();

        String query = "SELECT * FROM public.\"Info_Salud\" WHERE id_estudiante = ?;";

        InformacionSalud informacionSalud = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEstudiante);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                informacionSalud = new InformacionSalud(
                        resultSet.getInt("numero_seguro_social"),
                        resultSet.getString("nombre_entidad"),
                        resultSet.getString("grupo_sanguineo"),
                        resultSet.getInt("id_estudiante"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return informacionSalud;
    }
}
