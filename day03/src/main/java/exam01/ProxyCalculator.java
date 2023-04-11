package exam01;

public class ProxyCalculator implements Calculator{

    private Calculator calculator;

    public ProxyCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    @Override
    public long factorial(long num) {
        long startTime = System.nanoTime(); // 시간 시간
        long result = calculator.factorial(num);
        long endTime = System.nanoTime(); // 종료 시간 - 공통기능
        System.out.printf("걸린 시간: %d%n", endTime - startTime);

        return result;
    }
}
