package archive.command;

import archive.ControllerArchive;
import archive.ZipFileManager;
import archive.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand{
    public ZipRemoveCommand(ControllerArchive controllerArchive) {
        super(controllerArchive);
    }

    @Override
    public void execute() throws Exception {
        try {
            controllerArchive.printMessageToView("Удаление файла из архива...\n");
            ZipFileManager zipFileManager = getZipFileManager();
            Path sourcePath = Paths.get(
                    controllerArchive.getPathFromView("Введите полный путь файла в архиве:",
                    "Ввод полного пути файла в архиве:"));
            zipFileManager.removeFile(sourcePath);
            controllerArchive.printMessageToView("Удаление из архива завершено.\n");
        } catch (WrongZipFileException e) {
            controllerArchive.printMessageToView("Проверьте существует ли zip файл.\n");
        }
    }
}
