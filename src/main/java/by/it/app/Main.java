package by.it.app;

import by.it.app.model.*;
import by.it.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Main {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PosRepository posRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

    public InsuranceRepository getInsuranceRepository() {
        return insuranceRepository;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public ShiftRepository getShiftRepository() {
        return shiftRepository;
    }

    public PosRepository getPosRepository() {
        return posRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public PointRepository getPointRepository() {
        return pointRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(by.it.app.config.AppConfiguration.class);
        Main main = annotatedClassApplicationContext.getBean("main", Main.class);

        /*System.out.println("=== === CRUD EXAMPLE === ===");
        System.out.println("=== ROLE ===");
        Role roleCustomer = new Role();
        roleCustomer.setRole(RoleEnum.CUSTOMER);
        main.getRoleRepository().save(roleCustomer);

        Role roleEmployee = new Role();
        roleEmployee.setRole(RoleEnum.EMPLOYEE);
        main.getRoleRepository().save(roleEmployee);

        Role roleManager = new Role();
        roleManager.setRole(RoleEnum.MANAGER);
        main.getRoleRepository().save(roleManager);

        main.getRoleRepository().findById(1L).ifPresent(System.out::println);
        main.getRoleRepository().findById(2L).ifPresent(System.out::println);
        main.getRoleRepository().findById(3L).ifPresent(System.out::println);

        System.out.println("=== USER ===");
        User user1 = new User();
        user1.setName("John");
        user1.setRole(roleEmployee);
        main.getUserRepository().save(user1);

        User user2 = new User();
        user2.setName("Jane");
        user2.setRole(roleManager);
        main.getUserRepository().save(user2);

        User user3 = new User();
        user3.setName("Jack Customer");
        user3.setRole(roleCustomer);
        main.getUserRepository().save(user3);

        main.getUserRepository().findById(1L).ifPresent(System.out::println);
        main.getUserRepository().findById(2L).ifPresent(System.out::println);
        main.getUserRepository().findById(3L).ifPresent(System.out::println);

        user1.setName("new John");
        main.getUserRepository().save(user1);
        main.getUserRepository().findById(1L).ifPresent(System.out::println);

        System.out.println("=== POINT ===");
        Point point1 = new Point();
        point1.setName("NY office");
        main.getPointRepository().save(point1);

        Point point2 = new Point();
        point2.setName("LA office");
        main.getPointRepository().save(point2);

        main.getPointRepository().findById(1L).ifPresent(System.out::println);
        main.getPointRepository().findById(2L).ifPresent(System.out::println);

        Address address1 = new Address();
        address1.setCity("NY");
        address1.setStreet("Central");
        address1.setHouseNumber("1");
        address1.setPostcode("11122");
        main.getAddressRepository().save(address1);

        main.getAddressRepository().findById(1L).ifPresent(System.out::println);

        Address address2 = new Address();
        address2.setCity("LA");
        address2.setStreet("West");
        address2.setHouseNumber("2");
        address2.setPostcode("55566");
        main.getAddressRepository().save(address2);
        main.getAddressRepository().findById(2L).ifPresent(System.out::println);

        point1.setAddress(address1);
        point2.setAddress(address2);
        main.getPointRepository().save(point1);
        main.getPointRepository().save(point2);
        main.getPointRepository().findById(1L).ifPresent(System.out::println);
        main.getPointRepository().findById(2L).ifPresent(System.out::println);

        Set<Point> pointsUser1 = new HashSet<Point>();
        Set<Point> pointsUser2 = new HashSet<Point>();

        Point point3 = new Point();
        point3.setName("New Point");
        main.getPointRepository().save(point3);

        pointsUser1.add(point1);
        pointsUser1.add(point2);
        pointsUser1.add(point3);
        user1.setPoints(pointsUser1);
        main.getUserRepository().save(user1);

        pointsUser2.add(point3);
        user2.setPoints(pointsUser2);
        main.getUserRepository().save(user2);

        printSetOfPoints(user1);
        printSetOfPoints(user2);

        System.out.println("=== POS ===");
        Pos pos1 = new Pos();
        pos1.setNumber("POS_1111");
        pos1.setPoint(point1);
        main.getPosRepository().save(pos1);

        Pos pos2 = new Pos();
        pos2.setNumber("POS_2222");
        pos2.setPoint(point2);
        main.getPosRepository().save(pos2);

        main.getPosRepository().findById(1L).ifPresent(System.out::println);
        main.getPosRepository().findById(2L).ifPresent(System.out::println);

        System.out.println("=== Shift ===");
        Shift shift1 = new Shift();
        shift1.setPos(pos1);
        shift1.setUser(user1);
        main.getShiftRepository().save(shift1);

        main.getShiftRepository().findById(1L).ifPresent(System.out::println);

        System.out.println("=== Insurance ===");
        Car car = new Car();
        car.setCountry("GB");
        car.setCarNumber("QWE123");

        Insurance insurance = new Insurance();
        insurance.setCar(car);
        insurance.setPayment(500F);
        insurance.setUser(user3);
        insurance.setShift(shift1);
        main.getInsuranceRepository().save(insurance);
        main.getInsuranceRepository().findById(1L).ifPresent(System.out::println);

        System.out.println("Set payment 300:");
        Insurance insurance2 = main.getInsuranceRepository().findById(1L).get();
        insurance2.setPayment(300F);
        main.getInsuranceRepository().save(insurance2);
        main.getInsuranceRepository().findById(1L).ifPresent(System.out::println);*/

        System.out.println("=== === QUERY & NAMED QUERY === ===");

        List<User> users = main.getUserRepository().findByNameContains("Ja");
        if (users.isEmpty()) {
            System.out.println("User(s) with name like 'Ja' not found");
        } else {
            System.out.println("User(s) with name like'Ja':");
            for (User user : users) {
                System.out.println(user);
            }
        }

        List<Point> points = main.getPointRepository().findByAddressCityContains("NY");
        if (points.isEmpty()) {
            System.out.println("Point(s) in city 'NY' not found");
        } else {
            System.out.println("Point(s) in city 'NY':");
            for (Point point : points) {
                System.out.println(point);
            }
        }

        List<Address> addresses = main.getAddressRepository().findAllByHouseNumberAfter("1");
        if (addresses.isEmpty()) {
            System.out.println("Address(es) with house number after '1' not found");
        } else {
            System.out.println("Address(es) with house number after '1':");
            for (Address address : addresses) {
                System.out.println(address);
            }
        }

        List<Car> cars = main.getCarRepository().findByCarNumberIsEndingWith("123");
        if (cars.isEmpty()) {
            System.out.println("Car(s) with number is ending with '123' not found");
        } else {
            System.out.println("Car(s) with number is ending with '123':");
            for (Car car : cars) {
                System.out.println(car);
            }
        }

        List<Insurance> insurances = main.getInsuranceRepository().findInsuranceByPaymentBetween1(100F, 200F);
        if (insurances.isEmpty()) {
            System.out.println("Insurance(s) with payment between 100 and 200 not found");
        } else {
            System.out.println("Insurance(s) with payment between 100 and 200:");
            for (Insurance insurance : insurances) {
                System.out.println(insurance);
            }
        }

        insurances = main.getInsuranceRepository().findInsuranceByPaymentBetween1(250F, 500F);
        if (insurances.isEmpty()) {
            System.out.println("Insurance(s) with payment between 250 and 500 not found");
        } else {
            System.out.println("Insurance(s) with payment between 250 and 500:");
            for (Insurance insurance : insurances) {
                System.out.println(insurance);
            }
        }




     /*   one.ifPresent(main.getUserRepository()::delete);
        one = main.getUserRepository().findById(1L);
        System.out.println(one.isPresent());

        User user = new User();
        user.setName("TESTA");
        main.getUserRepository().save(user);
        Optional<User> three = main.getUserRepository().findById(3L);
        three.ifPresent(System.out::println);

        user.setName("TESTB");
        main.getUserRepository().saveAndFlush(user);
        three = main.getUserRepository().findById(3L);
        three.ifPresent(System.out::println);*/


  /*  private static void exampleCRUD() {
        updateInsuranceAmount(insuranceDAO.getOne(1L), 1000F);
    }

    private static void hqlExample() {
        System.out.println("=== HQL section ===");

        List<Point> points = pointDAO.findAll(0, 5);
        System.out.println("### Point: " + points.get(0).getName());
        System.out.println("### Point: " + points.get(1).getName());

        Car car = carDAO.findByCountryAndNumber("BY", "AA1234");
        System.out.println("### Car [BY AA1234] has ID = " + car.getId());

        List<Shift> shiftsListWithInsurances = shiftDAO.findAllWithInsurances(0, 5);
        System.out.println("### Shift with ID = " + shiftsListWithInsurances.get(0).getId() +
                " has " + shiftsListWithInsurances.get(0).getInsurances().size() + " insurances");

        posDAO.updateNumber(1L, "POS456");

        List<Address> addresses = addressDAO.findByCity(0, 5, "Grodno");
        System.out.println("### Find " + addresses.size() + " address with city \"Grodno\"");

        insuranceDAO.deleteByNumber(101L);
    }

    private static void nsqlExample() {
        System.out.println("=== NativeSQL section ===");
        List<User> users = userDAO.findByName(0, 5, "Jane");
        System.out.println("### Find " + users.size() + " user(s) with name \"Jane\"");
    }

    private static void criteriaExample() {
        System.out.println("=== Criteria section ===");
        System.out.println("### Quantity of users with role = EMPLOYEE: "
                + roleDAO.getQuantityOfUsersByRole(RoleEnum.EMPLOYEE));
    }
*/


    }

    private static void printSetOfPoints(User user) {
        Set<Point> userPoints = user.getPoints();
        System.out.println(user + " has " + userPoints.size() + " point(s):");
        for (Point point : userPoints)
            System.out.println("    " + point);
    }

}

