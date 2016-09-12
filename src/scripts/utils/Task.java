package scripts.utils;

import org.osbot.rs07.script.MethodProvider;

/**
 * Created by Jeremy on 8/25/2016.
 */
public abstract class Task extends Methods {

    protected MethodProvider api;
    protected Data d;
    protected Methods m;

    public Task(MethodProvider api, Methods m) {
        super(api);
        this.api = api;
        this.m = m;
        this.d = m.data();
    }

    public abstract boolean verify();

    public abstract void execute() throws InterruptedException;
}
