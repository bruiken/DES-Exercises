/*
 * generated by Xtext 2.23.0
 */
package gameoflife;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class GolStandaloneSetup extends GolStandaloneSetupGenerated {

	public static void doSetup() {
		new GolStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
