import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarService {
    private String carType;
    private List<String> serviceCodes;

    public CarService(String carType, List<String> serviceCodes) {
        this.carType = carType;
        this.serviceCodes = serviceCodes;
    }

    public String getCarType() {
        return carType;
    }

    public List<String> getServiceCodes() {
        return serviceCodes;
    }
}

class ServicePrice {
    private String serviceCode;
    private Map<String, Integer> prices;

    public ServicePrice(String serviceCode) {
        this.serviceCode = serviceCode;
        this.prices = new HashMap<>();
    }

    public void setPriceForCarType(String carType, int price) {
        prices.put(carType, price);
    }

    public int getPriceForCarType(String carType) {
        return prices.getOrDefault(carType, 0);
    }

    public String getServiceCode() {
        return serviceCode;
    }
}

class CarServiceStation {
    private List<CarService> carServices;
    private List<ServicePrice> servicePrices;

    public CarServiceStation() {
        this.carServices = new ArrayList<>();
        this.servicePrices = new ArrayList<>();
    }

    public void addCarService(String carType, List<String> serviceCodes) {
        carServices.add(new CarService(carType, serviceCodes));
    }

    public void addServicePrice(String serviceCode) {
        servicePrices.add(new ServicePrice(serviceCode));
    }

    public void setPriceForService(String serviceCode, String carType, int price) {
        for (ServicePrice servicePrice : servicePrices) {
            if (servicePrice.getServiceCode().equals(serviceCode)) {
                servicePrice.setPriceForCarType(carType, price);
                break;
            }
        }
    }

    public int calculateTotalBill(String carType, List<String> serviceCodes) {
        int totalBill = 0;
        for (String serviceCode : serviceCodes) {
            for (ServicePrice servicePrice : servicePrices) {
                if (servicePrice.getServiceCode().equals(serviceCode)) {
                    int servicePriceForCar = servicePrice.getPriceForCarType(carType);
                    totalBill += servicePriceForCar;
                }
            }
        }
        return totalBill;
    }

    public String generateBill(String carType, List<String> serviceCodes) {
        int totalBill = calculateTotalBill(carType, serviceCodes);
        StringBuilder bill = new StringBuilder("Type of Car - " + carType + "\nService Codes - " + String.join(", ", serviceCodes) + "\n");

        for (String serviceCode : serviceCodes) {
            for (ServicePrice servicePrice : servicePrices) {
                if (servicePrice.getServiceCode().equals(serviceCode)) {
                    int servicePriceForCar = servicePrice.getPriceForCarType(carType);
                    bill.append("Charges for ").append(serviceCode).append(" - ₹ ").append(servicePriceForCar).append("\n");
                }
            }
        }

        if (totalBill > 10000) {
            bill.append("Complimentary Cleaning - Included\n");
        }

        bill.append("Total Bill - ₹ ").append(totalBill);

        return bill.toString();
    }
}
