package ds.garden;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teppei Shiroyama under MIT License.
 */
public class Garden extends Object {
    private final Counter counter;
    private final List<Turnstile> turnstiles;
    private Integer closedTurnstiles;

    public Garden(Integer westTurnstileLimit, Integer eastTurnstileLimit) {
        this.counter = new Counter(0);
        var list = new ArrayList<Turnstile>();
        list.add(new Turnstile(this.counter, westTurnstileLimit, "西門", this::closeTurnstile));
        list.add(new Turnstile(this.counter, eastTurnstileLimit, "東門", this::closeTurnstile));
        this.turnstiles = list;
        this.closedTurnstiles = 0;
    }

    public static void main(String[] args) {
        var westTurnstileLimit = 1000;
        var eastTurnstileLimit = 2000;
        if (args.length > 0) {
            westTurnstileLimit = Integer.valueOf(args[0], 10);
        }
        if (args.length > 1) {
            eastTurnstileLimit = Integer.valueOf(args[1], 10);
        }
        var self = new Garden(westTurnstileLimit, eastTurnstileLimit);
        self.run();
    }

    void closeTurnstile(Turnstile closed) {
        closedTurnstiles ++;
        if (closedTurnstiles.equals(turnstiles.size())) {
            this.reportCounter();
        }
    }

    void reportCounter () {
        System.out.println("Closed all turnstiles");
        System.out.printf("Counter is %d\n", this.counter.readValue());

        System.out.println("Turnstiles:");
        this.turnstiles
                .stream()
                .map(turnstile -> {
                    var builder  = new StringBuilder();
                    builder
                        .append('\t')
                        .append(turnstile.getName())
                        .append(":")
                        .append(turnstile.getLimit());
                    return builder.toString();
                }).forEach(System.out::println);
        var sum = this.turnstiles.stream().mapToInt(Turnstile::getLimit).sum();
        System.out.printf("sum: %d\n", sum);
    }

    void run () {
        this.turnstiles.stream().map(Thread::new).forEach(Thread::start);
    }
}
