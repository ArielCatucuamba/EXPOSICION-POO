import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar extends JFrame{
    private JTextField cuenta;
    private JButton buscarSegunTipoDeButton;
    private JPanel panel1;
    private JComboBox combo1;
    private JTextArea resultadoTextArea;
    private JButton regresarButton;

    public Buscar(){
        setContentPane(panel1);

        buscarSegunTipoDeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarTipo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        combo1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String valor=combo1.getSelectedItem().toString();
                cuenta.setText(valor);
            }
        });
        regresarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar r4 = new Registrar();
                r4.Iniciar();
                dispose();
            }
        });
    }
    public void buscarTipo() throws SQLException {
        String tipo=cuenta.getText();
        Connection conecta=conexion();
        String sql="select * from cliente where tipo_cuenta=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,tipo);
        ResultSet rs=pstmt.executeQuery();


        StringBuilder resultados = new StringBuilder();
        if (rs.next()) {
            resultados.append("Tipo de cuenta ").append(tipo).append(" existente\n");
            do {
                String nombre = rs.getString("nombre");
                resultados.append("Nombre: ").append(nombre).append("\n");
            } while (rs.next());
        } else {
            resultados.append("No se encontraron cuentas del tipo ").append(tipo).append("\n");
        }

        resultadoTextArea.setText(resultados.toString());
        conecta.close();
        pstmt.close();
        rs.close();

    }

    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/pruebaAC";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Buscar  clientes segun tipo de cuenta");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
