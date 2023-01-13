package kodlamaio.hrms.core.utilities.business;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;

public class BusinessRules {
	
	public static Result run(Result... operations ) {
		for(Result operation : operations) {
			if (!operation.isSuccess()) {
				return new ErrorResult(operation.getMessage());
			}
		}
		return null;
//		return new SuccessResult(operation.getMessage());
	}
}
