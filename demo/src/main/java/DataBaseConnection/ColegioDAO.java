package DataBaseConnection;

import POJO.Colegio;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColegioDAO {
    private final DBConnector conexionPostgreSQL = new DBConnector();
    private boolean resultado;

    public boolean agregarColegio(Colegio colegio) {
        resultado = false;
        String consulta = "INSERT INTO colegio(tipocolegio, ciudad, nombrecolegio, estado) VALUES(?,?,?,?)";
        try (Connection conexion = conexionPostgreSQL.getConnection();
            PreparedStatement declaracionPreparada = conexion.prepareStatement(consulta)) {
            declaracionPreparada.setString(1, colegio.getTipoColegio());
            declaracionPreparada.setString(2, colegio.getCiudad());
            declaracionPreparada.setString(3, colegio.getNombre());
            declaracionPreparada.setBoolean(4, colegio.isEstado());
            int filasAfectadas = declaracionPreparada.executeUpdate();
            resultado = (filasAfectadas > 0);
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(ColegioDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
        return resultado;
    }

    public boolean eliminarColegio(Colegio colegio) {
        resultado = false;
        String consulta = "UPDATE colegio SET estado = false WHERE idcolegio = ?";
        try (Connection conexion = conexionPostgreSQL.getConnection();
            PreparedStatement declaracionPreparada = conexion.prepareStatement(consulta)) {
            declaracionPreparada.setInt(1, colegio.getIdColegio());
            int filasAfectadas = declaracionPreparada.executeUpdate();
            resultado = (filasAfectadas > 0);
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(ColegioDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
        return resultado;
    }

    public void llenarTablaColegio(ObservableList<Colegio> listaColegios) {
        String consulta = "SELECT * FROM colegio WHERE estado = true";
        try (Connection conexion = conexionPostgreSQL.getConnection();
             Statement instruccion = conexion.createStatement();
             ResultSet resultadoConsulta = instruccion.executeQuery(consulta)) {
            while(resultadoConsulta.next()){
                Colegio colegio = new Colegio();
                llenarColegio(colegio, resultadoConsulta);
                listaColegios.add(colegio);
            }
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(ColegioDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
    }

    private static void llenarColegio(Colegio colegio, ResultSet conjuntoResultados) throws SQLException {
        colegio.setIdColegio(conjuntoResultados.getInt("idcolegio"));
        colegio.setTipoColegio(conjuntoResultados.getString("tipocolegio"));
        colegio.setCiudad(conjuntoResultados.getString("ciudad"));
        colegio.setNombre(conjuntoResultados.getString("nombrecolegio"));
        colegio.setEstado(conjuntoResultados.getBoolean("estado"));
    }

}
