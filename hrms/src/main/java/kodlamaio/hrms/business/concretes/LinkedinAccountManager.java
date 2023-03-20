package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LinkedinAccountService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.LinkedinAccountRequests.CreateLinkedinAccountRequest;
import kodlamaio.hrms.business.requests.LinkedinAccountRequests.UpdateLinkedinAccountRequest;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetAllLinkedinAccountResponse;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetLinkedinAccountResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LinkedinAccountRepository;
import kodlamaio.hrms.entities.concretes.LinkedinAccount;

@Service
public class LinkedinAccountManager implements LinkedinAccountService {
	private LinkedinAccountRepository linkedinAccountRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public LinkedinAccountManager(LinkedinAccountRepository linkedinAccountRepository, ModelMapperService mapperService) {
		this.linkedinAccountRepository = linkedinAccountRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateLinkedinAccountRequest linkedinAccountRequest) {
		var result = BusinessRules.run(checkIfLinkContainsDomain(linkedinAccountRequest.getAccountAddress()));
		if (result != null) {
			return new ErrorResult();
		}
		LinkedinAccount linkedinAccount = this.mapperService.forRequest().map(linkedinAccountRequest, LinkedinAccount.class);
		linkedinAccount.setId(0);
		this.linkedinAccountRepository.save(linkedinAccount);
		return new SuccessResult();
	}

	@Override
	public Result update(int id, UpdateLinkedinAccountRequest linkedinRequest) {
		var result  = BusinessRules.run(checkAccountExist(id));
		if (result != null) {
			return new ErrorResult();
		}
		LinkedinAccount linkedinAccount = this.linkedinAccountRepository.getReferenceById(id);
		updateAccount(linkedinAccount, linkedinRequest);
		this.linkedinAccountRepository.save(linkedinAccount);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllLinkedinAccountResponse>> getAll() {
		List<GetAllLinkedinAccountResponse> accountResponses = this.linkedinAccountRepository.findAll().stream().map(account->this.mapperService.forResponse().map(account, GetAllLinkedinAccountResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllLinkedinAccountResponse>>(accountResponses);
	}

	@Override
	public DataResult<GetLinkedinAccountResponse> getById(int id) {
		GetLinkedinAccountResponse accountResponse = this.mapperService.forResponse().map(linkedinAccountRepository.findById(id), GetLinkedinAccountResponse.class);
		return new SuccessDataResult<GetLinkedinAccountResponse>(accountResponse);
	}
	
	// private codes -- business rules
	
	private Result checkIfLinkContainsDomain(String accountAddress) {
		if (accountAddress.contains("https://linkedin.com/") || accountAddress.contains("linkedin.com/")) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.CHECK_ACCOUNT_ADDRESS);
	}
	
	private Result checkAccountExist(int id) {
		if (this.linkedinAccountRepository.existsLinkedinAccountByIdEquals(id)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.ACCOUNT_NOT_FOUND);
	}
	
	private void updateAccount(LinkedinAccount linkedinAccount, UpdateLinkedinAccountRequest updatelinkedinRequest) {
		linkedinAccount.setAccountAddress(updatelinkedinRequest.getAccountAddress());
	}
}
