package tank.view;


import tank.model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class Scena extends JPanel  {

    private JMenuBar menuBar;
    private JFrame frame;

    public Scena (JFrame frame) {
        this.frame= frame;
        createMenuBar();
    }

    // Метод создания меню
    private void createMenuBar() {
        menuBar = new JMenuBar();
        // Создаем пункты в меню
        JMenu fileMenu = new JMenu("File");
        JMenu settingsMenu = new JMenu("Settings");
        JMenu aboutMenu = new JMenu("About");

        // Создаем элементы для каждого пункта
        JMenuItem fileItem = new JMenuItem("Open File Dialog");
        JMenuItem settingsItem = new JMenuItem("Open Settings Dialog");
        JMenuItem aboutItem = new JMenuItem("Open About Dialog");

        // Добавляем слушателей для элементов меню
        fileItem.addActionListener(e -> showCustomDialog("File Dialog"));
        settingsItem.addActionListener(e -> showCustomDialog("Settings Dialog"));
        aboutItem.addActionListener(e -> showCustomDialog("About Dialog"));

        // Добавляем элементы в соответствующие пункты меню
        fileMenu.add(fileItem);
        settingsMenu.add(settingsItem);
        aboutMenu.add(aboutItem);

        // Добавляем пункты в меню-бар
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(aboutMenu);

        // Устанавливаем меню-бар в главное окно (фрейм)
        if (frame != null) {
            frame.setJMenuBar(menuBar);
        }
    }

    private void showCustomDialog(String title) {
        // Создаем диалоговое окно
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setLayout(new FlowLayout());

        // Создаем кнопки
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        // Добавляем слушателей на кнопки
        button1.addActionListener(e -> {
            // Сюда вставляем код обработки событий для кнопки 1
            dialog.dispose(); // Закрыть диалоговое окно после клика
        });
        button2.addActionListener(e -> {
            // Сюда вставляем код обработки событий для кнопки 2
            dialog.dispose();
        });
        button3.addActionListener(e -> {
            // Сюда вставляем код обработки событий для кнопки 3
            dialog.dispose();
        });

        // Добавляем кнопки в диалоговое окно
        dialog.add(button1);
        dialog.add(button2);
        dialog.add(button3);

        // Устанавливаем размер и позицию диалогового окна
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true); // Показываем диалоговое окно
    }
}
