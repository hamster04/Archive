package archive.command;

import archive.ControllerArchive;
import archive.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command {
    protected ControllerArchive controllerArchive;

    public ZipCommand(ControllerArchive controllerArchive) {
        this.controllerArchive = controllerArchive;
    }

    public ZipFileManager getZipFileManager() throws Exception {

        Path zipPath = Paths.get(controllerArchive.getPathFromView("Введите полный путь файла архива:",
                "Ввод полного пути файла архива"));
        return new ZipFileManager(zipPath, controllerArchive);
    }
}