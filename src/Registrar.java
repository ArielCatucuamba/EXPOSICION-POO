import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar extends JFrame{
    private JButton ingresarElRegistroDelButton;
    private JTextField idd;
    private JTextField tip;
    private JTextField sal;
    private JTextField nom;
    private JTextField ape;
    private JTextField dir;
    private JPanel panel1;
    private JButton buscarPorTipoDeButton;

    public Registrar(){
        setContentPane(panel1);

        ingresarElRegistroDelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buscarPorTipoDeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar b3=new Buscar();
                b3.Iniciar();
                dispose();
            }
        });
    }

    public void registro() throws SQLException {
        String IDE=idd.getText();
        String TIP=tip.getText();
        String SAL=sal.getText();
        String NOM=nom.getText();
        String APE=ape.getText();
        String DIR=dir.getText();

        Connection conecta = conexion();
        String sql="insert into cliente(id_cliente,tipo_cuenta,saldo_inicial,nombre,apellido,direccion) values" +
                "(?,?,?,?,?,?)";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(IDE));
        pstmt.setString(2,TIP);
        pstmt.setDouble(3,Double.parseDouble(SAL));
        pstmt.setString(4,NOM);
        pstmt.setString(5,APE);
        pstmt.setString(6,DIR);

        int ariel=pstmt.executeUpdate();
        if (ariel>0){
            JOptionPane.showConfirmDialog(null,"REGISTRO EXITOSO");
        }else {
            JOptionPane.showConfirmDialog(null,"REGISTRO NO EXITOSO");
        }
        conecta.close();
        pstmt.close();


    }

    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/pruebaAC";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Registro de nuevos clientes ");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
