/*
 * generated by Xtext 2.23.0
 */
package gameoflife.validation;

import java.util.List;

import org.eclipse.xtext.validation.Check;

import gameoflife.gol.GolPackage;
import gameoflife.gol.Rule;
import gameoflife.gol.TotalRules;
import gameoflife.gol.Operator;


/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class GolValidator extends AbstractGolValidator {
	
  	public static final String INVALID_NAME = "invalidName";

  	@Check
  	public void checkEmptyRules(TotalRules rules) {
  		if (rules.getOnRules().size() == 0 && rules.getOffRules().size() == 0) {
  			info("No rules found, always goes into 'else' case", null);
  		}
  	}
  	
  	@Check
  	public void checkDuplicateRules(TotalRules rules) {
  		if (checkDuplicates(rules.getOffRules())) {
  			warning("There are duplicate rules in the 'Off' section!", null);
  		}
  		if (checkDuplicates(rules.getOnRules())) {
  			warning("There are duplicate rules in the 'On' section!", null);
  		}
  	}
  	
  	private boolean checkDuplicates(List<Rule> rules) {
  		for (Rule r1 : rules) {
  			for (Rule r2 : rules) {
  				if (r1.getOnOff() == r2.getOnOff() && 
  					r1.getOp() == r2.getOp() && 
  					r1.getCompareValue() == r2.getCompareValue() &&
  					r1 != r2) {
  					return true;
  				}
  			}
  		}
  		return false;
  	}
  	
 	@Check
	public void checkNonsensicalNumbers(Rule rule) {
		if (rule.getCompareValue() < 0) {
			switch (rule.getOp()) {
				case EQ:
				case LT:
				case LEQ:
					warning("Comparison with negative number of neighbors will never succeed", null);
					break;
				case GEQ:
				case GT:
					warning("Comparison will always be true", null);
			}
		}
		if (rule.getCompareValue() == 0) {
			switch (rule.getOp()) {
				case LT:
					warning("Comparison with negative number of neighbors will never succeed", null);
					break;
				case LEQ:
					info("Consider rewriting to = 0", null);
					break;
				default:
					break;
			}
		}
		if (rule.getCompareValue() == 4) {
			switch (rule.getOp()) {
				case GT:
					warning("Comparison with > 4 neighbors will never succeed", null);
					break;
				case GEQ:
					info("Consider rewriting to = 4", null);
					break;
				default:
					break;
			}
		}
		if (rule.getCompareValue() > 4) {
			switch (rule.getOp()) {
				case EQ:
				case GT:
				case GEQ:
					warning("Comparison will never succeed", null);
					break;
				case LEQ:
				case LT:
					warning("Comparison will always be true", null);
			}
		}
	}
	
}