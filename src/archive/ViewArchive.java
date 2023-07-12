package archive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewArchive {
    private ControllerArchive controllerArchive = new ControllerArchive(this);
    private JFrame frame = new JFrame("Archiver");
    private JTextArea dialogWindow = new JTextArea(10, 40);
    private JButton archiveFiles= new JButton("Упаковать файлы в архив");
    private JButton addFilesToArchive = new JButton("Добавить файл в архив");
    private JButton deleteFilesFromArchive = new JButton("Удалить файл из архива");
    private JButton unpackTheArchive = new JButton("Распаковать архив");
    private JButton contentsArchive = new JButton("Просмотреть содержимое архива");
//    private JButton exitFromArchive = new JButton("Выход");
    private JPanel panelButtons = new JPanel();

    protected void initFrameServer() {
        dialogWindow.setEditable(false); // текст в доступен только для чтения, редактировать его нельзя
        dialogWindow.setLineWrap(true);  // автоматический перенос строки в JTextArea
        frame.add(new JScrollPane(dialogWindow), BorderLayout.CENTER);// добавляем текстовую область в окно с JScrollPane (прокрутка)
        panelButtons.setLayout(new GridLayout(2,2));
        panelButtons.add(archiveFiles);//добавление кнопки
        panelButtons.add(addFilesToArchive);//добавление кнопки
        panelButtons.add(deleteFilesFromArchive);//добавление кнопки
        panelButtons.add(unpackTheArchive);//добавление кнопки
        //panelButtons.add(contentsArchive);//добавление кнопки
        //panelButtons.add(exitFromArchive);// добавление кнопки
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.add(panelButtons);// добавляем панель в панель
        westPanel.add(contentsArchive, BorderLayout.PAGE_END);// и кнопку в панель панели
        frame.add(westPanel, BorderLayout.SOUTH);// добавляем в окно
        //frame.add(panelButtons, BorderLayout.SOUTH);// добавление панели с кнопками вниз
        frame.setLocationRelativeTo(null); // задает расположение окна относительно указанного компонента.
        // Если компонент имеет значение null, окно центрируется на экране.
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();// упаковывает компоненты в окне на основе предпочтительных размеров компонента
        //класс обработки события при закрытии окна приложения Сервера
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {// добавление слушателя на окно
                // как только окно закрывается, сервер необходимо остановить и закрыть программу корректно
                System.exit(0);
            }
        });
        frame.setVisible(true); //сделать окно видимым
        archiveFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerArchive.execute(Operation.CREATE);
            }
        });
        addFilesToArchive.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              controllerArchive.execute(Operation.ADD);
           }
       });
        deleteFilesFromArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerArchive.execute(Operation.REMOVE);
            }
        });
        unpackTheArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerArchive.execute(Operation.EXTRACT);
            }
        });
        contentsArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerArchive.execute(Operation.CONTENT);
            }
        });
    }

    //метод который добавляет в текстовое окно новое сообщение
    public void refreshDialogWindowServer(String serviceMessage) {
        dialogWindow.append(serviceMessage);
    }

    //метод вызывающий диалоговое окно для ввода порта сервера
    public String getPath(String info, String title) {
        while (true) {
            String arcPath = JOptionPane.showInputDialog(//Диалоговое окно с выбором
                    frame, info, title,
                    JOptionPane.INFORMATION_MESSAGE);
            //frame - родительское окно
            //message - отображаемый в окне текст сообщения.
            //title - заголовок окна
            //messageType - тип диалогового окна
            //INFORMATION_MESSAGE - стандартное диалоговое окно для вывода информации со значком соответствующего вида;
            try {
                return arcPath;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        frame, "Вы неверно указали имя файла или директории.",
                        "Ошибка ввода файла или директории", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
