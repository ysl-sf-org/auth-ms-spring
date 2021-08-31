package application.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import application.customer.Cust;
import application.customer.CustomerServiceClient;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger = LoggerFactory.getLogger(CustomerAuthenticationProvider.class);

    @Autowired
    private CustomerServiceClient custSvc;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        logger.debug("authenticating: " + name );
        
        if (name.equals("user") && password.equals("password")) {
            // TEST
            return new UsernamePasswordAuthenticationToken(name, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        }

        // call customer service
        
    	final List<Cust> custList = custSvc.getCustomerByUsername(name);
    	
    	logger.debug("customer service returned:" + custList);
    	
    	if (custList == null || custList.isEmpty()) {
    		throw new AuthenticationException("Invalid username or password") {
				private static final long serialVersionUID = 1L;
			};
    	}
    	
    	final Cust cust = custList.get(0);
    	
    	// TODO: hash password -- in the customer service
    	if (!cust.getPassword().equals(password)) {
    		throw new AuthenticationException("Invalid password") {
				private static final long serialVersionUID = 1L;
			};
    	}
    	
    	// authentication was valid
		return new UsernamePasswordAuthenticationToken(cust.getUsername(), password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
