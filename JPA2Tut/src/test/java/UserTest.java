import net.domain.User;
import net.service.UserDaoImpl;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by greenlucky on 1/3/17.
 */
public class UserTest{

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

    private static UserDaoImpl userDaoImpl;

    private static EntityTransaction tx;
    private static EntityManager em;

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void initEntityManager() throws Exception{
        //userDaoImpl = new UserDaoImpl();
        //em = userDaoImpl.createEntityManager();*/
    }

    @Before
    public void initTransaction() throws Exception{
        //tx = em.getTransaction();
        //seedData();
    }


    protected void seedData() throws Exception {
        tx.begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        try {
            IDatabaseConnection dbUnitCon = new DatabaseConnection(connection);
            dbUnitCon.getConfig()
                    .setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
            IDataSet dataset;
            FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
            flatXmlDataSetBuilder.setColumnSensing(true);
            InputStream in = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("data/dataset.xml");
            if (in != null) {
                LOGGER.warn("DataSet␣found");
                dataset = flatXmlDataSetBuilder.build(in);
            } else {
                LOGGER.warn("DataSet not found");
                dataset = new DefaultDataSet();
            }
            DatabaseOperation.REFRESH.execute(dbUnitCon, dataset);
        } finally {
            tx.commit();
        }
    }

    @Test
    public void testCreateUser() throws Exception{
        userDaoImpl = new UserDaoImpl();
        Date now = new Date();
        User user1 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);
        User user2 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);
        User user3 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);
        User user4 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);
        User user5 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);
        User user6 = userDaoImpl.createUser(testName.getMethodName(), testName.getMethodName(), now);


        userDaoImpl.delete(1);

        //find user's id 1
        User user = userDaoImpl.findById(1);

        Assert.assertNull(user);


        List<User> users = userDaoImpl.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void testCreateUsers() throws Exception{
        userDaoImpl = new UserDaoImpl();
        List<User> users = new ArrayList<User>();
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));
        users.add(new User(testName.getMethodName(),testName.getMethodName(), new Date()));

        userDaoImpl.createUser(users);

        List<User> expectUsers = userDaoImpl.findAll();

        System.out.println(expectUsers.toString());


    }


    @AfterClass
    public static void afterTest(){
        //userDaoImpl.closeEntityManager();
    }
}
