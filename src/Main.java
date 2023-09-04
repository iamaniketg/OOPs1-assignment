import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarServiceStation station = new CarServiceStation();

        // Add car services
        station.addCarService("Hatchback", List.of("BS01", "EF01"));
        station.addCarService("Sedan", List.of("BS01", "EF01"));
        station.addCarService("SUV", List.of("BS01", "EF01"));

        // Add service prices
        station.addServicePrice("BS01");
        station.addServicePrice("EF01");

        // Set prices for services and car types
        station.setPriceForService("BS01", "Hatchback", 2000);
        station.setPriceForService("BS01", "Sedan", 2500);
        station.setPriceForService("BS01", "SUV", 3000);
        station.setPriceForService("EF01", "Hatchback", 5000);
        station.setPriceForService("EF01", "Sedan", 6000);
        station.setPriceForService("EF01", "SUV", 7000);

        // Generate and print bill
        String carType = "Hatchback";
        List<String> serviceCodes = List.of("BS01", "EF01");
        String bill = station.generateBill(carType, serviceCodes);
        System.out.println(bill);
    }
}