package archive.command;
import archive.ControllerArchive;
import archive.ZipFileManager;
import archive.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipCreateCommand extends ZipCommand {
    public ZipCreateCommand(ControllerArchive controllerArchive) {
        super(controllerArchive);
    }

    @Override
    public void execute() throws Exception {
        try {
            controllerArchive.printMessageToView("Создание архива...\n");
            ZipFileManager zipFileManager = getZipFileManager();
            String path = controllerArchive.getPathFromView("Введите полное имя файла или директории для архивации:",
                    "Ввод полного имени файла или директории для архивации:");
            Path sourcePath = Paths.get(path);
            zipFileManager.createZip(sourcePath);
            controllerArchive.printMessageToView("Архив создан.\n");
        } catch (PathIsNotFoundException e) {
            controllerArchive.printMessageToView("Вы неверно указали имя файла или директории.\n");
        }
    }
}
