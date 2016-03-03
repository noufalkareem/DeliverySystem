package com.mikkysoft.auth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mikkysoft.controller.UserController;
import com.mikkysoft.model.User;


public class DBLoginModule implements LoginModule {
	 
	private static Log log = LogFactory.getLog(DBLoginModule.class);	 
	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroups;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		// TODO Auto-generated method stub
		handler = callbackHandler;
		this.subject = subject;

	}

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {
			handler.handle(callbacks);
			String name = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1])
					.getPassword());
			User user = null;
			if(name!=null){
				UserController controller = new UserController();
				user = controller.read(name);				
			}
			
			if (user!=null && user.getPassword().equals(password)) {

				login = name;
				userGroups = new ArrayList<String>();
				userGroups.add(user.getType().toString());
				UserController.setLoggedUser(user);
				return true;
			}

			// If credentials are NOT OK we throw a LoginException
			throw new LoginException("Authentication failed");

		} catch (IOException e) {
//			throw new LoginException(e.getMessage());
			log.error(e.fillInStackTrace());
//			e.printStackTrace(System.err);
		} catch (UnsupportedCallbackException e) {
//			throw new LoginException(e.getMessage());
			log.error(e.fillInStackTrace());
//			e.printStackTrace(System.err);
		} catch(SQLException e){
//			throw new LoginException(e.getMessage());
			log.error(e.fillInStackTrace());
//			e.printStackTrace(System.err);			
		}catch(LoginException e){
			log.error("Login Failed..."+e.fillInStackTrace());
//			e.printStackTrace(System.err);
		}
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		userPrincipal = new UserPrincipal(login);
		subject.getPrincipals().add(userPrincipal);

		if (userGroups != null && userGroups.size() > 0) {
			for (String groupName : userGroups) {
				rolePrincipal = new RolePrincipal(groupName);
				subject.getPrincipals().add(rolePrincipal);
			}
		}

		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(rolePrincipal);
		return true;
	}

}
