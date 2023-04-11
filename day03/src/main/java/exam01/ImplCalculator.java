package exam01;

public class ImplCalculator implements Calculator{
    public long factorial(long num) {
        long startTime = System.nanoTime();

        int total = 1;
        for(int i = 1; i <= num; i++){
            total *= i;
        }
        long endTime = System.nanoTime();
        System.out.printf("걸린시간 : %d%n", endTime - startTime);
        return total;
    }
}
