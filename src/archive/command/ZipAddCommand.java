package archive.command;

import archive.ControllerArchive;
import archive.ZipFileManager;
import archive.exception.PathIsNotFoundException;
import archive.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand{
    public ZipAddCommand(ControllerArchive controllerArchive) {
        super(controllerArchive);
    }

    @Override
    public void execute() throws Exception {
        try {
            controllerArchive.printMessageToView("Добавление нового файла в архив...\n");
            ZipFileManager zipFileManager = getZipFileManager();
            String path = controllerArchive.getPathFromView("Введите полное имя файла для добавления:",
                    "Ввод полного имени файла для добавления");
            Path sourcePath = Paths.get(path);
            zipFileManager.addFile(sourcePath);
            controllerArchive.printMessageToView("Добавление в архив завершено.\n");
        } catch (PathIsNotFoundException e) {
            controllerArchive.printMessageToView("Файл не был найден.\n");
        } catch (WrongZipFileException e) {
            controllerArchive.printMessageToView("Проверьте существует ли zip файл.\n");
        }
    }
}
