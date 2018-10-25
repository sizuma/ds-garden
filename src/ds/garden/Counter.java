package ds.garden;

/**
 * Created by Teppei Shiroyama under MIT License.
 */
public class Counter extends Object {

    private Integer count = 0;

    public Counter(Integer count) {
        this.count = count;
    }

    Integer readValue() {
        return this.count;
    }

    void writeValue () {
        this.count ++;
    }
}
