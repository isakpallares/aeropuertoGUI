package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.*;

import persistence.repository.PasajeroRepository;
import persistence.repository.VueloRepository;

public class PasajeroView {

    private static void enviarFormulario(String nombre, String pasaporte, String equipaje) {
        System.out.println(nombre + " " + pasaporte + " " + equipaje);
    }

    public static JPanel crearPanelPasajero() {
        VueloRepository vueloRepository = new VueloRepository();
        PasajeroRepository repository = new PasajeroRepository();
        JPanel panelPasajero = new JPanel();

        JTextField nombreTextField = new JTextField(20);
        JTextField pasaporteTextField = new JTextField(15);
        JTextField equipajeTextField = new JTextField(5);
        JTextField puestoTextField = new JTextField(4);
        JTextField idTextField = new JTextField(4);

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel pasaporteLabel = new JLabel("Número de Pasaporte:");
        JLabel equipajeLabel = new JLabel("Cantidad de Equipaje:");
        JLabel numVueloLabel = new JLabel("Numero de Vuelo:");
        JComboBox<String> comboBoxVuelos = new JComboBox<>(vueloRepository.vuelosDisponibles().toArray(new String[0]));
        JLabel puestoLabel = new JLabel("Puesto:");
        JLabel idLabel = new JLabel("Tu identificador del viaje:");

        // manejador de eventos
        JButton enviarButton = new JButton("Guardar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!nombreTextField.getText().isEmpty() && !pasaporteTextField.getText().isEmpty()) {
                    try {
                        String uuid = UUID.randomUUID().toString();
                        boolean isSuccess = repository.insertarPasajero(nombreTextField.getText(),
                                pasaporteTextField.getText(), equipajeTextField.getText(),
                                Integer.parseInt(comboBoxVuelos.getSelectedItem().toString()),
                                Integer.parseInt(puestoTextField.getText()), uuid);
                        if (isSuccess) {
                            nombreTextField.setText("");
                            pasaporteTextField.setText("");
                            equipajeTextField.setText("");

                            idTextField.setText(uuid);
                        }
                        
                        JOptionPane.showMessageDialog(panelPasajero, "Se ha creado correctamente", "Creado",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelPasajero, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                enviarFormulario(nombreTextField.getText(), pasaporteTextField.getText(), equipajeTextField.getText());
            }
        });

        // Configuración del diseño
        panelPasajero.setLayout(new GridLayout(8, 2, 10, 10));

        // Agregar componentes al panel
        panelPasajero.add(nombreLabel);
        panelPasajero.add(nombreTextField);
        panelPasajero.add(pasaporteLabel);
        panelPasajero.add(pasaporteTextField);
        panelPasajero.add(equipajeLabel);
        panelPasajero.add(equipajeTextField);
        panelPasajero.add(numVueloLabel);
        panelPasajero.add(comboBoxVuelos);
        panelPasajero.add(puestoLabel);
        panelPasajero.add(puestoTextField);
        panelPasajero.add(new JLabel()); // Espacio en blanco
        panelPasajero.add(enviarButton);
        panelPasajero.add(idLabel);
        panelPasajero.add(idTextField);

        return panelPasajero;
    }

    public static JPanel calcularPrecioReserva() {
        return null;
    }

    public static JPanel verPasajero() {
        PasajeroRepository repository = new PasajeroRepository();
        JPanel panelPasajero = new JPanel();

        JTextField idTextField = new JTextField(20);
        JTextField nombreTextField = new JTextField(20);
        nombreTextField.setEnabled(false);
        JTextField pasaporteTextField = new JTextField(15);
        pasaporteTextField.setEnabled(false);
        JTextField equipajeTextField = new JTextField(5);
        equipajeTextField.setEnabled(false);
        JTextField puestoTextField = new JTextField(4);
        puestoTextField.setEnabled(false);

        JLabel idLabel = new JLabel("Coloca tu identificador del viaje:");
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel pasaporteLabel = new JLabel("Número de Pasaporte:");
        JLabel equipajeLabel = new JLabel("Cantidad de Equipaje:");
        JLabel numVueloLabel = new JLabel("Numero de Vuelo:");
        JComboBox<String> comboBoxVuelos = new JComboBox<>();
        comboBoxVuelos.setEnabled(false);
        JLabel puestoLabel = new JLabel("Puesto:");

        // manejador de eventos
        JButton enviarButton = new JButton("Buscar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!idTextField.getText().isEmpty()) {
                    try {
                        persistence.entity.Pasajero pasajero = repository.verPasajeroPorIdViaje(idTextField.getText());
                        if (pasajero != null) {
                            nombreTextField.setText(pasajero.getNombre());
                            pasaporteTextField.setText(pasajero.getNumeroPasaporte());
                            equipajeTextField.setText(String.valueOf(pasajero.getCantidadEquipaje()));
                            puestoTextField.setText(String.valueOf(pasajero.getPuesto()));
                            comboBoxVuelos.addItem(String.valueOf(pasajero.getNumVuelo()));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelPasajero, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
                // enviarFormulario(nombreTextField.getText(), pasaporteTextField.getText(),
                // equipajeTextField.getText());
            }
        });

        // Configuración del diseño
        panelPasajero.setLayout(new GridLayout(8, 2, 10, 10));

        // Agregar componentes al panel
        panelPasajero.add(idLabel);
        panelPasajero.add(idTextField);
        panelPasajero.add(new JLabel()); // Espacio en blanco
        panelPasajero.add(enviarButton);
        panelPasajero.add(nombreLabel);
        panelPasajero.add(nombreTextField);
        panelPasajero.add(pasaporteLabel);
        panelPasajero.add(pasaporteTextField);
        panelPasajero.add(equipajeLabel);
        panelPasajero.add(equipajeTextField);
        panelPasajero.add(numVueloLabel);
        panelPasajero.add(comboBoxVuelos);
        panelPasajero.add(puestoLabel);
        panelPasajero.add(puestoTextField);

        return panelPasajero;
    }

}
