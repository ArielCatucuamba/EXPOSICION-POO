import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JButton validacionDeClientesButton;
    private JTextField con;
    private JPanel panel1;

    public Login(){
        setTitle("Logueo de usaurios ");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);

        validacionDeClientesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void validar() throws SQLException {
        String contraseña=con.getText();

        Connection conecta=conexion();
        String sql="select * from usuario where contraseña=?";
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1,contraseña);

        ResultSet rs=pstmt.executeQuery();
        if (rs.next()){
            JOptionPane.showConfirmDialog(null,"---------- ACCESO CORRECTO ----------");
            Registrar r1=new Registrar();
            r1.Iniciar();
            dispose();

        }else {
            JOptionPane.showConfirmDialog(null,"---------- ACCESO INCORRECTO ----------");
            con.setText("");
        }
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
        setTitle("Logueo de usaurios ");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
