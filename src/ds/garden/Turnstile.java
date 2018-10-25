package ds.garden;

import java.util.function.Consumer;

/**
 * Created by Teppei Shiroyama under MIT License.
 */
public class Turnstile extends Object implements Runnable {

    private final Counter counter;
    private final String name;
    private final Integer limit;
    private final Consumer<Turnstile> listener;

    public Turnstile(Counter counter, Integer limit, String name, Consumer<Turnstile> listener) {
        this.counter = counter;
        this.name = name;
        this.limit = limit;
        this.listener = listener;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLimit() {
        return this.limit;
    }

    @Override
    public void run() {
        for(var count = 0; count < this.limit; count++) {
            this.counter.writeValue();
        }
        this.listener.accept(this);
    }
}
