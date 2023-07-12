package archive;

import archive.command.*;

import java.util.HashMap;
import java.util.Map;

public class ControllerArchive {
    private ViewArchive viewArchive;
    private ZipCommand zipCommand;
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    {
        allKnownCommandsMap.put(Operation.CREATE, new ZipCreateCommand(this));
        allKnownCommandsMap.put(Operation.ADD, new ZipAddCommand(this));
        allKnownCommandsMap.put(Operation.REMOVE, new ZipRemoveCommand(this));
        allKnownCommandsMap.put(Operation.EXTRACT, new ZipExtractCommand(this));
        allKnownCommandsMap.put(Operation.CONTENT, new ZipContentCommand(this));
    }
    public ControllerArchive(ViewArchive viewArchive) {
        this.viewArchive = viewArchive;
    }

    public ZipCommand getZipCommand() {
        return zipCommand;
    }

    public void setZipCommand(ZipCommand zipCommand) {
        this.zipCommand = zipCommand;
    }

    public void execute(Operation operation) {
        try {
            allKnownCommandsMap.get(operation).execute();
        } catch (Exception exception) {
            printMessageToView("Произошла ошибка. Проверьте введенные данные.\n");
        }
    }
    public void printMessageToView(String info) {
        viewArchive.refreshDialogWindowServer(info);
    }
    public String getPathFromView(String info, String title) {
        return viewArchive.getPath(info, title);
    }
}
