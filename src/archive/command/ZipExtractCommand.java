package archive.command;
import archive.ControllerArchive;
import archive.ZipFileManager;
import archive.exception.PathIsNotFoundException;
import archive.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand{
    public ZipExtractCommand(ControllerArchive controllerArchive) {
        super(controllerArchive);
    }

    @Override
    public void execute() throws Exception {
        try {
            controllerArchive.printMessageToView("Распаковка архива...\n");
            ZipFileManager zipFileManager = getZipFileManager();
            Path sourcePath = Paths.get(controllerArchive.getPathFromView("Введите полную директорию для распаковки архива:",
                    "Ввод полной директории для распаковки архива:"));
            zipFileManager.extractAll(sourcePath);
            controllerArchive.printMessageToView("Архив распакован.\n");
        } catch (WrongZipFileException e) {
            controllerArchive.printMessageToView("Проверьте существует ли zip файл.\n");
        } catch (PathIsNotFoundException e) {
            controllerArchive.printMessageToView("Вы неверно указали директорию для распаковки.\n");
        }
    }
}
