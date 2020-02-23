package numberPlay.validator;
import java.util.Arrays;

import numberPlay.util.Constants;

/**
 * @author Milind
 *        below class is to validate arguments provided to driver class
 */
public class DriverValidator {
	private int argsLength;
	private String args[];

	private static class ValidatorFetcher {
		
		/**
		 * @param d it is the object of Driver class
		 * @return run method will check whether there are 6 
		 * arguments present or not. if not it will throw error else
		 * will return to calling method 
		 */
		public static Validator argsLengthValidator(DriverValidator d) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (d.getArgsLength() != 6) {
						throw new Exception(Constants.ERROR_INVALID_ARGUMENTS);
					}
				}
			};
		}

		/**
		 * @param d it is the object of Driver class
		 * @return run method will check whether all inputs file provided or not
		 * and it will also check that 4 files path and names are not same
		 */
		public static Validator argsValidator(DriverValidator d) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if ((d.getArgs()[0].equals(Constants.ARG_0)) || (d.getArgs()[1].equals(Constants.ARG_1))
							|| (d.getArgs()[2].equals(Constants.ARG_2) ) || (d.getArgs()[3].equals(Constants.ARG_3) ) ||(d.getArgs()[4].equals(Constants.ARG_4) ) 
							|| (d.getArgs()[5].equals(Constants.ARG_5) )) {
						throw new Exception(Constants.ERROR_ALL_ARG_FILES_REQUIRED);
					}else if((d.getArgs()[0].equals(d.getArgs()[2]) || d.getArgs()[0].equals(d.getArgs()[4]) || d.getArgs()[0].equals(d.getArgs()[5])) 
							|| (d.getArgs()[2].equals(d.getArgs()[4]) || d.getArgs()[2].equals(d.getArgs()[5])) || 
							(d.getArgs()[4].equals(d.getArgs()[5]))
							) {
						throw new Exception(Constants.ERROR_DUPLICATE_FILES);						
					}
					else {
						Integer args1;
						Integer args3;
						try {
							args1=Integer.parseInt(d.getArgs()[1]);
						} catch (NumberFormatException e) {
							throw new Exception(Constants.ERROR_RUN_AVG_WINDOW_SIZE);
						}
						try {
							args3=Integer.parseInt(d.getArgs()[3]);
						} catch (NumberFormatException e) {
							throw new Exception(Constants.ERROR_TOP_K_NUM);
						}
						if(args1<1) {
							throw new Exception(Constants.ERROR_RUN_AVG_WINDOW_SIZE_GREATER_THAN_ONE);
						}
						if(args3<1) {
							throw new Exception(Constants.ERROR_TOP_K_NUM_GREATER_THAN_ONE);
						}
						
						
					}
				}
			};
		}
	}

	public DriverValidator(int argsLength, String[] args) throws Exception {
		this.argsLength = argsLength;
		this.args = args;

		ValidatorUtil.validate("failed", ValidatorFetcher.argsLengthValidator(this),
				ValidatorFetcher.argsValidator(this));
	}

	public int getArgsLength() {
		return argsLength;
	}

	public void setArgsLength(int argsLength) {
		this.argsLength = argsLength;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "Driver [argsLength=" + argsLength + ", args=" + Arrays.toString(args) + "]";
	}

}
