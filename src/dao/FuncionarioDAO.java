package dao;

import conexion.Conexion;
import modelo.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void insertar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setString(9, f.getFechaNacimiento());
            ps.executeUpdate();
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("tipo_identificacion"),
                        rs.getString("numero_identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("estado_civil"),
                        rs.getString("sexo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento")
                );
                lista.add(f);
            }
        }
        return lista;
    }

    public void actualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionarios SET tipo_identificacion=?, numero_identificacion=?, nombres=?, apellidos=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id_funcionario=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setString(9, f.getFechaNacimiento());
            ps.setInt(10, f.getIdFuncionario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idFuncionario) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
        }
    }
}