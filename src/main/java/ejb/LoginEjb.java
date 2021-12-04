//package ejb;
//
//import dao.GenericDaoI;
//import model.Customer;
//import org.apache.commons.beanutils.BeanUtils;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.Map;
//import java.util.Random;
//
//@Stateless
//public class LoginEjb implements LoginEjbI {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    GenericDaoI basicDao;
//
//    @Override
//    public LoginResponse validate(Map<String, String[]> params) {
//        LoginResponse response = new LoginResponse();
//
//        if (params == null)
//            return response;
//
//        Login login = new Login();
//
//        try {
//            BeanUtils.populate(login, params);
//
//            if (login == null || login.getEmail() == null || login.getPassword() == null)
//                return response;
//
//            login.setEmail(em.find(Customer.class, login.getEmail()).getEmail());
//            login.setPassword(em.find(Customer.class, login.getPassword()).getPassword());
//
//            basicDao.setClazz(Customer.class);
//
//            response.setLoginError(!(login.getEmail().equalsIgnoreCase("")
//                    && login.getPassword().equals("")));
//
//            if (!response.isLoginError()) {
//                response.setSessionId(new Random(10000).nextInt() + "");
//                response.setEmail(login.getEmail());
//                response.setRedirectPage("./home.jsp");
//            }
//
//            return response;
//
//        } catch (Exception ex) {
//            response.setLoginError(true);
//            response.setLoginErrorMsg(ex.getMessage());
//        }
//
//        return response;
//    }
//}
