package environment;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import enterprise.service.Authentication;
import enterprise.service.Register;

public class ContextCreator {
	private InitialContext ctx;
	public ContextCreator() {
		try {
			System.setProperty("java.security.auth.login.config", "C:\\appclientlogin.conf");
			/*
				Properties props = new Properties();
				props.load(new FileInputStream("jndi.properties"));
			 */
			Properties props = new Properties(); 
			props.setProperty("java.naming.factory.initial",
					"com.sun.enterprise.naming.SerialInitContextFactory");
			props.setProperty("java.naming.factory.url.pkgs",
					"com.sun.enterprise.naming");
			props.setProperty("java.naming.factory.state",
					"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
			props.setProperty("org.omg.CORBA.ORBInitialHost", "ec2-35-157-0-118.eu-central-1.compute.amazonaws.com");
			props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
			this.ctx = new InitialContext(props);
		} catch (NamingException nex) {
			nex.printStackTrace();
		}
	}
	public InitialContext getCtx() {
		return ctx;
	}
//	public static void main(String[] args) throws NamingException {
//		ContextCreator contextcreator = new ContextCreator();
//		InitialContext ctx = contextcreator.getCtx();
//		Authentication testEJB = (Authentication) ctx.lookup("enterprise.service.Authentication");
//	
//	}
}
