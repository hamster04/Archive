package archive.command;

import archive.ControllerArchive;
import archive.FileProperties;
import archive.ZipFileManager;
import archive.exception.WrongZipFileException;

import java.util.List;

public class ZipContentCommand extends ZipCommand {
    public ZipContentCommand(ControllerArchive controllerArchive) {
        super(controllerArchive);
    }

    @Override
    public void execute() throws Exception {
        try {
            controllerArchive.printMessageToView("Просмотр содержимого архива.\n");
            ZipFileManager zipFileManager = getZipFileManager();
            controllerArchive.printMessageToView("Содержимое архива:\n");
            List<FileProperties> files = zipFileManager.getFilesList();
            for (FileProperties file : files) {
                controllerArchive.printMessageToView(file.toString() + "\n");
            }
            controllerArchive.printMessageToView("Содержимое архива прочитано.\n");
        } catch (WrongZipFileException e) {
            controllerArchive.printMessageToView("Проверьте существует ли zip файл");
        }
    }
}
