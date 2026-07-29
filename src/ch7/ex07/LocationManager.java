package src.ch7.ex07;

import java.util.*;

class Location {
    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

public class LocationManager {
    private final Scanner scanner;
    private final HashMap<String, Location> locationInfos;

    public LocationManager(Scanner scanner) {
        this.scanner = scanner;
        this.locationInfos = new HashMap<String, Location>();
    }

    private void readLocation() {
        System.out.println("도시, 경도, 위도를 입력하세요.");
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("도시, 경도, 위도를 ',' 기준으로 입력해야 합니다.");
                continue;
            }

            String[] inputs = line.split(",");
            if (inputs.length != 3) {
                System.out.println("도시, 경도, 위도를 ',' 기준으로 입력해야 합니다.");
                continue;
            }
            String name = inputs[0];
            try {
                double latitude = Double.parseDouble(inputs[1].trim());
                double longitude = Double.parseDouble(inputs[2].trim());
                locationInfos.put(name, new Location(name, latitude, longitude));
            } catch (NumberFormatException e) {
                System.out.println("경도와 위도는 숫자로 입력해야 합니다.");
                continue;
            }

            if (locationInfos.size() == 4) {
                return;
            }
        }
    }

    private void printAllLocations() {
        for (Map.Entry<String, Location> entry : locationInfos.entrySet()) {
            String name = entry.getKey();
            Location location = entry.getValue();
            System.out.println(name + "   " + location.getLatitude() + "   " + location.getLongitude());
        }
    }

    private void searchLocatioins() {
        while (true) {
            System.out.print("도시 이름 >> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("검색을 원하는 도시 이름을 입력해주세요.");
                continue;
            }

            if (input.equals("그만")) {
                return;
            }

            Location location = locationInfos.get(input);
            if (location == null) {
                System.out.println(input + "는(은) 없습니다.");
                continue;
            }
            System.out.println(location.getName() + "   " + location.getLatitude() + "   " + location.getLongitude());
        }
    }

    public void run() {
        readLocation();
        System.out.println("-------------------");
        printAllLocations();
        System.out.println("-------------------");
        searchLocatioins();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            LocationManager manager = new LocationManager(scanner);
            manager.run();
        } finally {
            scanner.close();
        }
    }
}
