public class Main {
    public static void main(String[] args) {
        // 변환 상수 정의
        final double FEET_TO_CM = 30.48;
        final double MILES_TO_CM = 160934;

        // 변환할 단위
        double feet = 9.2;
        double miles = 1.3;

        // 단위 변환
        double feetToCm = feet * FEET_TO_CM;
        double milesToCm = miles * MILES_TO_CM;

        // 반올림 (소수 첫째자리까지)
        feetToCm = Math.round(feetToCm * 10) / 10.0;
        milesToCm = Math.round(milesToCm * 10) / 10.0;

        // 결과 출력
        System.out.println("9.2ft = " + feetToCm + "cm");
        System.out.println("1.3mi = " + milesToCm + "cm");
    }
}