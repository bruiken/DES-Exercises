/*
 * generated by Xtext 2.23.0
 */
package lego.rover;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class MissionStandaloneSetup extends MissionStandaloneSetupGenerated {

	public static void doSetup() {
		new MissionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
