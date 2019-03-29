package by.it.app;

import by.it.app.model.Role;
import by.it.app.model.RoleEnum;
import by.it.app.model.User;
import by.it.app.repository.RoleRepository;
import by.it.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(by.it.app.config.AppConfiguration.class);
        Main main = annotatedClassApplicationContext.getBean("main", Main.class);

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

        User user1 = new User();
        user1.setName("John");
        user1.setRole(roleEmployee);
        main.getUserRepository().save(user1);

        User user2 = new User();
        user2.setName("Jane");
        user2.setRole(roleManager);
        main.getUserRepository().save(user2);

        User user3 = new User();
        user3.setName("Jack");
        user3.setRole(roleEmployee);
        main.getUserRepository().save(user3);

        main.getUserRepository().findById(1L).ifPresent(System.out::println);
        main.getUserRepository().findById(2L).ifPresent(System.out::println);
        main.getUserRepository().findById(3L).ifPresent(System.out::println);




        //one.ifPresent(System.out::println);

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

        /*       //CRUD
        exampleCRUD();

        //HQL
        hqlExample();

        //NativeSQL
        nsqlExample();

        //Criteria
        criteriaExample();

        //CRUD - delete
        deleteExample();*/
    }

  /*  private static void exampleCRUD() {
        System.out.println("=== CRUD section ===");

        createPoint("Grodno Office");
        createPoint("Minsk Office");
        createAddress();
        updatePointAddress(pointDAO.getOne(1L), addressDAO.getOne(1L));

        updateUserPoint(userDAO.getOne(1L), pointDAO.getOne(1L));
        updateUserPoint(userDAO.getOne(2L), pointDAO.getOne(1L));
        updateUserPoint(userDAO.getOne(2L), pointDAO.getOne(2L));

        createPos(pointDAO.getOne(1L));
        createShift(posDAO.getOne(1L), userDAO.getOne(1L));

        createCar("FR", "PRS123", "JKHFJ123");
        createCar("BY", "AA1234", "Q1W2E3R4");

        createInsurance(100L, 10F, 900F,
                now().plus(6, MONTHS), carDAO.getOne(1L), shiftDAO.getOne(1L), userDAO.getOne(3L));
        createInsurance(101L, 8F, 700F,
                now().plus(3, MONTHS), carDAO.getOne(2L), shiftDAO.getOne(1L), userDAO.getOne(3L));

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

    private static void deleteExample() {
        System.out.println("=== CRUD - Delete section ===");
        userDAO.delete(2L);
        pointDAO.delete(2L);
        carDAO.delete(2L);
    }*/
}

