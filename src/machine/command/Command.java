package machine.command;

import machine.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
