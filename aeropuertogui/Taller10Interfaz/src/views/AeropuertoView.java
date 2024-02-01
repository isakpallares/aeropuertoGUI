package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import persistence.repository.AeropuertoRepository;
import persistence.repository.VueloRepository;

public class AeropuertoView {

    public static JPanel crearArepuerto() {
        AeropuertoRepository repository = new AeropuertoRepository();
        JPanel crearAerpuertoPanel = new JPanel();
        JTextField nombreTextField, ubicacionTextField;

        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel ubicacionLabel = new JLabel("Ubicación:");

        nombreTextField = new JTextField(10);
        ubicacionTextField = new JTextField(20);

        JButton enviarButton = new JButton("Enviar");

        crearAerpuertoPanel.setLayout(new GridLayout(6, 2, 10, 10));

        crearAerpuertoPanel.add(nombreLabel);
        crearAerpuertoPanel.add(nombreTextField);
        crearAerpuertoPanel.add(ubicacionLabel);
        crearAerpuertoPanel.add(ubicacionTextField);
        crearAerpuertoPanel.add(new JLabel());
        crearAerpuertoPanel.add(enviarButton);
        
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!nombreTextField.getText().isEmpty() && !ubicacionTextField.getText().isEmpty()) {
                    try {
                        boolean isSuccess = repository.insertarAeropuerto(nombreTextField.getText(),
                                ubicacionTextField.getText());
                        if (isSuccess) {
                            nombreTextField.setText("");
                            ubicacionTextField.setText("");
                        }
                        JOptionPane.showMessageDialog(crearAerpuertoPanel, "Se ha creado correctamente", "Creado",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(crearAerpuertoPanel, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return crearAerpuertoPanel;
    }
    
    public static JPanel eliminarAeropuerto() {
        AeropuertoRepository repository = new AeropuertoRepository();
        JPanel eliminarAerpuertoPanel = new JPanel();
        JTextField nombreTextField;

        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");

        nombreTextField = new JTextField(10);

        JButton enviarButton = new JButton("Enviar");

        eliminarAerpuertoPanel.setLayout(new GridLayout(6, 2, 10, 10));

        eliminarAerpuertoPanel.add(nombreLabel);
        eliminarAerpuertoPanel.add(nombreTextField);
        eliminarAerpuertoPanel.add(new JLabel());
        eliminarAerpuertoPanel.add(enviarButton);
        
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!nombreTextField.getText().isEmpty()) {
                    try {
                        boolean isSuccess = repository.eliminarAeropuerto(nombreTextField.getText());
                        if (isSuccess) {
                            nombreTextField.setText("");
                        }
                        JOptionPane.showMessageDialog(eliminarAerpuertoPanel, "Se ha eliminado correctamente", "Eliminado",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(eliminarAerpuertoPanel, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return eliminarAerpuertoPanel;
    }
    
    public static JPanel verAeropuertos() {
        VueloRepository vueloRepository = new VueloRepository();
        AeropuertoRepository repository = new AeropuertoRepository();
        JPanel verAerpuertoPanel = new JPanel();
        JTable aeropuertosTable;

        // Crear componentes
        DefaultTableModel model = new DefaultTableModel();
        aeropuertosTable = new JTable(model);

        // Agregar columnas a la tabla
        model.addColumn("Nombre");
        model.addColumn("Ubicación");
        model.addColumn("Numero de Vuelos");

        try {
            List<persistence.entity.Aeropuerto> aeropuertos = repository.verAeropuerto();

            for (persistence.entity.Aeropuerto a : aeropuertos) {
                agregarAeropuerto(a.getNombre(), a.getUbicacion(),
                        vueloRepository.obtenerCantidadVuelosPorAir(a.getId()),
                        aeropuertosTable);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(verAerpuertoPanel, e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    
        JScrollPane scrollPane = new JScrollPane(aeropuertosTable);
        verAerpuertoPanel.add(scrollPane, BorderLayout.CENTER);

        return verAerpuertoPanel;
    }

    private static void agregarAeropuerto(String nombre, String ubicacion, Integer numVuelos, JTable aeropuertosTable) {
        DefaultTableModel model = (DefaultTableModel) aeropuertosTable.getModel();
        model.addRow(new Object[] { nombre, ubicacion, numVuelos });
    }
}
