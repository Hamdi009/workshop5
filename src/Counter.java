public class Counter {
    private int count;

    public synchronized void increment (int count){
        this.count +=count;
    }

    public int getCount() {
        return count;
    }

    
}