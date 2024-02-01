import views.AeropuertoView;
import views.PasajeroView;
import views.VueloView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class Main extends JFrame {

    private static final Color Color = null;
    private JPanel panelPrincipal;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    public Main() {
        // Configuración de la ventana principal
        setTitle("Sistema de Aeropuertos");
        setSize(500, 500);
        setBounds(600, 300, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.GREEN);
        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú Aeropuerto
        JMenu aeropuertoMenu = new JMenu("Aeropuerto");
        JMenuItem crearAeropuertoItem = new JMenuItem("Crear Aeropuerto");
        JMenuItem verAeropuertoItem = new JMenuItem("Ver Aeropuerto");
        JMenuItem eliminarAeropuertoItem = new JMenuItem("Eliminar Aeropuerto");
    
        crearAeropuertoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelaeropuerto = AeropuertoView.crearArepuerto();
                panelPrincipal.removeAll();
                panelPrincipal.add(panelaeropuerto, BorderLayout.CENTER);
                
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });
        
        eliminarAeropuertoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelaeropuerto = AeropuertoView.eliminarAeropuerto();
                panelPrincipal.removeAll();
                panelPrincipal.add(panelaeropuerto, BorderLayout.CENTER);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        verAeropuertoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelaeropuerto = AeropuertoView.verAeropuertos();
                panelPrincipal.removeAll();
                panelPrincipal.add(panelaeropuerto, BorderLayout.SOUTH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        aeropuertoMenu.add(crearAeropuertoItem);
        aeropuertoMenu.add(verAeropuertoItem);
        aeropuertoMenu.add(eliminarAeropuertoItem);
        
        

        // Menú Pasajeros
        JMenu pasajerosMenu = new JMenu("Pasajeros");
        JMenuItem crearPasajeroItem = new JMenuItem("Crear Pasajero");
        JMenuItem verPasajeroItem = new JMenuItem("Ver Pasajero");

        crearPasajeroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelPasajero = PasajeroView.crearPanelPasajero();
                panelPrincipal.removeAll();
                panelPrincipal.add(panelPasajero, BorderLayout.CENTER);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        verPasajeroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelPasajero = PasajeroView.verPasajero();
                panelPrincipal.removeAll();
                panelPrincipal.add(panelPasajero, BorderLayout.SOUTH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        pasajerosMenu.add(crearPasajeroItem);
        pasajerosMenu.add(verPasajeroItem);
        // pasajerosMenu.add(precioReservaItem);

        // Menú Vuelo
        JMenu vueloMenu = new JMenu("Vuelo");
        JMenuItem crearVueloItem = new JMenuItem("Crear Vuelo");
        JMenuItem mostrarInformacionItem = new JMenuItem("Ver informacion de vuelo");

        crearVueloItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel vuelo = VueloView.crearVuelo();
                panelPrincipal.removeAll();
                panelPrincipal.add(vuelo, BorderLayout.CENTER);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        mostrarInformacionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel vuelo = VueloView.verDetallesVuelo();
                panelPrincipal.removeAll();
                panelPrincipal.add(vuelo, BorderLayout.CENTER);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        vueloMenu.add(crearVueloItem);
        vueloMenu.add(mostrarInformacionItem);
        menuBar.add(aeropuertoMenu);
        menuBar.add(pasajerosMenu);
        menuBar.add(vueloMenu);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout()); 

        
        add(panelPrincipal);
        
        setJMenuBar(menuBar);
    }
}
