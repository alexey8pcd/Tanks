package ru.ovcharov_alexey.tanks.v4.engine.units.shell;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Предоставляет хранилище для снарядов, которые могут быть использованы
 * повторно
 *
 * @author alex
 */
public class ShellPool {

    private static ShellPool shellPool;

    public static ShellPool getInstance() {
        if (shellPool == null) {
            shellPool = new ShellPool();
        }
        return shellPool;
    }

    private final Queue<Shell> cash;

    private ShellPool() {
        cash = new LinkedList<>();
    }

    public Shell take() {
        if (cash.isEmpty()) {
            return new Shell();
        } else {
            Shell shell = cash.poll();
            shell.setLive(true);
            return shell;
        }
    }

    public void put(Shell missile) {
        cash.add(missile);
    }

}
