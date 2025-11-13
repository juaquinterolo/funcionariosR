package vista;

import dao.FuncionarioDAO;
import modelo.Funcionario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class FrmFuncionario extends JFrame {
    private JTextField txtId, txtTipo, txtNumero, txtNombres, txtApellidos, txtEstadoCivil, txtSexo, txtDireccion, txtTelefono, txtFechaNac;
    private JButton btnAgregar, btnEditar, btnEliminar, btnListar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private FuncionarioDAO dao = new FuncionarioDAO();

    public FrmFuncionario() {
        setTitle("Gestión de Funcionarios");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE FUNCIONARIOS", SwingConstants.CENTER);
        lblTitulo.setBounds(0, 10, 900, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 60, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 60, 80, 25);
        txtId.setEnabled(false);
        add(txtId);

        JLabel lblTipo = new JLabel("Tipo ID:");
        lblTipo.setBounds(200, 60, 80, 25);
        add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(260, 60, 80, 25);
        add(txtTipo);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(360, 60, 80, 25);
        add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(430, 60, 120, 25);
        add(txtNumero);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(30, 100, 80, 25);
        add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(100, 100, 180, 25);
        add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(300, 100, 80, 25);
        add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(380, 100, 180, 25);
        add(txtApellidos);

        JLabel lblEstado = new JLabel("Estado civil:");
        lblEstado.setBounds(580, 100, 100, 25);
        add(lblEstado);

        txtEstadoCivil = new JTextField();
        txtEstadoCivil.setBounds(670, 100, 150, 25);
        add(txtEstadoCivil);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(30, 140, 80, 25);
        add(lblSexo);

        txtSexo = new JTextField();
        txtSexo.setBounds(100, 140, 50, 25);
        add(txtSexo);

        JLabel lblDir = new JLabel("Dirección:");
        lblDir.setBounds(170, 140, 80, 25);
        add(lblDir);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(250, 140, 200, 25);
        add(txtDireccion);

        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setBounds(470, 140, 80, 25);
        add(lblTel);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(550, 140, 120, 25);
        add(txtTelefono);

        JLabel lblFecha = new JLabel("Nacimiento:");
        lblFecha.setBounds(690, 140, 80, 25);
        add(lblFecha);

        txtFechaNac = new JTextField();
        txtFechaNac.setBounds(770, 140, 100, 25);
        txtFechaNac.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFechaNac);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(100, 190, 100, 30);
        add(btnAgregar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(220, 190, 100, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(340, 190, 100, 30);
        add(btnEliminar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(460, 190, 100, 30);
        add(btnListar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(580, 190, 100, 30);
        add(btnLimpiar);

        // Tabla
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Tipo ID", "Número", "Nombres", "Apellidos", "Estado Civil", "Sexo", "Dirección", "Teléfono", "Nacimiento"});
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 240, 840, 300);
        add(scroll);

        // Listeners
        btnAgregar.addActionListener(e -> agregar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(modelo.getValueAt(fila, 0).toString());
                    txtTipo.setText(modelo.getValueAt(fila, 1).toString());
                    txtNumero.setText(modelo.getValueAt(fila, 2).toString());
                    txtNombres.setText(modelo.getValueAt(fila, 3).toString());
                    txtApellidos.setText(modelo.getValueAt(fila, 4).toString());
                    txtEstadoCivil.setText(modelo.getValueAt(fila, 5).toString());
                    txtSexo.setText(modelo.getValueAt(fila, 6).toString());
                    txtDireccion.setText(modelo.getValueAt(fila, 7).toString());
                    txtTelefono.setText(modelo.getValueAt(fila, 8).toString());
                    txtFechaNac.setText(modelo.getValueAt(fila, 9).toString());
                }
            }
        });
    }

    // Métodos CRUD
    private void agregar() {
        try {
            Funcionario f = new Funcionario(0,
                    txtTipo.getText(),
                    txtNumero.getText(),
                    txtNombres.getText(),
                    txtApellidos.getText(),
                    txtEstadoCivil.getText(),
                    txtSexo.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtFechaNac.getText());
            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Funcionario agregado con éxito");
            listar();
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            modelo.setRowCount(0);
            List<Funcionario> lista = dao.listar();
            for (Funcionario f : lista) {
                modelo.addRow(new Object[]{
                        f.getIdFuncionario(),
                        f.getTipoIdentificacion(),
                        f.getNumeroIdentificacion(),
                        f.getNombres(),
                        f.getApellidos(),
                        f.getEstadoCivil(),
                        f.getSexo(),
                        f.getDireccion(),
                        f.getTelefono(),
                        f.getFechaNacimiento()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + ex.getMessage());
        }
    }

    private void editar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un funcionario primero");
            return;
        }
        try {
            Funcionario f = new Funcionario(
                    Integer.parseInt(txtId.getText()),
                    txtTipo.getText(),
                    txtNumero.getText(),
                    txtNombres.getText(),
                    txtApellidos.getText(),
                    txtEstadoCivil.getText(),
                    txtSexo.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtFechaNac.getText());
            dao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente");
            listar();
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un funcionario primero");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(this, "Funcionario eliminado");
            listar();
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtTipo.setText("");
        txtNumero.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEstadoCivil.setText("");
        txtSexo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtFechaNac.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmFuncionario().setVisible(true));
    }
}