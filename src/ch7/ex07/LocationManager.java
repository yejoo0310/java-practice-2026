package src.ch7.ex07;

import java.util.*;

class Location {
    private final String name;
    private final double longitude;
    private final double latitude;

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

    public String toString() {
        return name + "   " + latitude + "   " + longitude;
    }
}

public class LocationManager {
    private final Scanner scanner;
    private final Map<String, Location> locations;

    public LocationManager(Scanner scanner) {
        this.scanner = scanner;
        this.locations = new HashMap<String, Location>();
    }

    private void readLocations() {
        System.out.println("도시, 위도, 경도를 입력하세요.");
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("도시, 위도, 경도를 ',' 기준으로 입력해야 합니다.");
                continue;
            }

            String[] inputs = line.split(",");
            if (inputs.length != 3) {
                System.out.println("도시, 위도, 경도를 ',' 기준으로 입력해야 합니다.");
                continue;
            }

            String name = inputs[0].trim();

            if (name.isEmpty()) {
                System.out.println("도시 이름을 입력해야 합니다.");
                continue;
            }

            if (locations.containsKey(name)) {
                System.out.println("이미 등록된 도시입니다.");
                continue;
            }

            try {
                double latitude = Double.parseDouble(inputs[1].trim());
                if (latitude < -90 || latitude > 90) {
                    System.out.println("위도는 -90에서 90 사이여야 합니다.");
                    continue;
                }
                double longitude = Double.parseDouble(inputs[2].trim());
                if (longitude < -180 || longitude > 180) {
                    System.out.println("경도는 -180에서 180 사이여야 합니다.");
                    continue;
                }
                locations.put(name, new Location(name, latitude, longitude));
            } catch (NumberFormatException e) {
                System.out.println("경도와 위도는 숫자로 입력해야 합니다.");
                continue;
            }

            if (locations.size() == 4) {
                return;
            }
        }
    }

    private void printAllLocations() {
        for (Location location : locations.values()) {
            System.out.println(location);
        }
    }

    private void searchLocations() {
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

            Location location = locations.get(input);
            if (location == null) {
                System.out.println(input + "는(은) 없습니다.");
                continue;
            }
            System.out.println(location.toString());
        }
    }

    public void run() {
        readLocations();
        System.out.println("-------------------");
        printAllLocations();
        System.out.println("-------------------");
        searchLocations();
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
