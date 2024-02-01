package views;

import javax.swing.*;

import persistence.repository.AeropuertoRepository;
import persistence.repository.VueloRepository;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueloView {

    public static JPanel crearVuelo() {
        AeropuertoRepository aeropuertoRepository = new AeropuertoRepository();
        VueloRepository repository = new VueloRepository();
        JPanel panelVuelo = new JPanel();
        JTextField numeroVueloField, aerolineaField, horaSalidaField, destinoField, capacidadMaximaField;

        // Crear componentes
        JLabel numeroVueloLabel = new JLabel("Número de Vuelo:");
        JLabel aerolineaLabel = new JLabel("Aerolínea:");
        JLabel horaSalidaLabel = new JLabel("Hora de Salida:");
        JLabel destinoLabel = new JLabel("Destino:");
        JLabel capacidadMaximaLabel = new JLabel("Capacidad Máxima:");
        JLabel aeropuertoPartidaLabel = new JLabel("Aeropuerto de Partida:");
        JLabel aeropuertoLlegadaLabel = new JLabel("Aeropuerto de Llegada:");

        numeroVueloField = new JTextField(20);
        aerolineaField = new JTextField(20);
        horaSalidaField = new JTextField(20);
        destinoField = new JTextField(20);
        capacidadMaximaField = new JTextField(20);
        JComboBox<String> comboBoxIda = new JComboBox<>(aeropuertoRepository.aeropuertos().toArray(new String[0]));
        JComboBox<String> comboBoxDestino = new JComboBox<>(aeropuertoRepository.aeropuertos().toArray(new String[0]));

        JButton enviarButton = new JButton("Enviar");

        panelVuelo.setLayout(new GridLayout(8, 2, 10, 10));

        // Agregar componentes al formulario
        panelVuelo.add(numeroVueloLabel);
        panelVuelo.add(numeroVueloField);
        panelVuelo.add(aerolineaLabel);
        panelVuelo.add(aerolineaField);
        panelVuelo.add(horaSalidaLabel);
        panelVuelo.add(horaSalidaField);
        panelVuelo.add(destinoLabel);
        panelVuelo.add(destinoField);
        panelVuelo.add(capacidadMaximaLabel);
        panelVuelo.add(capacidadMaximaField);
        panelVuelo.add(aeropuertoPartidaLabel);
        panelVuelo.add(comboBoxIda);
        panelVuelo.add(aeropuertoLlegadaLabel);
        panelVuelo.add(comboBoxDestino);
        panelVuelo.add(new JLabel()); // Espacio en blanco
        panelVuelo.add(enviarButton);

        // Agregar el manejador de eventos al botón
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!numeroVueloField.getText().isEmpty()) {
                    try {
                        boolean isSuccess = repository.crearVuelo(Integer.parseInt(numeroVueloField.getText()),
                                aerolineaField.getText(), horaSalidaField.getText(), destinoField.getText(),
                                Integer.parseInt(capacidadMaximaField.getText()),
                                comboBoxIda.getSelectedItem().toString(), comboBoxDestino.getSelectedItem().toString());
                        if (isSuccess) {
                            numeroVueloField.setText("");
                            aerolineaField.setText("");
                            horaSalidaField.setText("");
                            destinoField.setText("");
                            capacidadMaximaField.setText("");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelVuelo, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        return panelVuelo;
    }

    public static JPanel verDetallesVuelo() {
        VueloRepository repository = new VueloRepository();
        JPanel panelVuelo = new JPanel();
        JTextField numeroVueloField, aerolineaField, horaSalidaField, destinoField, capacidadMaximaField,
                numeroVueloFieldQuest;

        // Crear componentes
        JLabel numeroVueloLabelQuest = new JLabel("Escriba el numero del vuelo: ");
        JLabel numeroVueloLabel = new JLabel("Número de Vuelo:");
        JLabel aerolineaLabel = new JLabel("Aerolínea:");
        JLabel horaSalidaLabel = new JLabel("Hora de Salida:");
        JLabel destinoLabel = new JLabel("Destino:");
        JLabel capacidadMaximaLabel = new JLabel("Capacidad Máxima:");
        JLabel aeropuertoPartidaLabel = new JLabel("Aeropuerto de Partida:");
        JLabel aeropuertoLlegadaLabel = new JLabel("Aeropuerto de Llegada:");

        numeroVueloFieldQuest = new JTextField(20);
        numeroVueloField = new JTextField(20);
        numeroVueloField.setEnabled(false);
        aerolineaField = new JTextField(20);
        aerolineaField.setEnabled(false);
        horaSalidaField = new JTextField(20);
        horaSalidaField.setEnabled(false);
        destinoField = new JTextField(20);
        destinoField.setEnabled(false);
        capacidadMaximaField = new JTextField(20);
        capacidadMaximaField.setEnabled(false);
        JComboBox<String> comboBoxIda = new JComboBox<>();
        comboBoxIda.setEnabled(false);
        JComboBox<String> comboBoxDestino = new JComboBox<>();
        comboBoxDestino.setEnabled(false);

        JButton enviarButton = new JButton("Buscar");

        panelVuelo.setLayout(new GridLayout(10, 2, 10, 10));

        // Agregar componentes al formulario
        panelVuelo.add(numeroVueloLabelQuest);
        panelVuelo.add(numeroVueloFieldQuest);
        panelVuelo.add(new JLabel()); // Espacio en blanco
        panelVuelo.add(enviarButton);
        panelVuelo.add(numeroVueloLabel);
        panelVuelo.add(numeroVueloField);
        panelVuelo.add(aerolineaLabel);
        panelVuelo.add(aerolineaField);
        panelVuelo.add(horaSalidaLabel);
        panelVuelo.add(horaSalidaField);
        panelVuelo.add(destinoLabel);
        panelVuelo.add(destinoField);
        panelVuelo.add(capacidadMaximaLabel);
        panelVuelo.add(capacidadMaximaField);
        panelVuelo.add(aeropuertoPartidaLabel);
        panelVuelo.add(comboBoxIda);
        panelVuelo.add(aeropuertoLlegadaLabel);
        panelVuelo.add(comboBoxDestino);

        // Agregar el manejador de eventos al botón
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!numeroVueloFieldQuest.getText().isEmpty()) {
                    try {
                        persistence.entity.Vuelo vuelo = repository
                                .getVueloPorNumVuelo(Integer.parseInt(numeroVueloFieldQuest.getText()));
                        if (vuelo != null) {
                            numeroVueloFieldQuest.setText("");

                            numeroVueloField.setText(String.valueOf(vuelo.getNumeroVuelo()));
                            aerolineaField.setText(vuelo.getAerolinea());
                            horaSalidaField.setText(vuelo.getHoraSalida());
                            destinoField.setText(vuelo.getDestino());
                            capacidadMaximaField.setText(String.valueOf(vuelo.getCapacidadMaxima()));
                            comboBoxIda.addItem(vuelo.getNombreAirIda());
                            comboBoxDestino.addItem(vuelo.getNombreAirDest());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelVuelo, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        return panelVuelo;
    }

}
